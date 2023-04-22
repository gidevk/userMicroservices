package com.expriment.utils.audit.DAO.Impl;

import com.expriment.entity.AuditDetails;
import com.expriment.utils.audit.DAO.AuditDetailsDAO;
import com.expriment.utils.audit.Hibernate.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AuditDetailsDAOImpl implements AuditDetailsDAO {

	@Autowired
	HibernateUtils hibernateUtils;


	@Override
	public AuditDetails saveAuditDetails(AuditDetails auditDetails) {
		return hibernateUtils.saveEntity(auditDetails);
	}

	@Override
	public List<AuditDetails> loadAuditDetailsByLeadId(Long leadId) {
		return hibernateUtils.loadEntitiesByCriteria(AuditDetails.class, "leadId", leadId);
	}

	@Override
	public List<AuditDetails> getAuditDetailsList(String apiName, String leadId) {
		//return hibernateUtils.loadEntitiesByCriteria(AuditDetails.class, "jApplicationId", "apiName", appId, apiName);
		
		String hqlQuery = "FROM AuditDetails audit WHERE audit.apiName ='"+apiName+"' AND audit.leadId ="+leadId+" order by audit.requestTime DESC";
		
		List<AuditDetails>  auditDetailsList = hibernateUtils.loadEntitiesByHQL(hqlQuery);
		
		return auditDetailsList;
	}
	
	@Override
	public List<AuditDetails> getAuditDetailsForOtp(String apiName, String mobileNumber) {
		//return hibernateUtils.loadEntitiesByCriteria(AuditDetails.class, "mobileNumber", "apiName", mobileNumber, apiName);
		
		String hqlQuery = "FROM AuditDetails audit WHERE audit.apiName ='"+apiName+"' AND audit.mobileNumber ="+mobileNumber+" order by audit.requestTime DESC";
		
		List<AuditDetails>  auditDetailsList = hibernateUtils.loadEntitiesByHQL(hqlQuery);
		
		return auditDetailsList;
	}

}
