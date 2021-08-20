package com.example.core.common;

import com.example.api.type.AttitudeType;

public interface RedisEssayService {

    boolean setLike(Long essayId, Long uid);

    boolean setDislike(Long essayId, Long uid);

    boolean cancelLike(Long essayId, Long uid);

    boolean cancelDislike(Long essayId, Long uid);

    int getLikeNum(Long essayId);

    int getDislikeNum(Long essayId);

    String getAttitude(Long essayId, Long uid);

    boolean setAttitude(Long essayId, Long uid, String attitude, AttitudeType attitudeType, boolean state);

    boolean hasLike(String attitude);

    boolean hasDislike(String attitude);

}
