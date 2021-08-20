package com.example.core.common.impl;

import com.example.api.common.ChatException;
import com.example.api.type.AttitudeType;
import com.example.common.BaseErrorCode;
import com.example.core.common.RedisConstant;
import com.example.core.common.RedisEssayService;
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
public class RedisEssayServiceImpl implements RedisEssayService {
    @Autowired
    RedissonClient redissonClient;

    @Override
    public boolean setLike(Long essayId, Long uid){
        String userAttitude = getAttitude(essayId, uid);
        if (hasLike(userAttitude)){
            return true;
        }
        String likeKey = RedisConstant.getRedisKey(RedisConstant.ESSAY_LIKE_NUM, essayId);
        RAtomicLong redisAtomicLong = redissonClient.getAtomicLong(likeKey);
        if (!redisAtomicLong.isExists()){
            redisAtomicLong.set(1L);
        }else {
            redisAtomicLong.incrementAndGet();
        }
        // 更新用户状态
        setAttitude(essayId, uid, userAttitude, AttitudeType.LIKE, true);
        return true;
    }

    @Override
    public boolean setDislike(Long essayId, Long uid){
        String userAttitude = getAttitude(essayId, uid);
        if (hasDislike(userAttitude)){
            return true;
        }
        String likeKey = getRedisKey(RedisConstant.ESSAY_DISLIKE_NUM, essayId);
        RAtomicLong redisAtomicLong = redissonClient.getAtomicLong(likeKey);
        if (!redisAtomicLong.isExists()){
            redisAtomicLong.set(1L);
        }else {
            redisAtomicLong.incrementAndGet();
        }
        // 更新用户状态
        setAttitude(essayId, uid, userAttitude, AttitudeType.DISLIKE, true);
        return true;
    }
    @Override
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
        // 更新用户状态
        setAttitude(essayId, uid, userAttitude, AttitudeType.LIKE, false);
        return true;
    }
    @Override
    public boolean cancelDislike(Long essayId, Long uid){
        String userAttitude = getAttitude(essayId, uid);
        if (!hasDislike(userAttitude)){
            return true;
        }
        String likeKey = getRedisKey(RedisConstant.ESSAY_DISLIKE_NUM, essayId);
        RAtomicLong redisAtomicLong = redissonClient.getAtomicLong(likeKey);
        if (!redisAtomicLong.isExists()){
            redisAtomicLong.set(0L);
            return true;
        }else {
            redisAtomicLong.decrementAndGet();
        }
        // 更新用户状态
        setAttitude(essayId, uid, userAttitude, AttitudeType.DISLIKE, false);
        return true;
    }

    @Override
    public int getLikeNum(Long essayId) {
        String likeKey = getRedisKey(RedisConstant.ESSAY_DISLIKE_NUM, essayId);
        RAtomicLong redisAtomicLong = redissonClient.getAtomicLong(likeKey);
        if (!redisAtomicLong.isExists()){
            return 0;
        }
        return (int) redisAtomicLong.get();
    }

    @Override
    public int getDislikeNum(Long essayId) {
        String likeKey = getRedisKey(RedisConstant.ESSAY_LIKE_NUM, essayId);
        RAtomicLong redisAtomicLong = redissonClient.getAtomicLong(likeKey);
        if (!redisAtomicLong.isExists()){
            return 0;
        }
        return (int) redisAtomicLong.get();
    }

    @Override
    public String getAttitude(Long essayId, Long uid){
        String key = getRedisKey(RedisConstant.USER_ESSAY_STATE, uid, essayId);
        RBucket<String> bucket  =   redissonClient.getBucket(key);
        if (!bucket.isExists()){
            return "00";
        }
        return bucket.get();
    }

    @Override
    public boolean setAttitude(Long essayId, Long uid, String attitude, AttitudeType attitudeType, boolean state){
        checkParameter(attitude);
        char[] c = attitude.toCharArray();
        c[attitudeType.getCode()] = state ? '1' : '0';
        String key = getRedisKey(RedisConstant.USER_ESSAY_STATE, uid, essayId);
        RBucket<String> userAttitude =  redissonClient.getBucket(key);
        userAttitude.set(String.valueOf(c));
        return true;
    }

    @Override
    public boolean hasLike(String attitude){
        checkParameter(attitude);
        return attitude.charAt(AttitudeType.LIKE.getCode()) == '1';
    }

    @Override
    public boolean hasDislike(String attitude){
        checkParameter(attitude);
        return attitude.charAt(AttitudeType.DISLIKE.getCode()) == '1';
    }

    public void checkParameter(String parameter){
        if (!StringUtils.hasLength(parameter) || parameter.length() != 2){
            throw new ChatException(BaseErrorCode.ILLEGAL_PARAMETERS);
        }
    }
}
