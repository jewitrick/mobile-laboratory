package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.ExperimentType;
import com.gcsj.laboratory.service.ExperimentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExperimentTypeController {

    @Autowired
    private ExperimentTypeService experimentTypeService;

    @GetMapping("/experimetnType")
    public List<ExperimentType> selectAllTypes(){
        return experimentTypeService.selectAllTypes();
    }

}
