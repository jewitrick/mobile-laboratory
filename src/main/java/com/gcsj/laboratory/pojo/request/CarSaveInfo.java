package com.gcsj.laboratory.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarInfo.java
 * @Description TODO
 * @createTime 2020/12/7,22:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarSaveInfo {
    private String car_num;
    private Date save_date;
    private String save_desc;
    private String save_item;
    private Double save_pay;
}
