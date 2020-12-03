package com.gcsj.laboratory.pojo;

import lombok.Builder;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user_edu")
@Builder
public class UserEduBureau {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long user_id;
    private Long edu_id;
}
