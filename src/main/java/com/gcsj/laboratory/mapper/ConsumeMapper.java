package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.Consume;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName ConsumeMapper.java
 * @Description TODO
 * @createTime 2020/12/5,21:41
 */
public interface ConsumeMapper extends Mapper<Consume> {
    @Update("update consume set stock=stock-#{cost_count} where id = #{consume_id}")
    void updateStock(@Param("consume_id") Long consume_id, @Param("cost_count") Double cost_count);

    @Select("select * from consume where id = #{id}")
    Consume findConsumeByConsumeId(long id);

    @Results(id = "consumeMap", value = {
            @Result(column = "experiment_type_id", property = "experiment_type_id"),
            @Result(column = "experiment_type_id", property = "experimentType",
                    one = @One(select = "com.gcsj.laboratory.mapper.ExperimentTypeMapper.findById", fetchType = FetchType.EAGER))
    })
    @Select("<script>" +
            "select * from consume " +
            "<where>" +
            "<if test='experiment_type_id != null and experiment_type_id != -1'>" +
            " and experiment_type_id = #{experiment_type_id}" +
            "</if>" +
            "</where>" +
            " order by experiment_type_id" +
            "</script>")
    List<Consume> selectAllConsume(Long experiment_type_id);

    @Select("select * from consume where experiment_type_id = #{typeId}")
    List<Consume> selectConsumesByExperimentTypeId(long typeId);
}
