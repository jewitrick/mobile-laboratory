package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.Classes;
import com.gcsj.laboratory.pojo.Grade;
import com.gcsj.laboratory.pojo.request.InsertClassesRequest;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.gcsj.laboratory.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName GradeController.java
 * @Description TODO
 * @createTime 2020/12/8,14:23
 */
@RestController
@RequestMapping("grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    //学校新增年级
    @PostMapping("addGrade/{id}")
    public CommonResponse<Grade> addGrade(@PathVariable long id, @RequestBody Grade grade){
        return this.gradeService.addGrade(id,grade);
    }

    //显示所有的年级信息
    @GetMapping("{id}/{currentPage}/{pageSize}")
    public QueryResponse<Grade> selectPageGrade(@PathVariable("id") long id,
                                                @PathVariable("currentPage") int currentPage,
                                                @PathVariable("pageSize") int pageSize){
        return  this.gradeService.selectPageGrade(id,currentPage,pageSize);
    }

    //根据grade表id获取单条grade信息
    @GetMapping("getByGradeId/{gradeId}")
    public Grade getGradeById(@PathVariable long gradeId){
        return this.gradeService.getGradeById(gradeId);
    }

    //根据年级id添加班级
    @PostMapping("insert_classes/{gradeId}")
    public CommonResponse<Classes> insertClasses(@PathVariable long gradeId,
                                                 @RequestBody InsertClassesRequest insertClassesRequest){
        return this.gradeService.insertClasses(gradeId,insertClassesRequest);
    }
}
