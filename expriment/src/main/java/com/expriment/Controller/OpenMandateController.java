package com.expriment.Controller;

import com.expriment.entity.vo.OpenMadateReq;
import com.expriment.service.OpenMandateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @author Indradev.kuamr
 */
@Controller
@RequestMapping("/openMandate")
public class OpenMandateController {

   /* @Autowired
    OpenMandateService openMandateService;
*/
//    @PostMapping("/enquiry")
//    public ResponseEntity<?> enquiryApi(@RequestBody EnquiryRequest request){
//        return new ResponseEntity<>(openMandateService.enquiryApi(request), HttpStatus.OK);
//    }

//    @PostMapping("/token")
//    public ResponseEntity<?> createToken(@RequestBody MandatePayload request){
//        return new ResponseEntity<>(openMandateService.createToken(request), HttpStatus.OK);
//    }

//    @GetMapping("/getbatchId")
//    public ResponseEntity<?> getBatchId(){
//        return new ResponseEntity<>(openMandateService.getBatchId(), HttpStatus.OK);
//    }

  /*  @PostMapping("/openmandate")
    public ResponseEntity<?> OpenMandateAPI(@RequestBody String request){
        return new ResponseEntity<>(openMandateService.OpenMandate(request), HttpStatus.OK);
    }
*/
   /* @PostMapping("/openNachApis")
    public ResponseEntity<?> allopenmandate(@RequestBody OpenMadateReq request){
        return new ResponseEntity<>(openMandateService.OpenMandeteOperation(request.getEnquiryRequest(),request.getLeadId()), HttpStatus.OK);
    }*/
}
