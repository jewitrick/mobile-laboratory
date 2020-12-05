package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.Car;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarController.java
 * @Description TODO
 * @createTime 2020/12/5,21:26
 */
@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("addCar")
    public CommonResponse<Car> insertCar(@RequestBody Car car){
        return this.carService.insertCar(car);
    }

}
