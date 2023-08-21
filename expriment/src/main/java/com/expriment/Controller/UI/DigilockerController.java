package com.expriment.Controller.UI;

import com.expriment.entity.vo.CKYCRootPayload;
import com.expriment.entity.vo.CKYCStatusResponse;
import com.expriment.entity.vo.CheckDigilockerStatusRequest;
import com.expriment.entity.vo.CheckDigilockerStatusResponse;
import com.expriment.utils.AppProps;
import com.expriment.utils.audit.LoggerClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * @author Indradev.kuamr
 */
@CrossOrigin
@Controller
@RequestMapping("/digilocker/")
public class DigilockerController {

	Logger logger = LogManager.getLogger("DigilockerController");

//	@Autowired
	AppProps appProps;
	
	@RequestMapping(value = "v0.1/digiProcess", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView digilockerProcess(HttpServletRequest httpServletRequest) {
		try {
			LoggerClass.appLayerLogger.info("digilockerProcess UI Process");
			ObjectMapper mapper = new ObjectMapper();
			String code = httpServletRequest.getParameter("code");
			String state = httpServletRequest.getParameter("state");
			String hmac = httpServletRequest.getParameter("hmac");
			String error = httpServletRequest.getParameter("error");
			String error_description = httpServletRequest.getParameter("error_description");
			LoggerClass.appLayerLogger.info("Code : " + code + ", State : " + state + ", Hmac : " + hmac + ", Error : " + error + ", Error_Description : " + error_description);
		/*String ckycCallbackUrl = appProps.getCdPlcKycDetailsCallbackUrl()+"?leadId="+state+"&customerHash="
				+hmac;*/
			if (error != null && !error.isEmpty()) {
				LoggerClass.appLayerLogger.info("Error is not empty/null");
				CKYCRootPayload ckycRootPayload = new CKYCRootPayload();
				ckycRootPayload.setAppId(state);
				MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
				headers.add("Content-Type", "application/json");
				HttpEntity<CKYCRootPayload> httpEntity = new HttpEntity<>(ckycRootPayload, headers);
				String url = appProps.getTclPlDelegatorCkycDecisionUrl();
				LoggerClass.appLayerLogger.info("CKYC Decision URL : " + url);

				RestTemplate restTemplate = new RestTemplate();
				CKYCStatusResponse response = restTemplate.postForObject(url, httpEntity, CKYCStatusResponse.class);
				LoggerClass.appLayerLogger.info("CKYC Decision Response :" + mapper.writeValueAsString(response));
				if (response.getcKycDecision()) {
					return new ModelAndView("redirect:" + response.getUrl() + "?leadId=" + state + "&customerHash=" + response.getCustomerHash());
				} else {
					return new ModelAndView("redirect:" + response.getUrl());
				}
			} else {
				Thread thread = new Thread(() -> {
					String url = appProps.getTclPlDelegatorAuthTokenUrl() + "?code=" + code;
					LoggerClass.appLayerLogger.info("Authorization Token Generation Url : " + url);
					RestTemplate restTemplate = new RestTemplate();
					try {
						restTemplate.getForObject(url, Void.class);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				thread.start();

				String callbackUrl = appProps.getCdPlDocumentPickupCallbackUrl() + "?leadId=" + state;

				return new ModelAndView("redirect:" + callbackUrl);
			}
		} catch (Exception e) {
			logger.error("Error occurred in digilockerProcess api ", e);
		}
		return null;
	}

	@RequestMapping(value = "v0.1/checkDigilockerStatus", method = {RequestMethod.POST,RequestMethod.GET})
	public Object checkDigilockerStatus(HttpServletRequest httpServletRequest) {
		CheckDigilockerStatusRequest checkDigilockerStatusRequest = new CheckDigilockerStatusRequest();
		CheckDigilockerStatusResponse response = new CheckDigilockerStatusResponse();
		try {
			LoggerClass.appLayerLogger.info("checkStatus() UI Process started");
			ObjectMapper mapper = new ObjectMapper();
			String leadId = httpServletRequest.getParameter("leadId");
			LoggerClass.appLayerLogger.info("Lead Id : " + leadId);
			checkDigilockerStatusRequest.setLeadId(leadId);
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("Content-Type", "application/json");
			HttpEntity<CheckDigilockerStatusRequest> httpEntity = new HttpEntity(checkDigilockerStatusRequest, headers);
			String url = appProps.getTclPlDelegatorCheckStatusUrl();
			LoggerClass.appLayerLogger.info("Core DLP DigiLocker Check Status API details Url : " + url);

			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.postForObject(url, httpEntity, CheckDigilockerStatusResponse.class);
			if(response.getDigiLockerFlag()) {
				return new ModelAndView("redirect:" + response.getDigiLockerUrl());
			} else if(response.getCkycFlag()) {
				return new ModelAndView("redirect:" + response.getCkycUrl());
			}
		} catch (Exception e) {
			logger.error("Error occurred in DigiLocker Check Status api ", e);
		}
		return response;
	}

}
