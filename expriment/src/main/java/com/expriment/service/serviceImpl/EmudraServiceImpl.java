package com.expriment.service.serviceImpl;

import com.expriment.DAO.CDIOfferModuleDataDAO;
import com.expriment.DAO.Impl.AuditDetailsUtility;
import com.expriment.DAO.UploadDocDAO;
import com.expriment.Testing.*;
import com.expriment.entity.AuditDetails;
import com.expriment.entity.CDIOfferModule;
import com.expriment.entity.UploadDoc;
import com.expriment.entity.vo.AuditDetailsPayload;
import com.expriment.entity.vo.DocUploadPayload;
import com.expriment.entity.vo.EmudraExternalResponse;
import com.expriment.utils.ProjectConstants;
import com.expriment.utils.audit.entity.vo.RootResponse;
import com.expriment.utils.audit.entity.vo.SmsMailPayload;
import connectjar.org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class EmudraServiceImpl  {
    final Logger logger= LogManager.getLogger("EmudraServiceImpl");

   /* @Autowired
    TCLServiceManager tclServiceManager;

    @Autowired
    DocUploadService docUploadService;

    @Autowired
    EmailServices emailServices;
*/
    @Autowired
    CDIOfferModuleDataDAO cdiOfferModuleDataDAO;

    @Autowired
    SfdcTdlDocDAO sfdcTdlDocDAO;

    @Autowired
    UploadDocDAO uploadDocDAO;

    @Autowired
    AuditDetailsUtility auditDetailsUtility;


    static String basePath="C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/";

//    @Override
    public ResponseEntity<?> saveEmudraDocumentService(EmudraDocRequest emudraDocRequest){

        String leadId = null,opportunityId,custmerHash;
        SfdcTdlDocResponse sfdcTdlDocResponse=new SfdcTdlDocResponse();
        CDIOfferModule offerModule= new CDIOfferModule();
        RootResponse rootResponse= new RootResponse();
        RootResponse rootResponse1= new RootResponse();
        boolean kfsAvailable=false;
        String base64KfsDoc = null;
        EmudraExternalResponse emudraExternalResponse = new EmudraExternalResponse();
        EmudraRequest emudraRequest= new EmudraRequest();

        try {
            /*save all document whatever received from Database*/
            custmerHash= emudraDocRequest.getCustHash();
            opportunityId= emudraDocRequest.getOpportunityId();

            if (custmerHash !=null)
                rootResponse.setCustomerHash(custmerHash);
            else if(opportunityId !=null){
                rootResponse.setCustomerHash(opportunityId);
            }

            if (custmerHash !=null){
                offerModule = cdiOfferModuleDataDAO.getOfferDataByCustomerHashNew(custmerHash);
                opportunityId= offerModule.getPlOpportunityId();
            }else if(opportunityId != null) {
                offerModule = cdiOfferModuleDataDAO.getOfferDataByPlOpportunityId(opportunityId);
                custmerHash= offerModule.getCustomerHashNew();  /*In offerMudel customer plleadId, customerHash, OpportutnityId ,getEmailAddress must be there*/
            }else  {
                logger.info("customerHas not persent in request.");
                rootResponse.setSysErrorMessage("customerHas or OpportunityId is missing.");
                rootResponse.setRetStatus(ProjectConstants.FAIL);
                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_SYS_ERROR_CODE);
            }

            if (offerModule.getPlLeadId() !=null)
                leadId= String.valueOf(offerModule.getPlLeadId());

            rootResponse.setLeadId(leadId);

            logger.info("customerhash {}, opportunityid {} and leadid {}",custmerHash,opportunityId,leadId);// for testing

            List<DocTypeData> docTypeDataList= emudraDocRequest.getDocTypeData();
            if(custmerHash !=null || opportunityId !=null ){
                for (DocTypeData doc :docTypeDataList){
                    if (doc.getDoc() !=null && doc.getDocType() != null) {
//                        if (doc.getLeadId()== null){doc.setLeadId(leadId); }

                        rootResponse1= saveDocForEmudra(doc,leadId,custmerHash);
                        logger.info("Saved docuementType {} with Status {}",doc.getDocType(), rootResponse1.getRetStatus());
                    }
                }
                rootResponse.setSysErrorMessage("You document is saved.");
            }

            sfdcTdlDocResponse= sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));

            /*check all 4 document is available then hit external service for emudra*/
            List<UploadDoc> uploadDoc=uploadDocDAO.getUploadDocResByPlleadIdAndDocType(leadId, ProjectConstants.CUSTOMER_KFS_GENERATION_DOC_TYPE);

//            logger.info(Arrays.toString(uploadDoc.toArray()));
            if (uploadDoc.size() >0)
                base64KfsDoc =uploadDoc.get(0).getBase64FormattedData();
            logger.info("UploadDoc Size is {}", uploadDoc.size());

            if(!uploadDoc.isEmpty()  && base64KfsDoc != null){
                kfsAvailable = true;
                logger.info("kfsAvailable "+kfsAvailable);
                rootResponse.setSysErrorMessage("You document is saved with KFS data.");
                makeEsignPDF(Integer.valueOf(leadId),base64KfsDoc);
            }


            if(sfdcTdlDocResponse.getSfdcDoc() != null && !sfdcTdlDocResponse.getSfdcDoc().isEmpty()
                    && sfdcTdlDocResponse.getTdlDocTnc() != null && !sfdcTdlDocResponse.getTdlDocTnc().isEmpty()
                    && sfdcTdlDocResponse.getTdlDocLoanAgr() != null && !sfdcTdlDocResponse.getTdlDocLoanAgr().isEmpty() &&
                    kfsAvailable) {
                if(sfdcTdlDocResponse.getEmudraStatus() == null || !sfdcTdlDocResponse.getEmudraStatus().equals(ProjectConstants.SUCCESS)) {

                    logger.info("calling creatingEmudraRequest for esigned doc pushing for leadId {}", leadId);
                    emudraRequest = creatingEmudraRequest(leadId);

                    if (sfdcTdlDocResponse.getEmudraStatus() !=null && emudraRequest.getFile_content_string() !=null) {
                        logger.info("calling EmudraService ...");
                        emudraExternalResponse = eMudraService(leadId, emudraRequest);

                    }

                    if (emudraExternalResponse.getSigned_file_content() != null) {
                        logger.info("Pusing Document to DMS and Emails");
                        pushEmudraToDMSAndEmail(leadId, emudraExternalResponse.getSigned_file_content());
                    }

                }else {
                    logger.info("Emudra Esigned document is already available.");
                    rootResponse.setSysErrorMessage("Esigned document is already available");
                }
                rootResponse.setRetStatus(ProjectConstants.SUCCESS);
                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_SYS_SUCCESS_CODE);
            }else {
                logger.info("All document are not available now.");
                rootResponse.setSysErrorMessage("Necessary document are not available");
                rootResponse.setRetStatus(ProjectConstants.FAIL);
                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_SYS_ERROR_CODE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.info("saveEmudraDocumentService ended.");
        }

        return  new ResponseEntity<>(rootResponse,HttpStatus.OK);
    }

//    @Override
    public RootResponse saveDocForEmudra(DocTypeData emudraDocRequest, String leadId, String customerHash){

        SfdcTdlDocResponse sfdcTdlDocResponse =new SfdcTdlDocResponse();
        RootResponse rootResponse = new RootResponse();
        String docType = null;
//        basePath=appProps.geteMudraFilePath();


        // based on the tpye we can save in Db.
        try {
            logger.info("Inside saveDocForEmudra");
            logger.info("Doc path "+basePath);
            if(emudraDocRequest != null && emudraDocRequest.getDoc() != null && emudraDocRequest.getDocType() != null ){

                docType = emudraDocRequest.getDocType().toLowerCase();
                logger.info("customer docType is {}",docType);

                if (docType.equalsIgnoreCase("sl")){
                    sfdcTdlDocResponse.setSfdcDoc(emudraDocRequest.getDoc());

                }else {
                    switch (docType){

                        case "tc":
                            sfdcTdlDocResponse.setTdlDocTnc(emudraDocRequest.getDoc());
                            break;
                        case "la":
                            sfdcTdlDocResponse.setTdlDocLoanAgr(emudraDocRequest.getDoc());
                            break;
                    }
                }
                //sl Sanction Letter , tc-Terms&Conditions , la- Loan

                if (sfdcTdlDocResponse != null) {
                    sfdcTdlDocResponse.setLeadId(Integer.valueOf(leadId));
                }
                sfdcTdlDocResponse.setCustomerHash(customerHash);
                SfdcTdlDocResponse sfdcTdlDocResponse1= sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));
                logger.info("sfdcTdlDoc data {}",sfdcTdlDocResponse1.toString());
                if(sfdcTdlDocResponse1.getCreatedDate() == null){
                    sfdcTdlDocResponse.setCreatedDate(new Date());
                }else {
                    sfdcTdlDocResponse.setUpdatedDate(new Date());
                }

                rootResponse.setRetStatus(ProjectConstants.SUCCESS);

            }else{
                logger.info("some necessary field is missing..");
                rootResponse.setRetStatus(ProjectConstants.FAIL);
                rootResponse.setSysErrorCode(ProjectConstants.EMUDRA_SYS_ERROR_CODE);
                rootResponse.setSysErrorMessage("Some necessary field is missing like leadId, docType.");
                return rootResponse;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            if (sfdcTdlDocResponse != null && sfdcTdlDocResponse.getLeadId() != null) {
                sfdcTdlDocDAO.saveOrUpdateSfdcTdlDoc(sfdcTdlDocResponse);
                logger.info("Emudra Document is updated successfully Document {} for customer :{}",docType ,sfdcTdlDocResponse.getLeadId());

            }
        }
        return rootResponse	;
    }

    public void pushEmudraToDMSAndEmail(String plLeadId, String esignedDocBase64Date){
        DocUploadPayload docUploadPayload = new DocUploadPayload();
        UploadDoc uploadDoc=new UploadDoc();
        SmsMailPayload smsMailPayload=new SmsMailPayload();
        CDIOfferModule offerModule=new CDIOfferModule();
//        basePath=appProps.geteMudraFilePath();

        try {
            logger.info("pushEmudraToDMS is started.");
            logger.info("fileBasePath "+basePath);
            offerModule = cdiOfferModuleDataDAO.getOfferDataByPlLeadId(Long.valueOf(plLeadId));

            docUploadPayload.setWebtopNo(offerModule.getPlWebTopId() != null?offerModule.getPlWebTopId():null);
            docUploadPayload.setApplicantType("esigned"); // TODO: 3/10/2023  verify what i've to put.
            docUploadPayload.setMobileNo(String.valueOf(offerModule.getMobileNo()  != null ? offerModule.getMobileNo():null));
            docUploadPayload.setDocUploadName("esignedDoc");
            /*converting the base64 to pdf.
             *   base64 to .text file after that .text file to pdf*/

//            if (esignedDocBase64Date !=null) {
//                File tempFile= textToFile(esignedDocBase64Date,basePath);
//                File pdfFile = decode(tempFile.getAbsolutePath(), basePath+plLeadId+"_EsignedDoc.pdf");
//            }
            docUploadPayload.setBase64(esignedDocBase64Date);
            docUploadPayload.setDocUploadType("EsignedDoc");

            // TODO: 3/10/2023  have to save file in uploadDoc. col name basd64formateddata for asaving base64 data of docment.
            uploadDoc.setLeadId(String.valueOf(offerModule.getPlLeadId() != null ? offerModule.getPlLeadId():null));
            uploadDoc.setDocUploadName("EsignedDoc");
            uploadDoc.setBase64FormattedData(esignedDocBase64Date);
//			uploadDoc.setApplicantType();
//			uploadDoc.setApplicantNature();
            uploadDoc.setCreatedDate(new Date());
            uploadDoc.setWebtopNo(offerModule.getPlWebTopId() != null ? offerModule.getPlWebTopId() : null);

            uploadDocDAO.saveOrUpdate(uploadDoc);

//            docUploadService.uploadDocV2(docUploadPayload); //  for pushing DMS.
            logger.info("DMS pushed for Esigned document of customer leadId"+offerModule.getPlLeadId());

            /*********************************************************************************************************************************/
            // TODO: 3/9/2023  send email to customer.
            // Sending mail to customer
//            Thread thread = new Thread(() -> {
            smsMailPayload.setSubject("Digital Signed Document of-" + offerModule.getCustomerName() + "-"
                    + offerModule.getPlOpportunityId() + " ");
            smsMailPayload.setMessage("<html><body><div><p>Dear User ,</p><p>Please find digital Signature's Statement for your reference.</p><br><p>Regards,</p><p style="
                    + "font-weight: bold;" + ">Tata Capital Financial Services Ltd</p></div></body>");
            if (offerModule.getEmailAddress() != null)
                smsMailPayload.setMailTo(offerModule.getEmailAddress());
            else
                logger.info("EmilsId is not available in payload.");
            smsMailPayload.setLeadId(String.valueOf(offerModule.getPlLeadId() != null ? offerModule.getPlLeadId():null));
            String  filepath=basePath+plLeadId+"_EsignedDoc.pdf";
            File file = new File(filepath);
            if (file != null) {
                smsMailPayload.setFile(file);
                logger.info("Name: " + file.getName());
                smsMailPayload.setFileName(file.getName());
                smsMailPayload.setCompleteFilePath(file.getPath());
                logger.info("AbsolutePath: " + file.getAbsolutePath());
                logger.info("Path: " + file.getPath());
            }


            logger.info("Sending mail to customer : Stated");
//            emailServices.sendMail(smsMailPayload);

            logger.info("Sending mail to customer : Ended");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
    public EmudraRequest creatingEmudraRequest(String leadId){
        //lead_id	customer_hash	sfdc_doc	tdl_tnc	tdl_loneAg
        EmudraRequest emudraRequest = new EmudraRequest();
        String combinedDoc = null;
        List<Coordinates> coordinatesList= new ArrayList<>();
        EmudraSign_coordinates sign_coordinates=new EmudraSign_coordinates();
        Coordinates coordinates = new Coordinates();
        try {
            // TODO: 3/13/2023  have to review
//            combinedDoc= makeEsignPDF(Integer.valueOf(leadId),base64KfsDoc);
            SfdcTdlDocResponse sfdcTdlDocResponse = sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));

            emudraRequest.setName("Prod DLG");
            emudraRequest.setReason("Testing");
            emudraRequest.setLocation("Mumbai");
            emudraRequest.setKey_store_name("TCFSL");
            if (sfdcTdlDocResponse.getEsignDoc() !=null)
                emudraRequest.setFile_content_string(sfdcTdlDocResponse.getEsignDoc());

            emudraRequest.setDisplay_on_page("custom");
            coordinates.setLlx(35);
            coordinates.setLly(35);
            coordinates.setUrx(322);
            coordinates.setUry(60);

            coordinatesList.add(coordinates);
            sign_coordinates.setCoordinates(coordinatesList);

            emudraRequest.setSign_coordinates(sign_coordinates);

            return emudraRequest;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
    public EmudraExternalResponse eMudraService(String leadId, EmudraRequest emudraRequest){

        EmudraRequest emudraResponse =new EmudraRequest();
        SfdcTdlDocResponse sfdcTdlDocResponse = new SfdcTdlDocResponse();
        RestTemplate restTemplate = new RestTemplate();
        EmudraExternalResponse  emudraExternalResponse =new EmudraExternalResponse();
//        basePath=appProps.geteMudraFilePath();
        String url = "http://digio.tatacapital.com:8080/doc_signer/signdoc";
        AuditDetailsPayload auditDetails  = new AuditDetailsPayload();

        try {
            auditDetails.setApiName("Digio EmudraService");
            auditDetails.setLeadId(leadId);
            auditDetails.setRequestUrl(url);

            logger.info("Emudra call for leadId {}",leadId);

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Content-Type", "application/json");

            HttpEntity<?> httpEntity = new HttpEntity<>(emudraRequest, headers);

            auditDetails.setRequestTime(new Date());

            emudraExternalResponse = restTemplate.postForObject(url, httpEntity, EmudraExternalResponse.class);
            auditDetails.setResponseTime(new Date());

            if (emudraExternalResponse.getSigned_file_content() !=null) {
                auditDetails.setStatus(ProjectConstants.SUCCESS);
                sfdcTdlDocResponse.setEmudraStatus(ProjectConstants.SUCCESS);
            }

            auditDetails.setRequestFileName("Combind Pdf Document");
            auditDetails.setResponseFileName("Signed pdf Document");
            if (emudraExternalResponse.getSigned_file_content() !=null) {
                File tempFile= textToFile(emudraExternalResponse.getSigned_file_content(), basePath+leadId+"_EsignedDoc.txt");
                File file = decode(tempFile.getAbsolutePath(), basePath+leadId+ "_EsignedDoc.pdf");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (auditDetails.getLeadId() !=null && auditDetails.getRequestUrl() !=null ){
                auditDetailsUtility.saveAuditDetails(auditDetails);
            }

            if (emudraExternalResponse.getSigned_file_content() != null) {
                sfdcTdlDocResponse.setLeadId(Integer.valueOf(leadId));
                sfdcTdlDocResponse.setEsignDoc(emudraExternalResponse.getSigned_file_content());
            }

            if (sfdcTdlDocResponse.getLeadId() !=null) {
                sfdcTdlDocDAO.saveOrUpdateSfdcTdlDoc(sfdcTdlDocResponse);
                logger.info("EmudraDoc is updated successfully for customer :"+sfdcTdlDocResponse.getLeadId());
            }

            logger.info("Document is saved.");
        }

        return emudraExternalResponse;
    }

    public String makeEsignPDF(Integer leadId,String base64KfsDoc){
//        basePath=appProps.geteMudraFilePath();
        File file = null,file1 = null,file2 = null,file3=null, file0=null;
        SfdcTdlDocResponse sfdcTdlDocResponse= new SfdcTdlDocResponse();
        String path=basePath;
        File tempFile;
        String destenation;

        try {
            sfdcTdlDocResponse = sfdcTdlDocDAO.getSfdcTdlDocByLeadId(leadId);


            file0 = new File(basePath + "coverPage.pdf");

            if (sfdcTdlDocResponse.getSfdcDoc() !=null) {
                tempFile= textToFile(sfdcTdlDocResponse.getSfdcDoc(),path+leadId+ "_sfdcbase64.txt");
                file = decode(tempFile.getAbsolutePath(), path+leadId+ "_sfdcDoc.pdf");
                logger.info("file path "+tempFile.getAbsolutePath());
            }
            if (sfdcTdlDocResponse.getTdlDocLoanAgr() !=null) {
                tempFile= textToFile(sfdcTdlDocResponse.getTdlDocLoanAgr(),path+leadId+"_tdlLAgrbase64.txt");
                file1 = decode(tempFile.getAbsolutePath(), path+leadId+"_TdlDocLoanAgr.pdf");
            }
            if (sfdcTdlDocResponse.getTdlDocTnc() !=null) {
                tempFile= textToFile(sfdcTdlDocResponse.getTdlDocTnc(),path+leadId+"_tdlTncBase64.txt");
                file2 = decode(tempFile.getAbsolutePath(), path+leadId+"_TdlDocTnc.pdf");
            }

            if (base64KfsDoc !=null) {
                tempFile= textToFile(base64KfsDoc,path+leadId+"_KfsBase64.txt");
                file3 = decode(tempFile.getAbsolutePath(), path+leadId+"_KfsDoc.pdf");
            }

            //file array creating to send for merging.
            File[] files={file0,file,file1,file2,file3};

            destenation=path;

            return combineTwoPdf(files,destenation, String.valueOf(leadId));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.info("PDF created successfully.");
        }
        return "Success";
    }

    public void deleteExtraFileFromServer(String leadId){
//        String basePath= appProps.geteMudraFilePath();
        try {
            logger.info("Detleting the file For leadId {}",leadId);
//            StringBuilder completeFileName = new StringBuilder(file1.getPath());
//            completeFileName.append("/").append("PL_CKYCDATA_").append(appId).append(".txt");
//            logger.info("Complete File Name : " + completeFileName);
//            File newFile = new File(completeFileName.toString());

            File sfdcTextDoc = new File(basePath+leadId+ "_sfdcbase64.txt");
            if (sfdcTextDoc.exists()) sfdcTextDoc.delete();

            File sfdcPdfDoc = new File(basePath+leadId+ "_sfdcDoc.pdf");
            if (sfdcPdfDoc.exists()) sfdcPdfDoc.delete();

            File tdlTnCTextDoc = new File(basePath+leadId+ "_tdlTncBase64.txt");
            if (tdlTnCTextDoc.exists()) tdlTnCTextDoc.delete();

            File tdlTnCPdfDoc = new File(basePath+leadId+ "_TdlDocTnc.pdf");
            if (tdlTnCPdfDoc.exists()) tdlTnCPdfDoc.delete();

            File tdlLAgrTextDoc = new File(basePath+leadId+ "_tdlLAgrbase64.txt");
            if (tdlLAgrTextDoc.exists()) tdlLAgrTextDoc.delete();

            File tdlLAgrPdfDoc = new File(basePath+leadId+ "_TdlDocLoanAgr.pdf");
            if (tdlLAgrPdfDoc.exists()) tdlLAgrPdfDoc.delete();

            File kfsTextDoc = new File(basePath+leadId+ "_KfsBase64.txt");
            if (kfsTextDoc.exists()) kfsTextDoc.delete();

            File kfsPdfDoc = new File(basePath+leadId+ "_KfsDoc.pdf");
            if (kfsPdfDoc.exists()) kfsPdfDoc.delete();


          /*  if (newFile.exists()) {
                newFile.delete();
                newFile.createNewFile();
            } else {
                newFile.createNewFile();
            }        } catch (Exception e) {*/

        } finally {
            logger.info("File deleted Successfully.");
        }
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

//    @Override
    public void jobsForEmudraAndDmsEmails(List<String> request){
        EmudraRequest emudraRequest =new EmudraRequest();
        EmudraExternalResponse emudraExternalResponse = new EmudraExternalResponse();
        SfdcTdlDocResponse sfdcTdlDocResponse = new SfdcTdlDocResponse();
        RootResponse rootResponse= new RootResponse();
        String base64KfsDoc=null;
        try {
            for(String leadId: request){
                logger.info("jobsForEmudraAndDmsEmails Stared For leadid {}",leadId);
                sfdcTdlDocResponse= sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));

                if(sfdcTdlDocResponse.getEsignDoc() ==null){ //checking if meargedpdf is not created

                    List<UploadDoc> uploadDoc=uploadDocDAO.getUploadDocResByPlleadIdAndDocType(leadId, ProjectConstants.CUSTOMER_KFS_GENERATION_DOC_TYPE);
                    if (uploadDoc.size() >0)
                        base64KfsDoc =uploadDoc.get(0).getBase64FormattedData();
                    logger.info("UploadDoc Size is {} in Job", uploadDoc.size());
                    makeEsignPDF(Integer.valueOf(leadId),base64KfsDoc);
                }else {
                    logger.info("Esigned merged doc is available.");
                }

                if (!sfdcTdlDocResponse.getEmudraStatus().equals(ProjectConstants.SUCCESS)) {
                    emudraRequest = creatingEmudraRequest(leadId);
                    emudraExternalResponse = eMudraService(leadId, emudraRequest);

                }else{
                    logger.info("Esign Status Is available.");
                }

                if (emudraExternalResponse.getSigned_file_content() != null) {
                    logger.info("Pusing Document to DMS and Emails");
                    pushEmudraToDMSAndEmail(leadId, emudraExternalResponse.getSigned_file_content());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.info("jobsForEmudraAndDmsEmails ended");
        }
    }

    public String combineTwoPdf(File[] filePaths, String destinationFileName, String leadId) throws IOException {// this for acceptng the file array.

        String  esignBase64Data= null;
        SfdcTdlDocResponse sfdcTdlDocResponse = new SfdcTdlDocResponse();
        String path=null;
        try {
            PDFMergerUtility obj = new PDFMergerUtility();
            obj.setDestinationFileName(destinationFileName+leadId+"_mergedFile.pdf");

            // Add all source files, to be merged
            for (File files : filePaths){
                obj.addSource(files.getAbsoluteFile());
            }

            obj.mergeDocuments();


            encode(destinationFileName+leadId+"_mergedFile.pdf", destinationFileName+leadId+"_mergedFile.txt", true);
            System.out.println("PDF Documents merged to a single file");
            sfdcTdlDocResponse =sfdcTdlDocDAO.getSfdcTdlDocByLeadId(Integer.valueOf(leadId));
            path =destinationFileName+leadId+"_mergedFile.txt";
            esignBase64Data =copyDataFromFile(path) ;

            if (sfdcTdlDocResponse.getEsignDoc() == null || esignBase64Data != null) {
                sfdcTdlDocResponse.setEsignDoc(esignBase64Data);
                sfdcTdlDocResponse.setLeadId(Integer.valueOf(leadId));
                sfdcTdlDocResponse.setUpdatedDate(new Date());
                sfdcTdlDocResponse.setEmudraStatus(ProjectConstants.FAIL);
            }
            sfdcTdlDocDAO.saveOrUpdateSfdcTdlDoc(sfdcTdlDocResponse);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String  copyDataFromFile(String filePath){
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(filePath)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static File encode(String sourceFile, String targetFile, boolean isChunked) {

        try {
            byte[] base64EncodedData = Base64.encodeBase64(loadFileAsBytesArray(sourceFile), isChunked);

            return writeByteArraysToFile(targetFile, base64EncodedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File decode(String sourceFile, String targetFile) {
        File responseFile;
        try {
            byte[] decodedBytes = Base64.decodeBase64(loadFileAsBytesArray(sourceFile));

            responseFile= writeByteArraysToFile(targetFile, decodedBytes);
            return  responseFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static File writeByteArraysToFile(String fileName, byte[] content) throws IOException {

        try {
            File file = new File(fileName);
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
            writer.write(content);
            writer.flush();
            writer.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] loadFileAsBytesArray(String fileName) throws Exception {

        try {
            File file = new File(fileName);
            int length = (int) file.length();
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[length];
            reader.read(bytes, 0, length);
            reader.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


}
