package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.School;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface SchoolMapper extends Mapper<School> {
    @Select("select * from school where school_name =#{schoolName}")
    School findByName(String schoolName);

    @Select("select school_name from school where id = " +
            "(select school_id from user_school where user_id=#{userId})")
    String findSchoolNameByUserId(Long userId);

    @Select("select school_address from school where id =" +
            "(select school_id from user_school where user_id=#{userId})")
    String findSchoolAddressByUserId(Long userId);

    @Select("select school_name from school where id = #{id}")
    String findSchoolNameBySchoolId(long id);
}
