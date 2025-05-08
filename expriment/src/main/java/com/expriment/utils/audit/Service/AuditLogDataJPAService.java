package com.expriment.utils.audit.Service;

import com.expriment.utils.audit.entity.AuditLogData;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuditLogDataJPAService {
    AuditLogData findAllAuditLogDataByID(Integer cp_id) throws JsonProcessingException;
}
