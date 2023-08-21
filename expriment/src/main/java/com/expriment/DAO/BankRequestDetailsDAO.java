package com.expriment.DAO;

import com.expriment.entity.BankRequestDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BankRequestDetailsDAO {

    BankRequestDetails saveBankRequestDetails(BankRequestDetails bankRequestDetails);

    BankRequestDetails getBankRequestDetailsByLeadId(String leadId);

    List<String> getStates(String bankName);

    List<String> getBankDetailsByState(String state, String bankName);

    List<String> getBankDetailsByCity(String city, String bankName);

    Map<String, String> getBankDetailsByBranch(String branch, String bankName);

//    IFSCBankDetails findIFSCBankDetailsByIfscCode(String ifscCode);



}
