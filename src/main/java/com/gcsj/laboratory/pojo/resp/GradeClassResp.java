package com.gcsj.laboratory.pojo.resp;

import com.gcsj.laboratory.pojo.Classes;
import com.gcsj.laboratory.pojo.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName GradeClassResp.java
 * @Description TODO
 * @createTime 2020/12/8,13:43
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradeClassResp {

    private Long id;
    private String name;
    private List<Grade> children;

}
