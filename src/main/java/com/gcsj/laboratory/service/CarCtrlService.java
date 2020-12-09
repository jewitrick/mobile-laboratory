package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.*;
import com.gcsj.laboratory.pojo.CarApply;
import com.gcsj.laboratory.pojo.CarCtrl;
import com.gcsj.laboratory.pojo.CarCtrlConsume;
import com.gcsj.laboratory.pojo.User;
import com.gcsj.laboratory.pojo.request.CarCtrlInfo;
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

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarCtrlService.java
 * @Description TODO
 * @createTime 2020/12/5,21:52
 */
@Service
public class CarCtrlService {

    @Autowired
    private CarCtrlMapper carCtrlMapper;

    @Autowired
    private ConsumeMapper consumeMapper;

    @Autowired
    private CarCtrlConsumeMapper carCtrlConsumeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CarApplyMapper carApplyMapper;

    @Transactional
    public CommonResponse<CarCtrlInfo> uploadCarCtrl(Long id, CarCtrlInfo carCtrlInfo) {
        CarCtrl carCtrl = CarCtrl.builder()
                .car_apply_id(id)
                .car_id(carCtrlInfo.getCar_id())
                .driver_id(carCtrlInfo.getDriver_id())
                .teacher_id(carCtrlInfo.getTeacher_id())
                .ctrl_status(1).build();
        int i = this.carCtrlMapper.insert(carCtrl);
       if (i==0){
           return new CommonResponse<>(false,"发布车辆调度信息失败，请联系管理员！",null);
       }
        for (CarCtrlConsume carCtrlconsume : carCtrlInfo.getConsumeList()){
            carCtrlconsume.setCar_ctrl_id(carCtrl.getId());
            int j = this.carCtrlConsumeMapper.insert(carCtrlconsume);
            this.consumeMapper.updateStock(carCtrlconsume.getConsume_id(),carCtrlconsume.getCost_count());
        }
        return new CommonResponse<>(true,"您已成功发布车辆调度信息",null);
    }

    public List<CarCtrl> getAllCarCtrl() {
        return this.carCtrlMapper.selectAll();
    }

    public QueryResponse<CarCtrl> getCarCtrlPageByUserId(long id, int currentPage, int pageSize) {
        User user = userMapper.findById(id);
        if (ObjectUtils.isEmpty(user)) {
            return new QueryResponse<>(false, "查询失败！", null, 0);
        }
        if (Objects.equals(user.getRole_id(),5L)){
            //判定角色为司机
            PageHelper.startPage(currentPage,pageSize);
            List<CarCtrl> carCtrls = this.carCtrlMapper.findAllByDriverId(id);
            PageInfo<CarCtrl> carCtrlPageInfo = new PageInfo<>(carCtrls);
            return new QueryResponse<>(true,"查询成功",carCtrls,carCtrlPageInfo.getTotal());
        }
        else if (Objects.equals(user.getRole_id(),4L)){
            //判定角色为实验员
            PageHelper.startPage(currentPage,pageSize);
            List<CarCtrl> carCtrls = this.carCtrlMapper.findAllByTeacherId(id);
            PageInfo<CarCtrl> carCtrlPageInfo = new PageInfo<>(carCtrls);
            return new QueryResponse<>(true,"查询成功",carCtrls,carCtrlPageInfo.getTotal());
        }
        return new QueryResponse<>(false, "非法请求！", null, 0);
    }

    public CarCtrl findCarCtrlById(long id) {
        return this.carCtrlMapper.findCarCtrlById(id);
    }

    public CommonResponse<CarCtrl> updateCtrl_status(long id, int ctrl_status) {
        CarCtrl carCtrl = this.carCtrlMapper.selectByPrimaryKey(id);
        CarApply carApply = this.carApplyMapper.selectOne(CarApply.builder().id(carCtrl.getCar_apply_id()).build());
        carCtrl.setCtrl_status(ctrl_status);
        carApply.setEdu_result("该实验已结束！");
        int j = this.carApplyMapper.updateByPrimaryKey(carApply);
        int i = this.carCtrlMapper.updateByPrimaryKey(carCtrl);
        if (i==1 && j==1){
            return new CommonResponse<>(true,"您已按时跟车完成该实验",null);
        }

        return new CommonResponse<>(false,"提交失败，请联系管理员！",null);
    }
}
