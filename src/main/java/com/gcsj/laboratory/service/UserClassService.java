package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.*;
import com.gcsj.laboratory.pojo.*;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName UserClassService.java
 * @Description TODO
 * @createTime 2020/12/9,10:37
 */
@Service
public class UserClassService {

    @Autowired
    private UserClassMapper userClassMapper;

    @Autowired
    private ExperimentApplyMapper experimentApplyMapper;

    @Autowired
    private ClassesMapper classesMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private UserSchoolMapper userSchoolMapper;

    @Autowired
    private CarApplyMapper carApplyMapper;

    @Autowired
    private CarCtrlMapper carCtrlMapper;


    //学生查询实验课程
    public QueryResponse<ExperimentApply> studentSelectExperiment(long id, int currentPage, int pageSize) {
        //根据id（user_id）找到userClass对象，获得班级id classes_id
        UserClass userClass = this.userClassMapper.selectOne(UserClass.builder().user_id(id).build());
        Long classes_id = userClass.getClasses_id();

        //用班级id去班级表找到学生所在年级
        Classes classes = this.classesMapper.selectOne(Classes.builder().id(classes_id).build());
        Long grade_id = classes.getGrade_id();

        //用grade_id去grade表找到学生所在的学校的 school_id
        Grade grade = this.gradeMapper.selectOne(Grade.builder().id(grade_id).build());
        Long school_id = grade.getSchool_id();
        //用school_id去userSchool找到学校负责人id；
        UserSchool userSchool = this.userSchoolMapper.selectOne(UserSchool.builder().school_id(school_id).build());
        Long user_id = userSchool.getUser_id();
        //根据user_id与classes_id找到已通过审核的实验申请id。
        List<Long> experimentApplyIdList = this.userClassMapper.findExperimentApplyIdByUserIdAndClassId(user_id, classes_id);

        List<ExperimentApply> experimentApplyList = new ArrayList<>();
        //根据实验申请id去调度表找到已通过用车申请的实验
        for (long experimentApplyId : experimentApplyIdList) {
            //已通过调度中心审核的用车申请
            CarApply carApply = this.userClassMapper.findCarApplyByExperimentApplyId(experimentApplyId);
            if (carApply != null){
                Long experiment_apply_id = carApply.getExperiment_apply_id();
                ExperimentApply experimentApply = this.experimentApplyMapper.findExperimentById(experiment_apply_id);
                experimentApplyList.add(experimentApply);
            }
        }
        PageHelper.startPage(currentPage, pageSize);
        PageInfo<ExperimentApply> pageInfo = new PageInfo<>(experimentApplyList);
        return new QueryResponse<>(true, "查询成功", experimentApplyList, pageInfo.getTotal());
    }

    //根据实验申请id查询详情信息
    public CarCtrl selectDetailById(long id) {
        CarApply carApply = this.carApplyMapper.selectOne(CarApply.builder().experiment_apply_id(id).build());
        Long carApplyId = carApply.getId();
        CarCtrl carCtrl = this.carCtrlMapper.selectOne(CarCtrl.builder().car_apply_id(carApplyId).build());
        return this.carCtrlMapper.findCarCtrlById(carCtrl.getId());
    }
}
