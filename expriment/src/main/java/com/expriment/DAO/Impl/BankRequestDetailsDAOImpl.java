package com.expriment.DAO.Impl;

import com.expriment.DAO.BankRequestDetailsDAO;
import com.expriment.entity.BankRequestDetails;
import com.expriment.utils.audit.DAO.BankDetailsDAO;
import com.expriment.utils.audit.Hibernate.HibernateUtils;
import com.expriment.utils.audit.LoggerClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankRequestDetailsDAOImpl implements BankRequestDetailsDAO {
    
    @Autowired
    HibernateUtils hibernateUtils;

    @Override
    public BankRequestDetails saveBankRequestDetails(BankRequestDetails bankRequestDetails) {
        return hibernateUtils.saveOrUpdateEntity(bankRequestDetails);
    }

    @Override
    public BankRequestDetails getBankRequestDetailsByLeadId(String leadId) {
        return hibernateUtils.findEntityByCriteria(BankRequestDetails.class,"leadId",leadId);
    }

	/*@Override
	public List<BankDetails> getStates() {
		return hibernateUtils.loadEntitiesByCriteria(BankDetails.class);
	}*/

    @Override
    public List<String> getStates(String bankName) {
        try {
            String hqlQuery = "SELECT DISTINCT pm.state FROM IFSCBankDetails pm WHERE pm.bankName=" +"'"+ bankName +"'"+" GROUP BY pm.state";
            List<String> statesList = hibernateUtils.loadEntitiesByHQL(hqlQuery);
            return statesList;
        }catch(Exception e) {
            LoggerClass.appLayerLogger.error("error while getting state wise states "+e);
        }
        return null;
    }


    @Override
    public List<String> getBankDetailsByState(String state, String bankName) {
        try {
            String hqlQuery = "SELECT pm.district FROM IFSCBankDetails pm WHERE pm.state=" +"'"+ state +"'"+" AND pm.bankName=" +"'"+ bankName +"'"+" GROUP BY pm.district";
            List<String> citiesList = hibernateUtils.loadEntitiesByHQL(hqlQuery);
            return citiesList;
        }catch(Exception e) {
            LoggerClass.appLayerLogger.error("error while getting cities "+e);
        }
        return null;
    }


    @Override
    public List<String> getBankDetailsByCity(String city, String bankName) {
        try {
            String hqlQuery = "SELECT pm.branch FROM IFSCBankDetails pm WHERE pm.district=" +"'"+ city +"'"+" AND pm.bankName=" +"'"+ bankName +"'"+" GROUP BY pm.branch";
            List<String> citiesList = hibernateUtils.loadEntitiesByHQL(hqlQuery);
            return citiesList;
        }catch(Exception e) {
            LoggerClass.appLayerLogger.error("error while getting branches "+e);
        }
        return null;
    }

    @Override
    public Map<String, String> getBankDetailsByBranch(String branch, String bankName) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            String hqlQuery = "SELECT pm.address FROM IFSCBankDetails pm WHERE pm.branch=" +"'"+ branch +"'"+"  GROUP BY pm.address";
            List<String> address = hibernateUtils.loadEntitiesByHQL(hqlQuery);
            String hqlQuery2 = "SELECT pm.ifscCode FROM IFSCBankDetails pm WHERE pm.branch=" +"'"+ branch +"'"+"  AND pm.bankName=" +"'"+ bankName +"'"+" GROUP BY pm.ifscCode";
            List<String> ifsc = hibernateUtils.loadEntitiesByHQL(hqlQuery2);
            if(address!=null && ifsc!=null) {
                result.put("address", address.get(0));
                result.put("IFSC", ifsc.get(0));
            }
            return result;
        }catch(Exception e) {
            LoggerClass.appLayerLogger.error("error while getting address details "+e);
        }
        return null;
    }

//    @Override
//    public IFSCBankDetails findIFSCBankDetailsByIfscCode(String ifscCode) {
//        return hibernateUtils.findEntityByCriteria(IFSCBankDetails.class, "ifscCode", ifscCode);
//    }
}
