package com.expriment.Testing;

import com.expriment.utils.ProjectConstants;
import com.expriment.utils.audit.DAO.AuditLogDataDAO;
import com.expriment.utils.audit.Hibernate.HibernateUtils;
import com.expriment.utils.audit.entity.AuditLogData;
import com.expriment.utils.audit.entity.vo.ErrorResponse;
import com.expriment.utils.audit.entity.vo.RootResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Component
public class DocUploadServiceImpl  {
	final Logger logger=LogManager.getLogger("DocUploadServiceImpl");
	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	FileCodecBase64 fileCodecBase64;

	@Autowired
	SfdcTdlDocDAO sfdcTdlDocDAO;

	@Autowired
	HibernateUtils hibernateUtils;

	@Autowired
	AuditLogDataDAO auditLogDataDAO;

//	@Autowired
//	PdfMerging pdfMerging;

	/*public RootResponse saveDocForEmudra(EmudraDocRequest emudraDocRequest){

		SfdcTdlDocResponse sfdcTdlDocResponse =null;
		RootResponse rootResponse = new RootResponse();
		// based on the tpye we can save in Db.
		try {
			logger.info("Inside saveDocForEmudra");
			if(emudraDocRequest != null && emudraDocRequest.getDoc() != null &&
					emudraDocRequest.getDocType() != null && emudraDocRequest.getLeadId() != null){
				String docType = emudraDocRequest.getDocType().toLowerCase();
				logger.info("customer leadId {} docType is {}",emudraDocRequest.getLeadId(),docType);

				switch (docType){
					case "sl":		//sl Sanction Letter , tc-Terms&Conditions , la- Loan
						sfdcTdlDocResponse.setSfdcDoc(emudraDocRequest.getDoc());
						break;
					case "tc":
						sfdcTdlDocResponse.setTdlDocTnc(emudraDocRequest.getDoc());
						break;
					case "la":
						sfdcTdlDocResponse.setTdlDocLoanAgr(emudraDocRequest.getDoc());
						break;
				}

				sfdcTdlDocResponse.setLeadId(Integer.valueOf(emudraDocRequest.getLeadId()));
				if(true){// sfdcTdlDocDAO.getSfdcTdlDocByLeadId(emudraDocRequest.getLeadId()).getLeadId() == null){  // TODO: 3/5/2023 check once db response
					sfdcTdlDocResponse.setCreatedDate(new Date());
				}else {
					sfdcTdlDocResponse.setUpdatedDate(new Date());
				}

//				rootResponse.setRetStatus(UtilityConstants.STATUS_CODE_PARAMS.SUCCESS);

			}else{
				logger.info("some necessary field is missing..");
//				rootResponse.setRetStatus(UtilityConstants.STATUS_CODE_PARAMS.FAILURE);
//				rootResponse.setSysErrorCode(UtilityConstants.STATUS_CODE_PARAMS.SYS_ERROR_CODE);
				rootResponse.setSysErrorMessage("Some necessary field is missing like leadId, docType..");
				return rootResponse;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {

			if (sfdcTdlDocResponse != null && sfdcTdlDocResponse.getLeadId() != null) {
//				sfdcTdlDocDAO.saveOrUpdateSfdcTdlDoc(sfdcTdlDocResponse);
				logger.info("Emudra Document is updated successfully for customer :"+sfdcTdlDocResponse.getLeadId());

			}
		}
		return rootResponse	;
	}*/

	/*public RootResponse pushEmudraToDMS(String plLeadId, String docBase64Date){
		DocUploadPayload docUploadPayload = new DocUploadPayload();
		RootResponse rootResponse = new RootResponse();
		try {
			logger.info("pushEmudraToDMS is started.");
			CDIOfferModule offerModule = tclServiceManager.getCdiOfferModuleDataService().getOfferDataByLeadId(Long.valueOf(plLeadId));
			*//*List<UploadDoc> uploadDoc=tclServiceManager.getUploadDocService().getUploadDocResByPlleadIdAndDocType(plLeadId, TCLConstants.CUSTOMER_KFS_GENERATION_DOC_TYPE);
			if(!uploadDoc.isEmpty() && uploadDoc.get(0).getWebtopNo() !=null){
				docUploadPayload.setWebtopNo(uploadDoc.get(0).getWebtopNo());
			}*//*
			docUploadPayload.setWebtopNo(offerModule.getPlWebTopId());
//			docUploadPayload.setApplicantNature();
//			docUploadPayload.setApplicantType();
			docUploadPayload.setMobileNumber(String.valueOf(offerModule.getMobileNo()));
			docUploadPayload.setDocUploadName("esignedDoc");
			docUploadPayload.setBase64(docBase64Date);

			docUploadService.uploadDocV2(docUploadPayload); // TODO: 3/6/2023  for pushing DMS.
			logger.info("DMS pushed for Esigned document of customer leadId"+offerModule.getPlLeadId());

			// TODO: 3/9/2023  send email to customer.
			*//*ObjectMapper objectMapper = new ObjectMapper();
			 need getOpportunityId(), customerHash(),getWebtopId
			ApplicationStatusHist applicationStatusHist= applicationStatusDAO.getApplicationStatusHist(plLeadId);
			KFSRequestPayload kfsRequestPayload= new KFSRequestPayload();
			kfsRequestPayload.setWebtopId(uploadDoc.get(0).getWebtopNo());
			kfsRequestPayload.setCustomerHash("customerHash");
			kfsRequestPayload.setOpportunityId(applicationStatusHist.getOpportunityId());
*//*

			*//*	KFSResponse response = kfsAppService.generateKFS(kfsRequestPayload, "CustomerKFS",offerModule);

			try {
				logger.info("customerKFS Response : " + objectMapper.writeValueAsString(response));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}*//*
			*//*********************************************************************************************************************************//*
			// Sending mail to customer
			Thread thread = new Thread(() -> {
				logger.info("Sending mail to customer : Stated");
				customerMailUtil.sendEmail(kfsAppServiceImpl.completePath, offerModule.getEmailAddress(),offerModule);

				logger.info("Sending mail to customer : Ended");
			});
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}
*/
	public EmudraRequest emudraPushEsignDoc(String leadId){//, String customerHash){
		//lead_id	customer_hash	sfdc_doc	tdl_tnc	tdl_loneAgr
		ErrorResponse errorResponse;
		SfdcTdlDocResponse sfdcTdlDocResponse = null;
		SfdcTdlDocResponse sfdcTdlDocReq = null;
		String plLeadId= null;
		boolean kfsAvailable=false;
		List<Coordinates> coordinatesList= new ArrayList<>();
		EmudraSign_coordinates sign_coordinates=new EmudraSign_coordinates();
		Coordinates coordinates = new Coordinates();


		EmudraRequest emudraRequest = new EmudraRequest();
//		DocUploadPayload docUploadPayload=null;
		try {

//			sfdcTdlDocReq.setLeadId(leadId);
//			sfdcTdlDocReq.setCustomerHash(customerHash);

//			SfdcTdlDocResponse sfdcTdlDocResponse = sfdcTdlDocDAO.getSfdcTdlDocByLeadId(leadId);

//			uploadDoc.setDocUploadType("KFS".equalsIgnoreCase(apiName)?TCLConstants.KFS_GENERATION_DOC_TYPE:TCLConstants.CUSTOMER_KFS_GENERATION_DOC_TYPE);
//			("KFS".equalsIgnoreCase(apiName)?TCLConstants.KFS_GENERATION_DOC_TYPE:TCLConstants.CUSTOMER_KFS_GENERATION_DOC_TYPE);

			/*List<UploadDoc> uploadDoc=tclServiceManager.getUploadDocService().getUploadDocResByPlleadIdAndDocType(plLeadId, TCLConstants.CUSTOMER_KFS_GENERATION_DOC_TYPE);
             if(!uploadDoc.isEmpty()   && uploadDoc.get(0).getBase64FormattedData() !=null){
             	kfsAvailable = true;
			 }*/

			// TODO: 3/3/2023  combined the all 4 doc and send to Emudra for esign.
			/*if(sfdcTdlDocResponse.getSfdcDoc() != null && !sfdcTdlDocResponse.getSfdcDoc().isEmpty()   // TODO: 3/3/2023  received the Sfdc doc from Sfdc
					&& sfdcTdlDocResponse.getTdlDocTnc() != null && !sfdcTdlDocResponse.getTdlDocTnc().isEmpty()  // TODO: 3/3/2023 received the TDL t&c and Loan Agreement.
					&& sfdcTdlDocResponse.getTdlDocLoanAgr() != null && !sfdcTdlDocResponse.getTdlDocLoanAgr().isEmpty() &&
					kfsAvailable*//*KFS dock check*//*){*/		 // TODO: 3/3/2023 fetch the KFS doc from our DB.{
				// TODO: 3/3/2023  first combine the doc.
				String combinedDoc = null;
//				combinedDoc=  uploadDoc.get(0).getBase64FormattedData()+sfdcTdlDocResponse.getEsignDoc()+sfdcTdlDocResponse.getTdlDocLoanAgr()+sfdcTdlDocResponse.getTdlDocTnc();

				/*making the emudra request payload.*/
			emudraRequest.setName("Prod DLG");
			emudraRequest.setReason("Testing");
			emudraRequest.setLocation("Mumbai");
			emudraRequest.setKey_store_name("TCFSL");
			emudraRequest.setDisplay_on_page("custom");

			coordinates.setLlx(35);
			coordinates.setLly(35);
			coordinates.setUrx(322);
			coordinates.setUry(60);

			coordinatesList.add(coordinates);
			sign_coordinates.setCoordinates(coordinatesList);

			emudraRequest.setSign_coordinates(sign_coordinates);

			makeEsignPDF(Integer.valueOf(leadId));

			sfdcTdlDocResponse =sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));
			if (sfdcTdlDocResponse.getSfdcDoc() !=null)
				emudraRequest.setFile_content_string(sfdcTdlDocResponse.getSfdcDoc());

//				emudraRequest.setSign_coordinates(coordinates);
				String esignedDoc= eMudraService(leadId,emudraRequest); // TODO: 3/6/2023 based on the response we have to return.


//			}

			// TODO: 3/3/2023 pushed to DMS and customer_emails.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// TODO: 3/6/2023 save the emudra response in db. 
		}


		return emudraRequest;
	}

	/*public String eMudraService(String leadId, EmudraRequest emudraRequest){

		EmudraRequest emudraResponse =new EmudraRequest();
		SfdcTdlDocResponse sfdcTdlDocResponse = new SfdcTdlDocResponse();
		RestTemplate restTemplate = new RestTemplate();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
		String esignedDoc = null;
		String url = "digio.tatacapital.com:8080/doc_signer/signdoc";//"http://digio.tatacapital.com:8080/doc_signer/signdo";

		try {
			logger.info("Emudra call for leadId {}",leadId);

			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("Content-Type", "application/json");

			HttpEntity<?> httpEntity = new HttpEntity<>(emudraRequest, headers);
//			String createOppUrl = tclServicesProps.getCreateOpportunityUrl();
//			logger.info("createOppUrl: " + createOppUrl);

			esignedDoc = restTemplate.postForObject(url, httpEntity, String.class);

			emudraResponse= objectMapper.readValue(esignedDoc,EmudraRequest.class);
			sfdcTdlDocResponse.setEmudraStatus(dateFormat.getCalendar().getTime());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (emudraResponse != null && emudraResponse.getKey_store_name() != null) {
				sfdcTdlDocResponse.setLeadId(Integer.valueOf(leadId));
				sfdcTdlDocResponse.setEsignDoc(emudraResponse.getFile_content_string());
			}

			if (sfdcTdlDocResponse.getLeadId() !=null) {
//				sfdcTdlDocDAO.saveOrUpdateSfdcTdlDoc(sfdcTdlDocResponse);
				logger.info("EmudraDoc is updated successfully for customer :"+sfdcTdlDocResponse.getLeadId());

			}
			logger.info("Document is saved.");
		}

		return esignedDoc;
	}

*/

	public String combineTwoBase64Data(){//String base64Data1, String base64Data2){
		String encodedUrl=null;
		String encodedUrl1=null;
		byte[] bytes=null;
		byte[] bytes1=null;
		/*try {
			Base64.Encoder encoder = Base64.getUrlEncoder();
			String originalinput = "https://stackabuse.com/tag/java/";
			encodedUrl = encoder.encodeToString(originalinput.getBytes());
			System.out.println("|||||||||||||||||||||||||||||||"+encodedUrl);

			String orignalinput1 = "hello bro"+originalinput;
			encodedUrl1= encoder.encodeToString(orignalinput1.getBytes());
			System.out.println(encodedUrl1);

			Base64.Decoder decoder = Base64.getUrlDecoder();
			bytes = decoder.decode(encodedUrl);

//			Base64.Decoder decoder = Base64.getUrlDecoder();
			bytes1 = decoder.decode(encodedUrl1);

			System.out.println(new String(bytes));
			System.out.println(new String(bytes1));
		} */
		try {
			boolean IS_CHUNKED = true;
//			makeEsignPDF();
			fileCodecBase64.encode("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/first.pdf" ,
					"C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/First-encoded.txt", IS_CHUNKED);

			/* Decode a file and write the decoded file to file system */
//			fileCodecBase64.decode("C:/temp/something-encoded.txt", "c:/temp/something-decoded.pdf");
			/*fileCodecBase64.decode("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/something-encoded.txt",
					"C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/something-decoded.pdf");
			File[] files={new File("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/something-decoded.pdf"),new File("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/something-decoded1.pdf")};
			String destenation="C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/esignDoc.pdf";
			combineTwoPdf(files,destenation,"1234");
			*/
		}catch(Exception e) {
			e.printStackTrace();
		} finally {

		}


//		return encodedUrl+"?????"+ new String(bytes);
		return "Success";
	}
	public File textToFile(String str,String path) {

		try {
//			String str = "SomeMoreTextIsHere";
			File newTextFile = new File(path);

			FileWriter fw = new FileWriter(newTextFile);
			fw.write(str);
			fw.close();
			return newTextFile;
		} catch (IOException iox) {
			//do stuff with exception
			iox.printStackTrace();
		}
		return null;
	}
	public String eMudraService(String leadId, EmudraRequest emudraRequest){

		EmudraRequest emudraResponse =new EmudraRequest();
		SfdcTdlDocResponse sfdcTdlDocResponse = new SfdcTdlDocResponse();
		RestTemplate restTemplate = new RestTemplate();
		String esignedDoc = null;
		String url = "https://digio.tatacapital.com:8080/doc_signer/signdoc";
		AuditLogData apiAuditLog = new AuditLogData();

		try {
			// TODO: 3/10/2023  	 AuditDetailsDAO saved in this entiy.
			apiAuditLog.setResponse(url);
			apiAuditLog.setCpId(Integer.valueOf(leadId));
			apiAuditLog.setRequest(emudraRequest.toString());
			apiAuditLog.setCreatedDate(new Date());

			logger.info("Emudra call for leadId {}",leadId);

			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("Content-Type", "application/json");

			HttpEntity<?> httpEntity = new HttpEntity<>(emudraRequest, headers);

			esignedDoc = restTemplate.postForObject(url, httpEntity, String.class);

//			emudraResponse= objectMapper.readValue(esignedDoc,EmudraRequest.class);

			sfdcTdlDocResponse.setEmudraStatus(ProjectConstants.SUCCESS);

			apiAuditLog.setResponse(esignedDoc);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (emudraResponse != null && emudraResponse.getKey_store_name() != null) {
				sfdcTdlDocResponse.setLeadId(Integer.valueOf(leadId));
				sfdcTdlDocResponse.setEsignDoc(emudraResponse.getFile_content_string());
			}

			if (sfdcTdlDocResponse.getLeadId() !=null) {
				sfdcTdlDocDAO.saveOrUpdateSfdcTdlDoc(sfdcTdlDocResponse);
				logger.info("EmudraDoc is updated successfully for customer :"+sfdcTdlDocResponse.getLeadId());
			}

			if(apiAuditLog.getRequest() !=null && apiAuditLog.getResponse() !=null){
				auditLogDataDAO.saveAuditLogs(apiAuditLog);
				logger.info("Saved Emudra response Successfully for leadId {}",apiAuditLog.getAuditLogId());
			}
			logger.info("Document is saved.");
		}

		return esignedDoc;
	}

	public String makeEsignPDF(Integer leadId){
		File file = null,file1 = null,file2 = null;
		SfdcTdlDocResponse sfdcTdlDocResponse= new SfdcTdlDocResponse();

		try {
			sfdcTdlDocResponse = sfdcTdlDocDAO.getSfdcTdlDocByLeadId(leadId);
			File tempFile;
			String path="C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/first-encoded.txt";
			String path1="C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/";
			if (sfdcTdlDocResponse.getSfdcDoc() !=null) {
//				file=fileCodecBase64.decode("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/first-encoded.txt","first-dec.pdf");
				tempFile= textToFile(sfdcTdlDocResponse.getSfdcDoc(),path);
				file = fileCodecBase64.decode(tempFile.getAbsolutePath(), path1+ "first-dec111.pdf");
			}
			if (sfdcTdlDocResponse.getTdlDocTnc() !=null) {
				tempFile= textToFile(sfdcTdlDocResponse.getTdlDocTnc(),path);
				file1 = fileCodecBase64.decode(tempFile.getAbsolutePath(), path1+"second-dec111.pdf");
			}
			if (sfdcTdlDocResponse.getTdlDocLoanAgr() !=null) {
				tempFile= textToFile(sfdcTdlDocResponse.getTdlDocLoanAgr(),path);
				file2 = fileCodecBase64.decode(tempFile.getAbsolutePath(), "third-dec111.pdf");
			}
//			String basePath=path1+leadId+"_third-dec1.pdf";
//			File combinedDoc = ResourceUtils.getFile( "classpath:basePath");

			//file array creating to send for merging.
			File[] files={file,file1};
//			String[] base64String = {sfdcTdlDocResponse.getSfdcDoc(),sfdcTdlDocResponse.getTdlDocLoanAgr(),sfdcTdlDocResponse.getTdlDocTnc()};
					/*{new File("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/something-decoded.pdf"),
					new File("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/something-decoded1.pdf")};*/

			String destenation="C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/";

//			 combineTwoPdf(files,destenation, String.valueOf(leadId));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "Success";
	}
	/*public  <PDFMergerUtility> void combineTwoPdf(File[] filePaths, String destinationFileName, String leadId) throws IOException {// this for acceptng the file array.
		*//*	public  <PDFMergerUtility> void combineTwoPdf(String[] base64DataList,String destinationFileName,String leadId) throws IOException {

		 loading all the pdf files we wish to merge

       /* File file1 = new File(
                "/Users/piyushkumar/Desktop/Merging Pdfs/file1.pdf");
        File file2 = new File(
                "/Users/piyushkumar/Desktop/Merging Pdfs/file2.pdf");

		// Instantiating PDFMergerUtility class

//        PDFMergerUtility obj = new PDFMergerUtility();
*//*
		org.apache.pdfbox.multipdf.PDFMergerUtility obj = new org.apache.pdfbox.multipdf.PDFMergerUtility();
		*//* Setting the destination file path
       obj.setDestinationFileName("/Users/piyushkumar/Desktop/Merging Pdfs/newMerged.pdf");
*//*
		obj.setDestinationFileName(destinationFileName+leadId+"_esignDoc.pdf");

		// Add all source files, to be merged
		for (File files : filePaths){
			obj.addSource(files);
		}

		*//*
       obj.addSource(filePaths[1]);

		 Merging documents
*//*

		obj.mergeDocuments();

		System.out.println("PDF Documents merged to a single file");
		String fileData = null;
		try {
			File esignBase64Data= fileCodecBase64.encode(obj.getDestinationFileName(), "esign.text", true);
			SfdcTdlDocResponse sfdcTdlDocResponse = new SfdcTdlDocResponse();
			sfdcTdlDocResponse =sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));
		*//*	try{

				FileReader fr=new FileReader(esignBase64Data);
				int i;
				while((i=fr.read())!=-1)
					fileData = fileData + i;
//					System.out.print((char)i);
				fr.close();
			}catch (Exception e){
				e.printStackTrace();
			}*//*
			*//*if (sfdcTdlDocResponse.getEsignDoc() != null) {
				sfdcTdlDocResponse.setEsignDoc(sfdcTdlDocResponse.getSfdcDoc());
//			sfdcTdlDocResponse.setLeadId(Integer.valueOf(leadId));
			sfdcTdlDocResponse.setUpdatedDate(new Date());
			sfdcTdlDocResponse.setEmudraStatus(new Date());
			}
			sfdcTdlDocDAO.saveOrUpdateSfdcTdlDoc(sfdcTdlDocResponse);
*//*
//			SfdcTdlDocResponse sfdcTdlDocResponse1 = sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));
		} catch (Exception e) {
			e.printStackTrace();
		}

//		return obj;
	}
*/
	public ResponseEntity<?> saveSfdcDocResponse(SfdcTdlDocResponse sfdcTdlDocResponse){
		SfdcTdlDocResponse sfdcTdlDocResponseResponse = new SfdcTdlDocResponse();

		try {
			sfdcTdlDocResponseResponse=hibernateUtils.saveEntity(sfdcTdlDocResponse);

			return new ResponseEntity<>(sfdcTdlDocResponseResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

