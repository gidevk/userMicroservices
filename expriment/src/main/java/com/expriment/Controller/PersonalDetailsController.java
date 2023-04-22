package com.expriment.Controller;

import com.expriment.entity.UserDetails;
import com.expriment.service.PersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/personalDetails")
public class PersonalDetailsController {

    @Autowired
    PersonalDetailsService personalDetailsService;

    @PostMapping(value="/savePersonalDetails", produces = {MediaType.APPLICATION_JSON_VALUE})///personalDetails/savePersonalDetails
   public ResponseEntity<?> savePersonalDetails(@RequestBody UserDetails userDetails){
       return personalDetailsService.savePersonalDetails(userDetails);
   }
    @GetMapping(value = "/getPersonalDetails/{cpId}", produces = {MediaType.APPLICATION_JSON_VALUE})///personalDetails/getPersonalDetails
    public ResponseEntity<?> getPersonalDetails(@PathVariable("cpId") Integer cpId){
       return personalDetailsService.getPersonalDetails(cpId);
    }
    @PostMapping(value = "/updatePersonalDetails",produces = {MediaType.APPLICATION_JSON_VALUE})//    /personalDetails/getPersonalDetails
    public ResponseEntity<?> updatePersonalDetails(@RequestBody UserDetails userDetails){
       return personalDetailsService.updatePersonalDetails(userDetails);
    }
}
