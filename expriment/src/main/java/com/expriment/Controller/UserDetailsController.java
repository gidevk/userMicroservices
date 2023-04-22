package com.expriment.Controller;


import com.expriment.entity.UserDetails;
import com.expriment.service.UserDetailsService;
import com.expriment.utils.audit.DAO.AuditLogDataDAO;
import com.expriment.utils.audit.entity.AuditLogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@
@Controller
@RequestMapping("/userDetails")
public class UserDetailsController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuditLogDataDAO auditLogDataDAO;

    @PostMapping(value = "/saveUser", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDetails> saveDetails(@RequestBody UserDetails  userDetails){
        return new ResponseEntity<>(userDetailsService.saveUserDetails(userDetails), HttpStatus.OK);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserDetails> getUserDetails(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(userDetailsService.getUserDetails(userId), HttpStatus.OK);
    }

    @GetMapping("/get/{alId}")
    public ResponseEntity<AuditLogData> getAuditLog(@PathVariable("alId") Integer alId){
        return new ResponseEntity<AuditLogData>(auditLogDataDAO.getAuditLogDataByLgId(alId) , HttpStatus.OK);
    }
    @GetMapping("/getByCpId/{cpId}")
    public ResponseEntity<List<AuditLogData>> getAuditLogByCpId(@PathVariable("cpId") Integer cpId){
        return new ResponseEntity<List<AuditLogData>>(auditLogDataDAO.getAuditLogDataByCpId(cpId), HttpStatus.OK);
    }


}


/*
*
*
* Given a string A containing just the characters ’(‘ and ’)’.

Find the length of the longest valid (well-formed) parentheses substring.




Input Format:

The only argument given is string A.
Output Format:

Return the length of the longest valid (well-formed) parentheses substring.
Constraints:

1 <= length(A) <= 750000
For Example

Input 1:
    A = "(()"
Output 1:
    2
    Explanation 1:
        The longest valid parentheses substring is "()", which has length = 2.

Input 2:
    A = ")()())"
Output 2:
    4
    Explanation 2:
        The longest valid parentheses substring is "()()", which has length = 4.

        *
        *
        * */