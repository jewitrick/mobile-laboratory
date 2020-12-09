package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.ExperimentApply;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ExperimentApplyMapper extends Mapper<ExperimentApply> {

    @Results(id = "experimentApplyMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "experi_id", property = "experi_id"),
            @Result(column = "experi_id", property = "experiment",
                    one = @One(select = "com.gcsj.laboratory.mapper.ExperimentMapper.findById", fetchType = FetchType.EAGER)),
            @Result(column = "user_id", property = "user",
                    one = @One(select = "com.gcsj.laboratory.mapper.UserMapper.findById", fetchType = FetchType.EAGER)),
            @Result(column = "user_id", property = "schoolName",
                    one = @One(select = "com.gcsj.laboratory.mapper.SchoolMapper.findSchoolNameByUserId", fetchType = FetchType.EAGER)),
            @Result(column = "user_id", property = "school_address",
                    one = @One(select = "com.gcsj.laboratory.mapper.SchoolMapper.findSchoolAddressByUserId", fetchType = FetchType.EAGER)),
            @Result(column = "id", property = "eduStatus",
                    one = @One(select = "com.gcsj.laboratory.mapper.CarApplyMapper.findEduStatusByApplyId", fetchType = FetchType.EAGER)),
            @Result(column = "classes_id", property = "classes",
                    one = @One(select = "com.gcsj.laboratory.mapper.ClassesMapper.findClassById", fetchType = FetchType.EAGER))
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
                " order by state" +
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
                " order by state" +
            "</script>")
    List<ExperimentApply> findAllByEduId(@Param("eduId") Long eduId,
                                            @Param("state") Long state);

    @ResultMap("experimentApplyMapper")
    @Select("select * from experi_apply where id = #{id} order by experi_date")
    ExperimentApply findExperimentById(@Param("id") Long id);

    @Select("select id from experi_apply where user_id = #{id} and state=1")
    List<Long> findExperimentApplyIdByUserId(long id);
}
