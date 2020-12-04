package com.gcsj.laboratory.pojo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Colin
 * @version 1.0.0
 * @description TODO
 * @date 2020/12/2 22:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryResponse<T> {

    private boolean flag;

    private String message;

    private List<T> data;

    private long total;
}
