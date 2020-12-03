package com.gcsj.laboratory.pojo;

import lombok.Builder;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Table(name = "school")
public class School {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long edu_id;
    private String school_name;
    private String school_address;
}
