package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.EduBureau;
import com.gcsj.laboratory.service.EduBureauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EduController {
    @Autowired
    private EduBureauService eduBureauService;

    @GetMapping("/getAllEduBureau")
    public List<EduBureau> selectAllEdu(){
        return eduBureauService.selectAllEdu();
    }
}
