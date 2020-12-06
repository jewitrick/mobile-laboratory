package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.CarCtrl;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarCtrlMapper.java
 * @Description TODO
 * @createTime 2020/12/5,21:51
 */
public interface CarCtrlMapper extends Mapper<CarCtrl> {

    @Select("select ctrl_status from car_ctrl where car_apply_id = #{carApplyId}")
    Integer findCtrlStatusByCarApplyId(Long carApplyId);

    List<CarCtrl> findAll();
}
