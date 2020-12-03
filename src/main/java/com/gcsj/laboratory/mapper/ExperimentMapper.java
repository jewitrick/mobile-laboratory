package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.Experiment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ExperimentMapper extends Mapper<Experiment> {

    @Results(id = "experimentMap", value = {
            @Result(column = "type_id", property = "type_id"),
            @Result(column = "type_id", property = "experimentType",
                    one = @One(select = "com.gcsj.laboratory.mapper.ExperimentTypeMapper.findById", fetchType = FetchType.EAGER))
    })
    @Select("select id,type_id,experi_name,experi_desc from experiment_info")
    public List<Experiment> findAllExperiments();

    @ResultMap("experimentMap")
    @Select("select * from experiment_info where id = #{id}")
    Experiment findById(long id);
}
