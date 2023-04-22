/*
package com.expriment.utils;


import com.expriment.DAO.Impl.AuditDetailsUtility;
import com.expriment.entity.vo.AuditDetailsPayload;
import com.expriment.utils.audit.entity.SmsMailResponse;
import com.expriment.utils.audit.entity.vo.SmsMailPayload;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeMultipart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
//import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;

@Component
public class EmailServicesImpl implements EmailServices {
	
	Logger logger = LogManager.getLogger("EmailServicesImpl");

	*/
/*@Autowired
	public TCLAPIsProps tclApisProps;

	@Autowired
	public TCLServiceManager tclServiceManager;
*//*

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	AuditDetailsUtility auditDetailsUtility;

	public  static String fromMailId=" idevk995@gmail.com";
	
	@Override
	public SmsMailResponse sendMail(SmsMailPayload mailPayload) {
		logger.info("sendMail block");
		SmsMailResponse rootResponse = new SmsMailResponse(); 
		AuditDetailsPayload auditDetailsPayload = new AuditDetailsPayload();
		
		auditDetailsPayload.setLeadId(mailPayload.getLeadId());
		auditDetailsPayload.setApiName(ProjectConstants.MAIL_API_CUSTOMER_CARE);
		auditDetailsPayload.setPayload(mailPayload.getMessage());
		String conversationId = String.valueOf(new Date().getTime());
		auditDetailsPayload.setConversationId(conversationId);
		
		auditDetailsPayload.setRequestTime(new Date());
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		
		try {
			helper = new MimeMessageHelper(message,true);
		} catch (javax.mail.MessagingException e1) {
			e1.printStackTrace();
		}
		try {		
			helper.setFrom(fromMailId);
			helper.setSentDate(new Date());
			helper.setTo(mailPayload.getMailTo());
			helper.setSubject(mailPayload.getSubject());
					
			MimeMultipart mimeMultipart = new MimeMultipart();
		    MimeBodyPart messageBodyPart = new MimeBodyPart();
		    messageBodyPart.setContent(mailPayload.getMessage(), "text/html");
			
			mimeMultipart.addBodyPart(messageBodyPart);
			helper.addAttachment(mailPayload.getFileName(), mailPayload.getFile());
			
//			message.setContent(mimeMultipart);
			
			if(mailPayload != null && mailPayload.getFile() != null && mailPayload.getFile().exists()) {
				logger.info("inside attachment block");
				*/
/*FileSystemResource fr = new FileSystemResource(mailPayload.getFile());
				helper.addAttachment(mailPayload.getFile().getName(), fr);*//*

				logger.info("CompleteFilePath: "+mailPayload.getCompleteFilePath());
				logger.info("File Name:"+mailPayload.getFile().getName());
				messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(mailPayload.getCompleteFilePath());
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(mailPayload.getFile().getName());
				mimeMultipart.addBodyPart(messageBodyPart);
		        
				// Send the complete message parts
		        message.setContent(mimeMultipart);
			} else {
				logger.info("no attacgement");
			}
			
			javaMailSender.send(message);
			logger.info("Mail send Successfully");
			auditDetailsPayload.setResponse("Email sent successfully");
			auditDetailsPayload.setStatus(ProjectConstants.SUCCESS);
			rootResponse.setRetStatus(ProjectConstants.SUCCESS);
			//need to enable this code to delete file from disk 
			*/
/*if(mailPayload!=null && mailPayload.getFile()!=null && mailPayload.getFile().exists()) {
				mailPayload.getFile().delete();
			}*//*

			
		} catch (MessagingException | javax.mail.MessagingException e) {
			e.printStackTrace();
			auditDetailsPayload.setResponse("Failed");
			auditDetailsPayload.setStatus("FAIL");
			auditDetailsPayload.setResponseTime(new Date());
			rootResponse.setSysErrorCode(ProjectConstants.DATA_NOT_FOUND_ERROR_CODE);
			rootResponse.setSysErrorMessage(ProjectConstants.API_FAIL);
			rootResponse.setRetStatus(ProjectConstants.FAIL);
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
