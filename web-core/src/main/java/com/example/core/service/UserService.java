package com.example.core.service;



import com.example.api.req.SignInReq;
import com.example.model.entity.User;

import java.util.List;

public interface UserService {
    void sendCode(SignInReq req);

    List<User> getAllUser(int size);

    Long signUser(SignInReq req);

    User findUserByUid(Long uid);

    User findUserByEmail(String email);
}
