package com.expriment.Controller;

import com.expriment.DAO.CDIOfferModuleDataDAO;
import com.expriment.Testing.EmudraDocRequest;
import com.expriment.entity.CDIOfferModule;
import com.expriment.service.serviceImpl.EmudraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/Offer")
public class CDIOfferModuleController {

    @Autowired
    CDIOfferModuleDataDAO cdiOfferModuleDataDAO;

    @Autowired
    EmudraServiceImpl emudraService;

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

    @GetMapping("/{oppId}") ////Offer/{oppId}
    public ResponseEntity<?> getOpportunityIdDetails(@PathVariable String oppId) {
        return new ResponseEntity<CDIOfferModule>(cdiOfferModuleDataDAO.getOfferDataByPlOpportunityId(oppId),HttpStatus.OK);
    }

    }

/*{
    "plWebTopId":
        "plOpportunityId":,
        "plLeadId":,
        "customerHash":,




        }*/
