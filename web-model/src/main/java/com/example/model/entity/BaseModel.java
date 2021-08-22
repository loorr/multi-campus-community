package com.example.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author zjianfa
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel {
    private Integer id;
    private Timestamp dbCreateTime;
    private Timestamp dbModifyTime;
}
