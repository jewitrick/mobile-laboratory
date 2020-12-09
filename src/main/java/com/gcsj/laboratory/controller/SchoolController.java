package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.ExperimentApply;
import com.gcsj.laboratory.pojo.School;
import com.gcsj.laboratory.pojo.resp.GradeClassResp;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.gcsj.laboratory.service.SchoolService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("school/gradeClass")
    public List<GradeClassResp> selectAllSchoolGradeClass(){
        return this.schoolService.selectAllSchoolGradeClass();
    }

    //学校查询本校的所有已通过用车申请的实验课程
    @GetMapping("school/{id}/{currentPage}/{pageSize}")
    public QueryResponse<ExperimentApply> schoolSelectExperimentApply(@PathVariable long id,
                                                                      @PathVariable int currentPage,
                                                                      @PathVariable int pageSize){
        return this.schoolService.schoolSelectExperimentApply(id,currentPage,pageSize);
    }
}
