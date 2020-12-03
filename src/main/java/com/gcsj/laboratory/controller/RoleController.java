package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.Role;
import com.gcsj.laboratory.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public List<Role> selectAllRoles(){
        return roleService.selectAllRoles();
    }
}
