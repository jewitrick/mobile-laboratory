package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.CarApply;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CarApplyMapper extends Mapper<CarApply> {

    @Results(id = "carApplyMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id", property = "user_id"),
            @Result(column = "experiment_apply_id", property = "experimentApply",
                    one = @One(select = "com.gcsj.laboratory.mapper.ExperimentApplyMapper.findExperimentById", fetchType = FetchType.EAGER)),
            @Result(column = "user_id", property = "edu_name",
                    one = @One(select = "com.gcsj.laboratory.mapper.EduBureauMapper.findEubreauById", fetchType = FetchType.EAGER)),
            @Result(column = "id", property = "ctrlStatus",
                    one = @One(select = "com.gcsj.laboratory.mapper.CarCtrlMapper.findCtrlStatusByCarApplyId", fetchType = FetchType.EAGER))
    })
    @Select("<script>" +
            "select * from car_apply " +
            "<where>" +
            "<if test='status != null and status != -1'>" +
            " and edu_status = #{status}" +
            "</if>" +
            "</where>" +
            " order by edu_status" +
            "</script>")
    List<CarApply> findAll(Long status);

    @Select("select edu_status from car_apply where experiment_apply_id = #{id}")
    Integer findEduStatusByApplyId(Long id);

    @ResultMap("carApplyMapper")
    @Select("<script>" +
            "select * from car_apply " +
            "<where>" +
            "<if test='status != null and status != -1'>" +
            " and edu_status = #{status}" +
            "</if>" +
            " and user_id in (select user_id from user_edu where edu_id = #{eduId}) " +
            "</where>" +
            " order by edu_status" +
            "</script>")
    List<CarApply> findAllByEduId(@Param("eduId") Long eduId, @Param("status") Long status);

    @ResultMap("carApplyMapper")
    @Select("select * from car_apply where id=#{id}")
    CarApply findExperimentById(Long id);

    @Select("select * from car_apply where id = #{id}")
    CarApply findById(Long id);

    @Select("select experiment_apply_id from car_apply where id = #{carApplyId}")
    long findExperimentApplyIdByCarApplyId(long carApplyId);
}
