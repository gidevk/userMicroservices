package com.expriment.utils.audit.DAO;

import com.expriment.utils.audit.entity.AuditLogData;

import java.util.List;

public interface AuditLogDataDAO{

    AuditLogData saveAuditLogs(AuditLogData auditLogData);

//    AuditLogData getAuditLogDataByCpIdgetAuditLogDataByLgId(Integer auditLogId);

    AuditLogData getAuditLogDataByLgId(Integer auditLogId);

    List<AuditLogData> getAuditLogDataByCpId(Integer auditLogId);
//    AuditLogData getAuditLogDataByLgId(Integer lgId);
}
