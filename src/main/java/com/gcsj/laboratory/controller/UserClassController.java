package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.CarCtrl;
import com.gcsj.laboratory.pojo.ExperimentApply;
import com.gcsj.laboratory.pojo.UserClass;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.gcsj.laboratory.service.UserClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName UserClassController.java
 * @Description 用户班级表控制层
 * @createTime 2020/12/9,10:38
 */
@RestController
@RequestMapping("userClass")
public class UserClassController {
    @Autowired
    private UserClassService userClassService;

    //查询用户（学生）所在班级已通过用车申请的实验，即确定可以开课的实验
    //id为学生的user_id
    @GetMapping("{id}/{currentPage}/{pageSize}")
    public QueryResponse<ExperimentApply> studentSelectExperiment(@PathVariable("id") long id,
                                                                  @PathVariable("currentPage") int currentPage,
                                                                  @PathVariable("pageSize") int pageSize){
        return this.userClassService.studentSelectExperiment(id,currentPage,pageSize);
    }

    //根据experimentApply_id 查找实验详情信息
    @GetMapping("selectDetailById/{id}")
    public CarCtrl selectDetailById(@PathVariable long id){
        return this.userClassService.selectDetailById(id);
    }

}
