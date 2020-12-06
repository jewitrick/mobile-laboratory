package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.Car;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.gcsj.laboratory.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getAllCars")
    public List<Car> getAllCars(){
        return this.carService.getAllCars();
    }

    @PostMapping("addCar")
    public CommonResponse<Car> insertCar(@RequestBody Car car){
        return this.carService.insertCar(car);
    }

    @GetMapping("{currentPage}/{pageSize}")
    public QueryResponse<Car> selectPageCar (@PathVariable("currentPage") int currentPage,
                                             @PathVariable("pageSize") int pageSize) {
        return carService.selectPageCar( currentPage, pageSize );
    }

    //根据id获取对应的车辆信息，用于编辑车辆信息
    @GetMapping("getCarById/{id}")
    public Car selectCarById(@PathVariable long id){
        return carService.selectCarById(id);
    }

    //根据id删除相应的车辆信息
    @DeleteMapping("deleteById/{id}")
    public int deleteById(@PathVariable long id){
        return carService.deleteById(id);
    }

    //根据id更新相应的车辆信息
    @PutMapping("updateCar/{id}")
    public CommonResponse<Car> updateCar(@PathVariable long id,
                                         @RequestBody Car updateCar){
        return this.carService.updateCar(id,updateCar);
    }
}
