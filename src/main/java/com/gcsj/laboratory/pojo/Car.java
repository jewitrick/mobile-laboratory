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
 * @ClassName Car.java
 * @Description TODO
 * @createTime 2020/12/5,21:22
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class Car {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String car_num;
    private String car_name;
    private Date car_date;
    private String car_type;
    private Long car_seat;
    private String busy_date;

}
