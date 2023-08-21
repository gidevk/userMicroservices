package com.expriment.utils;

import com.expriment.utils.audit.LoggerClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Base64;

@Component
public class PdfGenerationUtils {

	private Logger logger = LogManager.getLogger("PdfGenerationUtils");

	AppProps appProps;

	public static FileResponse prepareFileResponse(String filePath, String fileName) {
		FileResponse fileResponse = new FileResponse();
		try {
			fileResponse.setAbsoluteFilePath(filePath + fileName);
			fileResponse.setFilePath(filePath);
			fileResponse.setFileName(fileName);

			fileResponse.setFile(new FileInputStream(filePath + fileName));
			fileResponse.setStatus(ProjectConstants.SUCCESS);
			fileResponse.setMessage("");
		} catch (FileNotFoundException fileNotFound) {
			fileResponse.setStatus(ProjectConstants.SUCCESS);
			fileResponse.setStatus(ProjectConstants.SUCCESS);
			fileResponse.setMessage("File Not Found");
		}
		return fileResponse;
	}

	private void prepareFileResponse(FileResponse response, String tempFilePath, String fileName) {
		try {
			response.setFile(new FileInputStream(new File(tempFilePath + fileName)));
			response.setAbsoluteFilePath(tempFilePath + fileName);
			response.setFilePath(tempFilePath);
			response.setFileName(fileName);
			response.setStatus(ProjectConstants.SUCCESS);
		} catch (Exception e) {
			response.setStatus(ProjectConstants.API_FAIL);
			response.setMessage(e.getMessage());
		}
	}

	private String prepareFileName(String appId, String fileName) throws Exception {
		return new StringBuilder(File.separator)
				.append(appId)
				.append("_")
				.append(fileName).toString();
	}

	private String prepareTempPath(String appId) throws Exception {
		StringBuilder tmp = new StringBuilder("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/AuditDetailsUtility/");//(appProps.getLocationToUploadDoc());
		tmp.append(appId);
		File tmpDir = new File(tmp.toString());
		if (!tmpDir.exists()) {
			LoggerClass.appLayerLogger.info("Directory Created: " + tmpDir.mkdir());
		}
		return tmp.toString();
	}

	public FileResponse convertBase64StringToFile(String base64String, String fileName, String appId) {
		FileResponse response = new FileResponse();
		try {
			String tempPath = prepareTempPath(appId);
			String tempFileName = prepareFileName(appId, fileName);
			File file1 = new File(tempPath + tempFileName);
			OutputStream outputStream = new FileOutputStream(file1);
			outputStream.write(Base64.getDecoder().decode(base64String.replaceAll("\\s", "")));
			outputStream.close();
			prepareFileResponse(response, tempPath, tempFileName);
		} catch (FileNotFoundException e) {
			logger.error("While serching the file path it throws error(FileNotFound): ", e);
			response.setStatus(ProjectConstants.API_FAIL);
			response.setMessage(e.getMessage());
		} catch (IOException ex) {
			logger.error("While closing the streams it throws error: ", ex);
			response.setStatus(ProjectConstants.API_FAIL);
			response.setMessage(ex.getMessage());
		} catch (Exception exe) {
			logger.error("While preparing file paths to file it throws error: ", exe);
			response.setStatus(ProjectConstants.API_FAIL);
			response.setMessage(exe.getMessage());
		}
		return response;
	}

}
