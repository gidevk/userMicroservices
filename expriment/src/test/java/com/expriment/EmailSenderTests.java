package com.expriment;

import com.expriment.utils.emailsender.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailSenderTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendEmail("idjee0001@gmail.com",
                "email Testing by using springBoot",
                "Hi, this testing is successfully tested.");
    }
}