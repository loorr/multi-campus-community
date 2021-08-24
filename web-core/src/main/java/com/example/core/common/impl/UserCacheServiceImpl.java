package com.example.core.common.impl;

import com.example.core.common.UserCacheService;
import com.example.core.service.UserService;
import com.example.model.entity.User;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class UserCacheServiceImpl implements UserCacheService {

    @Resource
    private UserService userService;

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

    @Override
    public User getUserById(Long uid){
        System.out.println(cache.stats());
        return cache.get(uid, (key)-> userService.findUserByUid(key));
    }
}
