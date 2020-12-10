package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.SignIn;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SignInMapper extends Mapper<SignIn> {
    @Select("select * from singin where user_id = #{uid} and experiment_apply_id = #{experAppyId}")
    List<SignIn> findInfoByUidAndExperAppyId(@Param("uid") Long uid, @Param("experAppyId") Long experAppyId);

    @Select("select * from singin where experiment_apply_id = #{experAppyId}")
    List<SignIn> findInfoByExperAppyId(@Param("experAppyId") Long experAppyId);
}
