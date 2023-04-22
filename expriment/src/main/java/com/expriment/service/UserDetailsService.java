package com.expriment.service;

import com.expriment.entity.UserDetails;

public interface UserDetailsService {
    UserDetails saveUserDetails(UserDetails userDetails);

    UserDetails getUserDetails(Integer cpId);
}
