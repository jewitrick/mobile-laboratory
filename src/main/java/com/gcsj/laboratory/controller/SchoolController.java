package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.School;
import com.gcsj.laboratory.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/getAllSchool")
    public List<School> selectAllSchool(){
        return schoolService.selectAllSchool();
    }
}
