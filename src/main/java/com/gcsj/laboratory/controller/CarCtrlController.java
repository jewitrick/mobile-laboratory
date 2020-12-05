package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.service.CarCtrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
