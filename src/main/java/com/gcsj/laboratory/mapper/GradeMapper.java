package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.Grade;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName GradeMapper.java
 * @Description TODO
 * @createTime 2020/12/8,14:23
 */
public interface GradeMapper extends Mapper<Grade> {

    @Results(id = "gradeMap",value = {
            @Result(column = "school_id",property = "schoolName",
            one = @One(select="com.gcsj.laboratory.mapper.SchoolMapper.findSchoolNameBySchoolId",fetchType = FetchType.EAGER))
    })
    @Select("select * from grade where school_id = #{school_id}")
    List<Grade> selectGradeBySchool(Long school_id);

    @Select("select * from grade where id = #{id}")
    Grade findGradeById(long id);

}
