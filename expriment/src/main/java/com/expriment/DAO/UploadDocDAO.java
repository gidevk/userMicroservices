package com.expriment.DAO;

import com.expriment.entity.UploadDoc;

import java.util.List;

public interface UploadDocDAO {
//    UploadDoc saveOrUpdate(UploadDoc uploadDoc);

    UploadDoc saveOrUpdate(UploadDoc uploadDoc);

    List<UploadDoc> getUploadDocResByLeadId(String leadId, String docUploadType);

    List<UploadDoc> getUploadDocResByLeadIdAndDocName(String leadId, String docUploadName);

    List<UploadDoc> getUploadDocResByPlleadIdAndDocName(String plleadId, String docUploadName);

    List<String> getDocumentTypeByLeadId(String leadId);

    List<UploadDoc> getDocsToUpload(Short maxRetryCount);

    List<UploadDoc> getUploadedDocsByLeadId(String leadId);

    UploadDoc getUploadDocByLeadId(String leadId);

//    UploadDoc getUploadDocResByDocId(Long docId);

  /*  List<DocumentName> getDocumentsByLoanType(String loanType);

    NameMatchingUploadDoc saveOrUpdate(NameMatchingUploadDoc nameMatchingUploadDoc);

    List<NameMatchingUploadDoc> getNameMatchingCustomerUploadDetailsByLeadId(String leadId);
*/
    List<UploadDoc> getUploadDocResByPlleadIdAndContainsDocName(String plleadId, String docUploadName);

    List<UploadDoc> getUploadDocResByPlleadIdAndDocType(String plleadId, String docType);
}
