package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.User;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    @Select("select * from user where username=#{username} and password=#{password}")
    User findUsernameAndPassword(@Param("username") String nickname, @Param("password") String password);

    @Results(id = "userMapper", value = {
            @Result(column = "role_id", property = "role",
                    one = @One(select = "com.gcsj.laboratory.mapper.RoleMapper.findById")),
            @Result(column = "role_id", property = "role_id")
    })
    @Select("select id,role_id,username,realname,phone,birth from user order by role_id")
    List<User> findAllUser();

    @ResultMap("userMapper")
    @Select("select * from user where id = #{id}")
    User findById(long id);

    @Select("select * from user where role_id = #{roleId}")
    List<User> selectAllTeachers(long roleId);

    @Select("select * from user where role_id = #{roleId}")
    List<User> selectAllDrivers(long roleId);

    @Select("select realname from user where id = #{id}")
    String findRealnameByUserId(long id);
}
