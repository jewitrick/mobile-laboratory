package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.*;
import com.gcsj.laboratory.pojo.*;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSchoolMapper userSchoolMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private UserEduBureauMapper userEduBureauMapper;

    @Autowired
    private EduBureauMapper eduBureauMapper;

    //查询所有用户信息
    public List<User> selectAllUsers() {
        return this.userMapper.findAllUser();
    }

    //根据id查询某个用户信息
    public User selectUserById(Long id) {
        return this.userMapper.selectByPrimaryKey( id );
    }

    //更新用户信息
    @Transactional
    public CommonResponse<User> updateUser(Long id, User user) {
        int i = this.userMapper.updateByPrimaryKey( user );
        if (i == 1) {
            return new CommonResponse<User>( true, "更新成功", null );
        }
        return new CommonResponse<User>( true, "更新失败", null );
    }

    //用户注册
    @Transactional
    public CommonResponse<User> userRegister(User user) {
        // 1. 拿到 role_id，判断用户选择注册的角色
        if (Objects.equals( 3L, user.getRole_id() )) { // 用户选择的注册角色是学校
            String schoolName = user.getSchoolName();
            School school = this.schoolMapper.findByName( schoolName );
            // 判断 school 是否为空
            if (ObjectUtils.isEmpty( school )) { // 如果为空，注册失败
                return new CommonResponse<>( false, "查询不到您输入的学校，请与管理员联系！", null );
            }

            this.userMapper.insert( user );

            // 关联 user_id 和 school_id （user_school）
            UserSchool userSchool = UserSchool.builder()
                    .id( null )
                    .user_id( user.getId() )
                    .school_id( school.getId() ).build();
            this.userSchoolMapper.insert( userSchool );

        } else if (Objects.equals( 2L, user.getRole_id() )) { // 用户选择的注册角色是教育局
            String eduBureauName = user.getEduBureau();
            EduBureau edu = this.eduBureauMapper.findByName( eduBureauName );
            // 判断 school 是否为空
            if (ObjectUtils.isEmpty( edu )) { // 如果为空，注册失败
                return new CommonResponse<>( false, "查询不到您输入的教育局，请与管理员联系！", null );
            }

            this.userMapper.insert( user );

            // 关联 user_id 和 edu_id （user_edu）
            UserEduBureau userEduBureau = UserEduBureau.builder()
                    .id( null )
                    .user_id( user.getId() )
                    .edu_id( edu.getId() ).build();
            this.userEduBureauMapper.insert( userEduBureau );

        } else if (Objects.equals( 1L, user.getRole_id() )) {
            this.userMapper.insert( user );     //调度中心
        } else if (Objects.equals(5L, user.getRole_id() )){
            this.userMapper.insert(user);    //司机
        }else if (Objects.equals(4L, user.getRole_id())){
            this.userMapper.insert(user);       //实验员
        }
        return new CommonResponse<>( true, "注册成功！", null );
    }

    //用户登录，查看用户名，密码是否正确
    public User checkLogin(String username, String password) {
        User user = userMapper.findUsernameAndPassword( username, password );
        return user;
    }


    //删除用户
    @Transactional
    public int deleteById(Long id) {
        return this.userMapper.deleteByPrimaryKey( id );
    }

    public QueryResponse<User> selectPageUsers(int currentPage, int pageSize) {
        PageHelper.startPage( currentPage, pageSize );
        List<User> users = this.userMapper.findAllUser();
        PageInfo<User> pageInfo = new PageInfo<>( users );
        return new QueryResponse<>( true, "查询成功！", users, pageInfo.getTotal() );
    }

    public List<User> selectAllTeachers() {
        long id=4L;
        return this.userMapper.selectAllTeachers(id);
    }

    public List<User> selectAllDrivers(){
        long id = 5L;
        return this.userMapper.selectAllDrivers(id);
    }
}
