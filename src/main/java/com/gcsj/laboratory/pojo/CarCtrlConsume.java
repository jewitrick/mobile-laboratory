package com.gcsj.laboratory.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarCtrlConsume.java
 * @Description 车辆调度耗材表
 * @createTime 2020/12/5,21:55
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_ctrl_consume")
public class CarCtrlConsume {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long car_ctrl_id;
    private Long consume_id;
    private Double cost_count;  // 耗材数量/重量（单位以该耗材的库存单位为准
}
