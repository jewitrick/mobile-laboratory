package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.User;
import com.gcsj.laboratory.resp.CommonResponse;
import com.gcsj.laboratory.resp.QueryResponse;
import com.gcsj.laboratory.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("user/verify")
    User getLoginUser(HttpSession session) {
        return (User) session.getAttribute( "user" );
    }

    //查询所有用户信息
    @GetMapping("/getAllUserData")
    public List<User> selectAllUserData() {
        return userService.selectAllUsers();
    }

    //分页查询所有信息
    @GetMapping("/getPageUserData/{currentPage}/{pageSize}")
    public QueryResponse<User> selectPageUserData(@PathVariable("currentPage") int currentPage,
                                                  @PathVariable("pageSize") int pageSize) {
        return userService.selectPageUsers( currentPage, pageSize );
    }

    //根据id查询某个用户信息
    @GetMapping("/getUserDataById/{id}")
    public User selectUserById(@PathVariable Long id) {
        return userService.selectUserById( id );
    }

    //删除用户
    @DeleteMapping("/deleteUser")
    public int deleteById(Long id) {
        return userService.deleteById( id );
    }

    //更新用户信息
    @PostMapping("/updateUser/{id}")
    public CommonResponse<User> updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        return userService.updateUser( id, updateUser );
    }

    //用户注册
    @PostMapping("/userRegister")
    public CommonResponse<User> userRegister(@RequestBody User user) {
        return userService.userRegister( user );
    }

    //用户登录
    @PostMapping("/login")
    public CommonResponse<User> login(@RequestBody User userLogin, HttpSession session) throws Exception {
        User user = userService.checkLogin( userLogin.getUsername(), userLogin.getPassword() );
        if (user != null) {
            session.setAttribute( "user", user );
            return new CommonResponse<>( true, "登录成功！",
                    Collections.singletonList( user ) );
        }
        return new CommonResponse<>( false, "用户名或密码错误", null );
    }


}
