package com.expriment.utils.audit.DAO;

import com.expriment.entity.AuditDetails;

import java.util.List;

public interface AuditDetailsDAO {
    AuditDetails saveAuditDetails(AuditDetails auditDetails);

    List<AuditDetails> loadAuditDetailsByLeadId(Long leadId);

    List<AuditDetails> getAuditDetailsList(String apiName, String leadId);

    List<AuditDetails> getAuditDetailsForOtp(String apiName, String mobileNumber);
}
