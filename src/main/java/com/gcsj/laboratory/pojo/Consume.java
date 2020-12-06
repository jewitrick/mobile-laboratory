package com.gcsj.laboratory.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName Consume.java
 * @Description 耗材表
 * @createTime 2020/12/5,21:39
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consume")
public class Consume {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;       //耗材名称
    private Double stock;      //耗材库存
    private String unit;       //数量单位
    private Date date;         //存入日期
}
