package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.CarCtrl;
import com.gcsj.laboratory.pojo.request.CarCtrlInfo;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
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

    @GetMapping("getCarCtrlByUserId/{id}/{currentPage}/{pageSize}")
    public QueryResponse<CarCtrl> getCarCtrlPageByUserId(@PathVariable("id") long id,
                                                         @PathVariable("currentPage") int currentPage,
                                                         @PathVariable("pageSize") int pageSize){
        return this.carCtrlService.getCarCtrlPageByUserId(id,currentPage,pageSize);
    }

    @GetMapping("getById/{id}")
    public CarCtrl findCarCtrlById(@PathVariable long id){
        return this.carCtrlService.findCarCtrlById(id);
    }

    @PutMapping("{id}/ctrl_status/{ctrl_status}")
    public CommonResponse<CarCtrl> updataCtrl_status(@PathVariable long id,
                                                     @PathVariable int ctrl_status){
        return this.carCtrlService.updateCtrl_status(id,ctrl_status);
    }
}
