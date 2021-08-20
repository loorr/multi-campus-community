package com.example.core.common;

import com.example.api.common.ChatException;
import com.example.common.BaseErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.example.core.common.RedisConstant.getRedisKey;


@Slf4j
@Service
public class RedisEssayService {
    @Autowired
    RedissonClient redissonClient;

    public boolean setLike(Long essayId, Long uid){
        String userAttitude = getAttitude(essayId, uid);
        if (hasLike(userAttitude)){
            return true;
        }
        String likeKey = getRedisKey(RedisConstant.ESSAY_LIKE_NUM, essayId);
        RAtomicLong redisAtomicLong = redissonClient.getAtomicLong(likeKey);
        if (!redisAtomicLong.isExists()){
            redisAtomicLong.set(1L);
        }else {
            redisAtomicLong.incrementAndGet();
        }
        return true;
    }


    public boolean setDislike(Long essayId, Long uid){
        return false;
    }

    public boolean cancelLike(Long essayId, Long uid){
        String userAttitude = getAttitude(essayId, uid);
        if (!hasLike(userAttitude)){
            return true;
        }
        String likeKey = getRedisKey(RedisConstant.ESSAY_LIKE_NUM, essayId);
        RAtomicLong redisAtomicLong = redissonClient.getAtomicLong(likeKey);
        if (!redisAtomicLong.isExists()){
            redisAtomicLong.set(0L);
            return true;
        }else {
            redisAtomicLong.decrementAndGet();
        }
        return true;
    }

    public boolean cancelDislike(Long essayId, Long uid){
        return false;
    }

    public int getLikeNum(Long essayId){
        String likeKey = getRedisKey(RedisConstant.ESSAY_LIKE_NUM, essayId);
        RAtomicLong redisAtomicLong = redissonClient.getAtomicLong(likeKey);
        if (!redisAtomicLong.isExists()){
            return 0;
        }
        return (int) redisAtomicLong.get();
    }

    public int getDisLikeNum(Long essayId){
        String likeKey = getRedisKey(RedisConstant.ESSAY_DISLIKE_NUM, essayId);
        RAtomicLong redisAtomicLong = redissonClient.getAtomicLong(likeKey);
        if (!redisAtomicLong.isExists()){
            return 0;
        }
        return (int) redisAtomicLong.get();
    }

    public String getAttitude(Long essayId, Long uid){
        String key = getRedisKey(RedisConstant.USER_ESSAY_STATE, uid, essayId);
        String userAttitude =  (String) redissonClient.getBucket(key).get();
        return userAttitude;
    }

    public boolean setAttitude(Long essayId, Long uid, boolean like, boolean dislike){
        String key = getRedisKey(RedisConstant.USER_ESSAY_STATE, uid, essayId);
        StringBuffer sb = new StringBuffer(2);
        if (like){
            sb.append("1");
        }else {
            sb.append("0");
        }
        if (dislike){
            sb.append("1");
        }else {
            sb.append("0");
        }
        RBucket<String> userAttitude =  redissonClient.getBucket(key);
        userAttitude.set(sb.toString());
        return true;
    }

    public boolean hasLike(String attitude){
        checkParameter(attitude);
        return attitude.charAt(0) == '1';
    }

    public boolean hasDislike(String attitude){
        checkParameter(attitude);
        return attitude.charAt(1) == '1';
    }

    public void checkParameter(String parameter){
        if (!StringUtils.hasLength(parameter) || parameter.length() != 2){
            throw new ChatException(BaseErrorCode.ILLEGAL_PARAMETERS);
        }
    }
}
