/*
package com.expriment.Controller;

import java.util.ArrayList;
import java.util.List;

public class TestMain {


    public static void main1(String[] args) {




        List<Object> docTypeDataList= new ArrayList<>();
        docTypeDataList.add("");
//        if((custmerHash !=null || opportunityId !=null) && docTypeDataList.size() >0 ){
            int flag=0;
            for (DocTypeData doc :docTypeDataList){
                if (doc.getDoc() !=null && !doc.getDoc().equals("") && doc.getDocType() != null && !doc.getDocType().equals("")) {
//                        if (doc.getLeadId()== null){doc.setLeadId(leadId); }

                    rootResponse1= saveDocForEmudra(doc,leadId,custmerHash);
                    logger.info("Saved docuementType {} with Status {}",doc.getDocType(), rootResponse1.getRetStatus());
                }else if(doc.getDocType() == null && doc.getDocType().equals("")){
                    flag=1;
                    if (!doc.getDocType().equals("sl"))  // TODO: 4/10/2023 hhhh
                        continue;
                }else {
                    flag=2;
                    continue;
                }
            }
            if (flag==1) rootResponse.setSysErrorMessage("");
            else if (flag==2) rootResponse.setSysErrorMessage("");
            else if(flag ==0) rootResponse.setSysErrorMessage("Given document is saved.");
        }
//        else{
//            rootResponse.setSysErrorMessage("DocTypeData is not available");
//        }
    }
}
*/
