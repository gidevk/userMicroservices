package com.expriment.DAO.Impl;

import com.expriment.DAO.DigilockerDetailsDAO;
import com.expriment.entity.DigilockerDetails;
import com.expriment.utils.audit.Hibernate.HibernateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DigilockerDetailsDAOImpl implements DigilockerDetailsDAO {

    Logger logger = LogManager.getLogger("DigilockerDetailsDAOImpl");

    @Autowired
    HibernateUtils hibernateUtils;

    @Override
    public DigilockerDetails saveOrUpdate(DigilockerDetails digilockerDetails) {
        return hibernateUtils.saveOrUpdateEntity(digilockerDetails);
    }


    @Override
    public DigilockerDetails getDigilockerDetails(long leadId) {
        DigilockerDetails digilockerDetails=new DigilockerDetails();
        try {
            digilockerDetails =  hibernateUtils.findEntityById(DigilockerDetails.class, leadId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return digilockerDetails;

    }
}
