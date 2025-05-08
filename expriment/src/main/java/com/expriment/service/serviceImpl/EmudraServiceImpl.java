package com.expriment.service.serviceImpl;

import com.expriment.DAO.CDIOfferModuleDataDAO;
import com.expriment.DAO.Impl.AuditDetailsUtility;
import com.expriment.DAO.UploadDocDAO;
import com.expriment.Testing.*;
import com.expriment.entity.CDIOfferModule;
import com.expriment.entity.UploadDoc;
import com.expriment.entity.vo.AuditDetailsPayload;
import com.expriment.entity.vo.DocUploadPayload;
import com.expriment.entity.vo.EmudraExternalResponse;
import com.expriment.service.EmudraService;
import com.expriment.utils.AppProps;
import com.expriment.utils.FileResponse;
import com.expriment.utils.PdfGenerationUtils;
import com.expriment.utils.ProjectConstants;
import com.expriment.utils.audit.LoggerClass;
import com.expriment.utils.audit.entity.vo.DocUploadResponse;
import com.expriment.utils.audit.entity.vo.RootResponse;
import com.expriment.utils.audit.entity.vo.SmsMailPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class EmudraServiceImpl implements EmudraService {
    final Logger logger= LogManager.getLogger("EmudraServiceImpl");

    ObjectMapper objMapper = new ObjectMapper();

   /* @Autowired
    TCLServiceManager tclServiceManager;

    @Autowired
    DocUploadService docUploadService;
*/
//    @Autowired
//    EmailServices emailServices;

    @Autowired
    CDIOfferModuleDataDAO cdiOfferModuleDataDAO;

    @Autowired
    SfdcTdlDocDAO sfdcTdlDocDAO;

    @Autowired
    UploadDocDAO uploadDocDAO;

    @Autowired
    AuditDetailsUtility auditDetailsUtility;

    @Autowired
    PdfGenerationUtils pdfGenerationUtils;

//    @Autowired
    AppProps appProps;

    static String basePath="C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/";

    //    String basePath;
    @Override
    public ResponseEntity<?> saveEmudraDocumentService(EmudraDocRequest emudraDocRequest){

        String leadId = null,opportunityId,custmerHash;
        CDIOfferModule offerModule= new CDIOfferModule();
        RootResponse rootResponse= new RootResponse();
        RootResponse response= new RootResponse();

        try {
            /*save all document whatever received from Database*/
            custmerHash= emudraDocRequest.getCustomerHash();
            opportunityId= emudraDocRequest.getOpportunityId();

            if (custmerHash !=null )
                rootResponse.setCustomerHash(custmerHash);
            if(opportunityId !=null)
                rootResponse.setOpportunityId(opportunityId);

            rootResponse.setRetStatus(ProjectConstants.FAILURE);

            if (emudraDocRequest.getSource() !=null) {
                if (emudraDocRequest.getSource().trim().equalsIgnoreCase("tdl") || emudraDocRequest.getSource().trim().equalsIgnoreCase("sfdc")) {
                    LoggerClass.appLayerLogger.info("Source is "+emudraDocRequest.getSource());
                }
                else{
                    LoggerClass.appLayerLogger.info("Source is Not tdl or sfdc.");
                    rootResponse.setSource(emudraDocRequest.getSource());
                    rootResponse.setSysErrorMessage("Source is Not TDL or SFDC");
                    rootResponse.setSysErrorCode("04");
                    return new ResponseEntity<>(rootResponse, HttpStatus.OK);
                }
                rootResponse.setSource(emudraDocRequest.getSource());
            }
            else{
                LoggerClass.appLayerLogger.info("Source Not received.");
                rootResponse.setSource(emudraDocRequest.getSource());
                rootResponse.setSysErrorMessage("Source not received");
                rootResponse.setSysErrorCode("04");

                return new ResponseEntity<>(rootResponse, HttpStatus.OK);
            }

            if (custmerHash !=null && opportunityId == null){
                if (!StringUtils.isEmpty(custmerHash.trim())){
                    offerModule = cdiOfferModuleDataDAO.getOfferDataByCustomerHashNew(custmerHash);

                    if (offerModule == null){
                        rootResponse.setSysErrorMessage("Unique customer not found in db for given customerhash");
                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                        LoggerClass.appLayerLogger.info("TestCase 1");
                        return new ResponseEntity<>(rootResponse,HttpStatus.OK);
                    }
                    opportunityId=offerModule.getPlOpportunityId();
                }
                else{
                    rootResponse.setSysErrorMessage("Customer hash is not valid");
                    rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                    rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                    rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                    LoggerClass.appLayerLogger.info("TestCase 2");
                    return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                }
                opportunityId= offerModule.getPlOpportunityId();

            }
            else if(opportunityId != null && custmerHash ==null) {
                if (!StringUtils.isEmpty(opportunityId.trim())){
                    offerModule = cdiOfferModuleDataDAO.getOfferDataByPlOpportunityId(opportunityId);
                    custmerHash=offerModule.getCustomerHash();
                    if (offerModule == null){
                        rootResponse.setSysErrorMessage("Unique customer not found in db for given opportunityId");
                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                        rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                        rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                        LoggerClass.appLayerLogger.info("TestCase 3");
                        return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                    }
                }
                else{
                    rootResponse.setSysErrorMessage("opportunityId is not valid");
                    rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                    rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                    rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                    LoggerClass.appLayerLogger.info("TestCase 4");
                    return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                }
                custmerHash= offerModule.getCustomerHash();  /*In offerMudel customer plleadId, customerHash, OpportutnityId ,getEmailAddress must be there*/
            }
            else if (custmerHash != null && opportunityId != null){

                if (!StringUtils.isEmpty(custmerHash.trim()) && StringUtils.isEmpty(opportunityId.trim())){
                    offerModule = cdiOfferModuleDataDAO.getOfferDataByCustomerHashNew(custmerHash);

                    if (offerModule == null){
                        rootResponse.setSysErrorMessage("Unique customer not found in db for given customerHash");
                        rootResponse.setRetStatus(ProjectConstants.FAILURE);
                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                        LoggerClass.appLayerLogger.info("TestCase 51");
                        return new ResponseEntity<>(rootResponse,HttpStatus.OK);

                    }else {
                        rootResponse.setRetStatus(ProjectConstants.SUCCESS);
                    }

                    LoggerClass.appLayerLogger.info("TestCase 5");
                    opportunityId= offerModule.getPlOpportunityId();
                }
                else if(StringUtils.isEmpty(custmerHash.trim()) && !StringUtils.isEmpty(opportunityId.trim())){
                    offerModule = cdiOfferModuleDataDAO.getOfferDataByPlOpportunityId(opportunityId);

                    if (offerModule == null){
                        rootResponse.setSysErrorMessage("Unique customer not found in db for given OpportunityId");
                        rootResponse.setRetStatus(ProjectConstants.FAILURE);
                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                        LoggerClass.appLayerLogger.info("TestCase 051");
                        return new ResponseEntity<>(rootResponse,HttpStatus.OK);
                    }else {
                        rootResponse.setRetStatus(ProjectConstants.SUCCESS);
                    }

                    LoggerClass.appLayerLogger.info("TestCase 6");

                    custmerHash= offerModule.getCustomerHash();  /*In offerMudel customer plleadId, customerHash, OpportutnityId ,getEmailAddress must be there*/

                }
                else if (StringUtils.isEmpty(custmerHash.trim()) && StringUtils.isEmpty(opportunityId.trim())){
                    LoggerClass.appLayerLogger.info("customerHash not present in request");
                    rootResponse.setSysErrorMessage("customerHash and OpportunityId both are not valid either null or empty");
                    rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                    rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                    rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                    LoggerClass.appLayerLogger.info("TestCase 7");
                    return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                }
                else if(!StringUtils.isEmpty(custmerHash.trim()) && !StringUtils.isEmpty(opportunityId.trim())){
                    offerModule =cdiOfferModuleDataDAO.getOfferDataByCustomerHashNew(custmerHash); // in uat getOfferDataByCustomerHash

                    if (!StringUtils.isEmpty(offerModule)){
                        if (opportunityId.trim().equals(offerModule.getPlOpportunityId())){
                            rootResponse.setRetStatus(ProjectConstants.SUCCESS);
                        }else{


                            LoggerClass.appLayerLogger.info("CustomerHash and OpportunityId not matching with same customer.");
                            rootResponse.setSysErrorMessage("customerHash and opportunityId both are not matching with same customer");
                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                            rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                            rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                            LoggerClass.appLayerLogger.info("TestCase 8");
                            return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                        }
                    }
                    else if(StringUtils.isEmpty(offerModule)){
                        offerModule= cdiOfferModuleDataDAO.getOfferDataByPlOpportunityId(opportunityId);

                        if(!StringUtils.isEmpty(offerModule) ){
                            LoggerClass.appLayerLogger.info("OpportunityId and customerHash not matching with same customer.TestCase 9");
                            rootResponse.setSysErrorMessage("OpportunityId and customerHash both are not matching with same customer");
                            custmerHash=offerModule.getCustomerHash();
                        }
                        else if (StringUtils.isEmpty(offerModule)){
                            LoggerClass.appLayerLogger.info("OpportunityId and CustomerHash both are not valid.");
                            rootResponse.setSysErrorMessage("OpportunityId and CustomerHash both are not valid");
                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                            rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                            rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                            LoggerClass.appLayerLogger.info("TestCase 11");
                            return new ResponseEntity<>(rootResponse, HttpStatus.OK);
                        }
/*
                    }

                }

            }

        else{
                LoggerClass.appLayerLogger.info("customerHash not present in request");
                rootResponse.setSysErrorMessage("customerHash And OpportunityId both are missing");
                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                LoggerClass.appLayerLogger.info("TestCase 10");
                return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
            }
            if (offerModule.getPlLeadId() !=null)
                leadId= String.valueOf(offerModule.getPlLeadId());

            rootResponse.setCustomerHash(custmerHash);
            rootResponse.setOpportunityId(opportunityId);
            rootResponse.setLeadId(leadId);

            LoggerClass.appLayerLogger.info("customerhash {}, opportunityid {} and leadid {}",custmerHash,opportunityId,leadId);

            List<DocTypeData> docTypeDataList= emudraDocRequest.getDocTypeData();
            if((custmerHash !=null || opportunityId !=null) && docTypeDataList.size() >0 ){
                for (DocTypeData doc :docTypeDataList){

                    if (doc.getDocType() != null && !StringUtils.isEmpty(doc.getDocType().trim()) && doc.getDoc() != null && !StringUtils.isEmpty(doc.getDoc().trim())){
                        switch (doc.getDocType().toLowerCase().trim()){
                            case "sl":
                                break;
                            case "tc":
                                break;
                            case "la":
                                break;
                            default:
                                rootResponse.setSysErrorMessage("docType not valid");
                                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                                return  new ResponseEntity<>(rootResponse,HttpStatus.OK);

                        }

                    }else {
                        if (doc.getDocType() == null || StringUtils.isEmpty(doc.getDocType().trim())){
                            rootResponse.setSysErrorMessage("DocType not valid either NULL or Empty");
                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                            return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                        }else if (doc.getDoc() == null || StringUtils.isEmpty(doc.getDoc().trim())){
                            rootResponse.setSysErrorMessage("Document not valid either NULL or Empty");
                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                            return new ResponseEntity<>(rootResponse,HttpStatus.OK);
                        }
                    }
                }
                response = saveDocForEmudra(docTypeDataList, leadId, custmerHash);
                LoggerClass.appLayerLogger.info("Saved docuement with Status {}", response.getRetStatus());

            }else{
                rootResponse.setSysErrorMessage("DocTypeData is not available");
                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
            }
            rootResponse.setSysErrorMessage("Your Document is saved");
            rootResponse.setOpportunityId(offerModule.getPlOpportunityId());
            rootResponse.setRetStatus(ProjectConstants.SUCCESS);

            if (rootResponse.getRetStatus().equalsIgnoreCase(ProjectConstants.SUCCESS)){
                String finalLeadId = leadId;
                LoggerClass.appLayerLogger.info("Calling Async call for leadId {}",leadId);

//                CompletableFuture.runAsync(() ->
                dgoBackEndProcess(finalLeadId);
                LoggerClass.appLayerLogger.info("Async call ended for leadId {}",leadId);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LoggerClass.appLayerLogger.info("saveEmudraDocumentService ended.");
        }

        return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
    }

 public ResponseEntity<?> saveEmudraDocumentService1(EmudraDocRequest emudraDocRequest){

        String leadId = null,opportunityId,custmerHash;
        CDIOfferModule offerModule= new CDIOfferModule();
        RootResponse rootResponse= new RootResponse();
        RootResponse response= new RootResponse();

        try {
           */
/*save all document whatever received from Database*//*

            custmerHash= emudraDocRequest.getCustomerHash();
            opportunityId= emudraDocRequest.getOpportunityId();

            if (custmerHash !=null )
                rootResponse.setCustomerHash(custmerHash);
            if(opportunityId !=null)
                rootResponse.setOpportunityId(opportunityId);

            rootResponse.setRetStatus(ProjectConstants.FAILURE);

            if (emudraDocRequest.getSource() !=null) {
                if (emudraDocRequest.getSource().trim().equalsIgnoreCase("tdl") || emudraDocRequest.getSource().trim().equalsIgnoreCase("sfdc")) {
                    LoggerClass.appLayerLogger.info("Source is "+emudraDocRequest.getSource());
                }
                else{
                    LoggerClass.appLayerLogger.info("Source is Not tdl or sfdc.");
                    rootResponse.setSource(emudraDocRequest.getSource());
                    rootResponse.setSysErrorMessage("Source is Not TDL or SFDC");
                    rootResponse.setSysErrorCode("04");
                    return new ResponseEntity<>(rootResponse, HttpStatus.OK);
                }
                rootResponse.setSource(emudraDocRequest.getSource());
            }
            else{
                LoggerClass.appLayerLogger.info("Source Not received.");
                rootResponse.setSource(emudraDocRequest.getSource());
                rootResponse.setSysErrorMessage("Source not received");
                rootResponse.setSysErrorCode("04");

                return new ResponseEntity<>(rootResponse, HttpStatus.OK);
            }

            if (custmerHash !=null && opportunityId == null){
                if (!StringUtils.isEmpty(custmerHash.trim())){
                    offerModule = cdiOfferModuleDataDAO.getOfferDataByCustomerHashNew(custmerHash);

                    if (offerModule == null){
                        rootResponse.setSysErrorMessage("Unique customer not found in db for given customerhash");
                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                        LoggerClass.appLayerLogger.info("TestCase 1");
                        return new ResponseEntity<>(rootResponse,HttpStatus.OK);
                    }
                    opportunityId=offerModule.getPlOpportunityId();
                }
                else{
                    rootResponse.setSysErrorMessage("Customer hash is not valid");
                    rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                    rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                    rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                    LoggerClass.appLayerLogger.info("TestCase 2");
                    return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                }
                opportunityId= offerModule.getPlOpportunityId();

            }
            else if(opportunityId != null && custmerHash ==null) {
                if (!StringUtils.isEmpty(opportunityId.trim())){
                    offerModule = cdiOfferModuleDataDAO.getOfferDataByPlOpportunityId(opportunityId);
                    custmerHash=offerModule.getCustomerHash();
                    if (offerModule == null){
                        rootResponse.setSysErrorMessage("Unique customer not found in db for given opportunityId");
                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                        rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                        rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                        LoggerClass.appLayerLogger.info("TestCase 3");
                        return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                    }
                }
                else{
                    rootResponse.setSysErrorMessage("opportunityId is not valid");
                    rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                    rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                    rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                    LoggerClass.appLayerLogger.info("TestCase 4");
                    return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                }
                custmerHash= offerModule.getCustomerHash();  */
/*In offerMudel customer plleadId, customerHash, OpportutnityId ,getEmailAddress must be there*//*

            }
            else if (custmerHash != null && opportunityId != null){

                if (!StringUtils.isEmpty(custmerHash.trim()) && StringUtils.isEmpty(opportunityId.trim())){
                    offerModule = cdiOfferModuleDataDAO.getOfferDataByCustomerHashNew(custmerHash);

                    if (offerModule == null){
                        rootResponse.setSysErrorMessage("Unique customer not found in db for given customerHash");
                        rootResponse.setRetStatus(ProjectConstants.FAILURE);
                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                        LoggerClass.appLayerLogger.info("TestCase 51");
                        return new ResponseEntity<>(rootResponse,HttpStatus.OK);

                    }else {
                        rootResponse.setRetStatus(ProjectConstants.SUCCESS);
                    }

                    LoggerClass.appLayerLogger.info("TestCase 5");
                    opportunityId= offerModule.getPlOpportunityId();
                }
                else if(StringUtils.isEmpty(custmerHash.trim()) && !StringUtils.isEmpty(opportunityId.trim())){
                    offerModule = cdiOfferModuleDataDAO.getOfferDataByPlOpportunityId(opportunityId);

                    if (offerModule == null){
                        rootResponse.setSysErrorMessage("Unique customer not found in db for given OpportunityId");
                        rootResponse.setRetStatus(ProjectConstants.FAILURE);
                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                        LoggerClass.appLayerLogger.info("TestCase 051");
                        return new ResponseEntity<>(rootResponse,HttpStatus.OK);
                    }else {
                        rootResponse.setRetStatus(ProjectConstants.SUCCESS);
                    }

                    LoggerClass.appLayerLogger.info("TestCase 6");

                    custmerHash= offerModule.getCustomerHash();  */
/*In offerMudel customer plleadId, customerHash, OpportutnityId ,getEmailAddress must be there*//*


                }
                else if (StringUtils.isEmpty(custmerHash.trim()) && StringUtils.isEmpty(opportunityId.trim())){
                    LoggerClass.appLayerLogger.info("customerHash not present in request");
                    rootResponse.setSysErrorMessage("customerHash and OpportunityId both are not valid either null or empty");
                    rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                    rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                    rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                    LoggerClass.appLayerLogger.info("TestCase 7");
                    return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                }
                else if(!StringUtils.isEmpty(custmerHash.trim()) && !StringUtils.isEmpty(opportunityId.trim())){
                    offerModule =cdiOfferModuleDataDAO.getOfferDataByCustomerHashNew(custmerHash); // in uat getOfferDataByCustomerHash

                    if (!StringUtils.isEmpty(offerModule)){
                        if (opportunityId.trim().equals(offerModule.getPlOpportunityId())){
                            rootResponse.setRetStatus(ProjectConstants.SUCCESS);
                        }else{


                            LoggerClass.appLayerLogger.info("CustomerHash and OpportunityId not matching with same customer.");
                            rootResponse.setSysErrorMessage("customerHash and opportunityId both are not matching with same customer");
                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                            rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                            rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                            LoggerClass.appLayerLogger.info("TestCase 8");
                            return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                        }
                    }
                    else if(StringUtils.isEmpty(offerModule)){
                        offerModule= cdiOfferModuleDataDAO.getOfferDataByPlOpportunityId(opportunityId);

                        if(!StringUtils.isEmpty(offerModule) ){
                            LoggerClass.appLayerLogger.info("OpportunityId and customerHash not matching with same customer.TestCase 9");
                            rootResponse.setSysErrorMessage("OpportunityId and customerHash both are not matching with same customer");
                            custmerHash=offerModule.getCustomerHash();
                        }
                        else if (StringUtils.isEmpty(offerModule)){
                            LoggerClass.appLayerLogger.info("OpportunityId and CustomerHash both are not valid.");
                            rootResponse.setSysErrorMessage("OpportunityId and CustomerHash both are not valid");
                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                            rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                            rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                            LoggerClass.appLayerLogger.info("TestCase 11");
                            return new ResponseEntity<>(rootResponse, HttpStatus.OK);
                        }
*/
                    }

                }

            }
            else{
                LoggerClass.appLayerLogger.info("customerHash not present in request");
                rootResponse.setSysErrorMessage("customerHash And OpportunityId both are missing");
                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                LoggerClass.appLayerLogger.info("TestCase 10");
                return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
            }
            if (offerModule.getPlLeadId() !=null)
                leadId= String.valueOf(offerModule.getPlLeadId());

            rootResponse.setCustomerHash(custmerHash);
            rootResponse.setOpportunityId(opportunityId);
            rootResponse.setLeadId(leadId);

            LoggerClass.appLayerLogger.info("customerhash {}, opportunityid {} and leadid {}",custmerHash,opportunityId,leadId);

            List<DocTypeData> docTypeDataList= emudraDocRequest.getDocTypeData();
            if((custmerHash !=null || opportunityId !=null) && docTypeDataList.size() >0 ){
                for (DocTypeData doc :docTypeDataList){

                    if (doc.getDocType() != null && !StringUtils.isEmpty(doc.getDocType().trim()) && doc.getDoc() != null && !StringUtils.isEmpty(doc.getDoc().trim())){
                        switch (doc.getDocType().toLowerCase().trim()){
                            case "sl":
                                break;
                            case "tc":
                                break;
                            case "la":
                                break;
                            default:
                                rootResponse.setSysErrorMessage("docType not valid");
                                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                                return  new ResponseEntity<>(rootResponse,HttpStatus.OK);

                        }

                    }else {
                        if (doc.getDocType() == null || StringUtils.isEmpty(doc.getDocType().trim())){
                            rootResponse.setSysErrorMessage("DocType not valid either NULL or Empty");
                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                            return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                        }else if (doc.getDoc() == null || StringUtils.isEmpty(doc.getDoc().trim())){
                            rootResponse.setSysErrorMessage("Document not valid either NULL or Empty");
                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                            return new ResponseEntity<>(rootResponse,HttpStatus.OK);
                        }
                    }
                }
                response = saveDocForEmudra(docTypeDataList, leadId, custmerHash);
                LoggerClass.appLayerLogger.info("Saved docuement with Status {}", response.getRetStatus());

            }else{
                rootResponse.setSysErrorMessage("DocTypeData is not available");
                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
            }
            rootResponse.setSysErrorMessage("Your Document is saved");
            rootResponse.setOpportunityId(offerModule.getPlOpportunityId());
            rootResponse.setRetStatus(ProjectConstants.SUCCESS);

            if (rootResponse.getRetStatus().equalsIgnoreCase(ProjectConstants.SUCCESS)){
                String finalLeadId = leadId;
                LoggerClass.appLayerLogger.info("Calling Async call for leadId {}",leadId);

//                CompletableFuture.runAsync(() ->
                        dgoBackEndProcess(finalLeadId);
                LoggerClass.appLayerLogger.info("Async call ended for leadId {}",leadId);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LoggerClass.appLayerLogger.info("saveEmudraDocumentService ended.");
        }

        return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
    }

   /* public ResponseEntity<?> saveEmudraDocumentService1(EmudraDocRequest emudraDocRequest){

        String leadId = null,opportunityId,custmerHash;
        CDIOfferModule offerModule= new CDIOfferModule();
        RootResponse rootResponse= new RootResponse();
        RootResponse response= new RootResponse();

        try {
            *//*save all document whatever received from Database*//*
            custmerHash= emudraDocRequest.getCustomerHash();
            opportunityId= emudraDocRequest.getOpportunityId();

            if (custmerHash !=null )
                rootResponse.setCustomerHash(custmerHash);
            if(opportunityId !=null)
                rootResponse.setOpportunityId(opportunityId);

            rootResponse.setRetStatus(ProjectConstants.API_FAIL);

            if (emudraDocRequest.getSource() !=null) {
                if (emudraDocRequest.getSource().trim().equalsIgnoreCase("tdl") || emudraDocRequest.getSource().trim().equalsIgnoreCase("sfdc")) {
                    LoggerClass.appLayerLogger.info("Source is "+emudraDocRequest.getSource());
                }
                else{
                    LoggerClass.appLayerLogger.info("Source is Not tdl or sfdc.");
                    rootResponse.setSource(emudraDocRequest.getSource());
                    rootResponse.setSysErrorMessage("Source is Not TDL or SFDC");
                    rootResponse.setSysErrorCode("04");
                    return new ResponseEntity<>(rootResponse, HttpStatus.OK);
                }
                rootResponse.setSource(emudraDocRequest.getSource());
            }
            else{
                LoggerClass.appLayerLogger.info("Source Not received.");
                rootResponse.setSource(emudraDocRequest.getSource());
                rootResponse.setSysErrorMessage("Source not received");
                rootResponse.setSysErrorCode("04");

                return new ResponseEntity<>(rootResponse, HttpStatus.OK);
            }

            if (custmerHash !=null && opportunityId == null){
                if (!StringUtils.isEmpty(custmerHash.trim())){
                    offerModule = cdiOfferModuleDataDAO.getOfferDataByCustomerHashNew(custmerHash);

                    if (offerModule == null){
//                        rootResponse.setSysErrorMessage("Customer hash is not present in Db");
                        rootResponse.setSysErrorMessage("Unique customer not found in db for given customerhash");
                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                        LoggerClass.appLayerLogger.info("TestCase 1");
                        return new ResponseEntity<>(rootResponse,HttpStatus.OK);
                    }
                    opportunityId=offerModule.getPlOpportunityId();
                }
                else{
                    rootResponse.setSysErrorMessage("Customer hash is not valid");
                    rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                    rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                    rootResponse.setOpportunityId((emudraDocRequest.getOpportunityId()));
                    LoggerClass.appLayerLogger.info("TestCase 2");
                    return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                }
                opportunityId= offerModule.getPlOpportunityId();

            }
            else if(opportunityId != null && custmerHash ==null) {
                if (!StringUtils.isEmpty(opportunityId.trim())){
                    offerModule = cdiOfferModuleDataDAO.getOfferDataByPlOpportunityId(opportunityId);

                    if (offerModule == null){
//                        rootResponse.setSysErrorMessage("opportunityId is not present in Db  ");
                        rootResponse.setSysErrorMessage("Unique customer not found in db for given opportunityId");
                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                        rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                        rootResponse.setOpportunityId((emudraDocRequest.getOpportunityId()));
                        LoggerClass.appLayerLogger.info("TestCase 3");
                        return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                    }
                    custmerHash=offerModule.getCustomerHash();
                }
                else{
                    rootResponse.setSysErrorMessage("opportunityId is not valid");
                    rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                    rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                    rootResponse.setOpportunityId((emudraDocRequest.getOpportunityId()));
                    LoggerClass.appLayerLogger.info("TestCase 4");
                    return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                }
                custmerHash= offerModule.getCustomerHash();  *//*In offerMudel customer plleadId, customerHash, OpportutnityId ,getEmailAddress must be there*//*
            }
            else if (custmerHash != null && opportunityId != null){

                if (!StringUtils.isEmpty(custmerHash.trim()) && StringUtils.isEmpty(opportunityId.trim())){
//                    if (!StringUtils.isEmpty(custmerHash.trim())){
                    offerModule = cdiOfferModuleDataDAO.getOfferDataByCustomerHashNew(custmerHash);

                    if (offerModule == null){
//                            rootResponse.setSysErrorMessage("OpportunityId and CustomerHash are not valid either null or empty");
                        rootResponse.setSysErrorMessage("Unique customer not found in db for given customerHash");
                        rootResponse.setRetStatus(ProjectConstants.FAILURE);
                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                        LoggerClass.appLayerLogger.info("TestCase 51");
                        return new ResponseEntity<>(rootResponse,HttpStatus.OK);

                    }else {
//                            rootResponse.setSysErrorMessage("OpportunityId is not valid either null or empty");
                        rootResponse.setRetStatus(ProjectConstants.SUCCESS);
                    }
//                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
//                            rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
//                            rootResponse.setOpportunityId((emudraDocRequest.getOpportunityId());
                    LoggerClass.appLayerLogger.info("TestCase 5");
//                            return new ResponseEntity<>(rootResponse,HttpStatus.OK);
                    opportunityId= offerModule.getPlOpportunityId();
                }
                else if(StringUtils.isEmpty(custmerHash.trim()) && !StringUtils.isEmpty(opportunityId.trim())){
                    offerModule = cdiOfferModuleDataDAO.getOfferDataByPlOpportunityId(opportunityId);

                    if (offerModule == null){
                        rootResponse.setSysErrorMessage("Unique customer not found in db for given OpportunityId");
                        rootResponse.setRetStatus(ProjectConstants.FAILURE);
                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                        LoggerClass.appLayerLogger.info("TestCase 051");
                        return new ResponseEntity<>(rootResponse,HttpStatus.OK);
                    }else {
//                            rootResponse.setSysErrorMessage("Customer Hash is not valid either null or empty");
                        rootResponse.setRetStatus(ProjectConstants.SUCCESS);
                    }
//                        rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
//                        rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
//                        rootResponse.setOpportunityId((emudraDocRequest.getOpportunityId());
                    LoggerClass.appLayerLogger.info("TestCase 6");
//                        return  new ResponseEntity<>(rootResponse,HttpStatus.OK);

                    custmerHash= offerModule.getCustomerHash();  *//*In offerMudel customer plleadId, customerHash, OpportutnityId ,getEmailAddress must be there*//*

                }
                else if (StringUtils.isEmpty(custmerHash.trim()) && StringUtils.isEmpty(opportunityId.trim())){
                    LoggerClass.appLayerLogger.info("customerHash not present in request");
                    rootResponse.setSysErrorMessage("customerHash and OpportunityId both are not valid either null or empty");
                    rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                    rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                    rootResponse.setOpportunityId((emudraDocRequest.getOpportunityId()));
                    LoggerClass.appLayerLogger.info("TestCase 7");
                    return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                }
                else if(!StringUtils.isEmpty(custmerHash.trim()) && !StringUtils.isEmpty(opportunityId.trim())){
                    offerModule =cdiOfferModuleDataDAO.getOfferDataByCustomerHash(custmerHash);

                    if (!StringUtils.isEmpty(offerModule)){
                        if (opportunityId.trim().equals(offerModule.getPlOpportunityId())){
                            rootResponse.setRetStatus(ProjectConstants.SUCCESS);
                        }else{


                            LoggerClass.appLayerLogger.info("CustomerHash and OpportunityId not matching with same customer.");
                            rootResponse.setSysErrorMessage("customerHash and opportunityId both are not matching with same customer");
                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                            rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                            rootResponse.setOpportunityId(emudraDocRequest.getOpportunityId());
                            LoggerClass.appLayerLogger.info("TestCase 8");
                            return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                        }
                    }
                    else if(StringUtils.isEmpty(offerModule)){
                        offerModule= cdiOfferModuleDataDAO.getOfferDataByPlOpportunityId(opportunityId);

                        if(!StringUtils.isEmpty(offerModule) ){
                            LoggerClass.appLayerLogger.info("OpportunityId and customerHash not matching with same customer.TestCase 9");
                            rootResponse.setSysErrorMessage("OpportunityId and customerHash both are not matching with same customer");
//                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
//                            rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
//                            rootResponse.setOpportunityId((emudraDocRequest.getOpportunityId());
//                            LoggerClass.appLayerLogger.info("TestCase 9");
//                            return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                            custmerHash=offerModule.getCustomerHash();
                        }
                        else if (StringUtils.isEmpty(offerModule)){
                            LoggerClass.appLayerLogger.info("OpportunityId and CustomerHash both are not valid.");
                            rootResponse.setSysErrorMessage("OpportunityId and CustomerHash both are not valid");
                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                            rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                            rootResponse.setOpportunityId((emudraDocRequest.getOpportunityId()));
                            LoggerClass.appLayerLogger.info("TestCase 11");
                            return new ResponseEntity<>(rootResponse, HttpStatus.OK);
                        }
                    }

                }

            }
            else{
                LoggerClass.appLayerLogger.info("customerHash not present in request");
                rootResponse.setSysErrorMessage("customerHash And OpportunityId both are missing");
                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_HashOrOppId_ERROR_CODE);
                rootResponse.setCustomerHash(emudraDocRequest.getCustomerHash());
                rootResponse.setOpportunityId((emudraDocRequest.getOpportunityId()));
                LoggerClass.appLayerLogger.info("TestCase 10");
                return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
            }
            if (offerModule.getPlLeadId() !=null)
                leadId= String.valueOf(offerModule.getPlLeadId());

            rootResponse.setCustomerHash(custmerHash);
            rootResponse.setOpportunityId((opportunityId));
            rootResponse.setLeadId(leadId);
//            FileResponse fileResponse = pdfGenerationUtils.convertBase64StringToFile(emudraDocRequest,"esignDmsRequest" , "1234");

            LoggerClass.appLayerLogger.info("customerhash {}, opportunityid {} and leadid {}",custmerHash,opportunityId,leadId);

            List<DocTypeData> docTypeDataList= emudraDocRequest.getDocTypeData();
            if((custmerHash !=null || opportunityId !=null) && docTypeDataList.size() >0 ){
                for (DocTypeData doc :docTypeDataList){

                    if (doc.getDocType() != null && !StringUtils.isEmpty(doc.getDocType().trim()) && doc.getDoc() != null && !StringUtils.isEmpty(doc.getDoc().trim())){
                        switch (doc.getDocType().toLowerCase().trim()){
                            case "sl":
                                break;
                            case "tc":
                                break;
                            case "la":
                                break;
                            default:
                                rootResponse.setSysErrorMessage("docType not valid");
                                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                                return  new ResponseEntity<>(rootResponse,HttpStatus.OK);

                        }

                    }else {
                        if (doc.getDocType() == null || StringUtils.isEmpty(doc.getDocType().trim())){
                            rootResponse.setSysErrorMessage("DocType not valid either NULL or Empty");
                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                            return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
                        }else if (doc.getDoc() == null || StringUtils.isEmpty(doc.getDoc().trim())){
                            rootResponse.setSysErrorMessage("Document not valid either NULL or Empty");
                            rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                            return new ResponseEntity<>(rootResponse,HttpStatus.OK);
                        }
                    }
                }
                response = saveDocForEmudra(docTypeDataList, leadId, custmerHash);
                LoggerClass.appLayerLogger.info("Saved docuement with Status {}", response.getRetStatus());

            }else{
                rootResponse.setSysErrorMessage("DocTypeData is not available");
                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
            }
            rootResponse.setSysErrorMessage("");
            rootResponse.setOpportunityId((offerModule.getPlOpportunityId()));
            rootResponse.setRetStatus(ProjectConstants.SUCCESS);

            if (rootResponse.getRetStatus().equalsIgnoreCase(ProjectConstants.SUCCESS)){
                String finalLeadId = leadId;
                LoggerClass.appLayerLogger.info("Calling Async call for leadId {}",leadId);

//                CompletableFuture.runAsync(() ->
                dgoBackEndProcess(finalLeadId);
                LoggerClass.appLayerLogger.info("Async call ended for leadId {}",leadId);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LoggerClass.appLayerLogger.info("saveEmudraDocumentService ended.");
        }

        return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
    }
*/
   private void dgoBackEndProcess(String plLeadId){
       String leadId;
       String base64KfsDoc =null;
       boolean kfsAvailable = false;
       SfdcTdlDocResponse sfdcTdlDocResponse =new SfdcTdlDocResponse();
       EmudraRequest emudraRequest= new EmudraRequest();
       EmudraExternalResponse emudraExternalResponse = new EmudraExternalResponse();

       try {
           /*check all 4 document is available then hit external service for emudra*/
           leadId=plLeadId;
           List<UploadDoc> uploadDoc=uploadDocDAO.getUploadDocResByPlleadIdAndDocType(leadId, ProjectConstants.CUSTOMER_KFS_GENERATION_DOC_TYPE);
           sfdcTdlDocResponse= sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));
           if (uploadDoc.size() >0)
               base64KfsDoc =uploadDoc.get(0).getBase64FormattedData();
           LoggerClass.appLayerLogger.info("UploadDoc Size is {}", uploadDoc.size());


           if(!uploadDoc.isEmpty()  && base64KfsDoc != null){
               kfsAvailable = true;
               logger.info("kfsAvailable {} for LeadId {}",kfsAvailable , leadId);
           }

           if(!ObjectUtils.isEmpty(sfdcTdlDocResponse) && sfdcTdlDocResponse.getSfdcDoc() != null && !sfdcTdlDocResponse.getSfdcDoc().isEmpty()
                   && sfdcTdlDocResponse.getTdlDocTnc() != null && !sfdcTdlDocResponse.getTdlDocTnc().isEmpty()
                   && sfdcTdlDocResponse.getTdlDocLoanAgr() != null && !sfdcTdlDocResponse.getTdlDocLoanAgr().isEmpty() &&
                   kfsAvailable) {
               if(sfdcTdlDocResponse.getEmudraStatus() == null || !sfdcTdlDocResponse.getEmudraStatus().equals(ProjectConstants.SUCCESS)) {

                   makeEsignPDF(Integer.valueOf(leadId),base64KfsDoc);
                   logger.info("calling creatingEmudraRequest for esigned doc pushing for leadId {}", leadId);
                   emudraRequest = creatingEmudraRequest(leadId);

                   if (emudraRequest.getFile_content_string() !=null) {
                       logger.info("calling EmudraService ...");
                       emudraExternalResponse = eMudraService(leadId, emudraRequest);
                   }

                   if (emudraExternalResponse.getSigned_file_content() != null) {
                       logger.info("Pusing Document to DMS and Emails");
                       pushEmudraToDMSAndEmail(leadId, emudraExternalResponse.getSigned_file_content());
                   }

               }else {
                   logger.info("Emudra Esigned document is already available.");
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

/*
    private void dgoBackEndProcess1(String plLeadId){
        String leadId;
        String base64KfsDoc =null;
        boolean kfsAvailable = false;
        SfdcTdlDocResponse sfdcTdlDocResponse =new SfdcTdlDocResponse();
        EmudraRequest emudraRequest= new EmudraRequest();
        EmudraExternalResponse emudraExternalResponse = new EmudraExternalResponse();

        try {
            */
/*check all 4 document is available then hit external service for emudra*//*

            leadId=plLeadId;
            List<UploadDoc> uploadDoc=uploadDocDAO.getUploadDocResByPlleadIdAndDocType(leadId, ProjectConstants.CUSTOMER_KFS_GENERATION_DOC_TYPE);
            sfdcTdlDocResponse= sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));
            if (uploadDoc.size() >0)
                base64KfsDoc =uploadDoc.get(0).getBase64FormattedData();
            logger.info("UploadDoc Size is {}", uploadDoc.size());


           if(!uploadDoc.isEmpty()  && base64KfsDoc != null){
               kfsAvailable = true;
               logger.info("kfsAvailable {} for LeadId {}",kfsAvailable , leadId);
           }

           if(!ObjectUtils.isEmpty(sfdcTdlDocResponse) && sfdcTdlDocResponse.getSfdcDoc() != null && !sfdcTdlDocResponse.getSfdcDoc().isEmpty()
                   && sfdcTdlDocResponse.getTdlDocTnc() != null && !sfdcTdlDocResponse.getTdlDocTnc().isEmpty()
                   && sfdcTdlDocResponse.getTdlDocLoanAgr() != null && !sfdcTdlDocResponse.getTdlDocLoanAgr().isEmpty() &&
                   kfsAvailable) {
               if(sfdcTdlDocResponse.getEmudraStatus() == null || !sfdcTdlDocResponse.getEmudraStatus().equals(ProjectConstants.SUCCESS)) {

                   makeEsignPDF(Integer.valueOf(leadId),base64KfsDoc);
                   logger.info("calling creatingEmudraRequest for esigned doc pushing for leadId {}", leadId);
                   emudraRequest = creatingEmudraRequest(leadId);

                   if (emudraRequest.getFile_content_string() !=null) {
                       logger.info("calling EmudraService ...");
                       emudraExternalResponse = eMudraService(leadId, emudraRequest);
                   }

                   if (emudraExternalResponse.getSigned_file_content() != null) {
                       logger.info("Pusing Document to DMS and Emails");
                       pushEmudraToDMSAndEmail(leadId, emudraExternalResponse.getSigned_file_content());
                   }

               }else {
                   logger.info("Emudra Esigned document is already available.");
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

/*
    private void dgoBackEndProcess1(String plLeadId){
        String leadId;
        String base64KfsDoc =null;
        boolean kfsAvailable = false;
        SfdcTdlDocResponse sfdcTdlDocResponse =new SfdcTdlDocResponse();
        EmudraRequest emudraRequest= new EmudraRequest();
        EmudraExternalResponse emudraExternalResponse = new EmudraExternalResponse();

        try {
            */
/*check all 4 document is available then hit external service for emudra*//*

            leadId=plLeadId;
            List<UploadDoc> uploadDoc=uploadDocDAO.getUploadDocResByPlleadIdAndDocType(leadId, ProjectConstants.CUSTOMER_KFS_GENERATION_DOC_TYPE);
            sfdcTdlDocResponse= sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));
            if (uploadDoc.size() >0)
                base64KfsDoc =uploadDoc.get(0).getBase64FormattedData();
            logger.info("UploadDoc Size is {}", uploadDoc.size());


           if(!uploadDoc.isEmpty()  && base64KfsDoc != null){
               kfsAvailable = true;
               LoggerClass.appLayerLogger.info("kfsAvailable {} for LeadId {}",kfsAvailable , leadId);
           }

           if(!ObjectUtils.isEmpty(sfdcTdlDocResponse) && sfdcTdlDocResponse.getSfdcDoc() != null && !sfdcTdlDocResponse.getSfdcDoc().isEmpty()
                   && sfdcTdlDocResponse.getTdlDocTnc() != null && !sfdcTdlDocResponse.getTdlDocTnc().isEmpty()
                   && sfdcTdlDocResponse.getTdlDocLoanAgr() != null && !sfdcTdlDocResponse.getTdlDocLoanAgr().isEmpty() &&
                   kfsAvailable) {
               if(sfdcTdlDocResponse.getEmudraStatus() == null || !sfdcTdlDocResponse.getEmudraStatus().equals(ProjectConstants.SUCCESS)) {

                   makeEsignPDF(Integer.valueOf(leadId),base64KfsDoc);
                   LoggerClass.appLayerLogger.info("calling creatingEmudraRequest for esigned doc pushing for leadId {}", leadId);
                   emudraRequest = creatingEmudraRequest(leadId);

                   if (emudraRequest.getFile_content_string() !=null) {
                       LoggerClass.appLayerLogger.info("calling EmudraService ...");
                       emudraExternalResponse = eMudraService(leadId, emudraRequest);
                   }

                   if (emudraExternalResponse.getSigned_file_content() != null) {
                       LoggerClass.appLayerLogger.info("Pusing Document to DMS and Emails");
                       pushEmudraToDMSAndEmail(leadId, emudraExternalResponse.getSigned_file_content());
                   }

               }else {
                   LoggerClass.appLayerLogger.info("Emudra Esigned document is already available.");
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

/*
    private void dgoBackEndProcess1(String plLeadId){
        String leadId;
        String base64KfsDoc =null;
        boolean kfsAvailable = false;
        SfdcTdlDocResponse sfdcTdlDocResponse =new SfdcTdlDocResponse();
        EmudraRequest emudraRequest= new EmudraRequest();
        EmudraExternalResponse emudraExternalResponse = new EmudraExternalResponse();

        try {
            */
/*check all 4 document is available then hit external service for emudra*//*

            leadId=plLeadId;
            List<UploadDoc> uploadDoc=uploadDocDAO.getUploadDocResByPlleadIdAndDocType(leadId, ProjectConstants.CUSTOMER_KFS_GENERATION_DOC_TYPE);
            sfdcTdlDocResponse= sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));
            if (uploadDoc.size() >0)
                base64KfsDoc =uploadDoc.get(0).getBase64FormattedData();
            LoggerClass.appLayerLogger.info("UploadDoc Size is {}", uploadDoc.size());


            if(!uploadDoc.isEmpty()  && base64KfsDoc != null){
                kfsAvailable = true;
                LoggerClass.appLayerLogger.info("kfsAvailable {} for LeadId {}",kfsAvailable , leadId);
            }

            if(!ObjectUtils.isEmpty(sfdcTdlDocResponse) && sfdcTdlDocResponse.getSfdcDoc() != null && !sfdcTdlDocResponse.getSfdcDoc().isEmpty()
                    && sfdcTdlDocResponse.getTdlDocTnc() != null && !sfdcTdlDocResponse.getTdlDocTnc().isEmpty()
                    && sfdcTdlDocResponse.getTdlDocLoanAgr() != null && !sfdcTdlDocResponse.getTdlDocLoanAgr().isEmpty() &&
                    kfsAvailable) {
                if(sfdcTdlDocResponse.getEmudraStatus() == null || !sfdcTdlDocResponse.getEmudraStatus().equals(ProjectConstants.SUCCESS)) {

                    makeEsignPDF(Integer.valueOf(leadId),base64KfsDoc);
                    LoggerClass.appLayerLogger.info("calling creatingEmudraRequest for esigned doc pushing for leadId {}", leadId);
                    emudraRequest = creatingEmudraRequest(leadId);

                    if (emudraRequest.getFile_content_string() !=null) {
                        LoggerClass.appLayerLogger.info("calling EmudraService ...");
                        emudraExternalResponse = eMudraService(leadId, emudraRequest);
                    }

                    if (emudraExternalResponse.getSigned_file_content() != null) {
                        LoggerClass.appLayerLogger.info("Pusing Document to DMS and Emails");
                        pushEmudraToDMSAndEmail(leadId, emudraExternalResponse.getSigned_file_content());
                    }

                }else {
                    LoggerClass.appLayerLogger.info("Emudra Esigned document is already available.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    @Override
    public RootResponse saveDocForEmudra(List<DocTypeData> emudraDocRequest, String leadId, String customerHash){

        SfdcTdlDocResponse sfdcTdlDocResponse =new SfdcTdlDocResponse();
        RootResponse rootResponse = new RootResponse();
        String docType = null;
        basePath="C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/doc/";//appProps.geteMudraFilePath();
//        SfdcTdlDocResponse sfdcTdlDocResponse1 = new SfdcTdlDocResponse();

        // based on the tpye we can save in Db.
        try {
            LoggerClass.appLayerLogger.info("Inside saveDocForEmudra");
            LoggerClass.appLayerLogger.info("Doc path "+basePath);
            for (DocTypeData doc : emudraDocRequest) {
                FileResponse fileResponse = convertBase64StringToFile(doc.getDoc(),"esignDmsRequest" , leadId);
                if (doc != null && doc.getDoc() != null && doc.getDocType() != null) {

                    docType = doc.getDocType().toLowerCase();
                    LoggerClass.appLayerLogger.info("customer docType is {}", docType);

                    if (docType.equalsIgnoreCase("sl")) {
                        sfdcTdlDocResponse.setSfdcDoc(doc.getDoc());

                    } else {
                        switch (docType) {

                            case "tc":
                                sfdcTdlDocResponse.setTdlDocTnc(doc.getDoc());
                                break;
                            case "la":
                                sfdcTdlDocResponse.setTdlDocLoanAgr(doc.getDoc());
                                break;
                        }
                    }
                    //sl Sanction Letter , tc-Terms&Conditions , la- Loan

                } else {
                    LoggerClass.appLayerLogger.info("some necessary field is missing..");
                    rootResponse.setRetStatus(ProjectConstants.FAILURE);
                    rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_DocData_ERROR_CODE);
                    rootResponse.setSysErrorMessage("Some necessary field is missing like leadId, docType");
                    return rootResponse;
                }
            }
            sfdcTdlDocResponse.setLeadId(Integer.valueOf(leadId));
            sfdcTdlDocResponse.setCustomerHash(customerHash);
            sfdcTdlDocResponse.setEmudraStatus(ProjectConstants.FAILURE);

            rootResponse.setRetStatus(ProjectConstants.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            if (sfdcTdlDocResponse.getLeadId() != null) {
                saveOrUpdateSfdcData(sfdcTdlDocResponse);
                LoggerClass.appLayerLogger.info("Emudra Document is updated successfully Document {} for customer :{}",docType ,sfdcTdlDocResponse.getLeadId());

            }
        }
        return rootResponse	;
    }

    public void pushEmudraToDMSAndEmail(String plLeadId, String esignedDocBase64Date){
        DocUploadPayload docUploadPayload = new DocUploadPayload();
        UploadDoc uploadDoc=new UploadDoc();
        SmsMailPayload smsMailPayload=new SmsMailPayload();
        CDIOfferModule offerModule=new CDIOfferModule();
        basePath="C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/doc/";

        try {
            LoggerClass.appLayerLogger.info("pushEmudraToDMS is started.");
            LoggerClass.appLayerLogger.info("fileBasePath {}",basePath);
            offerModule = cdiOfferModuleDataDAO.getOfferDataByPlLeadId(Long.valueOf(plLeadId));
            uploadDoc.setCreatedDate(new Date());
            docUploadPayload.setWebtopNo(offerModule.getPlWebTopId() != null?offerModule.getPlWebTopId():null);
//            docUploadPayload.set(String.valueOf(offerModule.getPlLeadId()));
            docUploadPayload.setApplicantType("1");
            docUploadPayload.setMobileNo(String.valueOf(offerModule.getMobileNo()  != null ? offerModule.getMobileNo():null));
            docUploadPayload.setDocUploadName(plLeadId+"_EsignedDoc.pdf");

            docUploadPayload.setBase64(esignedDocBase64Date);
            docUploadPayload.setDocUploadType("EsignedDoc");

            uploadDoc.setPlleadId(String.valueOf(offerModule.getPlLeadId() != null ? offerModule.getPlLeadId():null));
            uploadDoc.setDocUploadName(plLeadId+"_EsignedDoc.pdf");
//            uploadDoc.setBase64FormattedData(esignedDocBase64Date);
            uploadDoc.setDocUploadType("Dms_pushed_Esigned_Doc");
            uploadDoc.setApplicantType("1");
//			uploadDoc.setApplicantNature();
            uploadDoc.setUpdatedDate(new Date());
//            StringBuilder fileName = null;
//            fileName = new StringBuilder("/home/99037250/web/TCL/uploaded_doc/");
            FileResponse fileResponse = convertBase64StringToFile(esignedDocBase64Date,"esignDmsRequest" , plLeadId);
            uploadDoc.setDocUploadBase64(fileResponse.toString());
            uploadDoc.setWebtopNo(offerModule.getPlWebTopId() != null ? offerModule.getPlWebTopId() : null);

//            if (ObjectUtils.isEmpty(docUploadResponse)){
//                uploadDoc.setDocUploadRetStatus(docUploadResponse.getRetStatus());
//                uploadDoc.setDocUploadLogtxnid(docUploadResponse.getLogTxnID());
//            }
            uploadDoc= uploadDocDAO.saveOrUpdate(uploadDoc);
            docUploadPayload.setDocId(uploadDoc.getDocId());
            DocUploadResponse docUploadResponse = new DocUploadResponse();//docUploadService.uploadDocV2(docUploadPayload); //  for pushing DMS.
            if (docUploadResponse != null){
                uploadDoc.setDocUploadRetStatus(ProjectConstants.SUCCESS);
            }else {
                uploadDoc.setDocUploadRetStatus(ProjectConstants.API_FAIL);
            }


            LoggerClass.appLayerLogger.info("DMS pushed for Esigned document of customer leadId"+offerModule.getPlLeadId());

            smsMailPayload.setSubject("Important! Tata Capital Loan Application -" + offerModule.getCustomerName() + " ");
            smsMailPayload.setMessage("<html><body><div><p>Dear "+offerModule.getCustomerName()+" ,</p><p>Greetings from Tata Capital! We would like to thank you for choosing us as your financial partner.</p><br> <p>We are pleased to inform you that your Personal Loan application is submitted successfully and is currently under process. You will receive a confirmation on the Application status shortly.</p>" +
                    "<br><p>You may refer to  important loan related  details from the documents attached to this e-mail. </p>" +
                    "<br><p>For any clarification and more details, you may write to us at customercare@tatacapital.com  or Call us on 1860 267 6060 from 09:00am to 08:00pm, Monday to Saturday (except public holidays).</p>" +
                    "<br> <br> <p>To track your application status, click here: https://bit.ly/3o53lmj </p>"+
                    " <br> <br> <p> Looking forward to more opportunities to be of service to you.</p>"+
                    "<br><p>Thanks & Regards,</p><p style="
                    + "font-weight: bold;" + ">Tata Capital Financial Services Ltd</p></div></body>");
            if (offerModule.getEmailAddress() != null)
                smsMailPayload.setMailTo(offerModule.getEmailAddress());
            else
                LoggerClass.appLayerLogger.info("Emails Id is not available in payload for leadId {} email is {}",plLeadId,offerModule.getEmailAddress());
            smsMailPayload.setLeadId(String.valueOf(offerModule.getPlLeadId() != null ? offerModule.getPlLeadId():null));

            StringBuilder tmp = new StringBuilder("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/doc/"+plLeadId+"/");
            tmp.append(plLeadId).append(File.separator);
            String  filepath=tmp.toString() +plLeadId+"_EsignedDoc.pdf";
            File file = new File(filepath);
            if (file != null) {
                smsMailPayload.setFile(file);
                LoggerClass.appLayerLogger.info("Name: " + file.getName());
                smsMailPayload.setFileName(file.getName());
                smsMailPayload.setCompleteFilePath(file.getPath());
                LoggerClass.appLayerLogger.info("AbsolutePath: " + file.getAbsolutePath());
                LoggerClass.appLayerLogger.info("Path: " + file.getPath());
            }


            LoggerClass.appLayerLogger.info("Sending mail to customer : Stated");
//            Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
//            emailServices.sendMail(smsMailPayload);

            LoggerClass.appLayerLogger.info("Sending mail to customer : Ended");

            if (file.exists()){
                deleteExtraFileFromServer(plLeadId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public EmudraRequest creatingEmudraRequest(String leadId){
        //lead_id	customer_hash	sfdc_doc	tdl_tnc	tdl_loneAg
        EmudraRequest emudraRequest = new EmudraRequest();
        String combinedDoc = null;
        List<Coordinates> coordinatesList= new ArrayList<>();
        EmudraSign_coordinates sign_coordinates=new EmudraSign_coordinates();
        Coordinates coordinates = new Coordinates();
        try {
            // TODO: 3/13/2023  have to review
//            combinedDoc= makeEsignPDF(Integer.valueOf(leadId),base64KfsDoc);
            SfdcTdlDocResponse sfdcTdlDocResponse = sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf((leadId)));

            emudraRequest.setName("Prod DLG");
            emudraRequest.setReason("Testing");
            emudraRequest.setLocation("Mumbai");
            emudraRequest.setKey_store_name("TCFSL");
            if (sfdcTdlDocResponse.getEsignDoc() !=null)
                emudraRequest.setFile_content_string(sfdcTdlDocResponse.getEsignDoc());

            emudraRequest.setDisplay_on_page("custom");
            coordinates.setLlx(35);
            coordinates.setLly(35);
            coordinates.setUrx(322);
            coordinates.setUry(60);

            coordinatesList.add(coordinates);
            sign_coordinates.setCoordinates(coordinatesList);

            emudraRequest.setSign_coordinates(sign_coordinates);

            return emudraRequest;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EmudraExternalResponse eMudraService(String leadId, EmudraRequest emudraRequest){

//        EmudraRequest emudraResponse =new EmudraRequest();
        SfdcTdlDocResponse sfdcTdlDocResponse = new SfdcTdlDocResponse();
        RestTemplate restTemplate = new RestTemplate();
        EmudraExternalResponse  emudraExternalResponse =new EmudraExternalResponse();
        basePath="C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/doc/";
        String url = "http://digio.tatacapital.com:8080/doc_signer/signdoc";
//        AuditDetails auditDetails  = new AuditDetails();
//        UploadDoc uploadDoc= new UploadDoc();
        AuditDetailsPayload auditDetailsPayload = new AuditDetailsPayload();

        try {
//            auditDetails.setApiName("digio EmudraService");
//            auditDetails.setLeadId(leadId);
//            auditDetails.setRequestUrl(url);
            auditDetailsPayload.setRequestTime(new Date());
            LoggerClass.appLayerLogger.info("Emudra call for leadId {}",leadId);

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Content-Type", "application/json");

            HttpEntity<?> httpEntity = new HttpEntity<>(emudraRequest, headers);

//            auditDetails.setRequestTime(new Date());

            emudraExternalResponse = restTemplate.postForObject(url, httpEntity, EmudraExternalResponse.class);
//            auditDetails.setResponseTime(new Date());

            if (emudraExternalResponse.getSigned_file_content() !=null) {
//                auditDetails.setStatus(ProjectConstants.SUCCESS);
                sfdcTdlDocResponse.setEmudraStatus(ProjectConstants.SUCCESS);
            }

//            auditDetails.setRequestFileName(basePath+leadId+ "_mergedFile.pdf");
            if (emudraExternalResponse.getSigned_file_content() !=null) {

                StringBuilder tmp = new StringBuilder("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/doc/"+leadId+"/");
                tmp.append(leadId);
                File tmpDir = new File(tmp.toString());
                if (!tmpDir.exists()) {
                    LoggerClass.appLayerLogger.info("Directory Created: " + tmpDir.mkdirs());
                }
                base64ToPdf(emudraExternalResponse.getSigned_file_content(), tmpDir.getAbsolutePath()+leadId+ "_EsignedDoc.pdf");
            }
//            auditDetails.setResponseFileName(basePath+leadId+ "_EsignedDoc.pdf");

            auditDetailsPayload.setApiName("digioEsignDoc");
            auditDetailsPayload.setLeadId(leadId);
            auditDetailsPayload.setPayload(objMapper.writeValueAsString(emudraRequest)  );
            auditDetailsPayload.setRequestUrl(url);
            auditDetailsPayload.setResponse(objMapper.writeValueAsString(emudraExternalResponse));
            auditDetailsPayload.setConversationId(String.valueOf(new Date().getTime()));
            auditDetailsPayload.setResponseTime(new Date());
            auditDetailsPayload.setStatus(ProjectConstants.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            auditDetailsPayload.setResponseTime(new Date());
            auditDetailsPayload.setStatus(ProjectConstants.API_FAIL);
        } finally {
            auditDetailsUtility.saveAuditDetails(auditDetailsPayload);

            if (emudraExternalResponse.getSigned_file_content() != null) {
                sfdcTdlDocResponse.setLeadId(Integer.valueOf((leadId)));
                sfdcTdlDocResponse.setEsignDoc(emudraExternalResponse.getSigned_file_content());
            }

            if (sfdcTdlDocResponse.getLeadId() !=null) {
                saveOrUpdateSfdcData(sfdcTdlDocResponse);
                LoggerClass.appLayerLogger.info("EmudraDoc is updated successfully for customer :"+sfdcTdlDocResponse.getLeadId());
            }

            LoggerClass.appLayerLogger.info("Document is saved.");
        }

        return emudraExternalResponse;
    }

    public String makeEsignPDF(Integer leadId,String base64KfsDoc){
        basePath="C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/doc/";//appProps.geteMudraFilePath();
        File file = null,file1 = null,file2 = null,file3=null, file0=null;
        SfdcTdlDocResponse sfdcTdlDocResponse= new SfdcTdlDocResponse();
        String path=basePath;
        File tempFile;
        String destenation;

        try {
            StringBuilder tmp = new StringBuilder(basePath);
            tmp.append(leadId).append(File.separator);
            File path1= new File(tmp.toString());
            if (!path1.exists()) {
                LoggerClass.appLayerLogger.info("Directory Created in makeEsignPDF: {}",path1.mkdirs());
            }
            path=path1.getAbsolutePath()+File.separator;
            LoggerClass.appLayerLogger.info("");
            sfdcTdlDocResponse = sfdcTdlDocDAO.getSfdcTdlDocByLeadId(((leadId)));


            file0 = new File(basePath + "coverPage.pdf");

            if (sfdcTdlDocResponse.getSfdcDoc() !=null) {
                base64ToPdf(sfdcTdlDocResponse.getSfdcDoc(),path+leadId+ "_sfdcDoc.pdf");
                file= new File(path + leadId + "_sfdcDoc.pdf");
            }
            if (sfdcTdlDocResponse.getTdlDocLoanAgr() !=null) {
                base64ToPdf(sfdcTdlDocResponse.getTdlDocLoanAgr(),path+leadId+"_TdlDocLoanAgr.pdf" );
                file1= new File(path+leadId+"_TdlDocLoanAgr.pdf");
            }

            if (sfdcTdlDocResponse.getTdlDocTnc() !=null) {
                base64ToPdf(sfdcTdlDocResponse.getTdlDocTnc(),path+leadId+"_TdlDocTnc.pdf");
                file2= new File(path+leadId+"_TdlDocTnc.pdf");
            }

            if (base64KfsDoc !=null) {
                base64ToPdf(base64KfsDoc,  path+leadId+"_KfsDoc.pdf");
                file3 = new File(path+leadId+"_KfsDoc.pdf");
            }

            //file array creating to send for merging.
            File[] files={file0,file,file1,file2,file3};

            destenation=path;

            return combineTwoPdf(files,destenation, String.valueOf(leadId));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LoggerClass.appLayerLogger.info("PDF created successfully.");
        }
        return "Success";
    }

    public static void base64ToPdf(String b64,String filePath) {
        File file = new File(filePath);

        try (FileOutputStream fos = new FileOutputStream(file); ) {
            byte[] decoder = java.util.Base64.getDecoder().decode(b64);

            fos.write(decoder);
            System.out.println("PDF File Saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteExtraFileFromServer(String leadId){
        String basePath= "C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/doc/";
        try {
            StringBuilder tmp = new StringBuilder(basePath);
            tmp.append(leadId).append(File.separator);
            File path1= new File(tmp.toString());
//            if (!path1.exists()) {
//                LoggerClass.appLayerLogger.info("Directory Created in makeEsignPDF: {}",path1.mkdir());
//            }
            basePath=path1.getAbsolutePath();
            LoggerClass.appLayerLogger.info("Deleting the file For leadId {}",leadId);

            File sfdcTextDoc = new File(basePath+leadId+ "_sfdcbase64.txt");
            if (sfdcTextDoc.exists()) sfdcTextDoc.delete();

            File sfdcPdfDoc = new File(basePath+leadId+ "_sfdcDoc.pdf");
            if (sfdcPdfDoc.exists()) sfdcPdfDoc.delete();

            File tdlTnCTextDoc = new File(basePath+leadId+ "_tdlTncBase64.txt");
            if (tdlTnCTextDoc.exists()) tdlTnCTextDoc.delete();

            File tdlTnCPdfDoc = new File(basePath+leadId+ "_TdlDocTnc.pdf");
            if (tdlTnCPdfDoc.exists()) tdlTnCPdfDoc.delete();

            File tdlLAgrTextDoc = new File(basePath+leadId+ "_tdlLAgrbase64.txt");
            if (tdlLAgrTextDoc.exists()) tdlLAgrTextDoc.delete();

            File tdlLAgrPdfDoc = new File(basePath+leadId+ "_TdlDocLoanAgr.pdf");
            if (tdlLAgrPdfDoc.exists()) tdlLAgrPdfDoc.delete();

            File kfsTextDoc = new File(basePath+leadId+ "_KfsBase64.txt");
            if (kfsTextDoc.exists()) kfsTextDoc.delete();

            File kfsPdfDoc = new File(basePath+leadId+ "_KfsDoc.pdf");
            if (kfsPdfDoc.exists()) kfsPdfDoc.delete();

            File esignedDocTxt = new File(basePath+leadId+ "_EsignedDoc.txt");
            if (esignedDocTxt.exists()) esignedDocTxt.delete();

            File meargedDocTxt = new File(basePath+leadId+ "_mergedFile.txt");
            if (meargedDocTxt.exists()) meargedDocTxt.delete();

        } finally {
            LoggerClass.appLayerLogger.info("File deleted Successfully.");
        }
    }

/*
    public File textToFile(String str,String path) {

        try {
//			String str = "SomeMoreTextIsHere";
            File newTextFile = new File(path);

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(str);
            fw.close();
            return newTextFile;
        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
        return null;
    }
*/

    @Override
    public void jobsForEmudraAndDmsEmails(List<String> request){
        EmudraRequest emudraRequest =new EmudraRequest();
        EmudraExternalResponse emudraExternalResponse = new EmudraExternalResponse();
        SfdcTdlDocResponse sfdcTdlDocResponse = new SfdcTdlDocResponse();
        String base64KfsDoc=null;
        try {
            for(String leadId: request){
                LoggerClass.appLayerLogger.info("jobsForEmudraAndDmsEmails Stared For leadid {}",leadId);
                sfdcTdlDocResponse= sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));

                if(sfdcTdlDocResponse.getEsignDoc() ==null){ //checking if meargedpdf is not created

                    List<UploadDoc> uploadDoc=uploadDocDAO.getUploadDocResByPlleadIdAndDocType(leadId, ProjectConstants.CUSTOMER_KFS_GENERATION_DOC_TYPE);
                    if (uploadDoc.size() >0)
                        base64KfsDoc =uploadDoc.get(0).getBase64FormattedData();
                    LoggerClass.appLayerLogger.info("UploadDoc Size is {} in Job", uploadDoc.size());
                    makeEsignPDF(Integer.valueOf(leadId),base64KfsDoc);
                }else {
                    LoggerClass.appLayerLogger.info("Esigned merged doc is available for leadId {}",leadId);
                }

                if (!sfdcTdlDocResponse.getEmudraStatus().equals(ProjectConstants.SUCCESS)) {
                    emudraRequest = creatingEmudraRequest(leadId);
                    emudraExternalResponse = eMudraService(leadId, emudraRequest);

                }else{
                    LoggerClass.appLayerLogger.info("Esign Status Is available for leadId {}",leadId);
                }

                if (emudraExternalResponse.getSigned_file_content() != null && !sfdcTdlDocResponse.getEmudraStatus().equals(ProjectConstants.SUCCESS)) {
                    LoggerClass.appLayerLogger.info("Pusing Document to DMS and Emails");
                    pushEmudraToDMSAndEmail(leadId, emudraExternalResponse.getSigned_file_content());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LoggerClass.appLayerLogger.info("jobsForEmudraAndDmsEmails ended");
        }
    }

    public void saveOrUpdateSfdcData(SfdcTdlDocResponse sfdcTdlDocResponse){

        try {
            String leadId= null;
            SfdcTdlDocResponse sfdcTdlDocResponseSavedData= new SfdcTdlDocResponse();

            leadId = String.valueOf(sfdcTdlDocResponse.getLeadId());
            LoggerClass.appLayerLogger.info("leadId {}",leadId);
            sfdcTdlDocResponseSavedData = sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));

            if (ObjectUtils.isEmpty(sfdcTdlDocResponseSavedData) && sfdcTdlDocResponseSavedData == null){
                LoggerClass.appLayerLogger.info("No any record found in Table for leadId :"+leadId);
                sfdcTdlDocResponseSavedData = new SfdcTdlDocResponse();
                sfdcTdlDocResponseSavedData.setLeadId(Integer.valueOf(leadId));
                sfdcTdlDocResponseSavedData.setCreatedDate(new Date());
                sfdcTdlDocResponseSavedData.setUpdatedDate(new Date());
                sfdcTdlDocResponseSavedData.setEmudraStatus(ProjectConstants.FAILURE);
            }
            if (sfdcTdlDocResponse.getEsignDoc() !=null)
                sfdcTdlDocResponseSavedData.setEsignDoc(sfdcTdlDocResponse.getEsignDoc());
            if (sfdcTdlDocResponse.getCustomerHash() !=null)
                sfdcTdlDocResponseSavedData.setCustomerHash(sfdcTdlDocResponse.getCustomerHash());
            if (sfdcTdlDocResponse.getCreatedDate() != null){
                sfdcTdlDocResponseSavedData.setCreatedDate(sfdcTdlDocResponse.getCreatedDate());
            }
            if (sfdcTdlDocResponse.getUpdatedDate() !=null)
                sfdcTdlDocResponseSavedData.setUpdatedDate(sfdcTdlDocResponse.getUpdatedDate());
            else
                sfdcTdlDocResponseSavedData.setUpdatedDate(new Date());
            if (sfdcTdlDocResponse.getTdlDocLoanAgr() !=null)
                sfdcTdlDocResponseSavedData.setTdlDocLoanAgr(sfdcTdlDocResponse.getTdlDocLoanAgr());
            if (sfdcTdlDocResponse.getTdlDocTnc() !=null)
                sfdcTdlDocResponseSavedData.setTdlDocTnc(sfdcTdlDocResponse.getTdlDocTnc());
            if (sfdcTdlDocResponse.getSfdcDoc() !=null)
                sfdcTdlDocResponseSavedData.setSfdcDoc(sfdcTdlDocResponse.getSfdcDoc());
            if (!sfdcTdlDocResponseSavedData.getEmudraStatus().equalsIgnoreCase(ProjectConstants.SUCCESS))
                sfdcTdlDocResponseSavedData.setEmudraStatus(sfdcTdlDocResponse.getEmudraStatus() != null ? sfdcTdlDocResponse.getEmudraStatus():" ");

            sfdcTdlDocDAO.saveOrUpdateSfdcTdlDoc(sfdcTdlDocResponseSavedData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String combineTwoPdf(File[] filePaths, String destinationFileName, String leadId) throws IOException {// this for acceptng the file array.

        String  esignBase64Data= null;
        SfdcTdlDocResponse sfdcTdlDocResponse = new SfdcTdlDocResponse();
        String path=null;
        try {
            String newFilePath = destinationFileName +leadId+ "_mergedFile.pdf";
            File tmpDir = new File(newFilePath);

            PDFMergerUtility obj = new PDFMergerUtility();
//            obj.setDestinationFileName(tmpDir.getAbsolutePath()+leadId+ "_mergedFile.pdf");
            obj.setDestinationFileName(newFilePath);

            // Add all source files, to be merged
            for (File files : filePaths){
                obj.addSource(files.getAbsoluteFile());
            }

            obj.mergeDocuments();

//            encode(tmpDir.getParentFile()+"/" +leadId+"_mergedFile.pdf", tmpDir.getParentFile() +"/" +leadId+"_mergedFile.txt", true);
//            encode(newFilePath+leadId+"_mergedFile.pdf", newFilePath +leadId+"_mergedFile.txt", true);

            System.out.println("PDF Documents merged to a single file");
//            path =tmpDir.getParentFile() +"/" +leadId+"_mergedFile.txt";
//            esignBase64Data =copyDataFromFile(path) ;

            esignBase64Data= convertFileToBase64(tmpDir.getParentFile()+"/"+leadId+"_mergedFile.pdf");

//            logger.info("base64 {}",esignBase64Data);
            sfdcTdlDocResponse.setLeadId(Integer.valueOf(leadId));
            if (esignBase64Data != null) {
                sfdcTdlDocResponse.setEsignDoc(esignBase64Data);
                sfdcTdlDocResponse.setEmudraStatus(ProjectConstants.FAILURE);
            }
            saveOrUpdateSfdcData(sfdcTdlDocResponse);

        } catch (IOException | COSVisitorException e) {
            e.printStackTrace();
        }
        return null;
    }

/*
    public String combineTwoPdf1(File[] filePaths, String destinationFileName, String leadId) throws IOException {// this for acceptng the file array.

        String  esignBase64Data= null;
        SfdcTdlDocResponse sfdcTdlDocResponse = new SfdcTdlDocResponse();
        String path=null;
        try {
            StringBuilder tmp = new StringBuilder(destinationFileName);
            tmp.append(leadId).append(File.separator);
            File tmpDir = new File(tmp.toString());
            if (!tmpDir.exists()) {
                LoggerClass.appLayerLogger.info("Directory Created combineTwoPdf: {}",tmpDir.mkdirs());
            }
            PDFMergerUtility obj = new PDFMergerUtility();
            obj.setDestinationFileName(tmpDir.getAbsolutePath()+leadId+"_mergedFile.pdf");

            // Add all source files, to be merged
            for (File files : filePaths){
                obj.addSource(files.getAbsoluteFile());
            }

            obj.mergeDocuments();

//            StringBuilder tmp = new StringBuilder("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/doc/"+leadId+"/");
//            tmp.append(leadId);
//            File tmpDir = new File(tmp.toString());
//            if (!tmpDir.exists()) {
//                LoggerClass.appLayerLogger.info("Directory Created: " + tmpDir.mkdir());
//            }

//            encode(tmpDir.getAbsolutePath() +leadId+"_mergedFile.pdf", tmpDir.getAbsolutePath() +leadId+"_mergedFile.txt", true);
            System.out.println("PDF Documents merged to a single file");
            path =destinationFileName+leadId+"_mergedFile.txt";
//            esignBase64Data =copyDataFromFile(path) ;

            sfdcTdlDocResponse.setLeadId(Integer.valueOf(leadId));
            if (esignBase64Data != null) {
                sfdcTdlDocResponse.setEsignDoc(esignBase64Data);
                sfdcTdlDocResponse.setEmudraStatus(ProjectConstants.FAILURE);
            }
            saveOrUpdateSfdcData(sfdcTdlDocResponse);

        } catch (IOException | COSVisitorException e) {
            e.printStackTrace();
        }
        return null;
    }
*/


    public static String convertFileToBase64(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] pdfBytes = Files.readAllBytes(path);
        byte[] base64Bytes = java.util.Base64.getEncoder().encode(pdfBytes);
        return new String(base64Bytes);
    }

  /*  public String  copyDataFromFile(String filePath){
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(filePath)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static File encode(String sourceFile, String targetFile, boolean isChunked) {

        try {
            byte[] base64EncodedData = Base64.encodeBase64(loadFileAsBytesArray(sourceFile), isChunked);

            return writeByteArraysToFile(targetFile, base64EncodedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File decode(String sourceFile, String targetFile) {
        File responseFile;
        try {
            byte[] decodedBytes = Base64.decodeBase64(loadFileAsBytesArray(sourceFile));

            responseFile= writeByteArraysToFile(targetFile, decodedBytes);
            return  responseFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static File writeByteArraysToFile(String fileName, byte[] content) throws IOException {

        try {
            File file = new File(fileName);
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
            writer.write(content);
            writer.flush();
            writer.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] loadFileAsBytesArray(String fileName) throws Exception {

        try {
            File file = new File(fileName);
            int length = (int) file.length();
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[length];
            reader.read(bytes, 0, length);
            reader.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
*/
    public FileResponse convertBase64StringToFile(String base64String, String fileName, String appId) {
        FileResponse response = new FileResponse();
        try {
            String tempPath =prepareTempPath(appId);
            String tempFileName = prepareFileName(appId, fileName);
            File file1 = new File(tempPath + tempFileName);
//            OutputStream outputStream = new FileOutputStream(file1);
//            outputStream.write(java.util.Base64.getDecoder().decode(base64String.replaceAll("\\s", "")));
//            outputStream.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
            writer.write(base64String);
            writer.close();

            prepareFileResponse(response, tempPath, tempFileName);
        } catch (FileNotFoundException e) {
            logger.error("While serching the file path it throws error(FileNotFound): ", e);
            response.setStatus(ProjectConstants.API_FAIL);
            response.setMessage(e.getMessage());
        } catch (IOException ex) {
            logger.error("While closing the streams it throws error: ", ex);
            response.setStatus(ProjectConstants.API_FAIL);
            response.setMessage(ex.getMessage());
        } catch (Exception exe) {
            logger.error("While preparing file paths to file it throws error: ", exe);
            response.setStatus(ProjectConstants.API_FAIL);
            response.setMessage(exe.getMessage());
        }
        return response;
    }

    private String prepareTempPath(String appId) throws Exception {
        StringBuilder tmp = new StringBuilder("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/AuditDetailsUtility/");
        tmp.append(appId);
        File tmpDir = new File(tmp.toString());
        if (!tmpDir.exists()) {
            LoggerClass.appLayerLogger.info("Directory Created: " + tmpDir.mkdir());
        }
        return tmp.toString();
    }

    private String prepareFileName(String appId, String fileName) throws Exception {
        return new StringBuilder(File.separator)
                .append(appId)
                .append("_")
                .append(fileName).toString();
    }

    private void prepareFileResponse(FileResponse response, String tempFilePath, String fileName) {
        try {
            response.setFile(new FileInputStream(new File(tempFilePath + fileName)));
            response.setAbsoluteFilePath(tempFilePath + fileName);
            response.setFilePath(tempFilePath);
            response.setFileName(fileName);
            response.setStatus(ProjectConstants.SUCCESS);
        } catch (Exception e) {
            response.setStatus(ProjectConstants.API_FAIL);
            response.setMessage(e.getMessage());
        }
    }

//    public static void main(String[] args) {
//        File pdf = new File("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/1_KfsDoc.pdf");
//        File txt= new File("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/1_KfsDoc.txt");
//        base64ToPdf(pdf.getAbsolutePath(),txt.getAbsolutePath());
//        base64ToPdf("/1_KfsDoc.pdf","/1_KfsDoc1.txt");
//        EmudraServiceImpl emudraService= new EmudraServiceImpl();
//        emudraService.convertBase64StringToFile("ieieoeooeoe","convertedfile","1111");
//    }
    public static void pdfToBase64(File pdfFile, File txtFile) throws IOException {
        // Read the contents of the PDF file into a byte array
        byte[] pdfBytes = new byte[(int) pdfFile.length()];
        FileInputStream pdfInputStream = new FileInputStream(pdfFile);
        pdfInputStream.read(pdfBytes);
        pdfInputStream.close();

        // Convert the byte array to a Base64-encoded string
        String base64String =java.util.Base64.getEncoder().encodeToString(pdfBytes);

        // Write the Base64-encoded string to the text file
        BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile));
        writer.write(base64String);
        writer.close();
    }

    public ResponseEntity<SfdcTdlDocResponse> saveData(SfdcTdlDocResponse sfdcTdlDocResponse){
        try {
            return new ResponseEntity<>( sfdcTdlDocDAO.saveOrUpdateSfdcTdlDoc(sfdcTdlDocResponse),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) throws IOException {
//        // Create a file
//        String fileName = "C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/example.txt";
//        File file = new File(fileName);
//        file.createNewFile();
//
//// Get the absolute path of the file
//        String filePath = file.getAbsolutePath();
//        LoggerClass.appLayerLoggerClass.appLayerLogger.info("file path"+filePath);
//// Create a new directory
//        String newDirectoryName = "C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/new_directory";
//        File newDirectory = new File(newDirectoryName);
//        newDirectory.mkdir();
//
//// Create a new file in the new directory
//        String newFilePath = newDirectoryName + File.separator + "new_example.txt";
//        File newFile = new File(newFilePath);
//        newFile.createNewFile();
//
//    }
}
