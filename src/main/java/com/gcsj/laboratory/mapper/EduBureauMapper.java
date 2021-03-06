package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.EduBureau;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface EduBureauMapper extends Mapper<EduBureau> {
    @Select("select * from edu_bureau where edu_name =#{eduBureauName}")
    EduBureau findByName(String eduBureauName);

    @Select("select edu_name from edu_bureau where id = " +
            "(select edu_id from user_edu where user_id = #{id})")
    String findEubreauById(Long id);
}
