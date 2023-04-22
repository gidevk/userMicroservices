package com.expriment.Testing;

public interface SfdcTdlDocDAO {

    SfdcTdlDocResponse saveOrUpdateSfdcTdlDoc(SfdcTdlDocResponse sfdcTdlDocResponse);

//    SfdcTdlDocResponse getSfdcTdlDocByLeadId(String leadId);

    SfdcTdlDocResponse getSfdcTdlDocByLeadId(Integer leadId);
}
