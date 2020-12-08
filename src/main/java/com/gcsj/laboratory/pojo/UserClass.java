package com.gcsj.laboratory.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName UserClass.java
 * @Description 用户班级表
 * @createTime 2020/12/8,21:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_classes")
public class UserClass {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long user_id;
    private Long classes_id;
}
