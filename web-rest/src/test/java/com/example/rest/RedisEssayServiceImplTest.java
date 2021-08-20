package com.example.rest;


import com.example.core.common.impl.RedisEssayServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RedisEssayServiceImplTest {
    @Autowired
    RedisEssayServiceImpl redisEssayService;

    @Test
    void setLike() {
        redisEssayService.setLike(1L, 2L);
        redisEssayService.setLike(1L, 1L);
        redisEssayService.setLike(1L, 2L);
        System.out.println(redisEssayService.getLikeNum(1L));
        redisEssayService.cancelLike(1L, 2L);
        redisEssayService.cancelLike(1L, 2L);
        System.out.println(redisEssayService.getLikeNum(1L));
        System.out.println(redisEssayService.getDislikeNum(1L));
        System.out.println(redisEssayService.getDislikeNum(2L));
        redisEssayService.setLike(1L, 2L);
        System.out.println(redisEssayService.getAttitude(1L, 2L));
    }

    @Test
    void setDislike() {

    }

    @Test
    void cancelLike() {
    }

    @Test
    void cancelDislike() {
    }

    @Test
    void getLikeNum() {
    }

    @Test
    void getDisLikeNum() {
    }

    @Test
    void getAttitude() {
    }

    @Test
    void setAttitude() {
    }

    @Test
    void hasLike() {
    }

    @Test
    void hasDislike() {
    }

    @Test
    void checkParameter() {
    }
}
