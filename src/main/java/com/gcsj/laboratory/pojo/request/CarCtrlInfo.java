package com.gcsj.laboratory.pojo.request;

import com.gcsj.laboratory.pojo.CarCtrlConsume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName CarCtrlInfo.java
 * @Description TODO
 * @createTime 2020/12/6,20:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarCtrlInfo {

    private Long car_id;

    private Long teacher_id;

    private Long driver_id;

    private List<CarCtrlConsume> consumeList;

    private Date experi_date;

}
