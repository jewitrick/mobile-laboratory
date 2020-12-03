package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.ExperimentType;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface ExperimentTypeMapper extends Mapper<ExperimentType> {

    @Select("select id, type_name from experiment_type where id = #{id}")
    ExperimentType findById(Long id);

}
