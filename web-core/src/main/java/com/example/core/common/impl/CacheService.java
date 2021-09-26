package com.example.core.common.impl;

import com.example.model.entity.User;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    private Cache<Long, User> cache;

    @PostConstruct
    public void init(){
        // 通过CacheBuilder构建一个缓存实例
        cache = Caffeine.newBuilder()
                .maximumSize(1024)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .removalListener((key, value, cause)-> System.out.println(key + "" + value + " " + cause))
                .recordStats()
                .build();
    }

    public Cache<Long, User> getUserCache(){
        return cache;
    }

}
