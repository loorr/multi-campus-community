package com.example.common;

import lombok.Data;

import java.util.List;

/**
 * @des PageHelper 分页结果集
 * @param <T>
 */
@Data
public class PageHelperUtil<T>{
    /*共有数据*/
    private Long total;
    /*共有页数*/
    private Integer pageTotal;
    /*当前页*/
    private Integer page;
    /*每页显示条数*/
    private Integer pageSize;
    /*结果集*/
    private List<T> list;
}
