package com.expriment.DAO;

import com.expriment.entity.ApplicationStatus;

public interface ApplicationStatusDAO {

	ApplicationStatus getApplicationStatus(String leadId);
	ApplicationStatus saveOrUpdateApplicationStatus(ApplicationStatus applicationStatus);
	
	/*APIServiceUrls getAPIServiceUrls(String requestType);
	ApplicationStatusHist saveOrUpdateApplicationStatusHist(ApplicationStatusHist applicationStatusHist);
*/
	
	/*List<APIServiceUrls> loadAllAPIServiceUrls();
	List<Object[]> getCustomerData(String leadId);
	AppLeadPerformedUser getAppLeadPerformedUser(String leadId);
	AppLeadPerformedUser saveAppLeadPerformedUser(AppLeadPerformedUser newDetails); */
	
}
