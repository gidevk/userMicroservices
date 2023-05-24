package com.scheduler.microservice;

import Entity.vo.GameRequestPayload;
import Service.ColourGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class firstSchedulerJob {

    @GetMapping("/h")
    public String hello() {
        return "Hello, world!";
    }

    @Autowired
    ColourGameService colourGameService;

    @PostMapping(value="/play", produces = {MediaType.APPLICATION_JSON_VALUE})///personalDetails/savePersonalDetails
    public ResponseEntity<?> savePersonalDetails(@RequestBody GameRequestPayload gameRequestPayload){
        return colourGameService.gamePlayResult(gameRequestPayload);
    }
}
