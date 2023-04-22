package com.expriment.service;

import com.expriment.entity.UserDetails;
import org.springframework.http.ResponseEntity;

public interface PersonalDetailsService {
    ResponseEntity<?> savePersonalDetails(UserDetails userDetails);

    ResponseEntity<?> getPersonalDetails(Integer cpId);

    ResponseEntity<?> updatePersonalDetails(UserDetails userDetails);
}
