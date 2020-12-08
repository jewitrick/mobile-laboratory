package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.Classes;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.gcsj.laboratory.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName ClassedController.java
 * @Description TODO
 * @createTime 2020/12/8,17:04
 */
@RestController
@RequestMapping("classes")
public class ClassedController {
    @Autowired
    private ClassesService classesService;

    @GetMapping("{id}/{currentPage}/{pageSize}")
    public QueryResponse<Classes> getClassPageData(@PathVariable("id") long id,
                                                   @PathVariable("currentPage") int currentPage,
                                                   @PathVariable("pageSize") int pageSize){
        return this.classesService.getClassPageData(id,currentPage,pageSize);
    }
}
