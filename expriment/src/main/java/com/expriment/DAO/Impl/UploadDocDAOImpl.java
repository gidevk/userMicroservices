package com.expriment.DAO.Impl;

import com.expriment.entity.UploadDoc;
import com.expriment.utils.audit.Hibernate.HibernateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UploadDocDAOImpl implements com.expriment.DAO.UploadDocDAO {
	
	Logger logger = LogManager.getLogger("UploadDocDAOImpl");

	@Autowired
	HibernateUtils hibernateUtils;

	private HibernateUtils getHibernateUtils() {
		return hibernateUtils;
	}

	@Override
	public UploadDoc saveOrUpdate(UploadDoc uploadDoc) {
		return getHibernateUtils().saveOrUpdateEntity(uploadDoc);
	}

	@Override
	public List<UploadDoc> getUploadDocResByLeadId(String leadId, String docUploadType) {
		String query = " FROM UploadDoc obj WHERE obj.leadId="+leadId+" and obj.docUploadType='"+docUploadType+"' order by obj.updatedDate desc ";
		 
		return getHibernateUtils().loadEntitiesByHQL(query);
		  //return getHibernateUtils().loadEntitiesByCriteria(UploadDoc.class, "jApplicationId", "docUploadType", appId, docUploadType);
	}
	
	@Override
	public List<UploadDoc> getUploadDocResByLeadIdAndDocName(String leadId, String docUploadName) {
		String query = " FROM UploadDoc obj WHERE obj.leadId="+leadId+" and obj.docUploadName='"+docUploadName+"'";
		 
		return getHibernateUtils().loadEntitiesByHQL(query);
		  //return getHibernateUtils().loadEntitiesByCriteria(UploadDoc.class, "jApplicationId", "docUploadType", appId, docUploadType);
	}

	
	@Override
	public List<UploadDoc> getUploadDocResByPlleadIdAndDocName(String plleadId, String docUploadName) {
		String query = " FROM UploadDoc obj WHERE obj.plleadId="+plleadId+" and obj.docUploadName='"+docUploadName+"'";
		return getHibernateUtils().loadEntitiesByHQL(query);
	}
	
	
	@Override
	public List<String> getDocumentTypeByLeadId(String leadId) {
		String query = "SELECT docUploadType FROM UploadDoc WHERE leadId="+leadId;
		
		List<String> docTypeList = getHibernateUtils().loadEntitiesByHQL(query);		
		return docTypeList;
	}

	@Override
	public List<UploadDoc> getDocsToUpload(Short maxRetryCount) {
		//String query = "FROM UploadDoc obj WHERE (obj.docUploadRetStatus IS NULL OR obj.docUploadRetStatus IN ('FAIL')) AND obj.docUploadedToJocataStatus = 'SUCCESS' AND obj.docRetryCount<"+maxRetryCount;
		String query = "FROM UploadDoc obj WHERE obj.docUploadedToJocataStatus = 'SUCCESS' AND (obj.docUploadRetStatus IS NULL OR obj.docUploadRetStatus='FAIL') AND  obj.docRetryCount<"+maxRetryCount;
		List<UploadDoc> docList = getHibernateUtils().loadEntitiesByHQL(query);		
		return docList;
	}

	@Override
	public List<UploadDoc> getUploadedDocsByLeadId(String leadId) {
		return getHibernateUtils().loadEntitiesByCriteria(UploadDoc.class, "leadId", leadId);
	}

	@Override
	public UploadDoc getUploadDocByLeadId(String leadId) {
		return getHibernateUtils().findEntityByCriteria(UploadDoc.class, "leadId", leadId);
	}
	
/*
	@Override
	public UploadDoc getUploadDocResByDocId(Long docId) {
		return getHibernateUtils().findEntityByCriteria(UploadDoc.class, "docId", docId);
	}
*/

	/*@Override
	public List<DocumentName> getDocumentsByLoanType(String loanType) {

		String query = "FROM DocumentName obj WHERE obj.loanType ='"+loanType+"'";
		
		List<DocumentName> documentNamesList = getHibernateUtils().loadEntitiesByHQL(query);		
		return documentNamesList;
	}*/
	
	/*@Override
	public SfdcDetails getDetailsByAppId(String appId) {
		return getHibernateUtils().findEntityByCriteria(SfdcDetails.class, "jApplicationId", appId);
	}*/
	
	
	
/*	@Override
	public NameMatchingUploadDoc saveOrUpdate(NameMatchingUploadDoc nameMatchingUploadDoc) {
	
		return getHibernateUtils().saveOrUpdateEntity(nameMatchingUploadDoc);
	}*/

	
	/*@Override
	public List<NameMatchingUploadDoc> getNameMatchingCustomerUploadDetailsByLeadId(String leadId) {
		return getHibernateUtils().loadEntitiesByCriteria(NameMatchingUploadDoc.class, "leadId", leadId);
	}*/

	@Override
	public List<UploadDoc> getUploadDocResByPlleadIdAndContainsDocName(String plleadId, String docUploadName) {
		String query = " FROM UploadDoc obj WHERE obj.plleadId="+plleadId+" and obj.docUploadName like '"+docUploadName+"%'";
		return getHibernateUtils().loadEntitiesByHQL(query);
	}

	@Override
	public List<UploadDoc> getUploadDocResByPlleadIdAndDocType(String plleadId, String docType) {
		String query = " FROM UploadDoc obj WHERE obj.plleadId="+plleadId+" and obj.docUploadName like '"+ docType+"%'";
		return getHibernateUtils().loadEntitiesByHQL(query);
	}
}
