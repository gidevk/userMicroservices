/*
package com.expriment.Controller;

import com.expriment.entity.DigilockerDetails;
import com.expriment.entity.vo.*;
import com.expriment.utils.AppProps;
import com.expriment.utils.ProjectConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Status;
import java.io.IOException;

*/
/*
 * @author Indradev.kuamr
 *//*

@CrossOrigin
@Controller
@RequestMapping("/digilocker/")
public class DigilockerController {

	Logger logger = LogManager.getLogger("DigilockerController");

	@Autowired
	AppProps appProps;

	@Autowired
	TCLServicesProps tclServicesProps;

	@Autowired
	DigiLockerAppService digiLockerAppService;

	@Autowired
	TCLServiceManager tclServiceManager;

	ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "v0.1/digiProcess", method = {RequestMethod.POST,RequestMethod.GET})
	public Object digilockerProcess(HttpServletRequest httpServletRequest) {
		try {
			LoggerClass.appLayerLogger.info("digilockerProcess UI Process");
			String code = httpServletRequest.getParameter("code");
			String state = httpServletRequest.getParameter("state");
			String hmac = httpServletRequest.getParameter("hmac");
			String error = httpServletRequest.getParameter("error");
			String error_description = httpServletRequest.getParameter("error_description");
			LoggerClass.appLayerLogger.info("Code : " + code + ", State : " + state + ", Hmac : " + hmac + ", Error : " + error + ", Error_Description : " + error_description);
			*/
/*String ckycCallbackUrl = appProps.getCdPlcKycDetailsCallbackUrl()+"?leadId="+state+"&customerHash="
				+hmac;*//*

			if (error != null && !error.isEmpty()) {
				LoggerClass.appLayerLogger.info("Error is not empty/null so going for CKYC Journey");
				*/
/*CKYCRootPayload ckycRootPayload = new CKYCRootPayload();
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
				}*//*

				CKYCStatusResponse response = callingCKYCJourney(state);
				LoggerClass.appLayerLogger.info("Final CKYCStatusResponse in digilockerProcess() : " + mapper.writeValueAsString(response));
				if (response.getcKycDecision()) {
					return new ModelAndView("redirect:" + response.getUrl() + "?leadId=" + state);
				} else if(!response.getUrl().isEmpty()){
					return new ModelAndView("redirect:" + response.getUrl());
				}
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				Thread thread = new Thread(() -> {
					String url = appProps.getTclPlDelegatorDigilockerStatusByEaadhar() + "?code=" + code +"&leadId=" +Long.valueOf(state);
					LoggerClass.appLayerLogger.info("Authorization Token Generation Url : " + url);
					RestTemplate restTemplate = new RestTemplate();
					try {
						ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Void.class);
						LoggerClass.appLayerLogger.info("Status Code : {}", responseEntity.getStatusCode());
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				thread.start();

				String callbackUrl = appProps.getCdPlLoaderPageUrl() + "?leadId=" + state;
				LoggerClass.appLayerLogger.info("callbackUrl ::: {}", callbackUrl);
				return new ModelAndView("redirect:" + callbackUrl);
			}
		} catch (Exception e) {
			logger.error("Error occurred in digilockerProcess api ", e);
		}
		return null;
	}

	*/
/*@RequestMapping(value = "v0.1/checkStatus", method = {RequestMethod.POST,RequestMethod.GET})
	public Object checkDigilockerStatus(HttpServletRequest httpServletRequest) {
		CheckDigilockerStatusRequest checkDigilockerStatusRequest = new CheckDigilockerStatusRequest();
		CheckDigilockerStatusResponse response = new CheckDigilockerStatusResponse();
		ObjectMapper mapper = new ObjectMapper();
		try {
			LoggerClass.appLayerLogger.info("checkStatus() UI Process started");
			String leadId = httpServletRequest.getParameter("leadId");
			String flag = httpServletRequest.getParameter("flag");
			LoggerClass.appLayerLogger.info("Lead Id : " + leadId);
			LoggerClass.appLayerLogger.info("Flag : " + flag);
			if(flag !=null) {
				LoggerClass.appLayerLogger.info("Flag is not null i.e. DigiLocker Journey is rejected. Going for CKYC Journey");
				callingCKYCJourney(leadId);
			} else {
				checkDigilockerStatusRequest.setLeadId(leadId);
				MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
				headers.add("Content-Type", "application/json");
				HttpEntity<CheckDigilockerStatusRequest> httpEntity = new HttpEntity(checkDigilockerStatusRequest, headers);
				String url = appProps.getTclPlDelegatorCheckStatusUrl();
				LoggerClass.appLayerLogger.info("Core DLP DigiLocker Check Status API details Url : " + url);

				RestTemplate restTemplate = new RestTemplate();
				response = restTemplate.postForObject(url, httpEntity, CheckDigilockerStatusResponse.class);
				LoggerClass.appLayerLogger.info("checkDigilockerStatus Response : " + mapper.writeValueAsString(response));
				if (response.getDigiLockerFlag() != null) {
					if (response.getDigiLockerFlag() == true) {
						return new ModelAndView("redirect:" + response.getDigiLockerUrl() + "?leadId=" + leadId);
					} else {
						return new ModelAndView("redirect:" + response.getCkycUrl() +"?leadId="+leadId
								+customerHash);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error occurred in DigiLocker Check Status api ", e);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}*//*


	public CKYCStatusResponse callingCKYCJourney(String leadId) {
		LoggerClass.appLayerLogger.info("CKYC Journey Started");
		CKYCRootPayload ckycRootPayload = new CKYCRootPayload();
		ckycRootPayload.setAppId(leadId);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", "application/json");
		HttpEntity<CKYCRootPayload> httpEntity = new HttpEntity<>(ckycRootPayload, headers);
		String url = appProps.getTclPlDelegatorCkycDecisionUrl();
		LoggerClass.appLayerLogger.info("CKYC Decision URL : " + url);

		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject(url, httpEntity, String.class);
		try {
			LoggerClass.appLayerLogger.info("CKYC Decision Response :" + mapper.writeValueAsString(response));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		CKYCStatusResponse ckycStatusResponse = null;
		try {
			ckycStatusResponse = mapper.readValue(response, CKYCStatusResponse.class);
			LoggerClass.appLayerLogger.info("CKYC Decision Response :::: :" + mapper.writeValueAsString(ckycStatusResponse));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ckycStatusResponse;
	}

	 */
/*@RequestMapping("/v0.1/generateAuthToken")
    public void generateToken(@RequestParam String code) {
        try {
            LoggerClass.appLayerLogger.info("DigiLockerAppController : Token Generation API Started :");
            RestTemplate restTemplate = new RestTemplate();
            String url = tclServicesProps.getAuthTokenGenerationUrl() + "?code=" + code;
            LoggerClass.appLayerLogger.info("Token Generation url : " + url);
            restTemplate.getForObject(url, Void.class);
            LoggerClass.appLayerLogger.info("DigiLockerAppController : Token Generation API Started ended");
        } catch(Exception e) {
            logger.error("Exception in generateToken() from DigiLockerAppController class ", e);
        }
    }*//*


	@RequestMapping("/v0.1/digilockerStatusByEaadhar")
	public void digilockerStatusByEaadhar(@RequestParam Long leadId, String code) {
		try {
			LoggerClass.appLayerLogger.info("Inside DigiLockerAppController.class : digilockerStatusByEaadhar() started");
			RestTemplate restTemplate = new RestTemplate();
			String url = tclServicesProps.getDigilokerStatusUrl() + "?code=" + code +"&leadId=" + leadId;
			LoggerClass.appLayerLogger.info("Token Generation url : " + url);
			ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Void.class);
			LoggerClass.appLayerLogger.info("Status Code in App : {}", responseEntity.getStatusCode());
			LoggerClass.appLayerLogger.info("Inside DigiLockerAppController.class : digilockerStatusByEaadhar() ended");
		} catch (Exception e) {
			LoggerClass.appLayerLogger.info("Error While Calling Inside DigiLockerAppController.class : digilockerStatusByEaadhar()");
			e.printStackTrace();
		}

	}

	@RequestMapping("/v0.1/checkStatus")
	public ResponseEntity<CheckDigilockerStatusResponse> checkStatus(@RequestBody CheckDigilockerStatusRequest checkDigilockerStatusRequest) {
		CheckDigilockerStatusResponse response = new CheckDigilockerStatusResponse();
		try {
			LoggerClass.appLayerLogger.info("DigiLokerAppController : checkStatus API Started :");
			ObjectMapper objectMapper = new ObjectMapper();
			LoggerClass.appLayerLogger.info("CheckDigilockerStatusRequest : {}", objectMapper.writeValueAsString(checkDigilockerStatusRequest));
			response = digiLockerAppService.checkStatus(checkDigilockerStatusRequest);
			LoggerClass.appLayerLogger.info("CheckDigilockerStatusResponse : {}", objectMapper.writeValueAsString(response));
			LoggerClass.appLayerLogger.info("DigiLokerAppController : checkStatus API ended");
		} catch(Exception e) {
			logger.error("Exception in generateToken() from DigiLokerDelegatorController class {}", e);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping("/v0.1/getDigiLockerKYCDetails")
	public ResponseEntity<DigiLockerKYCDtlsResponse> digilockerKycDetails(@RequestParam Long leadId) throws Exception {
		Status status;
		CallStatus callStatus;
		LoggerClass.appLayerLogger.info("LeadId :" + leadId);
		DigiLockerKYCDtlsResponse response = new DigiLockerKYCDtlsResponse();
		try {
			DigilockerDetails digilockerDetails = tclServiceManager.getCommonService().getDigilockerDetails(leadId);

			if (digilockerDetails != null) {
				LoggerClass.appLayerLogger.info("----Digilocker Customer Name----" +digilockerDetails.getDigiCustomerName());
				response.setDigiLockerCustomerName(digilockerDetails.getDigiCustomerName() != null ? digilockerDetails.getDigiCustomerName()  : "" );
				String house = digilockerDetails.getHouse() !=null ? digilockerDetails.getHouse() : "";
				String street = digilockerDetails.getStreet() !=null ? digilockerDetails.getStreet() : "";
				String landMark = digilockerDetails.getLandMark() !=null ? digilockerDetails.getLandMark() : "";
				String location = digilockerDetails.getLocality() !=null ? digilockerDetails.getLocality() : "";
				String dist = digilockerDetails.getDist() !=null ? digilockerDetails.getDist() : "";

				String fullAddress = house + " " + street + " " + landMark + " " + location + " " + dist;
				response.setDigiLockerAddress(fullAddress.trim());
				response.setDigiLockerPincode(digilockerDetails.getPinCode() != null ? digilockerDetails.getPinCode()  : "" );
				response.setDigiLockerCity(digilockerDetails.getVillageTownCity() != null ? digilockerDetails.getVillageTownCity()  : "" );
				response.setDigiLockerState(digilockerDetails.getState() != null ? digilockerDetails.getState()  : "" );
				response.setImageinBase64(digilockerDetails.getDigiPhotograph() != null ? digilockerDetails.getDigiPhotograph()  : "" );
			}
            */
/*List<DigilockerDetails> digilockerDetailsList = tclServiceManager.getCommonService().getDigilockerDetails(String.valueOf(payload.getLeadId());

            LoggerClass.appLayerLogger.info("Digilocker Details size :"+ digilockerDetailsList.size());
            if (!digilockerDetailsList.isEmpty() && digilockerDetailsList.size() > 0) {
                DigilockerDetails digilockerDetails1 = digilockerDetailsList.get(digilockerDetailsList.size() - 1);
                response.setImageinBase64(doc.getBase64FormattedData());
            }*//*


			status = tclServiceManager.getStatusService().getStatusByStatusParam(ProjectConstants.STATUS_CODE_PARAMS.SUCCESS);
			callStatus = new CallStatus().setStatusCode(status.getStatusCode()).setStatusMessage(status.getStatusMessage());
			response.setRetStatus(ProjectConstants.STATUS_CODE_PARAMS.SUCCESS);
			response.setStatus(callStatus);
		} catch (Exception e) {
			logger.error("While calling digilockerKycDetails Response it throws error", e);
			Status digiLockerStatus = tclServiceManager.getStatusService().getStatusByStatusParam(ProjectConstants.STATUS_CODE_PARAMS.API_FAILED);
			CallStatus digiLockerCallStatus = new CallStatus().setStatusCode(digiLockerStatus.getStatusCode()).setStatusMessage(digiLockerStatus.getStatusMessage());
			response.setStatus(digiLockerCallStatus);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

//	bellow code is from Service controller
	  */
/*@RequestMapping("/v0.1/generateAuthToken")
    public void generateToken(@RequestParam String code) {
        try {
            LoggerClass.appLayerLogger.info("Inside DigiLockerServiceController.class : generateToken() started");
            digilockerService.generateToken(code);
            LoggerClass.appLayerLogger.info("Inside DigiLockerServiceController.class : generateToken() ended");
        } catch (Exception e) {
            LoggerClass.appLayerLogger.info("Error While Calling Inside DigiLockerServiceController.class : generateToken()");
            e.printStackTrace();
        }
    }*//*


	@RequestMapping("/v0.1/digilockerStatusByEaadhar")
	public void digilockerStatusByEaadhar(@RequestParam Long leadId, String code) {
		//boolean status = false;
		try {
			LoggerClass.appLayerLogger.info("Inside DigiLockerServiceController.class : digilockerStatusByEaadhar() started");
			boolean status= digilockerService.DigilockerStatusByEaadhar(leadId, code);
			LoggerClass.appLayerLogger.info("Inside DigiLockerServiceController.class : digilockerStatusByEaadhar() ended. Digilocker Status {}", status);
		} catch (Exception e) {
			LoggerClass.appLayerLogger.info("Error While Calling Inside DigiLockerServiceController.class : digilockerStatusByEaadhar()");
			e.printStackTrace();
		}
		//return status;
	}
}
*/
