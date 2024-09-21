package com.clientServices.controller;

import com.expriment.JobScheduler.welcomeEverHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Scanner;

@Component
public class offer {

    @Autowired (required = true)
    welcomeEverHour appProps;

    @RequestMapping(name = "/test")
    public void test(){
        System.out.println("hello");
        appProps.doJob();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inString = scanner.nextLine();

        int sum = 0;
        boolean foundDigit = false;

        for (int i = 0; i < inString.length(); i++) {
            char ch = inString.charAt(i);

            if (ch == '.') break;

            if (Character.isDigit(ch)) {
                sum += Character.getNumericValue(ch);
                foundDigit = true;
            }
        }
        if (!foundDigit) {
            sum = -1;
        }

        System.out.println(sum);
    }
}