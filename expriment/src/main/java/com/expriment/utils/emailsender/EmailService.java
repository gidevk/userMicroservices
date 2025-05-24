package com.expriment.utils.emailsender;

import com.expriment.utils.audit.LoggerClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendEmail(String to, String subject, String body){
        try {
            LoggerClass.appLayerLogger.info("this is sendEmail class");
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);

            javaMailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendEmailWithAttachment(String to, String subject, String body, File attachment) {
        try {
            LoggerClass.appLayerLogger.info("this is sendEmailWithAttachment class");

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // 'true' indicates multipart message

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            if (attachment != null && attachment.exists()) {
                LoggerClass.appLayerLogger.info("Attachment Name {} and Attachment {}",attachment.getName(), attachment);
                helper.addAttachment(attachment.getName(), attachment);
            }

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
