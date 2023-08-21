package com.expriment.utils;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        // Your initialization logic here
        System.out.println("Initializing application context...");
        // Perform any custom initialization tasks before XML configuration
    }
}
