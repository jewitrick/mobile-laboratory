package com.gcsj.laboratory.pojo;

import lombok.Builder;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Table(name = "edu_bureau")
public class EduBureau {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String edu_name;

}
