package com.example.api.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttitudeType {
    LIKE(0),
    DISLIKE(1);
    private int code;
}
