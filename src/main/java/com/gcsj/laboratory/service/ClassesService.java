package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.ClassesMapper;
import com.gcsj.laboratory.mapper.GradeMapper;
import com.gcsj.laboratory.mapper.UserSchoolMapper;
import com.gcsj.laboratory.pojo.Classes;
import com.gcsj.laboratory.pojo.Grade;
import com.gcsj.laboratory.pojo.UserSchool;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName ClassesService.java
 * @Description TODO
 * @createTime 2020/12/8,17:04
 */
@Service
public class ClassesService {
    @Autowired
    private ClassesMapper classesMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private UserSchoolMapper userSchoolMapper;

    //查询所有的班级
    public QueryResponse<Classes> getClassPageData(long id, int currentPage, int pageSize) {
        List<Classes> classesList= this.classesMapper.selectAllByGrade(id);
        PageHelper.startPage(currentPage, pageSize);
        PageInfo<Classes> classesPageInfo = new PageInfo<>(classesList);
        return new QueryResponse<>(true, "查询成功", classesList, classesPageInfo.getTotal());
    }
}
