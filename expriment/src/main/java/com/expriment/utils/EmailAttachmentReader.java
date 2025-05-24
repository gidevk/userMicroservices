package com.expriment.utils;


import com.expriment.Testing.study.UnsafeSingleton;
import com.expriment.utils.audit.LoggerClass;
import jdk.nashorn.internal.runtime.logging.Loggable;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
public class EmailAttachmentReader {

    /*@Autowired
    private EmailConfig emailConfig;*/

   /* @Bean
    public ImapMailReceiver mailReceiver() {
        ImapMailReceiver receiver = new ImapMailReceiver("imaps://your-email@gmail.com:your-password@imap.gmail.com:993/INBOX");
        receiver.setShouldMarkMessagesAsRead(true);
        receiver.setShouldDeleteMessages(false);
        return receiver;
    }*/


    // Define your filter date
    LocalDateTime filterDateTime = LocalDate.now().atStartOfDay();
    Date filterDate = Date.from(filterDateTime.atZone(ZoneId.systemDefault()).toInstant());

    public void readEmailsAndDownloadAttachments()  throws MessagingException, IOException {
        System.out.println("readEmailsAndDownloadAttachments start: ");

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getInstance(props, null);
        Store store = session.getStore();
//        store.connect("imap.gmail.com", "your-email@gmail.com", "your-app-password");
        store.connect("imap.gmail.com","idevk995@gmail.com", "Gm@95074");

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);


// Calculate the date 2 days ago
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -2);
        Date twoDaysAgo = calendar.getTime();

// Fetch only messages received in the last 2 days
        ReceivedDateTerm term = new ReceivedDateTerm(ComparisonTerm.GE, twoDaysAgo);

        Message[] messages = inbox.search(term);//inbox.getMessages();

        LoggerClass.appLayerLogger.info(" filter Date {}", filterDate);
        for (Message message : messages) {
            Date receivedDate = message.getReceivedDate();
            LoggerClass.appLayerLogger.info(" receivedDate {}", receivedDate);

            // Filter based on received date
            if (receivedDate != null && receivedDate.after(filterDate)) {
                if (message.isMimeType("multipart/*")) {
                    Multipart multipart = (Multipart) message.getContent();

                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart part = multipart.getBodyPart(i);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            String fileName = part.getFileName();
                            MimeBodyPart mimePart = (MimeBodyPart) part;
                            mimePart.saveFile(new File("D:/" + fileName));
                            System.out.println("Attachment saved: " + fileName);
                        }
                    }
                }
            }
        }  inbox.close(false);
        store.close();
    }

    public static void main(String[] args) {
       /* String str= "WELCOME";

        if (str == null || str.isEmpty()) return;

        HashSet<Character> seen= new HashSet<>();
        StringBuilder result = new StringBuilder();

        for (Character c:str.toCharArray()           ) {
            if (seen.add(c)) result.append(c);
        }
        System.out.println("Output is "+result);*/

      /*  Runnable task = () -> {
            UnsafeSingleton s = UnsafeSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " got: " + s);
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();*/

        Runnable task = () -> {
            UnsafeSingleton s = UnsafeSingleton.getInstance();
            if (!s.isInitialized()) {
                System.out.println(Thread.currentThread().getName() + " got a partially initialized object!");
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();


    }
}
