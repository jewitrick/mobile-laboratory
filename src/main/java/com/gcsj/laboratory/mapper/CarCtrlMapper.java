package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.CarCtrl;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarCtrlMapper.java
 * @Description TODO
 * @createTime 2020/12/5,21:51
 */
public interface CarCtrlMapper extends Mapper<CarCtrl> {

    @Select("select ctrl_status from car_ctrl where car_apply_id = #{carApplyId}")
    Integer findCtrlStatusByCarApplyId(Long carApplyId);

    @Results(id = "carCtrlMap",value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "driver_id",property = "driverName",
            one = @One(select = "com.gcsj.laboratory.mapper.UserMapper.findRealnameByUserId", fetchType = FetchType.EAGER)),
            @Result(column = "teacher_id",property = "teacherName",
                    one = @One(select = "com.gcsj.laboratory.mapper.UserMapper.findRealnameByUserId", fetchType = FetchType.EAGER)),
            @Result(column = "car_apply_id",property = "carApply",
                    one = @One(select = "com.gcsj.laboratory.mapper.CarApplyMapper.findExperimentById", fetchType = FetchType.EAGER)),
            @Result(column = "car_id",property = "car",
                    one = @One(select = "com.gcsj.laboratory.mapper.CarMapper.findCarById", fetchType = FetchType.EAGER)),
            @Result(column = "id",property = "carCtrlConsume",
                    one = @One(select = "com.gcsj.laboratory.mapper.CarCtrlConsumeMapper.findCarCtrlConsumeById", fetchType = FetchType.EAGER))
    })
    @Select("select * from car_ctrl where driver_id = #{id}")
    List<CarCtrl> findAllByDriverId(long id);

    @ResultMap("carCtrlMap")
    @Select("select * from car_ctrl where teacher_id = #{id}")
    List<CarCtrl> findAllByTeacherId(long id);

    @ResultMap("carCtrlMap")
    @Select("select * from car_ctrl where id = #{id}")
    CarCtrl findCarCtrlById(long id);
}
