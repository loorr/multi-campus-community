package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupType {
    GENERAL(0),
    PERSONAL(1);

    private int type;
}
