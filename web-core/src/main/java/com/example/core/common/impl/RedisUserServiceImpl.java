package com.example.core.common.impl;

import com.example.core.common.RedisUserService;
import com.example.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.example.core.common.RedisConstant.*;


@Slf4j
@Service
public class RedisUserServiceImpl implements RedisUserService {

    @Autowired
    RedissonClient redissonClient;

    @Override
    public void setAuthCode(String email, String code){
        String key = buildKey(AUTH_CODE_PREFIX, String.valueOf(email));
        RBucket<String> nameRBucket =  redissonClient.getBucket(key);
        nameRBucket.set(code, 2, TimeUnit.MINUTES);
        log.info("set value by {} -- {}", key, code);
    }

    @Override
    public String getAuthCode(String email){
        String key = buildKey(AUTH_CODE_PREFIX, email);
        String code = (String) redissonClient.getBucket(key).get();
        log.info("get value by {} -- {}", key, code);
        return code;
    }

    @Override
    public void setUserPasswordByUid(Long uid, String password){
        String key = buildKey(UID, String.valueOf(uid));
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(password, 15*60, TimeUnit.SECONDS);
    }

    @Override
    public String getUserPasswordByUid(Long uid){
        String key = buildKey(UID, String.valueOf(uid));
        String password = (String)redissonClient.getBucket(key).get();
        return password;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }
}
