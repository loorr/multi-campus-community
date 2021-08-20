package com.example.core.common;

public interface RedisUserService {

    void setAuthCode(String email, String code);

    String getAuthCode(String email);

    void setUserPasswordByUid(Long uid, String password);

    String getUserPasswordByUid(Long uid);

}
