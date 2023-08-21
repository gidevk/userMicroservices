package com.expriment.utils.audit.Service.Impl;

import com.expriment.utils.ProjectConstants;
import com.expriment.utils.audit.DAO.BankDetailsDAO;
import com.expriment.utils.audit.LoggerClass;
import com.expriment.utils.audit.Service.BankDetailsService;
import com.expriment.utils.audit.entity.BankDetails;
import com.expriment.utils.audit.entity.vo.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BankDetailsServiceImpl implements BankDetailsService {

    @Autowired
    BankDetailsDAO bankDetailsDAO;

    public static final Logger logger = LogManager.getLogger(BankDetailsServiceImpl.class);

    @Override
    public BankDetails saveBankDetails(BankDetails bankDetails){
        BankDetails bankDetailsResponse = new BankDetails();
        try {
            LoggerClass.appLayerLogger.info("Save bank Details stared");
            if (bankDetails != null && bankDetails.getCpId() != null) {
                bankDetails.setCreatedDate(new Date());
            }
            bankDetailsResponse= bankDetailsDAO.saveBankDetails(bankDetails);


            LoggerClass.appLayerLogger.info("Save bank details Ended.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankDetailsResponse;
    }

    @Override
    public ResponseEntity<?> getBankDetailsByBankId(Integer bankId){
        BankDetails bankDetailsResponse =new BankDetails();
        ErrorResponse errorResponse= new ErrorResponse();
        try {

            bankDetailsResponse = bankDetailsDAO.getBankDetailsByBankId(bankId);
            if (!bankDetailsResponse.getBankId().equals(bankId)){
                errorResponse.setErrorCode(ProjectConstants.DATA_NOT_FOUND_ERROR_CODE);
                errorResponse.setErrorMessage(ProjectConstants.DATA_NOT_FOUND_MESSAGE);
                errorResponse.setStatus(ProjectConstants.FAILURE);
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(bankDetailsResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getBankDetailsByAccountNumber(Integer accountNo){
        BankDetails bankDetailsResponse =new BankDetails();
        ErrorResponse errorResponse= new ErrorResponse();
        try {
           bankDetailsResponse= bankDetailsDAO.getBankDetailsByAccountNumber(accountNo);

           if (bankDetailsResponse.getBankId() == null){
               errorResponse.setErrorCode(ProjectConstants.DATA_NOT_FOUND_ERROR_CODE);
               errorResponse.setErrorMessage(ProjectConstants.DATA_NOT_FOUND_MESSAGE);
               errorResponse.setStatus(ProjectConstants.FAILURE);
               return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(bankDetailsResponse, HttpStatus.OK);
    }
}
