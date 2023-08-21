package com.expriment.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CustomerMailUtil {

	Logger logger = LogManager.getLogger("CustomerMailUtil");

//	@Autowired
//	JavaMailSender javaMailSender;

//	@Autowired
//	public AppProps appProps;

//	@Autowired
//	public TCLServicesProps tclServicesProps;

//	public AppProps getAprops() {
//		return this.appProps;
//	}

	// public static final String EMAIL_SUBJECT = "MIS Report.";
	public static final String EMAIL_BODY = "<html><body><div><p>Dear User ,</p><p>Please find Key Fact Statement for your reference.</p><br><p>Regards,</p><p style="
			+ "font-weight: bold;" + ">Tata Capital Financial Services Ltd</p></div></body>";

	/*public boolean sendEmail(String filePath, String toMail, CDIOfferModule cdiOfferModule) {
		LoggerClass.appLayerLogger.info("Begin : sendEmail()");

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Date date1 = new Date();
			DateFormat newDateForamt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			String timestamp = newDateForamt.format(date1);

			SmsMailResponse smsMailResponse = new SmsMailResponse();
			smsMailResponse.setIsSendMail(false);

			SmsMailPayload smsMailPayload = new SmsMailPayload();
			smsMailPayload.setSubject("Key Fact Statement-" + cdiOfferModule.getCustomerName() + "-"
					+ cdiOfferModule.getPlOpportunityId() + "-" + timestamp);
			File file = new File(filePath);
			if (file != null) {
				smsMailPayload.setFile(file);
				LoggerClass.appLayerLogger.info("Name: " + file.getName());
				smsMailPayload.setFileName(file.getName());
				smsMailPayload.setCompleteFilePath(file.getPath());
				LoggerClass.appLayerLogger.info("AbsolutePath: " + file.getAbsolutePath());
				LoggerClass.appLayerLogger.info("Path: " + file.getPath());
			}

			smsMailPayload.setMessage(EMAIL_BODY);
			smsMailPayload.setMailTo(toMail);
			LoggerClass.appLayerLogger.info("SmsMailPayload : " + objectMapper.writeValueAsString(smsMailPayload));
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("Content-Type", "application/json");
			RestTemplate restTemplate = new RestTemplate();
			String sendMailUrl = "tcl.pl.services.url}/sms/services/v0.1/sendEmail";//tclServicesProps.getSendEmailUrl();
			LoggerClass.appLayerLogger.info("Send Mail Url : " + sendMailUrl);

			HttpEntity<SmsMailPayload> httpEntity1 = new HttpEntity<>(smsMailPayload, headers);
			smsMailResponse = restTemplate.postForObject(sendMailUrl, httpEntity1, SmsMailResponse.class);

			LoggerClass.appLayerLogger.info("Mail status response for sanction : " + smsMailResponse);
			if (smsMailResponse == null) {
				smsMailResponse = new SmsMailResponse();
				smsMailResponse.setRetStatus(ProjectConstants.FAIL);
				smsMailResponse.setSysErrorCode(ProjectConstants.DATA_NOT_FOUND_ERROR_CODE);
				smsMailResponse.setSysErrorMessage("API failed");
				smsMailResponse.setIsSendMail(false);
			} else {
				smsMailResponse.setRetStatus("success");//ProjectConstants.STATUS_CODE_PARAMS.SUCCESS);
				smsMailResponse.setIsSendMail(true);
				smsMailResponse.setSysErrorCode("");
				smsMailResponse.setSysErrorMessage("");
				LoggerClass.appLayerLogger.info("***** Mail send Successfully for sanction : " + toMail);

			}
			LoggerClass.appLayerLogger.info("Email sending complete.");

			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Got the exception while sending email. : ", e);
		}
		LoggerClass.appLayerLogger.info("End : sendEmail()");
		return false;
	}

*/

}
