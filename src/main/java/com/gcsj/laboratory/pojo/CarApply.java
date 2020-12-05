package com.gcsj.laboratory.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car_apply")
public class CarApply {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long experiment_apply_id;        //实验申请表id
    private Long user_id;                   //用户id
    private String edu_principal;           //教育局用车申请负责人
    private String edu_phone;               //教育局负责人联系方式
    private String edu_instruction;         //用车申请说明
    private String edu_result;              //用车申请结果
    private Integer edu_status;             //用车申请状态

    @Transient
    private String edu_name;

    @Transient
    private ExperimentApply experimentApply;
}
