package com.expriment.DAO.Impl;

import com.expriment.entity.AuditDetails;
import com.expriment.entity.vo.AuditDetailsPayload;
import com.expriment.utils.audit.DAO.AuditDetailsDAO;
import com.expriment.utils.audit.LoggerClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


@Component
public class AuditDetailsUtility {
	final Logger logger=LogManager.getLogger("AuditDetailsUtility");

	@Autowired
	AuditDetailsDAO auditDetailsDAO;
//			tclServiceManager;

	String appProps="C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/AuditDetailsUtility/";

	public void saveAuditDetails(AuditDetailsPayload auditDetilsPayload){
		
		AuditDetails auditDetails = saveDetailsToFile(auditDetilsPayload);
		auditDetails.setApiName(auditDetilsPayload.getApiName());
		auditDetails.setLeadId(auditDetilsPayload.getLeadId());
		auditDetails.setRequestTime(auditDetilsPayload.getRequestTime());
		auditDetails.setResponseTime(auditDetilsPayload.getResponseTime());
		auditDetails.setStatus(auditDetilsPayload.getStatus());
		auditDetails.setNoOfAttempts(auditDetilsPayload.getNoOfAttempts());
		auditDetails.setRequestUrl(auditDetilsPayload.getRequestUrl());
		auditDetails.setMobileNumber(auditDetilsPayload.getMobileNumber()!=null &&!auditDetilsPayload.getMobileNumber().trim().isEmpty()?Long.valueOf(auditDetilsPayload.getMobileNumber()):null);
		auditDetails.setConversationId(String.valueOf(new Date().getTime()));
		auditDetailsDAO.saveAuditDetails(auditDetails);
	}
	

	public AuditDetails saveDetailsToFile(AuditDetailsPayload auditDetilsPayload){

		LoggerClass.appLayerLogger.info("In saveDetailsToFile method-------->");

		AuditDetails auditDetails = new AuditDetails();

		String path = appProps;
		LoggerClass.appLayerLogger.info("Path : "+path);

		Date date = new Date();

		String stamp = date.toString();
		stamp = stamp.replaceAll("\\s", "");
		stamp = stamp.replaceAll(":", "");

		StringBuilder fullPath = new StringBuilder();
		if(auditDetilsPayload != null && auditDetilsPayload.getLeadId() != null) {
			fullPath.append(path).append("/")
			.append(auditDetilsPayload.getLeadId())
			.append("/").append(auditDetilsPayload.getApiName())
			.append("/").append(stamp).append("/");
		}

		LoggerClass.appLayerLogger.info("FullPath : "+fullPath);

		File file1 = new File(fullPath.toString());

		if(!file1.exists()){
			file1.mkdirs();
		}

		//String requestFileName= file1.getPath()+"/request_file.txt";
		StringBuilder requestFileName=new StringBuilder(file1.getPath());
		requestFileName.append("request_file.txt");

		//String responseFileName = file1.getPath()+"/response_file.txt";
		StringBuilder responseFileName=new StringBuilder(file1.getPath());
		responseFileName.append("response_file.txt");

		File requestFile = new File(requestFileName.toString());
		if(!requestFile.exists()){
			try {
				requestFile.createNewFile();
			} catch (IOException e) {
				logger.error("Error while creating request_file.txt",e);
			}
		}

		try(FileWriter fileWriter1 = new FileWriter(requestFile);
				PrintWriter printWriter = new PrintWriter(fileWriter1);) {
			printWriter.println(auditDetilsPayload.getPayload());
		}catch(Exception e) {
		//	logger.error("Error while saving request payload for application id "+auditDetilsPayload.getAppId()+"", e);
		}
		auditDetails.setRequestFileName(requestFileName.toString());

		File responseFile = new File(responseFileName.toString());
		if(!responseFile.exists()){
			try {
				responseFile.createNewFile();
			} catch (IOException e) {
				logger.error("Error while creating response_file.txt",e);
			}
		}

		try(FileWriter fileWriter2 = new FileWriter(responseFile);
				PrintWriter printWriter = new PrintWriter(fileWriter2);) {
			printWriter.println(auditDetilsPayload.getResponse());
		}catch(Exception e) {
		//	logger.error("Error while saving response payload for application id "+auditDetilsPayload.getAppId()+"", e);
		}

		auditDetails.setResponseFileName(responseFileName.toString());
		return auditDetails;
	}
}




