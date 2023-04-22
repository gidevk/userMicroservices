package com.expriment.DAO;

import com.expriment.entity.UserDetails;

public interface UserDetailsDao {
    UserDetails saveUserDetails(UserDetails userDetails);
    UserDetails getUserDetails(Integer userId);

    UserDetails saveOrUpdateUserDteail(UserDetails userDetails);
}
