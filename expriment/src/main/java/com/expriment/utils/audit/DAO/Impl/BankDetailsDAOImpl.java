package com.expriment.utils.audit.DAO.Impl;

import com.expriment.utils.audit.Hibernate.HibernateUtils;
import com.expriment.utils.audit.DAO.BankDetailsDAO;
import com.expriment.utils.audit.LoggerClass;
import com.expriment.utils.audit.entity.BankDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankDetailsDAOImpl implements BankDetailsDAO {

    @Autowired
    HibernateUtils hibernateUtils;

    public static final Logger logger = LogManager.getLogger(BankDetailsDAOImpl.class);

    @Override
    public BankDetails saveBankDetails(BankDetails bankDetails){
        BankDetails bankDetailsResponse = new BankDetails();
        try {

            if (bankDetails != null) {
                bankDetailsResponse= hibernateUtils.saveEntity(bankDetails);
            }else {
                LoggerClass.appLayerLogger.info("BankDetails is Empty.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankDetailsResponse;
    }

    @Override
    public BankDetails getBankDetailsByBankId(Integer bankId){
        BankDetails bankDetailsResponse =new BankDetails();
        try {

            bankDetailsResponse = hibernateUtils.findEntityById(BankDetails.class, bankId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankDetailsResponse;
    }

    @Override
    public BankDetails getBankDetailsByAccountNumber(Integer accountNumber){
        BankDetails bankDetailsResponse =new BankDetails();
        try {
            LoggerClass.appLayerLogger.debug("getAuditLogDataByLgId Started.");
            String hql = "SELECT bank FROM BankDetails as bank WHERE accountNumber ="+accountNumber.toString();

            bankDetailsResponse= (BankDetails) hibernateUtils.loadEntitiesByHQL(hql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankDetailsResponse;
    }
}
