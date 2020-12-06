package com.gcsj.laboratory.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarCtrl.java
 * @Description 车辆调度表
 * @createTime 2020/12/5,21:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_ctrl")
public class CarCtrl {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long car_apply_id;

    @Transient
    private CarApply carApply;

    private Long car_id;
    private Long driver_id;
    private Long teacher_id;
    private Integer ctrl_status;
}
