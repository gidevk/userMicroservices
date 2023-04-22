package com.expriment.DAO.Impl;

import com.expriment.DAO.CDIOfferModuleDataDAO;
import com.expriment.entity.CDIOfferModule;
import com.expriment.utils.audit.Hibernate.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CDIOfferModuleDataDAOImpl implements CDIOfferModuleDataDAO {

	@Autowired
	HibernateUtils hibernateUtils;

	private HibernateUtils getHibernateUtils() {
		return hibernateUtils;
	}

	@Override
	public CDIOfferModule getOfferDataByPlOpportunityId(String PlOpportunityId) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class, "PlOpportunityId", PlOpportunityId);
	}

	@Override
	public CDIOfferModule getOfferDataByPan(String pan) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class,"pan",pan);
	}
	
	@Override
	public CDIOfferModule getOfferDataByMobileNumber(Long mobileNo) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class,"mobileNo",mobileNo);
	}
	
	@Override
	public CDIOfferModule getOfferDataByCustomerHash(String customerHash) {
		return getHibernateUtils().findEntityByOrCriteria(CDIOfferModule.class, "customerHash", "customerHashNew", customerHash, customerHash);
	}

	@Override
	public CDIOfferModule getOfferDataByPanAndMobileNumber(Long mobileNo,String pan) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class,"mobileNo","pan",mobileNo,pan);
	}
	
	@Override
	public CDIOfferModule getOfferDataByMobileNumberAndWebtop(Long customerMobile,String webTopId) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class, "mobileNo","webTopId",customerMobile,webTopId);
	}

	@Override
	public CDIOfferModule saveOfferModuleData(CDIOfferModule cdiOfferModule) {
		return getHibernateUtils().saveOrUpdateEntity(cdiOfferModule);
	}

	@Override
	public List<CDIOfferModule> getOfferMobileNumbersList() {
		return getHibernateUtils().loadEntitiesByCriteria(CDIOfferModule.class);
	}
	
	@Override
	public CDIOfferModule getOfferDataByLeadId(Long leadId) {
		return  getHibernateUtils().findEntityByCriteria(CDIOfferModule.class,"leadId",leadId);
	}
	
	@Override
	public CDIOfferModule getOfferDataByCardNumber(String cardNumber) {
		return  getHibernateUtils().findEntityByCriteria(CDIOfferModule.class,"emiCardNumber",cardNumber);
	}
	
	@Override
	public Long getCountOfRecordsInDB() {
		Object obj = getHibernateUtils().getEntitiesCountByCriteria(CDIOfferModule.class);
		return new Long(obj.toString());
	}

	@Override
	public CDIOfferModule getOfferDataByWebtopAndOpportunityId(String webtopId, String opportunityId) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class, "webTopId", "opportunityId", webtopId, opportunityId);
	}

	@Override
	public CDIOfferModule getOfferDataByHashAndDOB(String customerHash, Date dob) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class, "customerHash", "dob", customerHash, dob);
	}
	
	@Override
	public CDIOfferModule getOfferDataByCustomerHashNewAndDOB(String customerHashNew, Date dob) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class, "customerHashNew", "dob", customerHashNew, dob);
	}
	
	@Override
	public CDIOfferModule getOfferDataByCustomerHashNew(String customerHashNew) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class, "customerHashNew", customerHashNew);
	}

	@Override
	public CDIOfferModule getOfferDataByPlLeadId(Long plLeadId) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class,"plLeadId",plLeadId);
	}
	
	@Override
	public CDIOfferModule getPLDataByWebtopAndOpportunityId(String webtopId, String opportunityId) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class, "plWebTopId", "plOpportunityId", webtopId, opportunityId);
	}

	@Override
	public CDIOfferModule getOfferDataByCustomerHashNewAndPAN_4Digits(String customerHashNew, String pan4digits) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class, "customerHashNew", "pan4digits",customerHashNew,pan4digits);
	}
	
	@Override
	public CDIOfferModule getOfferData( String webtopId,String opportunityId, String customerHashNew) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class, "plWebTopId", "plOpportunityId","customerHashNew", webtopId, opportunityId,customerHashNew);
	}

	@Override
	public CDIOfferModule getOfferDataByCustomerHashNewAndPlLeaadId(String customerHashNew, String leadId) {
		return getHibernateUtils().findEntityByCriteria(CDIOfferModule.class, "customerHashNew", "plLeadId",customerHashNew,Long.valueOf(leadId));

	}
}
