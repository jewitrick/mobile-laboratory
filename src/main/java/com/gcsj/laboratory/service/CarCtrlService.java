package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.CarCtrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarCtrlService.java
 * @Description TODO
 * @createTime 2020/12/5,21:52
 */
@Service
public class CarCtrlService {

    @Autowired
    private CarCtrlMapper carCtrlMapper;
}
