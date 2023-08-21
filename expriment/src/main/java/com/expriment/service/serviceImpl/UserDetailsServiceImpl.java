package com.expriment.service.serviceImpl;

//import com.expriment.DAO.Impl.UserDetailsJPA;
import com.expriment.DAO.UserDetailsDao;
import com.expriment.entity.UserDetails;
import com.expriment.service.UserDetailsService;
import com.expriment.utils.audit.LoggerClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    public static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

 /*    @Autowired
    UserDetailsJPA  userDetailsJPA;

   @Autowired
    AuditLogDataDAO auditLogDataDAO;

    @Autowired
    AuditLogDataJPA auditLogDataJPA;*/

//    ObjectMapper objectMapper= new ObjectMapper();

    @Autowired
    UserDetailsDao userDetailsDao;

    EntityManager entityManager;
    public UserDetailsServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public UserDetails saveUserDetails(UserDetails userDetails){
        UserDetails user=null;
        try {
            LoggerClass.appLayerLogger.info("SaveUserDetails Started.");
//            entityManager.persist(userDetails); // setting the userId..
//            userDetails.setCpId(userDetails.getCpId());
//            user =userDetailsJPA.save(userDetails);
            userDetailsDao.saveUserDetails(userDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            LoggerClass.appLayerLogger.info("SaveUserDetails Ended Successfully.");
        }
       return user;
   }
   @Override
    public UserDetails getUserDetails(Integer cpId) {
        UserDetails userEntity= null;
//       AuditLogData auditLogData = new AuditLogData();
       try {
           LoggerClass.appLayerLogger.info("GetUserDetails service Started.");


//           Optional<UserDetails> optional = userDetailsJPA.findAll(cpId);
//           userEntity = optional.get();

//           auditLogData.setCpId(userId);
//           auditLogData.setRequest(String.valueOf(userId));
//           auditLogData.setResponse(objectMapper.writeValueAsString(userEntity));
//           auditLogData.setCreatedDate(new Date());
//           auditLogData.setServiceName("GetUserDetails");
//           auditLogData.setStatus(userEntity!=null ?ProjectConstants.SUCCESS:ProjectConstants.FAIL);
//
//         auditLogDataDAO.saveAuditLogs(auditLogData);

       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           LoggerClass.appLayerLogger.info("Get User Details ended Successfully");
       }
       return userEntity;
   }
}
