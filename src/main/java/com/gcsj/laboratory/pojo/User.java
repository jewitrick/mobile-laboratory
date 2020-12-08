package com.gcsj.laboratory.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "user")
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long role_id;
    @Transient
    private Role role;
    private String username;    //用户登录名
    private String password;    //密码
    private String realname;    //用户真实姓名
    private String phone;       //联系方式
    private Date birth;         //出生日期

    @Transient
    private String schoolName;
    @Transient
    private String eduBureau;
    @Transient
    private Long class_id;
}
