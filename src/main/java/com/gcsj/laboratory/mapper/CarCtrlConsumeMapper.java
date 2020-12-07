package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.Car;
import com.gcsj.laboratory.pojo.CarCtrlConsume;
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
 * @ClassName CarCtrlConsumeMapper.java
 * @Description 车辆调度耗材表通用mapper
 * @createTime 2020/12/5,21:58
 */
public interface CarCtrlConsumeMapper extends Mapper<CarCtrlConsume> {

    @Results(id = "carCtrlConsumeMap",value = {
            @Result(column = "consume_id",property = "consume",
                    one = @One(select = "com.gcsj.laboratory.mapper.ConsumeMapper.findConsumeByConsumeId", fetchType = FetchType.EAGER)),
    })
    @Select("select * from car_ctrl_consume where car_ctrl_id = #{id}")
    List<CarCtrlConsume> findCarCtrlConsumeById(long id);
}
