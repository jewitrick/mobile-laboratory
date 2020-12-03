package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.SchoolMapper;
import com.gcsj.laboratory.pojo.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;

    public List<School> selectAllSchool(){
        return this.schoolMapper.selectAll();
    }
}
