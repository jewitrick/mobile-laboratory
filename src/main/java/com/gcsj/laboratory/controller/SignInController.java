package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.SignIn;
import com.gcsj.laboratory.pojo.User;
import com.gcsj.laboratory.service.SignInService;
import com.gcsj.laboratory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class SignInController {

    @Autowired
    SignInService signInService;
    @Autowired
    UserService userService;
    //查询学生是否签到
    @PostMapping("/issignin")
    public boolean isSignIn(@RequestBody Map<String,String> data, HttpSession session){
        User user = (User) session.getAttribute("user");
        long uid = user.getId();
        long experAppyId = Long.parseLong(data.get("id"));
        return signInService.queryStuIsSignIn(uid,experAppyId);
    }
    //学生签到
    @PostMapping("/signin")
    public boolean signIn(@RequestBody Map<String,String> data, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            //封装数据
            SignIn signIn = new SignIn();
            signIn.setUser_id(user.getId());
            signIn.setExperiment_apply_id(Long.parseLong(data.get("id")));
            signIn.setDate(new Date());
            //插入
            if(signInService.insertSignInInfo(signIn) > 0){
                //成功
                return true;
            }
        }
        return false;
    }
    //查询实验的学生签到信息
    @PostMapping("/getsigninfo")
    public List<Map<String,Object>> getSignInfo(@RequestBody Map<String,String> data){
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        //获取ID
        long id = Long.parseLong(data.get("id"));
        //获取签到信息
        List<SignIn> signIns = signInService.querySignInInfoByExperId(id);
        for (SignIn signIn : signIns) {
            HashMap<String, Object> map = new HashMap<>();
            //获取姓名
            User user = userService.selectUserById(signIn.getUser_id());
            if(user!= null){
                String realname = user.getRealname();
                map.put("studentName",realname);
                map.put("date",signIn.getDate());
                //添加到list
                list.add(map);
            }
        }
        return list;
    }
}
