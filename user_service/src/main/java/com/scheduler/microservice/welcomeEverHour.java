package com.scheduler.microservice;

import com.expriment.utils.audit.LoggerClass;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class welcomeEverHour {

//    @Scheduled(fixedDelay=1000)//(cron = "${Welcome.every.hour1}")
    public void doJob(){
        try {
            LoggerClass.appLayerLogger.info("Welcome jobs started.");
            LoggerClass.appLayerLogger.info("Welcome to you time is {}",new Date());
            LoggerClass.appLayerLogger.info("Welcome Job Ended.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
