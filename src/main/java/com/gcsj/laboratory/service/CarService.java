package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.CarMapper;
import com.gcsj.laboratory.pojo.Car;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarService.java
 * @Description TODO
 * @createTime 2020/12/5,21:25
 */
@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    public CommonResponse<Car> insertCar(Car car) {
        int i = this.carMapper.insert(car);
        if (i==1){
            return new CommonResponse<>(true,"添加车辆信息成功",null);
        }
        return new CommonResponse<>(true,"添加车辆信息失败",null);
    }
}
