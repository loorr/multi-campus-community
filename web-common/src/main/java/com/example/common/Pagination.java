package com.example.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Pagination implements Serializable {
    private Integer page;
    private Integer rows;
    private String order;
    private String sort;

    public Integer getPage(){
        if (this.page == null){
            this.page = 1;
        }
        return this.page;
    }

    public Integer getRows(){
        if (this.rows == null){
            this.rows = 10;
        }
        return this.rows;
    }
}
