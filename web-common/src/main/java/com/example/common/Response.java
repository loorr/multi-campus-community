package com.example.common;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author zjianfa
 */
@Data

@AllArgsConstructor
public class Response<T> {


    private Integer code;

    private String message;

    private Date timestamp;


    private T data;


}
