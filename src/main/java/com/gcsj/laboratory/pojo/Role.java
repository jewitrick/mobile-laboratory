package com.gcsj.laboratory.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "role")
public class Role {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String role_name;
}
