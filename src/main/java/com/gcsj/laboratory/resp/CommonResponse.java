package com.gcsj.laboratory.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {

    private boolean flag;
    private String message;
    private List<T> data;

}