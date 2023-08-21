package com.expriment.Controller;

import com.expriment.Testing.DocUploadServiceImpl;
import com.expriment.Testing.EmudraDocRequest;
import com.expriment.Testing.EmudraRequest;
import com.expriment.Testing.SfdcTdlDocResponse;
import com.expriment.entity.UserEntity;
import com.expriment.service.XmlToJsonService;
import com.expriment.service.serviceImpl.EmudraServiceImpl;
import com.expriment.service.serviceImpl.UserDetailsServiceImpl;
import com.expriment.utils.audit.LoggerClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    UserService userService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    DocUploadServiceImpl docUploadService;

    @Autowired
    EmudraServiceImpl emudraService;

    @Autowired
    XmlToJsonService xmlToJsonService;

    @GetMapping("/{userId}")
    public UserEntity getUser(@PathVariable("userId") Long userId){
//        return userService.getUser(userId);
     return null;
    }

    @GetMapping("/find")
    public UserEntity getUserId(@PathVariable ("userId") Long id){
//        return userService.getUser(id);
        return null;
    }


    @PostMapping(value = "/leadId/{leadId}", produces = {MediaType.APPLICATION_JSON_VALUE}) ///user/leadId
    public ResponseEntity<?> validateString (@PathVariable ("leadId") String leadId){
        return  new ResponseEntity<EmudraRequest>(docUploadService.emudraPushEsignDoc((leadId)),HttpStatus.OK);
//        return  new ResponseEntity<String>(docUploadService.makeEsignPDF((leadId)),HttpStatus.OK);
//        return  new ResponseEntity<String>(docUploadService.combineTwoBase64Data(),HttpStatus.OK);
//        return new ResponseEntity<EmudraRequest>(docUploadService.emudraPushEsignDoc("hello", "pavan"),HttpStatus.OK);
//        return userService.validateString(str.toString()) == true ? str+" true" : str.toString() +" false";
    }
    @PostMapping(value = "/saveUser", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SfdcTdlDocResponse> saveDetails(@RequestBody SfdcTdlDocResponse sfdcTdlDocResponse){
            return (ResponseEntity<SfdcTdlDocResponse>) docUploadService.saveSfdcDocResponse(sfdcTdlDocResponse);
//        return new ResponseEntity<>(userDetailsService.saveUserDetails(userDetails), HttpStatus.OK);
    }

    @PostMapping(value = "/saveEmudra", produces = {MediaType.APPLICATION_JSON_VALUE}) // /user/saveEmudra
    public ResponseEntity<?> callEmudra(@RequestBody EmudraDocRequest emudraRequest){
     LoggerClass.appLayerLogger.info("hi /saveEmudra");
        return emudraService.saveEmudraDocumentService(emudraRequest);
//            return (ResponseEntity<SfdcTdlDocResponse>) docUploadService.saveSfdcDocResponse(sfdcTdlDocResponse);
//        return new ResponseEntity<>(userDetailsService.saveUserDetails(userDetails), HttpStatus.OK);
    }

    @PostMapping(value = "/digiloker",produces = {MediaType.APPLICATION_ATOM_XML_VALUE})
    public void digilocker(String data){
        xmlToJsonService.extractingXmlData(data);
//        xmlToJsonService.dobMatch(38480l);
//        return ResponseEntity(xmlToJsonService.extractingXmlData(data),HttpStatus.OK)
    }
}

