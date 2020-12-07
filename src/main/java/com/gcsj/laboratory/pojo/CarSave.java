package com.gcsj.laboratory.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarSave.java
 * @Description 车辆保障实体类
 * @createTime 2020/12/7,21:20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_save")
public class CarSave {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long car_id;        //车辆id

    @Transient
    private Car car;            //车辆实体类

    private Long user_id;       //用户id

    @Transient
    private String realName;    //用户真实姓名
    private Date save_date;     //保障日期
    private String save_item;   //车辆保障项目
    private Double save_pay;    //保障的费用
    private String save_desc;   //保障描述
}
