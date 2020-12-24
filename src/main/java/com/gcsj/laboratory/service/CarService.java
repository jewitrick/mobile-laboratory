package com.gcsj.laboratory.service;

import com.alibaba.fastjson.JSONObject;
import com.gcsj.laboratory.mapper.CarMapper;
import com.gcsj.laboratory.pojo.Car;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarService.java
 * @Description TODO
 * @createTime 2020/12/5,21:25
 */
@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    @Transactional
    public CommonResponse<Car> insertCar(Car car) {
        int i = this.carMapper.insert(car);
        if (i == 1) {
            return new CommonResponse<>(true, "添加车辆信息成功", null);
        }
        return new CommonResponse<>(true, "添加车辆信息失败", null);
    }

    public QueryResponse<Car> selectPageCar(int currentPage, int pageSize) {

        PageHelper.startPage(currentPage, pageSize);
        List<Car> cars = this.carMapper.selectAll();
        PageInfo<Car> carPageInfo = new PageInfo<>(cars);
        return new QueryResponse<>(true, "查询成功", cars, carPageInfo.getTotal());
    }

    public Car selectCarById(long id) {
        return this.carMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public int deleteById(long id) {
        return this.carMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public CommonResponse<Car> updateCar(long id, Car updateCar) {
        int i = this.carMapper.updateByPrimaryKey(updateCar);
        if (i == 1) {
            return new CommonResponse<>(true, "修改车辆信息成功", null);
        }
        return new CommonResponse<>(false, "修改车辆信息失败", null);
    }

    public List<Car> getAllCars(String experi_date) {
        return this.carMapper
                .selectAll()
                .stream()
                .filter(car -> ObjectUtils.isEmpty(car.getBusy_date()) || !JSONObject.parseArray(car.getBusy_date()).toJavaList(String.class).contains(experi_date))
                .collect(Collectors.toList());
    }
}
