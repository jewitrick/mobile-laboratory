package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.CarCtrl;
import com.gcsj.laboratory.pojo.request.CarCtrlInfo;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.service.CarCtrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarCtrlController.java
 * @Description TODO
 * @createTime 2020/12/5,21:52
 */
@RestController
@RequestMapping("carCtrl")
public class CarCtrlController {

    @Autowired
    private CarCtrlService carCtrlService;

    @GetMapping("getCarCtrl")
    public List<CarCtrl> getAllCarCtrl(){
        return this.carCtrlService.getAllCarCtrl();
    }

    @PostMapping("uploadCarCtrl/{id}")       //id为car_apply表id
    public CommonResponse<CarCtrlInfo> uploadCarCtrl(@PathVariable Long id,
                                                     @RequestBody CarCtrlInfo carCtrlInfo){
        return this.carCtrlService.uploadCarCtrl(id,carCtrlInfo);
    }
}
