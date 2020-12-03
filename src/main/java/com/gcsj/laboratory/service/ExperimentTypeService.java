package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.ExperimentTypeMapper;
import com.gcsj.laboratory.pojo.ExperimentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperimentTypeService {
    @Autowired
    private ExperimentTypeMapper experimentTypeMapper;

    public List<ExperimentType> selectAllTypes(){
        return this.experimentTypeMapper.selectAll();
    }

}
