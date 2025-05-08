package com.expriment.Controller;

import com.expriment.utils.audit.LoggerClass;
import com.expriment.utils.audit.Service.AuditLogDataJPAService;
import com.expriment.utils.audit.entity.AuditLogData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/Offers")
public class CDIOfferModuleController1 {

    @Autowired
    AuditLogDataJPAService auditLogDataJPAService;

    @GetMapping(value = "/ByCpId/{cp_id}", produces = {MediaType.APPLICATION_ATOM_XML_VALUE})
    public AuditLogData getOpportunityIdDetails(Integer cp_id) {
        LoggerClass.appLayerLogger.info("Cp_Id value is {}",cp_id);
        try {
            return auditLogDataJPAService.findAllAuditLogDataByID(cp_id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    }
