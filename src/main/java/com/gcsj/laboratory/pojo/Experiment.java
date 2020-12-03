package com.gcsj.laboratory.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Table(name = "experiment_info")
public class Experiment {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long type_id;

    @Transient
    private ExperimentType experimentType;
    private String experi_name;
    private String experi_desc;
}
