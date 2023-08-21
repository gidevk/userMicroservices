package com.expriment.Controller;


import com.expriment.DAO.ApplicationStatusDAO;
import com.expriment.entity.vo.NameMatchKarzaRequest;
import com.expriment.entity.vo.NameMatchKarzaResponse;
import com.expriment.service.NameMatchingService;
import com.expriment.utils.audit.LoggerClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Service
public class CKYCServiceImpl  {

    /*@Autowired
    TCLServiceManager tclServiceManager;

    @Autowired
    AuditDetailsUtility auditDetailsUtility;

    @Autowired
    AppCommonProps appCommonProps;

    @Autowired
    TCLAPIsProps tclApisProps;

    @Autowired
    CDIOfferModuleDataDAO cdiOfferModuleDataDAO;

    @Autowired
    PdfGenerationUtils generationUtils;

    @Autowired
    HVService hvService;

    @Autowired
    DocUploadRestService docUploadRestService;

    @Autowired
    public DocUploadServiceImpl docUploadServiceImpl;

    @Autowired
   NameMatchingService nameMatchingService;*/

    @Autowired
    ApplicationStatusDAO applicationStatusDAO;

    @Autowired
    NameMatchingService nameMatchingService;


    public static final Logger logger = LogManager.getLogger("CKYCServiceImpl.class");



   /* public static void main(String[] args) {
        CKYCServiceImpl ckycService = new CKYCServiceImpl();
        try {
            checkingMachingDetails(7l);
        } catch (Exception e) {
            logger.error(e.getMessage() ,e);
            e.printStackTrace();
        }
    }*/
    /*
     * @author Indradev.kuamr
     */

    public NameMatchKarzaResponse checkingMachingDetails(Long leadId) {
        LoggerClass.appLayerLogger.info("Inside checkingMachingDetails----->>>");
        String matchFaild = null;
        boolean flag= true;
        StringBuilder matchFailedError =new StringBuilder();
        NameMatchKarzaResponse nameMatchKarzaResponse = new NameMatchKarzaResponse();

        NameMatchKarzaRequest nameMatchKarzaRequest = new NameMatchKarzaRequest();
        nameMatchKarzaRequest.setCustomerHash("offerModule.getCustomerHashNew()");
        nameMatchKarzaRequest.setName("ckycName.trim()");
        nameMatchKarzaRequest.setPlWebtopId("offerModule.getPlWebTopId()");
        nameMatchKarzaRequest.setPlLeadId(leadId.toString());

        Map<String, Boolean> customerNameMatchResponse = nameMatchingService.matchingInputsWithEvokeAPI(nameMatchKarzaRequest);

//        CKYCDownloadResponse ckycDownAddressResp = tclServiceManager.getCommonService().getCKYCDownloadResponse(Long.valueOf(leadId));

        if(true/*ckycDownAddressResp!=null*/) {
            String firstName = "indradev";//ckycDownAddressResp.getCkycFirstName() !=null ? ckycDownAddressResp.getCkycFirstName() : "";
            String middleName ="" ;//ckycDownAddressResp.getCkycMiddleName() !=null ? ckycDownAddressResp.getCkycMiddleName() : "";
            String lastName = "kumar";//ckycDownAddressResp.getCkycLastName() !=null ? ckycDownAddressResp.getCkycLastName() : "";
            String ckycName = firstName + " " + middleName + " " + lastName;
            String pinCode = "500089";//ckycDownAddressResp.getCkycCorAddPin() !=null ? ckycDownAddressResp.getCkycCorAddPin() : null;
            String dob = "10-05-1991";// ckycDownAddressResp.getCkycDob() !=null ? ckycDownAddressResp.getCkycDob() : null;
//            CDIOfferModule offerModule = tclServiceManager.getCdiOfferModuleDataService().getOfferDataByPlLeadId(Long.valueOf(leadId));

            if(pinCode!=null /*&& offerModule!=null && offerModule.getCustomerPincode()!=null*/) { //pincode
                if(!pinCode.equalsIgnoreCase("500089"/*offerModule.getCustomerPincode()*/)) {
                    LoggerClass.appLayerLogger.info("Pin code match failed");
                    //matchFaild = "PINCODE_MATCH";
                    matchFailedError =matchFailedError.append( ",PINCODE_MISMATCH");
                    //have to drop here.
                    flag= false;
                }
            }

            if(dob!=null /*&& offerModule!=null && offerModule.getCustomerEnteredDob()!=null */&& flag) {
                String ckycDob = null;
                String offerModuledob2 = null;
                try {

                    LoggerClass.appLayerLogger.info("CKYC date before format : "  + dob);
                    Date ckycDate = new SimpleDateFormat("dd-MM-yyyy").parse(dob);
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    ckycDob = format.format(ckycDate);
                    LoggerClass.appLayerLogger.info("CKYC date after format : "  + ckycDob);

                    Date offerModuledob = new SimpleDateFormat("dd-MM-yyyy").parse("10-05-1991");//offerModule.getCustomerEnteredDob();
                    LoggerClass.appLayerLogger.info("offer module date before format : " + offerModuledob);
                    offerModuledob2 = format.format(offerModuledob);
                    LoggerClass.appLayerLogger.info("offer module date after format : "  + offerModuledob2);
                } catch (Exception e) {
                    LoggerClass.appLayerLogger.info("Exception while dob match failed ..",e);
                    matchFailedError =matchFailedError.append( ",DOB_MISMATCH");

                }

                LoggerClass.appLayerLogger.info("ckycDob and dobofferModule : " +ckycDob +"====>" +offerModuledob2);

                if(!ckycDob.equals(offerModuledob2)) {
                    LoggerClass.appLayerLogger.info("dob match failed");
                    matchFailedError =matchFailedError.append( ",DOB_MISMATCH");
                    //drop customer
                    flag= false;
                }
            }
            //CKYCStatus ckycStatus= tclServiceManager.commonService.getCKYCStatus(leadId);

            if( ckycName != null/* && offerModule != null  && offerModule.getCustomerName() != null*/ && flag) {
                //here we are checking the okycname and offermodule customerName
               /* Map<String, Boolean> customerNameMatchResponse = nameMatchingService.nameMatchingApi(ckycName,offerModule.getCustomerName(),leadId.toString(),offerModule);
                if(customerNameMatchResponse != null) {
                    Boolean exception = customerNameMatchResponse.get("exception");
                    Boolean proceed = customerNameMatchResponse.get("proceed");

                    if(exception != null && exception){
                        logger.error("Exception occured while customer name match for CKYC service---------------->");
                        matchFailedError =matchFailedError.append( ",NAME_MISMATCH");
                    }else if(proceed != null && !proceed){
                        logger.error("customer name match failed for CKYC service---------------->");
                        matchFailedError =matchFailedError.append( ",NAME_MISMATCH");
                    } else {
                        logger.error(" name match success for CKYC service---------------->");
                    }*/
                 nameMatchKarzaRequest = new NameMatchKarzaRequest();

                nameMatchKarzaRequest.setCustomerHash("a2d1f28472ef70ada542305fd7698871");//offerModule.getCustomerHash());
                nameMatchKarzaRequest.setName("Pradeep");
                nameMatchKarzaRequest.setPlWebtopId("453CZ0000542");//offerModule.getPlWebTopId());
                nameMatchKarzaRequest.setPlLeadId("26061");

                nameMatchKarzaResponse = NameMatchKarza(nameMatchKarzaRequest);
                if(nameMatchKarzaResponse != null) {
                    /* if(!nameMatchKarzaResponse.getRetStatus().equalsIgnoreCase("SUCCESS")*//*&&
                    nameMatchKarzaResponse.getResponse().getScore() == 00l*//*){
                        logger.error("customer name match RetStatus not Success for CKYC service");
                        matchFailedError =matchFailedError.append( ",NAME_MISMATCH");
                    }else */
                    if(false ||!nameMatchKarzaResponse.getRetStatus().equalsIgnoreCase("SUCCESS")
                            ||nameMatchKarzaResponse.getResponse().getScore() <= 0.6f){
                        logger.error("customer name match failed because of score is < 60 % OR RetStatus not Success in CKYC service");
                        matchFailedError =matchFailedError.append( ",NAME_MISMATCH");
                    } else {
                        logger.error(" name match success for CKYC service");
                    }
                } else {
                    logger.error("customer name match response is null in CKYC service ");
                }
            }
            if(matchFailedError.length()>0) {
                matchFailedError.deleteCharAt(0);
                LoggerClass.appLayerLogger.info("Final matchFailedError : "+ matchFailedError);
//                saveOrUpdateCKYCStatus(leadId, APINameConstants.CKYC_DOWNLOAD, "ERRMM01",
//                        matchFailedError.toString());
            }
           /* ApplicationStatus applicationStatus = applicationStatusDAO.getApplicationStatus(String.valueOf(leadId));
            if(applicationStatus!=null && nameMatchKarzaResponse != null) {
                LoggerClass.appLayerLogger.info("Updating application status table.."+nameMatchKarzaResponse.getResponse().getScore());
                applicationStatus.setCkyNameMatchScore(nameMatchKarzaResponse.getResponse().getScore());
                applicationStatusDAO.saveOrUpdateApplicationStatus(applicationStatus);
            }*/
        }
        return nameMatchKarzaResponse;
    }

    public static NameMatchKarzaResponse NameMatchKarza(NameMatchKarzaRequest request){

        RestTemplate restTemplate = new RestTemplate();
        NameMatchKarzaResponse response = new NameMatchKarzaResponse();
        String karzaURL;
        String conversationId = String.valueOf(new Date().getTime());

        try {
         /*   final String proxyUrl = appCommonProps.getProxyIpaddress();
            final int port = appCommonProps.getProxyPort();
            Boolean enabledProxy = appCommonProps.getEnableProxy();

            LoggerClass.appLayerLogger.info("Enabled proxy is set to {}",enabledProxy);*/
            karzaURL= "https://apicast-uat-jocatafrontendadapter.tclnprdservice.tatacapital.com/rest/jocata/v1.0/tatadigital/name-match-score";

            LoggerClass.appLayerLogger.info("karza api URL {}",karzaURL);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            headers.set("Content-Type", "application/json");
            headers.add("Authorization", "Basic MTNlMzkxNzY6am9jYXRhdWF0");
            headers.add("ConversationID", conversationId);
            headers.add("SourceName", "jocata");
            LoggerClass.appLayerLogger.info("headers: "+headers);

            HttpEntity<?> httpEntity = new HttpEntity<>(request, headers);

           response= restTemplate.postForObject(karzaURL, httpEntity, NameMatchKarzaResponse.class);
           /* karzaResponse karzaResponse= new karzaResponse();
            karzaResponse.setCustomerHash("customerhash");
            karzaResponse.setErrorCode("200");
            karzaResponse.setErrorMessage("error message");
            karzaResponse.setErrorReason("Error reason");
            karzaResponse.setPlLeadId("123456");
            karzaResponse.setScore(0.67);
            karzaResponse.setPlWebtopId("webtopId");

            response.setResponse(karzaResponse);
            response.setRetStatus("Success");
*/
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /*private boolean handlingCKYCDownloadErrorResponse(A51DownloadCkycResponse response, Long leadId, CKYCRootResponse rootResponse) {
        boolean status=false;
        if(response!=null && (StringUtils.equalsIgnoreCase(response.getRetStatus(),"ERROR") ||
                (response.getDownloadFromCkycResponseDetails()!=null && response.getDownloadFromCkycResponseDetails().getCkycDownloadResponseDetail()!=null &&
                        !StringUtils.equalsIgnoreCase(response.getDownloadFromCkycResponseDetails().getCkycDownloadResponseDetail().getTransactionStatus(),"CKYCSuccess")))) {
            status=true;
            String rejectionReason=(!StringUtils.equalsIgnoreCase(response.getDownloadFromCkycResponseDetails().getCkycDownloadResponseDetail().getTransactionStatus(),"CKYCSuccess"))
                    ? response.getDownloadFromCkycResponseDetails().getCkycDownloadResponseDetail().getTransactionRejectionDesciption()
                    : response.getSysErrorMessage().toString();
            String rejectionCode=(!StringUtils.equalsIgnoreCase(response.getDownloadFromCkycResponseDetails().getCkycDownloadResponseDetail().getTransactionStatus(),"CKYCSuccess"))
                    ? response.getDownloadFromCkycResponseDetails().getCkycDownloadResponseDetail().getTransactionRejectionCode()
                    : response.getSysErrorCode().toString();
            saveOrUpdateCKYCStatus(leadId,APINameConstants.CKYC_DOWNLOAD,rejectionCode,rejectionReason);
            rootResponse.setRetStatus(response.getRetStatus());
            CallStatus callStatus = new CallStatus();
            callStatus.setStatusCode(rejectionCode);
            callStatus.setStatusMessage(rejectionReason);
            rootResponse.setStatus(callStatus);
        }
        return status;
    }
*/
   /* public void saveOrUpdateCKYCStatus(Long leadId, String apiName, String rejectionCode, String rejectionReason) {
        CKYCStatus cKycStatus;
        cKycStatus = tclServiceManager.getCommonService().getCKYCStatus(leadId);
        if(cKycStatus == null) {
            logger.error("No any record found in DB for lead Id :"+ leadId);
            cKycStatus = new CKYCStatus();
            cKycStatus.setLeadId(leadId);
        }
        cKycStatus.setApiName(apiName);
        cKycStatus.setRejectionCode(rejectionCode);
        cKycStatus.setRejectionReason(rejectionReason);
        tclServiceManager.commonService.saveCKYCStatus(cKycStatus);
    }*/

   /* @SuppressWarnings("unused")
	private void callDmsPushService(UploadDoc uploadDoc, String appId) {
    	LoggerClass.appLayerLogger.info("Sending uploading doc to DMS : Start "+ uploadDoc.getDocId() + " ,"+ uploadDoc.getDocUploadName() );
    	DocUploadPayload docUploadPayload = new DocUploadPayload();

        CDIOfferModule offerModule= cdiOfferModuleDataDAO.getOfferDataByPlLeadId(Long.valueOf(appId));
        CKYCRootResponse rootResponse = new CKYCRootResponse();
        docUploadPayload.setDocId(uploadDoc.getDocId());
        docUploadPayload.setMobileNumber(offerModule.getMobileNo()+"");
        docUploadPayload.setLeadId(appId);
        docUploadPayload.setWebtopNo(offerModule.getPlWebTopId());
        docUploadPayload.setDocUploadType(uploadDoc.getDocUploadType());
        docUploadPayload.setDocUploadName(uploadDoc.getDocUploadName());

        String fileUploadPath = uploadDoc.getDocUploadBase64();
		File file = new File(fileUploadPath);
		byte[] base64;
		try {
			base64 = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
		       docUploadPayload.setBase64(new String(base64,StandardCharsets.US_ASCII));

		} catch (IOException e) {
			// TODO Auto-generated catch block
            logger.error("Exception in callDmsPushService()", e);
            savingExceptionDetails(APINameConstants.CKYC_DOWNLOAD, offerModule.getPlLeadId(), rootResponse);
		}

        try {
			LoggerClass.appLayerLogger.info("DocUploadPayload ->: " + objectMapper.writeValueAsString(docUploadPayload));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
            logger.error("Exception in callDmsPushService()", e);
            savingExceptionDetails(APINameConstants.CKYC_DOWNLOAD, offerModule.getPlLeadId(), rootResponse);
        }
		DocUploadResponse documentUploadResponse = docUploadServiceImpl.uploadDocV2(docUploadPayload);
        if (documentUploadResponse != null && documentUploadResponse.getRetStatus() != null) {
        	LoggerClass.appLayerLogger.info("Upload Doc sucess");
        }
    	LoggerClass.appLayerLogger.info("Sending uploading doc to DMS : Ends "+uploadDoc.getDocId());

    }*/

   /* public String removeSplChar(String data) {
        ValidationUtils validationUtils = new ValidationUtils();
        boolean check = false;
        String field = data;
        check = validationUtils.validateField("[^a-zA-Z0-9 ]", field);
        if (check) {
            field = field.replaceAll("[^a-zA-Z0-9 ]", "") + " ";
            return field;
        }
        return data;
    }

    private HttpHeaders generateHeaders(AuditDetailsPayload auditDetailsPayload) {
        HttpHeaders headers = new HttpHeaders();
        String conversationId = String.valueOf(new Date().getTime());

        List<TclConstant> tclConstants = tclServiceManager.getCommonService().getAllTclConstantsUsingApiName(APINameConstants.CKYC_DOWNLOAD);
        if (!CollectionUtils.isEmpty(tclConstants)) {
            Map<String, String> credentials = tclConstants.stream().collect(Collectors.toMap(TclConstant::getConstantName, TclConstant::getWebConstantValue));
            headers.set("Accept-Encoding", "gzip,deflate");
            headers.set("Authorization", credentials.get("CKYC_AUTHORIZATION"));
            headers.set("SourceName", credentials.get("CREATE_DMS_FOLDER_SOURCENAME"));
        }
        headers.set("ConversationID", conversationId);
        headers.setContentType(MediaType.APPLICATION_XML);
        auditDetailsPayload.setConversationId(conversationId);
        return headers;
    }*/
}
