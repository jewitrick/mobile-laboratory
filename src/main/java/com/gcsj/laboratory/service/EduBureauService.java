package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.EduBureauMapper;
import com.gcsj.laboratory.pojo.EduBureau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EduBureauService {
    @Autowired
    private EduBureauMapper eduBureauMapper;

    public List<EduBureau> selectAllEdu(){
        return this.eduBureauMapper.selectAll();
    }
}
