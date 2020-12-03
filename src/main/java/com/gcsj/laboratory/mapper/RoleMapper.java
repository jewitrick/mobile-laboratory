package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.Role;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface RoleMapper extends Mapper<Role> {
    @Select("select * from role where id = #{id}")
    Role findById(Long id);
}
