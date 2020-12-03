package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.RoleMapper;
import com.gcsj.laboratory.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> selectAllRoles(){
        return this.roleMapper.selectAll();
    }

}
