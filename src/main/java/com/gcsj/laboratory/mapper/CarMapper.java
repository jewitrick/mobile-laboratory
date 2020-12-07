package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.Car;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarMapper.java
 * @Description TODO
 * @createTime 2020/12/5,21:25
 */
public interface CarMapper extends Mapper<Car> {
    @Select("select * from car where id = #{id}")
    Car findCarById(long id);

    @Select("select id from car where car_num = #{car_num}")
    Long findIdByNum(String car_num);
}
