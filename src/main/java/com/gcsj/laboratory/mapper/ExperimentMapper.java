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
    @Select("<script>" +
            "select * from experiment_info " +
            "<where>" +
            "<if test='type_id != null and type_id != -1'>" +
            " and type_id = #{type_id}" +
            "</if>" +
            "</where>" +
            " order by type_id" +
            "</script>")
    List<Experiment> findAllExperiments(long type_id);

    @ResultMap("experimentMap")
    @Select("select * from experiment_info where id = #{id}")
    Experiment findById(long id);

    @Select("select type_id from experiment_info where id = #{experimentId}")
    long findTypeIdByExperimentId(long experimentId);
}
