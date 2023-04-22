package com.expriment.utils.audit.Controller;

import com.expriment.utils.audit.Service.BankDetailsService;
import com.expriment.utils.audit.entity.BankDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bankDetails")
public class BankDetailsController {
    @Autowired
    BankDetailsService bankDetailsService;

    @PostMapping(value = "/saveBank", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> saveBankDetails(@RequestBody BankDetails bankDetails){
        return new ResponseEntity<>(bankDetailsService.saveBankDetails(bankDetails), HttpStatus.OK);
    }

    @GetMapping("/bid/{bankId}")
    public ResponseEntity<?> getUserDetailsByBankId(@PathVariable("bankId") int bankId){
        return bankDetailsService.getBankDetailsByBankId(bankId);
    }
    @GetMapping("/{accountNo}")
    public ResponseEntity<?> getUserDetailsByAccountNo(@PathVariable("accountNo") int accountNo){
        return bankDetailsService.getBankDetailsByAccountNumber(accountNo);
    }
}
