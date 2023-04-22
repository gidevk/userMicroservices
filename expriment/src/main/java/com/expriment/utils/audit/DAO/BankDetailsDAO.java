package com.expriment.utils.audit.DAO;

import com.expriment.utils.audit.entity.BankDetails;

public interface BankDetailsDAO {

    BankDetails saveBankDetails(BankDetails bankDetails);

    BankDetails getBankDetailsByBankId(Integer bankId);

    BankDetails getBankDetailsByAccountNumber(Integer bankId);
}
