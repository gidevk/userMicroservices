package com.expriment.Controller;

import com.expriment.DAO.CDIOfferModuleDataDAO;
import com.expriment.Testing.EmudraDocRequest;
import com.expriment.Testing.SfdcTdlDocResponse;
import com.expriment.entity.CDIOfferModule;
import com.expriment.service.serviceImpl.EmudraServiceImpl;
import com.expriment.utils.audit.Service.AuditLogDataJPAService;
import com.expriment.utils.audit.entity.AuditLogData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/Offer")
public class CDIOfferModuleController {

    @Autowired
    CDIOfferModuleDataDAO cdiOfferModuleDataDAO;

    @Autowired
    EmudraServiceImpl emudraService;

      @Autowired
      AuditLogDataJPAService auditLogDataJPAService;

    @PostMapping(value="/saveOfferModule", produces = {MediaType.APPLICATION_JSON_VALUE})// /Offer/saveOfferModule
    public ResponseEntity<?> saveCDIOfferModule(@RequestBody CDIOfferModule cdiOfferModule){
        CDIOfferModule cdiOfferModuleResponse= new CDIOfferModule();
        try {
            cdiOfferModuleResponse = cdiOfferModuleDataDAO.saveOfferModuleData(cdiOfferModule);

            return new ResponseEntity<> (cdiOfferModuleResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/emudra", produces = {MediaType.APPLICATION_ATOM_XML_VALUE}) // /Offer/emudra
    public ResponseEntity<?> emudraRequest(@RequestBody EmudraDocRequest emudraDocRequestList){
        return new ResponseEntity<>(emudraService.saveEmudraDocumentService(emudraDocRequestList), HttpStatus.OK);

//        return null;
    }
    @PostMapping(value = "/saveSfdc", produces = {MediaType.APPLICATION_ATOM_XML_VALUE}) // /Offer/emudra
    public ResponseEntity<?> emudraRequest(@RequestBody SfdcTdlDocResponse emudraDocRequestList){
        return emudraService.saveData(emudraDocRequestList);

//        return null;
    }

    @GetMapping("/{oppId}") ////Offer/{oppId}
    public ResponseEntity<?> getOpportunityIdDetails(@PathVariable String oppId) {
        return new ResponseEntity<CDIOfferModule>(cdiOfferModuleDataDAO.getOfferDataByPlOpportunityId(oppId),HttpStatus.OK);
    }

    @GetMapping ("/ByCpId/{cp_id}") ////Offer/{oppId}
    public AuditLogData getOpportunityIdDetails(@PathVariable /*@RequestParam(required = false)*/ Integer cp_id) throws JsonProcessingException {
        return auditLogDataJPAService.findAllAuditLogDataByID(cp_id);
    }

    }

/*{
    "plWebTopId":
        "plOpportunityId":,
        "plLeadId":,
        "customerHash":,




        }*/
