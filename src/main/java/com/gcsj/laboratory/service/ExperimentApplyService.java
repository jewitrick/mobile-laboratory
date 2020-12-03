package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.ExperimentApplyMapper;
import com.gcsj.laboratory.mapper.UserEduBureauMapper;
import com.gcsj.laboratory.mapper.UserMapper;
import com.gcsj.laboratory.mapper.UserSchoolMapper;
import com.gcsj.laboratory.pojo.ExperimentApply;
import com.gcsj.laboratory.pojo.User;
import com.gcsj.laboratory.pojo.UserEduBureau;
import com.gcsj.laboratory.pojo.UserSchool;
import com.gcsj.laboratory.resp.CommonResponse;
import com.gcsj.laboratory.resp.QueryResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ExperimentApplyService {
    @Autowired
    private ExperimentApplyMapper experimentApplyMapper;

    @Autowired
    private UserSchoolMapper userSchoolMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserEduBureauMapper userEduBureauMapper;

    public QueryResponse<ExperimentApply> findPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<ExperimentApply> experimentApplies = this.experimentApplyMapper.findAll();
        PageInfo<ExperimentApply> pageInfo = new PageInfo<>(experimentApplies);
        return new QueryResponse<>(true, "查询成功！", experimentApplies, pageInfo.getTotal());
    }

    public CommonResponse<ExperimentApply> insert(long id, ExperimentApply experimentApply) {
        experimentApply.setUser_id(id);
        experimentApply.setState(0);
        int i = this.experimentApplyMapper.insert(experimentApply);
        if (i == 1) {
            return new CommonResponse<>(true, "申请成功", null);
        }
        return new CommonResponse<>(false, "申请失败", null);
    }

    public QueryResponse<ExperimentApply> findPageByUserId(Long id, int currentPage, int pageSize, Long state) {

        User user = this.userMapper.findById(id);

        if (ObjectUtils.isEmpty(user)) {
            return new QueryResponse<>(false, "查询失败！", null, 0);
        }

        if (Objects.equals(user.getRole_id(), 3L)) { // 学校
            UserSchool userSchool = this.userSchoolMapper.selectOne(UserSchool.builder().user_id(id).build());
            PageHelper.startPage(currentPage, pageSize);
            List<ExperimentApply> experimentApplies = this.experimentApplyMapper.findAllBySchoolId(userSchool.getSchool_id(), state);
            PageInfo<ExperimentApply> pageInfo = new PageInfo<>(experimentApplies);
            return new QueryResponse<>(true, "查询成功", experimentApplies, pageInfo.getTotal());

        } else if (Objects.equals(user.getRole_id(), 2L)) { // 教育局
            UserEduBureau userEduBureau = this.userEduBureauMapper.selectOne(UserEduBureau.builder().user_id(id).build());
            PageHelper.startPage(currentPage, pageSize);
            List<ExperimentApply> experimentApplies = this.experimentApplyMapper.findAllByEduId(userEduBureau.getEdu_id(), state);
            PageInfo<ExperimentApply> pageInfo = new PageInfo<>(experimentApplies);
            return new QueryResponse<>(true, "查询成功", experimentApplies, pageInfo.getTotal());
        }

        return new QueryResponse<>(false, "非法请求！", null, 0);

    }

    public ExperimentApply findExperimentById(Long id) {
        ExperimentApply experimentApply = this.experimentApplyMapper.findExperimentById(id);
        return experimentApply;
    }

    public CommonResponse<ExperimentApply> updateState(Long id, int state) {
        ExperimentApply experimentApply = this.experimentApplyMapper.selectByPrimaryKey(id);
        experimentApply.setState(state);
        experimentApply.setResult("请等待教育局申请用车");
        int i = this.experimentApplyMapper.updateByPrimaryKey(experimentApply);
        if (i==1){
            return new CommonResponse<>(true,"您已成功提交审核",null);
        }
        return new CommonResponse<>(false,"审核失败",null);

    }

    public CommonResponse<ExperimentApply> updateStateAndResult(Long id, int state, String result) {
        ExperimentApply experimentApply = this.experimentApplyMapper.selectByPrimaryKey(id);
        experimentApply.setState(state);
        experimentApply.setResult(result);
        int i = this.experimentApplyMapper.updateByPrimaryKey(experimentApply);
        if (i==1){
            return new CommonResponse<>(true,"您已驳回实验",null);
        }
        return new CommonResponse<>(false,"驳回失败",null);
    }
}
