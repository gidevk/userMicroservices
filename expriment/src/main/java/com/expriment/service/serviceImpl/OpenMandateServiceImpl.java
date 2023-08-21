package com.expriment.service.serviceImpl;

import com.expriment.DAO.BankRequestDetailsDAO;
import com.expriment.entity.BankRequestDetails;
import com.expriment.entity.vo.*;
import com.expriment.service.OpenMandateService;
import com.expriment.utils.ProjectConstants;
import com.expriment.utils.audit.LoggerClass;
import com.expriment.utils.audit.entity.vo.RootResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/*
 * @author Indradev.kuamr
 */
@Service
public class OpenMandateServiceImpl implements OpenMandateService {


    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    BankRequestDetailsDAO bankRequestDetailsDAO;
    
    @Override
    public RootResponse OpenMandeteOperation(EnquiryRequest enquiryRequest, String leadId){
        RootResponse response = new RootResponse();
        EnquiryAPIResp enquiryAPIResp=null;
        String token=null;
//        ResponseEntity<String> batchIdRes=null;
        int batchId;

        MatrixRequest matrixRequest=null;
        MatrixResponse matrixResponse=null;
        BlockApiReq blockApiReq= null;
        BlockApiRes blockApiRes = null;

        try {
          /*  call enquiry

            if enquiry succees
            token

            if token status == true && response != null
            call batchId

            if batchId succees
            call matrix
            clone bankDetails

            if matrix succees
            call block api*/

            response.setRetStatus("Success");
            response.setLeadId(enquiryRequest.getPan_id());

            LoggerClass.appLayerLogger.info("enquiryRequest {}",objectMapper.writeValueAsString(enquiryRequest));
            enquiryAPIResp= enquiryApi(enquiryRequest,leadId);

              LoggerClass.appLayerLogger.info("Enquiry response {}",objectMapper.writeValueAsString(enquiryAPIResp));
            if (enquiryAPIResp!= null && enquiryAPIResp.getMessage().equalsIgnoreCase("success") && enquiryAPIResp.getStatus().equalsIgnoreCase("000")){
                token= createToken(leadId);

                if (token !=null){
                      LoggerClass.appLayerLogger.info("token {}",token);
                    batchId= getBatchId(token,leadId);
                      LoggerClass.appLayerLogger.info("BatchId response {}",objectMapper.writeValueAsString(batchId));

                    if(batchId !=0){// && batchId.getStatusCode() != null && batchId.getStatusCode() == HttpStatus.valueOf(200)){
//
//                        String batchIdResponse = batchIdRes.getBody();
//                        JSONObject batchIdResponseJson = new JSONObject(batchIdResponse);
//                        int batchId = batchIdResponseJson.getInt("max_batch_id");
//                          LoggerClass.appLayerLogger.info("Batch Id : "+batchId);

                        matrixRequest.setBatch_id(batchId);
                        matrixRequest.setDOB(enquiryRequest.getDob());
                        matrixRequest.setPan_number(enquiryRequest.getPan_id());
                        matrixRequest.setData_flag("S");
                        matrixRequest.setUnique_identifier(enquiryAPIResp.getUnique_identifier());

                        matrixResponse= OpenMandate(matrixRequest,leadId);
                          LoggerClass.appLayerLogger.info("Matrix request {} response {}",objectMapper.writeValueAsString(matrixRequest)
                                ,objectMapper.writeValueAsString(matrixResponse));

                        saveBankRequestDetails(matrixResponse,leadId);
                        if (matrixResponse !=null && matrixResponse.getStatus().equalsIgnoreCase("200")
                                && matrixResponse.getMessage().equalsIgnoreCase(ProjectConstants.SUCCESS)){

                            blockApiReq.setUnique_identifier(enquiryAPIResp.getUnique_identifier());
                            blockApiReq.setTenure(enquiryRequest.getTenure());
                            blockApiReq.setSource_system(enquiryRequest.getSource_system());
                            blockApiReq.setRepayment_mode(matrixResponse.getData().getEmandateData().get(0).getRepayment_mode());
                            blockApiReq.setProduct_type(matrixResponse.getData().getEmandateData().get(0).getProduct());
                            blockApiReq.setMicr(matrixResponse.getData().getEmandateData().get(0).getMicr());
                            blockApiReq.setMandate_start_date(matrixResponse.getData().getEmandateData().get(0).getMandate_start_date());
                            blockApiReq.setMandate_end_date(matrixResponse.getData().getEmandateData().get(0).getMandate_end_date());
                            blockApiReq.setLoan_amount(matrixResponse.getData().getEmandateData().get(0).getLoan_amount());
                            blockApiReq.setLegal_entity(matrixResponse.getData().getEmandateData().get(0).getLegal_entity());
                            blockApiReq.setIfsc(matrixResponse.getData().getEmandateData().get(0).getIfsc());
                            blockApiReq.setEmi_amount(enquiryRequest.getEmi_amount());
                            blockApiReq.setCustomer_type(matrixResponse.getData().getEmandateData().get(0).getCustomer_type());
                            blockApiReq.setCustomer_name(matrixResponse.getData().getEmandateData().get(0).getCustomer_name());
                            blockApiReq.setCompany_code(matrixResponse.getData().getEmandateData().get(0).getCompany_code());
                            blockApiReq.setBank_name(matrixResponse.getData().getEmandateData().get(0).getMandate_bank_name());
                            blockApiReq.setAccount_type(matrixResponse.getData().getEmandateData().get(0).getAccount_type());
                            blockApiReq.setAccount_number(matrixResponse.getData().getEmandateData().get(0).getCustomer_account_number());
                            blockApiReq.setAccount_holder_name(matrixResponse.getData().getEmandateData().get(0).getAccount_holder_name());
                            blockApiReq.setContract_number(matrixResponse.getData().getEmandateData().get(0).getContract_number());
                            blockApiReq.setLos_id("APPL10781707");///doubt
                            blockApiReq.setWeb_flag("N");
                            blockApiReq.setOther_detail("");

                            blockApiRes= blockAPI(blockApiReq,leadId);
                              LoggerClass.appLayerLogger.info("block request {} response {}",objectMapper.writeValueAsString(blockApiReq)
                                    ,objectMapper.writeValueAsString(blockApiRes));

                        }else {
                            response.setRetStatus("Failure");
                              LoggerClass.appLayerLogger.info("Matrix api is valid {}",matrixResponse.toString());
                        }
                    }else{
                        response.setRetStatus("Failure");
                          LoggerClass.appLayerLogger.info("batchId response is not valid {}",batchId);
                    }
                }else {
                    response.setRetStatus("Failure");
                      LoggerClass.appLayerLogger.info("Token is null.");
                }
            }else {
                response.setRetStatus("Failure");
                  LoggerClass.appLayerLogger.info("Enquiry APi is Fail with Message {} and Status {}",enquiryAPIResp.getMessage(),enquiryAPIResp.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public void saveBankRequestDetails(MatrixResponse matrixResponse, String leadId){

//        String batchId = String.valueOf(matrixResponse.getData().getBatchID());
        BankRequestDetailsResponse response = new BankRequestDetailsResponse();
        try {
            if (leadId != null) {
                BankRequestDetails bankRequestDetails = bankRequestDetailsDAO.getBankRequestDetailsByLeadId(leadId);
                if (bankRequestDetails == null) {
                    bankRequestDetails = new BankRequestDetails();
                }

                bankRequestDetails.setLeadId(leadId);
                bankRequestDetails.setBankName(matrixResponse.getData().getEmandateData().get(0).getMandate_bank_name());
//                bankRequestDetails.setBankCode(matrixResponse.getData().getEmandateData().get(0).getCompany_code());//doubt
                bankRequestDetails.setIfscCode(matrixResponse.getData().getEmandateData().get(0).getIfsc());
                bankRequestDetails.setAccountNumber(matrixResponse.getData().getEmandateData().get(0).getCustomer_account_number());
                bankRequestDetails.setConfirmAccountNumber(matrixResponse.getData().getEmandateData().get(0).getCustomer_account_number());
                bankRequestDetails.setAccountType(matrixResponse.getData().getEmandateData().get(0).getAccount_type());
//                bankRequestDetails.setCardType(matrixResponse.getData().getEmandateData().get(0).getCustomer_type());//doubt
//                 bankRequestDetails.setAccountName(bankRequestDetailsPayload.getAccountName()!=null?bankRequestDetailsPayload.getAccountName():"");

                bankRequestDetails = bankRequestDetailsDAO.saveBankRequestDetails(bankRequestDetails);
                response.setRetStatus(ProjectConstants.SUCCESS);

                LoggerClass.appLayerLogger.info("bankRequestDetails saved data {}",objectMapper.writeValueAsString(bankRequestDetails));
//
            } else {
                response.setRetStatus(ProjectConstants.FAILURE);
                response.setSysErrorCode(ProjectConstants.SYS_ERROR_CODE);
                response.setSysErrorMessage("Details not avaiable");
            }
        } catch (Exception e) {
            LoggerClass.appLayerLogger.error("Exception in get method while saveBankRequestDetails :", e);
        }
//        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @Override
    public EnquiryAPIResp enquiryApi(EnquiryRequest request, String leadId){
        EnquiryAPIResp response = new EnquiryAPIResp();
        RestTemplate restTemplate= new RestTemplate();
        try {
            String url= "http://13.234.160.51:10010/api/open_mandate/enquiry";

            LoggerClass.appLayerLogger.info("enquiryApi payload {}",objectMapper.writeValueAsString(request));

            LoggerClass.appLayerLogger.info("enquiryApi URL {}",url);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            headers.set("Content-Type", "application/json");
//            headers.add("Authorization", "Basic MTNlMzkxNzY6am9jYXRhdWF0");
//            headers.add("ConversationID", conversationId);
//            headers.add("SourceName", "Jocata");
            LoggerClass.appLayerLogger.info("headers: "+headers);

            HttpEntity<?> httpEntity = new HttpEntity<>(request, headers);

            response= restTemplate.postForObject(url, httpEntity, EnquiryAPIResp.class);
            LoggerClass.appLayerLogger.info("Enquiry API payload {}",objectMapper.writeValueAsString(response));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

   @Override
    public Integer getBatchId(String token, String leadId){
        Integer response = null;
//        String token=null;
        RestTemplate restTemplate= new RestTemplate();
        try {
            String url= "http://13.234.160.51:10010/api/mandate_registration/findMandateBatchId";

//            LoggerClass.appLayerLogger.info("enquiryApi payload {}",objectMapper.writeValueAsString(request));

            LoggerClass.appLayerLogger.info("getBatchId URL {}",url);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            headers.set("Content-Type", "application/json");
            headers.set("user_id","JOCATA_HL");// impsAuthDetails.getUserId());
            headers.set("key", "rzNtQQTd5Gl0HqAn8dfIvg==" );//impsAuthDetails.getKey());
            headers.set("x-access-token", token );
//			jsonObj.put("validity", impsAuthDetails.getValidity());
//            headers.add("Authorization", "Basic MTNlMzkxNzY6am9jYXRhdWF0");
//            headers.add("ConversationID", conversationId);
//            headers.add("SourceName", "Jocata");
            LoggerClass.appLayerLogger.info("headers: "+headers);

            HttpEntity<?> httpEntity = new HttpEntity<>(null, headers);

            response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Integer.class).getBody();
            response= restTemplate.getForObject(url, Integer.class);

//            response= restTemplate.postForObject(url, httpEntity, Integer.class);
            LoggerClass.appLayerLogger.info("batch Id payload {}",objectMapper.writeValueAsString(response));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public MatrixResponse OpenMandate(MatrixRequest request, String leadId){
        MatrixResponse response= new MatrixResponse();
        String url= "http://13.234.160.51:10010/api/open_mandate/Open_mandate_matrix";
        RestTemplate restTemplate= new RestTemplate();
        try {
            LoggerClass.appLayerLogger.info("OpenMandate URL {}",url);

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            headers.set("Content-Type", "application/json");
            headers.set("user_id","JOCATA_HL");// impsAuthDetails.getUserId());
//            headers.set("key", "rzNtQQTd5Gl0HqAn8dfIvg==" );//impsAuthDetails.getKey());
            headers.set("x-access-token", "token" );

            LoggerClass.appLayerLogger.info("headers: "+headers);

            HttpEntity<?> httpEntity = new HttpEntity<>(request, headers);

            response= restTemplate.postForObject(url, httpEntity, MatrixResponse.class);
            LoggerClass.appLayerLogger.info("batch Id payload {}",objectMapper.writeValueAsString(response));


        } catch (Exception e) {
            e.printStackTrace();
        }
    return response;

    }

    @Override
    public BlockApiRes blockAPI(BlockApiReq request, String leadId){
        BlockApiRes response= new BlockApiRes();
        try {
            String url= "http://13.234.160.51:10010/api/open_mandate/block";
            RestTemplate restTemplate= new RestTemplate();
                LoggerClass.appLayerLogger.info("blockAPI URL {}",url);

                MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

                headers.set("Content-Type", "application/json");
                headers.set("user_id","JOCATA_HL");// impsAuthDetails.getUserId());
//            headers.set("key", "rzNtQQTd5Gl0HqAn8dfIvg==" );//impsAuthDetails.getKey());
                headers.set("x-access-token", "token" );

                LoggerClass.appLayerLogger.info("blockAPI headers: "+headers);

                HttpEntity<?> httpEntity = new HttpEntity<>(request, headers);

                response= restTemplate.postForObject(url, httpEntity, BlockApiRes.class);
                LoggerClass.appLayerLogger.info("blockAPI payload {}",objectMapper.writeValueAsString(response));


            } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }
    // Creating token
    @Override
    public String createToken(String leadId){
        String token = null;
        JSONObject jsonObj = new JSONObject();
        try {
            final String proxyUrl ="157.227.4.10";
            final int port = 3128;
            Boolean enabledProxy =true;
            HttpHost httpHost = new HttpHost(proxyUrl,port);
            HttpClient httpClient =  HttpClientBuilder.create().setProxy(httpHost).build();
            HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory
                    = new HttpComponentsClientHttpRequestFactory(httpClient);
            RestTemplate restTemplate = null;
            if(enabledProxy != null && enabledProxy) {
                restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);
            } else {
                restTemplate = new RestTemplate();
                LoggerClass.appLayerLogger.info("disabled proxy");
            }
            AuditDetailsPayload auditDetailsPayload= new AuditDetailsPayload();
            auditDetailsPayload.setApiName("EMANDATE");
            auditDetailsPayload.setConversationId(String.valueOf(new Date().getTime()));
			/*auditDetailsPayload.setAppId(mandatePayload.getAppId());
			auditDetailsPayload.setMobileNumber(mandatePayload.getMobileNumber());
			auditDetailsPayload.setAppLeadId(mandatePayload.getAppLeadId());
			*/

            jsonObj.put("user_id","JOCATA_HL");// impsAuthDetails.getUserId());
            jsonObj.put("key", "rzNtQQTd5Gl0HqAn8dfIvg==" );//impsAuthDetails.getKey());
//			jsonObj.put("validity", impsAuthDetails.getValidity());

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Content-Type", "application/json");

            String pennyDropGenerateTokenUrl = "http://13.234.160.51:10010/api/user/token/generate";// tclAPIsProps.getPennydropGenerateTokenUrl();

            auditDetailsPayload.setPayload(jsonObj.toString());
            auditDetailsPayload.setRequestTime(new Date());
            auditDetailsPayload.setRequestUrl(pennyDropGenerateTokenUrl);


            LoggerClass.appLayerLogger.info("mandate token Url : "+pennyDropGenerateTokenUrl);
            HttpEntity<String> httpEntity = new HttpEntity<>(jsonObj.toString(), headers);
            String responseString = restTemplate.postForObject(pennyDropGenerateTokenUrl,httpEntity, String.class);
            LoggerClass.appLayerLogger.info("mandate token response : "+responseString);

            auditDetailsPayload.setResponseTime(new Date());
            auditDetailsPayload.setResponse(responseString);

            JSONObject jsonObject = null;

            if(responseString != null){
                jsonObject = new JSONObject(responseString);
                if(jsonObject.getBoolean("status")){
                    token = jsonObject.get("response").toString();
                }
            }

        } catch (JSONException e) {
            LoggerClass.appLayerLogger.error("JSON Exception :",e);
        }
        return token;
    }

}
