package com.expriment.service;

import com.expriment.Testing.DocTypeData;
import com.expriment.Testing.EmudraDocRequest;
import com.expriment.Testing.EmudraRequest;
import com.expriment.entity.vo.EmudraExternalResponse;
import com.expriment.utils.audit.entity.vo.RootResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmudraService {
    ResponseEntity<?> saveEmudraDocumentService(EmudraDocRequest emudraDocRequest);

//    RootResponse saveDocForEmudra(List<DocTypeData> emudraDocRequest, String leadId, String customerHash);

    RootResponse saveDocForEmudra(DocTypeData emudraDocRequest, String leadId, String customerHash);

    EmudraRequest creatingEmudraRequest(String leadId);

    EmudraExternalResponse eMudraService(String leadId, EmudraRequest emudraRequest);

    void jobsForEmudraAndDmsEmails(List<String> request);

//    SmsMailResponse sendMail(SmsMailPayload mailPayload);
}
