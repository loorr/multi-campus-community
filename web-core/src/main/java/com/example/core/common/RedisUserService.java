package com.example.core.common;

import com.example.model.entity.User;

public interface RedisUserService {

    void setAuthCode(String email, String code);

    String getAuthCode(String email);

    void setUserPasswordByUid(Long uid, String password);

    String getUserPasswordByUid(Long uid);

    User getUserById(Long id);
}
