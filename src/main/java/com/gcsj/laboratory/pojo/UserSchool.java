package com.gcsj.laboratory.pojo;

import lombok.Builder;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Table(name = "user_school")
public class UserSchool {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long user_id;
    private Long school_id;
}
