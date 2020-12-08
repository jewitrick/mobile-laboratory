package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.Classes;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName ClassesMapper.java
 * @Description TODO
 * @createTime 2020/12/8,16:11
 */
public interface ClassesMapper extends Mapper<Classes> {
    @Results(id = "classesMap",value = {
            @Result(column = "grade_id",property = "grade",
            one =@One(select = "com.gcsj.laboratory.mapper.GradeMapper.findGradeById",fetchType = FetchType.EAGER))
    })
    @Select("select * from classes where grade_id = #{id}")
    List<Classes> selectAllByGrade(long id);

    @ResultMap("classesMap")
    @Select("select * from classes where id = #{id}")
    Classes findClassById(long id);
}
