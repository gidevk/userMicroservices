package com.user.serviceImpl;

import com.user.entity.userEntity;
import com.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    userEntity[] lists={new userEntity(1111L,"Indradev","950783892"),
                    new userEntity(1112L,"dev","950745892")};


    @Override
    public userEntity getUser(Long id){
        return Arrays.stream(lists).filter(userEntity -> userEntity.getUserId().equals(id)).findAny().orElse(null);
//                lists.stream().filter(userEntity -> userEntity.getUserId().equals(id)).findAny().orElse(null);
    }
}
