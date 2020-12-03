package com.gcsj.laboratory.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "experi_apply")
public class ExperimentApply {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long user_id;       //用户id
    private Long experi_id;     //实验信息id
    private String principal;   //负责人
    private String phone_number;//联系方式
    private Date experi_date;   //实验日期
    private Long experi_num;    //实验人数
    private String instruction; //申请说明
    private Integer state;      // 审核状态
    private String result;      // 审核结果

    @Transient
    private String schoolName;

    @Transient
    private Experiment experiment;  // 实验申请记录-实验 多对一

    @Transient
    private User user;  // 实验申请记录-用户 多对一
}
