package com.example.core.common;

import com.example.api.type.AttitudeType;

public interface RedisEssayService {
    public boolean setLike(Long essayId, Long uid);
    public boolean setDislike(Long essayId, Long uid);
    public boolean cancelLike(Long essayId, Long uid);
    public boolean cancelDislike(Long essayId, Long uid);
    public int getLikeNum(Long essayId);
    public int getDislikeNum(Long essayId);
    public String getAttitude(Long essayId, Long uid);
    public boolean setAttitude(Long essayId, Long uid, String attitude, AttitudeType attitudeType, boolean state);
    public boolean hasLike(String attitude);
    public boolean hasDislike(String attitude);

}
