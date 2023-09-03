package com.expriment.service;

import com.expriment.entity.vo.*;
import com.expriment.utils.audit.entity.vo.RootResponse;
import org.springframework.http.ResponseEntity;

public interface OpenMandateService {

//    ResponseEntity<?> openNachApis(String leadId, String topUpLeadId);

    ResponseEntity<?> openNachApis(String leadId);

    RootResponse OpenMandeteOperation(EnquiryRequest enquiryRequest, String leadId);

//    EnquiryAPIResp enquiryApi(EnquiryRequest request);
//
//    Integer getBatchId();
//
//    MatrixResponse OpenMandate(MandatePayload request);
//
//    BlockApiRes blockAPI(BlockApiReq request);

    // Creating token
//    String createToken(MandatePayload mandatePayload);


    EnquiryAPIResp enquiryApi(EnquiryRequest request, String leadId);

//    Integer getBatchId(String leadId);

//    MatrixResponse OpenMandate(MandatePayload request, String leadId);

//    Integer getBatchId(String leadId);

    Integer getBatchId(String token, String leadId);

    MatrixResponse OpenMandate(MatrixRequest request, String leadId);

    BlockApiRes blockAPI(BlockApiReq request, String leadId);

    // Creating token
    String createToken(String leadId);

    // Creating token
//    String createToken(MandatePayload mandatePayload, String leadId);

    // Creating token
//    String createToken(String leadId);
}
