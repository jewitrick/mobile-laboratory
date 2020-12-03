package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.service.CarApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @description 用车申请记录控制层
 * @date 2020/12/3 23:26
 */
@RestController
@RequestMapping("carApply")
public class CarApplyController {
    @Autowired
    private CarApplyService carApplyService;


}
