package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.CarCtrlConsumeMapper;
import com.gcsj.laboratory.mapper.CarCtrlMapper;
import com.gcsj.laboratory.mapper.ConsumeMapper;
import com.gcsj.laboratory.pojo.CarCtrl;
import com.gcsj.laboratory.pojo.CarCtrlConsume;
import com.gcsj.laboratory.pojo.request.CarCtrlInfo;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public CommonResponse<CarCtrlInfo> uploadCarCtrl(Long id, CarCtrlInfo carCtrlInfo) {
        /*CarCtrl carCtrl = new CarCtrl();
        carCtrl.setCar_apply_id(id);
        carCtrl.setCar_id(carCtrlInfo.getCar_id());
        carCtrl.setDriver_id(carCtrlInfo.getDriver_id());
        carCtrl.setTeacher_id(carCtrlInfo.getTeacher_id());
        carCtrl.setCtrl_status(0);*/
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
}
