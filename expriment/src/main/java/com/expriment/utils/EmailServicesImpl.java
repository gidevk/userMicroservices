/*
package com.expriment.utils;

import com.expriment.DAO.Impl.AuditDetailsUtility;
import com.expriment.utils.audit.LoggerClass;
import com.expriment.utils.audit.entity.SmsMailResponse;
import com.expriment.utils.audit.entity.vo.SmsMailPayload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class EmailServicesImpl implements EmailServices {


    Logger logger = LogManager.getLogger("EmailServicesImpl");


	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	AuditDetailsUtility auditDetailsUtility;

	public  static String fromMailId=" idevk995@gmail.com";

    @Autowired
    AppProps appProps;

    @Override
    public SmsMailResponse sendMail(SmsMailPayload mailPayload) {

        LoggerClass.appLayerLogger.info("sendMail block");
        SmsMailResponse rootResponse = new SmsMailResponse();
        AuditDetailsPayload auditDetailsPayload = new AuditDetailsPayload();

        auditDetailsPayload.setLeadId(mailPayload.getLeadId());
        auditDetailsPayload.setApiName(ProjectConstants.MAIL_API_CUSTOMER_CARE);
        auditDetailsPayload.setPayload(mailPayload.getMessage());
        auditDetailsPayload.setRequestTime(new Date());
        auditDetailsPayload.setConversationId(String.valueOf(new Date().getTime()));
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {
            helper = new MimeMessageHelper(message,true);
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }
        try {
            helper.setFrom(appProps.getFromMailId());
            helper.setSentDate(new Date());
            helper.setTo(mailPayload.getMailTo());
            helper.setSubject(mailPayload.getSubject());

            javax.mail.internet.MimeMultipart mimeMultipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(mailPayload.getMessage(), "text/html");

            mimeMultipart.addBodyPart(messageBodyPart);
            helper.addAttachment(mailPayload.getFileName(), mailPayload.getFile());

            message.setContent(mimeMultipart);

            if(mailPayload != null && mailPayload.getFile() != null && mailPayload.getFile().exists()) {
                LoggerClass.appLayerLogger.info("inside attachment block");
                FileSystemResource fr = new FileSystemResource(mailPayload.getFile());
				helper.addAttachment(mailPayload.getFile().getName(), fr);

                LoggerClass.appLayerLogger.info("CompleteFilePath: {}",mailPayload.getCompleteFilePath());
                LoggerClass.appLayerLogger.info("File Name:{}",mailPayload.getFile().getName());
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(mailPayload.getCompleteFilePath());
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(mailPayload.getFile().getName());
                mimeMultipart.addBodyPart(messageBodyPart);

                // Send the complete message parts
                message.setContent(mimeMultipart);
            } else {
                LoggerClass.appLayerLogger.info("no attacgement");
            }

            javaMailSender.send(message);
            LoggerClass.appLayerLogger.info("Mail send Successfully");
            auditDetailsPayload.setResponse("Email sent successfully");
            auditDetailsPayload.setStatus(ProjectConstants.SUCCESS);
            rootResponse.setRetStatus(ProjectConstants.SUCCESS);
            //need to enable this code to delete file from disk 
if(mailPayload!=null && mailPayload.getFile()!=null && mailPayload.getFile().exists()) {
				mailPayload.getFile().delete();
			}


        } catch (MessagingException e) {
            e.printStackTrace();
            auditDetailsPayload.setResponse(e.getMessage());
            auditDetailsPayload.setStatus("FAIL");
            auditDetailsPayload.setResponseTime(new Date());
            rootResponse.setSysErrorCode(ProjectConstants.SYS_ERROR_CODE);
            rootResponse.setSysErrorMessage(ProjectConstants.API_FAIL);
            rootResponse.setRetStatus(ProjectConstants.FAILURE);
       } catch(Exception e) {
            LoggerClass.appLayerLogger.error("Exception occurred in Email Service ", e);
           auditDetailsPayload.setResponse(e.getMessage());
            auditDetailsPayload.setStatus("FAIL");
        }
        if (mailPayload.getMailTo() == null || mailPayload.getMailTo() == "") {
            String response = "Email-Id not found!!!";
            auditDetailsPayload.setResponse(response);
        }
        auditDetailsPayload.setResponseTime(new Date());
        auditDetailsUtility.saveAuditDetails(auditDetailsPayload);

        return rootResponse;
    }

}

*/
