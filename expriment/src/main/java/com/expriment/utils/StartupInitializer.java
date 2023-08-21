package com.expriment.utils;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class StartupInitializer {

    @PostConstruct
    public void init() {
        // Code to be executed on application startup
        System.out.println("Application has started!");
    }
}
