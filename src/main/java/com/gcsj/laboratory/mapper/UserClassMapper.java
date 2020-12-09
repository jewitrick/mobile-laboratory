package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.CarApply;
import com.gcsj.laboratory.pojo.ExperimentApply;
import com.gcsj.laboratory.pojo.UserClass;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName UserClassMapper.java
 * @Description TODO
 * @createTime 2020/12/8,21:56
 */
public interface UserClassMapper extends Mapper<UserClass> {

    @Select("select id from experi_apply where user_id = #{user_id} and classes_id=#{classes_id} and state = 1")
    List<Long> findExperimentApplyIdByUserIdAndClassId(@Param("user_id") Long user_id,
                                                                @Param("classes_id") Long classes_id);

    @Select("select * from car_apply where experiment_apply_id = #{experimentApplyId} and edu_status = 2")
    CarApply findCarApplyByExperimentApplyId(long experimentApplyId);
}
