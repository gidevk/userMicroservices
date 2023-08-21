package com.expriment.utils;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Configuration
@Order(1)
public class MyStartupRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Your initialization code here
        // This code will run at the very beginning of your application startup
        System.out.println("Initializing before Log4j configuration in MyStartupRunner");

    }
}

@Component
class MyStartupRunners implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Your initialization logic here
        System.out.println("Initializing before Log4j configuration");
    }
}
