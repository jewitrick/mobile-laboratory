package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.*;
import com.gcsj.laboratory.pojo.*;
import com.gcsj.laboratory.pojo.resp.GradeClassResp;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    private CarApplyMapper carApplyMapper;

    @Autowired
    private UserClassMapper userClassMapper;

    @Autowired
    private ExperimentApplyMapper experimentApplyMapper;

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

    public QueryResponse<ExperimentApply> schoolSelectExperimentApply(long id, int currentPage, int pageSize) {
        //根据user_id 去expri_apply实验申请表查找已通过申请审核的实验的id
        List<Long> experimentApplyIdList= this.experimentApplyMapper.findExperimentApplyIdByUserId(id);
        //用查询得到的已通过审核的实验申请id去调度表查询已通过用车申请的课程
        List<ExperimentApply> experimentApplyList=new ArrayList<>();
        for (long experimentApplyId : experimentApplyIdList){
            CarApply carApply=this.userClassMapper.findCarApplyByExperimentApplyId(experimentApplyId);
            Long experiment_apply_id = carApply.getExperiment_apply_id();

            ExperimentApply exprimentApply = this.experimentApplyMapper.findExperimentById(experiment_apply_id);
            experimentApplyList.add(exprimentApply);
        }
        PageHelper.startPage(currentPage,pageSize);
        PageInfo<ExperimentApply> pageInfo = new PageInfo<>(experimentApplyList);
        return new QueryResponse<>(true,"查询成功",experimentApplyList,pageInfo.getTotal());
    }
}
