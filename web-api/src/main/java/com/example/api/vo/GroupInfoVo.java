package com.example.api.vo;

import com.example.api.type.GroupType;
import lombok.Data;

/**
 * @author zjianfa
 */
@Data
public class GroupInfoVo {
    private Long groupId;
    private String groupName;
    private GroupType type;
    private int currNum;
    private int maxNum;
    private String nickname;
}
