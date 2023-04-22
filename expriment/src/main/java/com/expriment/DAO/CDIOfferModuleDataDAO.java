package com.expriment.DAO;

import com.expriment.entity.CDIOfferModule;

import java.util.Date;
import java.util.List;

public interface CDIOfferModuleDataDAO {

	CDIOfferModule getOfferDataByPlOpportunityId(String PlOpportunityId);

	CDIOfferModule getOfferDataByPan(String pan);
	
	CDIOfferModule getOfferDataByMobileNumber(Long mobileNo);
	
	CDIOfferModule getOfferDataByCustomerHash(String customerHash);
	
	CDIOfferModule getOfferDataByPanAndMobileNumber(Long mobileNo,String pan);
	
	CDIOfferModule getOfferDataByMobileNumberAndWebtop(Long customerMobile,String webtopId);

	CDIOfferModule saveOfferModuleData(CDIOfferModule cdiOfferModule);

	List<CDIOfferModule> getOfferMobileNumbersList();
	
	CDIOfferModule getOfferDataByLeadId(Long leadId);
	
	CDIOfferModule getOfferDataByCardNumber(String cardNumber);
	
	Long getCountOfRecordsInDB();

	CDIOfferModule getOfferDataByWebtopAndOpportunityId(String webtopId, String opportunityId);

	CDIOfferModule getOfferDataByHashAndDOB(String customerHash, Date dob);
	
	CDIOfferModule getOfferDataByCustomerHashNewAndDOB(String customerHashNew, Date dob);

	CDIOfferModule getOfferDataByCustomerHashNew(String customerHashNew);
	
	CDIOfferModule getOfferDataByPlLeadId(Long plLeadId);
	
	CDIOfferModule getPLDataByWebtopAndOpportunityId(String webtopId, String opportunityId);

	CDIOfferModule getOfferDataByCustomerHashNewAndPAN_4Digits(String customerHashAndNew, String pan);

	CDIOfferModule getOfferData(String webtopId, String opportunityId, String customerHashNew);
	
	CDIOfferModule getOfferDataByCustomerHashNewAndPlLeaadId(String customerHashNew,String leadId);

}
