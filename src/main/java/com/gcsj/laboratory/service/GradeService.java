package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.ClassesMapper;
import com.gcsj.laboratory.mapper.GradeMapper;
import com.gcsj.laboratory.mapper.UserSchoolMapper;
import com.gcsj.laboratory.pojo.Classes;
import com.gcsj.laboratory.pojo.Grade;
import com.gcsj.laboratory.pojo.UserSchool;
import com.gcsj.laboratory.pojo.request.InsertClassesRequest;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName GradeService.java
 * @Description TODO
 * @createTime 2020/12/8,14:24
 */
@Service
public class GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private UserSchoolMapper userSchoolMapper;

    @Autowired
    private ClassesMapper classesMapper;

    public CommonResponse<Grade> addGrade(long id, Grade grade) {
        UserSchool userSchool = this.userSchoolMapper.selectOne(UserSchool.builder().user_id(id).build());
        Long school_id = userSchool.getSchool_id();
        // 校验年级名称的唯一性
        if (Objects.nonNull(this.gradeMapper.selectOne(Grade.builder().school_id(school_id).name(grade.getName()).build()))) {
            return new CommonResponse<>(false, "该年级已存在！", null);
        }
        grade.setSchool_id(school_id);
        int i = this.gradeMapper.insert(grade);
        if (i == 1) {
            return new CommonResponse<>(true, "新增年级成功", null);
        }
        return new CommonResponse<>(false, "新增年级失败，请联系管理员", null);
    }

    //添加班级信息
    public CommonResponse<Classes> insertClasses(long gradeId, InsertClassesRequest insertClassesRequest) {
        for (String className : insertClassesRequest.getClassList()) {
            if (Objects.nonNull(this.classesMapper.selectOne(Classes.builder().grade_id(gradeId).name(className).build()))) {
                return new CommonResponse<>(false, "该年级已存在" + className, null);
            }
            Classes insertClass = Classes.builder().grade_id(gradeId).name(className).build();
            int i = this.classesMapper.insert(insertClass);
        }
        return new CommonResponse<>(true, "所有的班级信息均已添加成功", null);
    }

    //查询该学校所有的年级信息
    public QueryResponse<Grade> selectPageGrade(long id, int currentPage, int pageSize) {
        UserSchool userSchool = this.userSchoolMapper.selectOne(UserSchool.builder().user_id(id).build());
        Long school_id = userSchool.getSchool_id();
        PageHelper.startPage(currentPage, pageSize);
        List<Grade> gardes = this.gradeMapper.selectGradeBySchool(school_id);
        PageInfo<Grade> gardePageInfo = new PageInfo<>(gardes);
        return new QueryResponse<>(true, "查询成功", gardes, gardePageInfo.getTotal());
    }

    public Grade getGradeById(long gradeId) {
        return this.gradeMapper.selectByPrimaryKey(gradeId);
    }
}
