package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.CarApplyMapper;
import com.gcsj.laboratory.mapper.CarCtrlMapper;
import com.gcsj.laboratory.mapper.UserEduBureauMapper;
import com.gcsj.laboratory.mapper.UserMapper;
import com.gcsj.laboratory.pojo.*;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Service
public class CarApplyService {
    @Autowired
    private CarApplyMapper carApplyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserEduBureauMapper userEduBureauMapper;

    @Autowired
    private CarCtrlMapper carCtrlMapper;

    @Transactional
    public CommonResponse<CarApply> insert(long id, long user_id, CarApply carApply) {
        carApply.setExperiment_apply_id(id);
        carApply.setUser_id(user_id);
        carApply.setEdu_result("请等待调度中心审核");
        carApply.setEdu_status(1);
        int i = this.carApplyMapper.insert(carApply);
        if (i == 1) {
            return new CommonResponse<>(true, "提交用车申请成功", null);
        }
        return new CommonResponse<>(true, "提交用车申请失败，请联系管理员", null);
    }

    //查询所有用车申请记录
    public List<CarApply> selectAllRecord() {
        return this.carApplyMapper.findAll();
    }


    public QueryResponse<CarApply> findPageByUserId(Long id, int currentPage, int pageSize, Long status) {
        User user = this.userMapper.findById(id);

        if (ObjectUtils.isEmpty(user)) {
            return new QueryResponse<>(false, "查询失败！", null, 0);
        }

        if (Objects.equals(user.getRole_id(), 1L)) { // 调度中心
            PageHelper.startPage(currentPage, pageSize);
            List<CarApply> carApplies = this.carApplyMapper.findAll();
            PageInfo<CarApply> pageInfo = new PageInfo<>(carApplies);
            return new QueryResponse<>(true, "查询成功", carApplies, pageInfo.getTotal());

        } else if (Objects.equals(user.getRole_id(), 2L)) { // 教育局
            UserEduBureau userEduBureau = this.userEduBureauMapper.selectOne(UserEduBureau.builder().user_id(id).build());
            PageHelper.startPage(currentPage, pageSize);
            List<CarApply> carApplies = this.carApplyMapper.findAllByEduId(userEduBureau.getEdu_id(), status);
            PageInfo<CarApply> pageInfo = new PageInfo<>(carApplies);
            return new QueryResponse<>(true, "查询成功", carApplies, pageInfo.getTotal());
        }
        return new QueryResponse<>(false, "非法请求！", null, 0);
    }

    public CarApply findCarApplyById(Long id) {
        return this.carApplyMapper.findExperimentById(id);
    }

    @Transactional
    public CommonResponse<CarApply> updateState(Long id, int status) {
        CarApply carApply = this.carApplyMapper.selectByPrimaryKey(id);
        carApply.setEdu_status(status);
        carApply.setEdu_result("请等待调度中心派车");
        int i = this.carApplyMapper.updateByPrimaryKey(carApply);
        if (i == 1) {
            return new CommonResponse<>(true, "您已成功提交审核", null);
        }
        return new CommonResponse<>(false, "审核失败", null);
    }

    @Transactional
    public CommonResponse<CarApply> updateStateAndResult(Long id, int status, String result) {

        CarApply carApply = this.carApplyMapper.selectByPrimaryKey(id);
        carApply.setEdu_status(status);
        carApply.setEdu_result(result);
        int i = this.carApplyMapper.updateByPrimaryKey(carApply);
        if (i == 1) {
            return new CommonResponse<>(true, "您已驳回实验", null);
        }
        return new CommonResponse<>(false, "驳回失败", null);
    }
}
