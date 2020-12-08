package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.ClassesMapper;
import com.gcsj.laboratory.mapper.GradeMapper;
import com.gcsj.laboratory.mapper.SchoolMapper;
import com.gcsj.laboratory.pojo.Classes;
import com.gcsj.laboratory.pojo.Grade;
import com.gcsj.laboratory.pojo.School;
import com.gcsj.laboratory.pojo.resp.GradeClassResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private ClassesMapper classesMapper;

    public List<School> selectAllSchool() {
        return this.schoolMapper.selectAll();
    }

    public List<GradeClassResp> selectAllSchoolGradeClass() {
        List<GradeClassResp> gradeClassRespList = new ArrayList<>();

        List<School> schools = this.schoolMapper.selectAll();
        schools.forEach(school -> {
            GradeClassResp gradeClassResp = new GradeClassResp();
            gradeClassResp.setId(school.getId());
            gradeClassResp.setName(school.getSchool_name());

            List<Grade> children = new ArrayList<>();
            List<Grade> gradeList = this.gradeMapper.select(Grade.builder().school_id(school.getId()).build());
            gradeList.forEach(grade -> {
                grade.setChildren(this.classesMapper.select(Classes.builder().grade_id(grade.getId()).build()));
                children.add(grade);
            });

            gradeClassResp.setChildren(children);

            gradeClassRespList.add(gradeClassResp);

        });
        return gradeClassRespList;
    }
}
