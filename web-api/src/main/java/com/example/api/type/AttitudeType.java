package com.example.api.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttitudeType {
    LIKE(0),
    DISLIKE(1),
    CANCEL_LIKE(2),
    CANCEL_DISLIKE(3),
    ;
    private int code;
}
