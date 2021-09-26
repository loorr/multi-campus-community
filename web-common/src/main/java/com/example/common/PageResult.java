package com.example.common;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private Integer currPage;
    private Integer total;
    private Integer pageSize;
    private List<T> rows;

    public static <T> PageResult<T> getPageResult(Page<T> page){
        return new PageResult<T>(page.getPageNum(),new Integer((int) page.getTotal()), page.getPageSize(), page.getResult());
    }
}
