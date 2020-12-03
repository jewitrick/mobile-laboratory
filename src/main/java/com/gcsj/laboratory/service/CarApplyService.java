package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.CarApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarApplyService {
    @Autowired
    private CarApplyMapper carApplyMapper;
}
