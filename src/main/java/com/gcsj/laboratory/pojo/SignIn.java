package com.gcsj.laboratory.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "singin")
public class SignIn {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long user_id;
    private Long experiment_apply_id;
    private Date date;
}
