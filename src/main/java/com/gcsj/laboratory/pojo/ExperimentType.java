package com.gcsj.laboratory.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "experiment_type")
public class ExperimentType {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String type_name;
}
