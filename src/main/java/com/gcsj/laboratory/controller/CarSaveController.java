package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.CarSave;
import com.gcsj.laboratory.pojo.request.CarSaveInfo;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.gcsj.laboratory.service.CarSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarSaveController.java
 * @Description TODO
 * @createTime 2020/12/7,21:26
 */
@RestController
@RequestMapping("carSave")
public class CarSaveController {
    @Autowired
    private CarSaveService carSaveService;

    //获取所有的车辆保障信息
    @GetMapping("{currentPage}/{pageSize}")
    public QueryResponse<CarSave> selectPageCar (@PathVariable("currentPage") int currentPage,
                                             @PathVariable("pageSize") int pageSize) {
        return carSaveService.selectPageCarSave( currentPage, pageSize );
    }

    @PostMapping("addCarSave/{id}")
    public CommonResponse<CarSave> addCarSave(@PathVariable long id, @RequestBody CarSaveInfo carSaveInfo){
        return this.carSaveService.addCarSave(id,carSaveInfo);
    }

    //根据id获取对应的车辆保障信息，用于编辑车辆保障信息
    @GetMapping("getCarSaveById/{id}")
    public CarSave selectCarById(@PathVariable long id){
        return this.carSaveService.selectCarSaveById(id);
    }

    //根据id删除相应的车辆保障信息
    @DeleteMapping("deleteById/{id}")
    public int deleteById(@PathVariable long id){
        return this.carSaveService.deleteById(id);
    }

    //根据id更新相应的车辆信息
    @PutMapping("updateCarSave/{id}")
    public CommonResponse<CarSave> updateCar(@PathVariable long id,
                                         @RequestBody CarSaveInfo carSaveInfo){
        return this.carSaveService.updateCarSave(id,carSaveInfo);
    }
}
