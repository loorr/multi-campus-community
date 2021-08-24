package com.example.rest;

import com.example.core.common.UserCacheService;
import com.example.core.service.UserService;
import com.example.model.entity.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserCacheServiceTest {
    @Resource
    UserCacheService userCacheService;
    @Resource
    UserService userService;


    @SneakyThrows
    @Test
    void test(){
        for (int i = 0; i < 10; i++) {
            long startTime=System.currentTimeMillis();   //获取开始时间
            System.out.println(userCacheService.getUserById(123456L));
            long endTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("run time "+(endTime-startTime)+" ms");

        }
    }
}
