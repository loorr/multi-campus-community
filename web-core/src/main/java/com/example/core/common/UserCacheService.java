package com.example.core.common;

import com.example.model.entity.User;

import java.util.concurrent.ExecutionException;

public interface UserCacheService {
    User getUserById(Long uid) throws ExecutionException;

}
