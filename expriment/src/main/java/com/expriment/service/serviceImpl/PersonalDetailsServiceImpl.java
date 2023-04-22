package com.expriment.service.serviceImpl;

import com.expriment.DAO.UserDetailsDao;
import com.expriment.entity.UserDetails;
import com.expriment.service.PersonalDetailsService;
import com.expriment.utils.ProjectConstants;
import com.expriment.utils.audit.entity.vo.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonalDetailsServiceImpl implements PersonalDetailsService {

    public static final Logger logger = LogManager.getLogger(PersonalDetailsServiceImpl.class);

    @Autowired
    UserDetailsDao userDetailsDao;

    @Override
    public ResponseEntity<?> savePersonalDetails(UserDetails userDetails){
        UserDetails userDetailsResponse =new UserDetails();
        ErrorResponse errorResponse = new ErrorResponse();

        try {
            logger.info("savePersonalDetails is Stared.");

           /* if (Integer.toString(userDetails.getCpId()).equalsIgnoreCase("null"))
                userDetails.setCreatedDate(new Date());
*/
            if (userDetails.getCreatedDate() == null)
                userDetails.setCreatedDate(new Date());
            userDetails.setUpdateDate(new Date());
            userDetailsResponse = userDetailsDao.saveUserDetails(userDetails);

            if (userDetailsResponse == null){
                errorResponse.setStatus(ProjectConstants.FAIL);
                errorResponse.setErrorMessage(ProjectConstants.DATA_NOT_FOUND_MESSAGE);
                errorResponse.setErrorCode(ProjectConstants.DATA_NOT_FOUND_ERROR_CODE);

                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
            logger.info(" getPersonalDetails is Ended.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(userDetailsResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> getPersonalDetails(Integer cpId){
        UserDetails userDetailsResponse = new UserDetails();
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            logger.info(" getPersonalDetails is Stared.");

            userDetailsResponse= userDetailsDao.getUserDetails(cpId);

            if (userDetailsResponse == null){
                errorResponse.setStatus(ProjectConstants.FAIL);
                errorResponse.setErrorMessage(ProjectConstants.DATA_NOT_FOUND_MESSAGE);
                errorResponse.setErrorCode(ProjectConstants.DATA_NOT_FOUND_ERROR_CODE);
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
            logger.info(" getPersonalDetails is Ended.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(userDetailsResponse, HttpStatus.OK);

    }
    @Override
    public ResponseEntity<?> updatePersonalDetails(UserDetails userDetails){
        UserDetails userDetailsResponse = new UserDetails();
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            logger.info(" updatePersonalDetails is Stared.");
            userDetailsResponse= userDetailsDao.saveOrUpdateUserDteail(userDetails);

            if (userDetailsResponse == null){
                errorResponse.setStatus(ProjectConstants.FAIL);
                errorResponse.setErrorMessage(ProjectConstants.DATA_NOT_FOUND_MESSAGE);
                errorResponse.setErrorCode(ProjectConstants.DATA_NOT_FOUND_ERROR_CODE);
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
            logger.info(" updatePersonalDetails is Ended.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(userDetailsResponse, HttpStatus.OK);
    }
}
