package com.example.model.entity;

import com.example.model.GroupType;
import lombok.Data;

/**
 * @author zjianfa
 */
@Data
public class Group {
    private Long groupId;
    private GroupType type;
    private Long leaderId;
    private String groupName;
    private int maxNum = 500;
    private int currNum = 0;
}
