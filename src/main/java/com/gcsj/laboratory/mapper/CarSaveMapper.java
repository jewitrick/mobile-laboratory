package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.CarSave;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarSaveMapper.java
 * @Description TODO
 * @createTime 2020/12/7,21:25
 */
public interface CarSaveMapper extends Mapper<CarSave> {
    @Results(id = "carSaveMap",value = {
            @Result(column = "car_id",property = "car",
            one = @One(select = "com.gcsj.laboratory.mapper.CarMapper.findCarById",fetchType = FetchType.EAGER)),
            @Result(column = "user_id",property = "realName",
            one = @One(select = "com.gcsj.laboratory.mapper.UserMapper.findRealnameByUserId",fetchType = FetchType.EAGER))
    })
    @Select("select * from car_save")
    List<CarSave> selectAllCarSave();

    @ResultMap("carSaveMap")
    @Select("select * from car_save where id = #{id}")
    CarSave selectByCarSaveId(long id);
}
