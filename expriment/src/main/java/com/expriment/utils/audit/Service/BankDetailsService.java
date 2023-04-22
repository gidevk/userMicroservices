package com.expriment.utils.audit.Service;

import com.expriment.utils.audit.entity.BankDetails;
import org.springframework.http.ResponseEntity;

public interface BankDetailsService {

    BankDetails saveBankDetails(BankDetails bankDetails);

    ResponseEntity<?> getBankDetailsByBankId(Integer bankId);

    ResponseEntity<?> getBankDetailsByAccountNumber(Integer accountNo);
}
