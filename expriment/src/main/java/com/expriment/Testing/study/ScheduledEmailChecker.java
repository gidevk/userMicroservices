package com.expriment.Testing.study;

import com.expriment.utils.EmailAttachmentReader;
import com.expriment.utils.emailsender.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ScheduledEmailChecker {

    private EmailAttachmentReader emailReader;

    @Autowired
    EmailService emailService;


//    @Scheduled(fixedRate = 3600000) // every 1 hour
    public void checkEmailInbox() {
        try {
            emailReader.readEmailsAndDownloadAttachments();

            emailService.sendEmail("idjee0001@gmail.com",
                    "email Testing by using springBoot",
                    "Hi, this testing is successfully tested.");

            File file = new File("D:/IndradevKumarResume.pdf");
             emailService.sendEmailWithAttachment("idjee0001@gmail.com",
                    "Attachment email Testing by using springBoot"+System.currentTimeMillis(),
                    "Hi, this testing is successfully tested.",file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
