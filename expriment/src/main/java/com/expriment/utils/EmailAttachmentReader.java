package com.expriment.utils;


import com.expriment.Testing.study.UnsafeSingleton;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

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
    public void readEmailsAndDownloadAttachments() throws Exception {
        System.out.println("readEmailsAndDownloadAttachments start: ");

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getInstance(props, null);
        Store store = session.getStore();
//        store.connect("imap.gmail.com", "your-email@gmail.com", "your-app-password");
        store.connect("imap.gmail.com","idevk995@gmail.com", "Gm@95074");

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();
        for (Message message : messages) {
            if (message.isMimeType("multipart/*")) {
                Multipart multipart = (Multipart) message.getContent();

                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart part = multipart.getBodyPart(i);
                    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                        String fileName = part.getFileName();
                        MimeBodyPart mimePart = (MimeBodyPart) part;
                        mimePart.saveFile(new File("downloads/" + fileName));
                        System.out.println("Attachment saved: " + fileName);
                    }
                }
            }
        }
        inbox.close(false);
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
