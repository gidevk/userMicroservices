package com.expriment.DAO.Impl;

import com.expriment.DAO.UserDetailsDao;
import com.expriment.entity.UserDetails;
import com.expriment.utils.audit.Hibernate.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDerailsDaoImpl implements UserDetailsDao {

    @Autowired
    HibernateUtils hibernateUtils;

    @Override
    public UserDetails saveUserDetails(UserDetails userDetails){
        UserDetails user=null;
            try {
                user= hibernateUtils.saveEntity(userDetails);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return user;
    }

    @Override
    public UserDetails getUserDetails(Integer cpId) {
        UserDetails userDetails=new UserDetails();
        try {
            userDetails =  hibernateUtils.findEntityById(UserDetails.class, cpId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDetails;

    }

    @Override
    public UserDetails saveOrUpdateUserDteail(UserDetails userDetails) {
        UserDetails userDetailsResponse=new UserDetails();
        try {
            userDetailsResponse =  hibernateUtils.saveOrUpdateEntity(userDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDetailsResponse;

    }


}
