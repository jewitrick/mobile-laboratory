package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.CarMapper;
import com.gcsj.laboratory.mapper.CarSaveMapper;
import com.gcsj.laboratory.pojo.CarSave;
import com.gcsj.laboratory.pojo.request.CarSaveInfo;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarSaveService.java
 * @Description TODO
 * @createTime 2020/12/7,21:25
 */
@Service
public class CarSaveService {
    @Autowired
    private CarSaveMapper carSaveMapper;

    @Autowired
    private CarMapper carMapper;

    public CommonResponse<CarSave> addCarSave(long id, CarSaveInfo carSaveInfo) {
        String car_num = carSaveInfo.getCar_num();
        Long car_id = this.carMapper.findIdByNum(car_num);
        CarSave carSave=CarSave.builder()
                .car_id(car_id)
                .save_desc(carSaveInfo.getSave_desc())
                .save_item(carSaveInfo.getSave_item())
                .save_pay(carSaveInfo.getSave_pay())
                .save_date(carSaveInfo.getSave_date())
                .user_id(id).build();
        int insert = this.carSaveMapper.insert(carSave);

        if (insert==1){
            return new CommonResponse<>(true,"您已成功上传车辆的保障信息",null);
        }
        return new CommonResponse<>(false,"上传车辆的保障信息失败，请联系管理员",null);
    }

    public QueryResponse<CarSave> selectPageCarSave(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<CarSave> carSaves = this.carSaveMapper.selectAllCarSave();
        PageInfo<CarSave> carPageInfo = new PageInfo<>(carSaves);
        return new QueryResponse<>(true,"查询成功",carSaves,carPageInfo.getTotal());
    }

    public CarSave selectCarSaveById(long id) {
        return this.carSaveMapper.selectByCarSaveId(id);
    }

    @Transactional
    public int deleteById(long id){
        //删除对应id的车辆保障记录
        return this.carSaveMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public CommonResponse<CarSave> updateCarSave(long id, CarSaveInfo carSaveInfo) {
        CarSave carSave = this.carSaveMapper.selectByPrimaryKey(id);
        carSave.setSave_item(carSaveInfo.getSave_item());
        carSave.setSave_date(carSaveInfo.getSave_date());
        carSave.setSave_desc(carSaveInfo.getSave_desc());
        carSave.setSave_pay(carSaveInfo.getSave_pay());
        int i = this.carSaveMapper.updateByPrimaryKey(carSave);
        if (i == 1) {
            return new CommonResponse<>(true,"修改车辆保障信息成功",null);
        }
        return new CommonResponse<>(false,"修改车辆保障信息失败，请联系管理员",null);
    }
}
