package com.expriment.DAO;

import com.expriment.entity.DigilockerDetails;
import com.expriment.entity.UserDetails;

public interface DigilockerDetailsDAO {
    DigilockerDetails saveOrUpdate(DigilockerDetails digilockerDetails);


    DigilockerDetails getDigilockerDetails(long leadId);
}
