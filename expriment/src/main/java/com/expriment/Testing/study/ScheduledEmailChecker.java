package com.expriment.Testing.study;

import com.expriment.utils.EmailAttachmentReader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledEmailChecker {

    private final EmailAttachmentReader emailReader;

    public ScheduledEmailChecker(EmailAttachmentReader emailReader) {
        this.emailReader = emailReader;
    }

    @Scheduled(fixedRate = 3600000/12) // every 1 hour
    public void checkEmailInbox() {
        try {
            emailReader.readEmailsAndDownloadAttachments();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
