package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.ExperimentApply;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ExperimentApplyMapper extends Mapper<ExperimentApply> {

    @Results(id = "experimentApplyMapper", value = {
            @Result(column = "experi_id", property = "experi_id"),
            @Result(column = "experi_id", property = "experiment",
                    one = @One(select = "com.gcsj.laboratory.mapper.ExperimentMapper.findById", fetchType = FetchType.EAGER)),
            @Result(column = "user_id", property = "user",
                    one = @One(select = "com.gcsj.laboratory.mapper.UserMapper.findById", fetchType = FetchType.EAGER)),
            @Result(column = "user_id", property = "schoolName",
                    one = @One(select = "com.gcsj.laboratory.mapper.SchoolMapper.findSchoolNameByUserId", fetchType = FetchType.EAGER))
    })
    @Select("select * from experi_apply")
    List<ExperimentApply> findAll();

    @ResultMap("experimentApplyMapper")
    @Select("<script>" +
                "select * from experi_apply " +
                    "<where>" +
                        "<if test='state != null and state != -1'>" +
                            " and state = #{state}" +
                        "</if>" +
                        " and user_id in (select user_id from user_school where school_id = #{schoolId})" +
                    "</where>" +
            "</script>")
    List<ExperimentApply> findAllBySchoolId(@Param("schoolId") Long schoolId,
                                            @Param("state") Long state);

    @ResultMap("experimentApplyMapper")
    @Select("<script>" +
                "select * from experi_apply " +
                    "<where>" +
                        "<if test='state != null and state != -1'>" +
                             " and state = #{state}" +
                         "</if>" +
                         " and user_id in (select user_id from user_school where school_id in " +
                                " (select id from school where edu_id = #{eduId}))" +
                    "</where>" +
            "</script>")
    List<ExperimentApply> findAllByEduId(@Param("eduId") Long eduId,
                                            @Param("state") Long state);

    @ResultMap("experimentApplyMapper")
    @Select("select * from experi_apply where id = #{id}")
    ExperimentApply findExperimentById(@Param("id") Long id);
}