package com.gcsj.laboratory.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName Grade.java
 * @Description 年级表
 * @createTime 2020/12/8,13:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grade")
public class Grade {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long school_id;
    @Transient
    private String schoolName;
    private String name;

    @Transient
    private List<Classes> children;
}
