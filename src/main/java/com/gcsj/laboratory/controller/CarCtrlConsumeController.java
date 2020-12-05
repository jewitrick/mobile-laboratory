package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.service.CarCtrlConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarCtrlConsumeController.java
 * @Description TODO
 * @createTime 2020/12/5,22:00
 */
@RestController
@RequestMapping("carCtrlConsume")
public class CarCtrlConsumeController {

    @Autowired
    private CarCtrlConsumeService carCtrlConsumeService;


}
