package com.expriment.DAO.Impl;

import com.expriment.DAO.ApplicationStatusDAO;
import com.expriment.entity.ApplicationStatus;
import com.expriment.utils.audit.Hibernate.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ApplicationStatusDAOImpl implements ApplicationStatusDAO {

	@Autowired
	HibernateUtils hibernateUtils;
	
	@Override
	public ApplicationStatus getApplicationStatus(String leadId) {
		return hibernateUtils.findEntityByCriteria(ApplicationStatus.class, "leadId", leadId);
	}

	@Override
	public ApplicationStatus saveOrUpdateApplicationStatus(ApplicationStatus applicationStatus) {
		return hibernateUtils.saveOrUpdateEntity(applicationStatus);
	}

/*
	@Override
	public APIServiceUrls getAPIServiceUrls(String requestType) {
		return hibernateUtils.findEntityByCriteria(APIServiceUrls.class, "requestType", requestType);
	}

	@Override
	public ApplicationStatusHist saveOrUpdateApplicationStatusHist(ApplicationStatusHist applicationStatusHist) {
		return hibernateUtils.saveOrUpdateEntity(applicationStatusHist);
	}
*/

	/*
	@Override
	public List<APIServiceUrls> loadAllAPIServiceUrls() {
		return hibernateUtils.loadEntitiesByCriteria(APIServiceUrls.class);
	}

	@Override
	public List<Object[]> getCustomerData(String leadId) {
		List<Object[]> details = null;
		try {
			String query = "SELECT `tbl_app_lead_mapping`.`device_type`, `tbl_app_lead_mapping`.`lead_id`, `tbl_app_lead_mapping`.`mobile_number`,\n"+
					"`tbl_app_lead_mapping`.`stage`, `tbl_app_lead_mapping`.`appl_source`, `tbl_webtop_creation`.`createwebtop_webtop_no`\n,"+
					" `tbl_dtl_personal_info`.`dob`,`tbl_pan_validation`.`pan_number`,\n"+
					"`tbl_vm_final_response`.`cibil_score`, `tbl_appl_addl_info`.`dropoff_identifiers`,\n"+
					"CASE\n"+
					"WHEN `tbl_dtl_personal_info`.`first_name`='' THEN\n"+
					"`tbl_personal_info`.`first_name`\n"+
					"ELSE\n"+
					"`tbl_dtl_personal_info`.`first_name`\n"+
					"END AS first_name,\n"+
					"CASE\n"+
					"WHEN `tbl_dtl_personal_info`.`middle_name`='' THEN\n"+
					"`tbl_personal_info`.`middle_name`\n"+
					"ELSE\n"+
					"`tbl_dtl_personal_info`.`middle_name`\n"+
					"END AS middle_name,\n"+
					"CASE\n"+
					"WHEN `tbl_dtl_personal_info`.`last_name`='' THEN\n"+
					"`tbl_personal_info`.`last_name`\n"+
					"ELSE\n"+
					"`tbl_dtl_personal_info`.`last_name`\n"+
					"END AS last_name\n,"+
					"tbl_appl_addl_info.is_already_rejected_at_jocata\n"+
					"FROM ((((((tbl_app_lead_mapping\n"+
					"LEFT JOIN tbl_dtl_personal_info ON tbl_app_lead_mapping.`j_application_id`=tbl_dtl_personal_info.`j_application_id`)\n"+
					"LEFT JOIN tbl_pan_validation ON tbl_app_lead_mapping.`j_application_id`=tbl_pan_validation.`j_application_id`)\n"+
					"LEFT JOIN tbl_vm_final_response ON tbl_app_lead_mapping.`j_application_id`=tbl_vm_final_response.`j_application_id`)\n"+
					"LEFT JOIN tbl_appl_addl_info ON tbl_app_lead_mapping.`j_application_id`=tbl_appl_addl_info.`j_application_id`)\n"+
					"LEFT JOIN tbl_webtop_creation ON tbl_app_lead_mapping.`j_application_id`=tbl_webtop_creation.`j_application_id`)\n"+
					"LEFT JOIN tbl_personal_info ON tbl_app_lead_mapping.`j_application_id`=tbl_personal_info.`j_application_id`)\n"+
					"WHERE tbl_app_lead_mapping.`j_application_id` = "+leadId;
			
			logger.info(" getCustomerData query " +  query);
			details = hibernateUtils.loadDetailsBySqlQuery(query);
			return details;
		} catch (Exception e) {
			logger.error("Exception while getting details" + e.getMessage());
			return null;
		}
	}

	@Override
	public AppLeadPerformedUser getAppLeadPerformedUser(String leadId) {
		return hibernateUtils.findEntityByCriteria(AppLeadPerformedUser.class, "leadId", leadId);
	}

	@Override
	public AppLeadPerformedUser saveAppLeadPerformedUser(AppLeadPerformedUser newDetails) {
		return hibernateUtils.saveOrUpdateEntity(newDetails);
	}
	*/
	
}

