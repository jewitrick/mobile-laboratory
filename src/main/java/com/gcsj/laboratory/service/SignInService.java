package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.SignInMapper;
import com.gcsj.laboratory.pojo.SignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignInService {

    @Autowired
    SignInMapper signInMapper;

    //插入签到信息
    public int insertSignInInfo(SignIn signIn){
        return signInMapper.insertSelective(signIn);
    }

    //查询是否签到
    public boolean queryStuIsSignIn(Long uid,Long experAppyId){
        List<SignIn> list = signInMapper.findInfoByUidAndExperAppyId(uid, experAppyId);
        System.out.println(list);
        //返回学生是否签到，
        return list.size() != 0;
    }

    //查询签到信息By实验ID
    public List<SignIn> querySignInInfoByExperId(Long experid){
        return signInMapper.findInfoByExperAppyId(experid);
    }
}
