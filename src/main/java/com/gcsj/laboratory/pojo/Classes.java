package com.gcsj.laboratory.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName Classes.java
 * @Description 班级表
 * @createTime 2020/12/8,13:45
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "classes")
public class Classes {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long grade_id;
    private String name;

    @Transient
    private Grade grade;

    @Transient
    private String schoolName;
}
