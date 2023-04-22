package com.expriment.service;

import com.expriment.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface UserService {
    UserEntity getUser(Long id);

    boolean validateString(String s) throws IOException;
    boolean validateStringList(String s) throws IOException;
}
