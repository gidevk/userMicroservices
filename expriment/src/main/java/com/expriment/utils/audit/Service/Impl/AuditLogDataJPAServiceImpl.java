package com.expriment.utils.audit.Service.Impl;

import com.expriment.utils.audit.DAO.AuditLogDataJPA;
import com.expriment.utils.audit.Service.AuditLogDataJPAService;
import com.expriment.utils.audit.entity.AuditLogData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditLogDataJPAServiceImpl implements AuditLogDataJPAService {

    public static final Logger logger = LogManager.getLogger("AuditLogDataJPAServiceImpl");

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AuditLogDataJPA auditLogDataJPA;

    @Override
    public AuditLogData findAllAuditLogDataByID(Integer cp_id) throws JsonProcessingException {
        Optional<AuditLogData> opt = Optional.ofNullable(auditLogDataJPA.findByCpId(cp_id));

        logger.info("Pin code match failed {}",objectMapper.writeValueAsString(opt) );

        if (opt.isPresent())
            return opt.get();
        else
            return null;
    }
}
