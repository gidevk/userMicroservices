package com.expriment.utils.audit.DAO.Impl;


import com.expriment.utils.audit.Hibernate.HibernateUtils;
import com.expriment.utils.audit.DAO.AuditLogDataDAO;
import com.expriment.utils.audit.LoggerClass;
import com.expriment.utils.audit.entity.AuditLogData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Repository
@Service
public class AuditLogDataDAOImpl implements AuditLogDataDAO {

    public static final Logger logger = LogManager.getLogger(AuditLogDataDAOImpl.class);
    @Autowired
    HibernateUtils hibernateUtils;

    @Override
//    @Transactional
    public AuditLogData saveAuditLogs(AuditLogData auditLogData) {
        AuditLogData auditLogDataResponse = new AuditLogData();
        try {
            LoggerClass.appLayerLogger.debug("saveAuditLogs Started.");
            auditLogDataResponse= hibernateUtils.saveOrUpdateEntity(auditLogData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auditLogDataResponse;
    }

   @Override
    public AuditLogData getAuditLogDataByLgId(Integer auditLogId) {
        AuditLogData auditLogData= new AuditLogData();
        try {
            LoggerClass.appLayerLogger.debug("getAuditLogDataByLgId Started.");
            auditLogData= hibernateUtils.findEntityById(AuditLogData.class,(auditLogId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auditLogData;
    }

    @Override
    public  List<AuditLogData> getAuditLogDataByCpId(Integer cpId) {
        List<AuditLogData> auditLogData = null;
        try {
            LoggerClass.appLayerLogger.debug("getAuditLogDataByLgId Started.");
            String hql = "SELECT aud FROM AuditLogData as aud WHERE cpId ="+cpId;
            auditLogData= hibernateUtils.loadEntitiesByHQL(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auditLogData;
    }
}
