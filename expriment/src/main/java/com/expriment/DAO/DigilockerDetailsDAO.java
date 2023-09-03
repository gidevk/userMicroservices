package com.expriment.DAO;

import com.expriment.entity.DigilockerDetails;
import com.expriment.entity.UserDetails;
import org.springframework.http.ResponseEntity;

public interface DigilockerDetailsDAO {
    DigilockerDetails saveOrUpdate(DigilockerDetails digilockerDetails);


    DigilockerDetails getDigilockerDetails(long leadId);

//    ResponseEntity<?> openNachApis(String leadId, String topUpLeadId);
}
