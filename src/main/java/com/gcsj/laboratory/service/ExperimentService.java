package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.ExperimentMapper;
import com.gcsj.laboratory.pojo.Experiment;
import com.gcsj.laboratory.resp.CommonResponse;
import com.gcsj.laboratory.resp.QueryResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperimentService {

    @Autowired
    private ExperimentMapper experimentMapper;

    public List<Experiment> selectAllExperiment() {
        return this.experimentMapper.findAllExperiments();
    }

    public CommonResponse<Experiment> insertExperiment(Experiment experiment) {
        int i = this.experimentMapper.insert( experiment );
        if (i == 1) {
            return new CommonResponse<>( true, "新增成功", null );
        }
        return new CommonResponse<>( false, "新增失败", null );

    }

    public int deleteById(Long id) {
        return this.experimentMapper.deleteByPrimaryKey( id );
    }

    public Experiment selectById(Long id) {
        return this.experimentMapper.selectByPrimaryKey( id );
    }

    public CommonResponse<Experiment> updateExperiment(Long id, Experiment updateExperiment) {
        int i = this.experimentMapper.updateByPrimaryKey( updateExperiment );
        if (i == 1) {
            return new CommonResponse<Experiment>( true, "修改成功", null );
        }
        return new CommonResponse<Experiment>( true, "修改失败", null );
    }

    public QueryResponse<Experiment> selectPageExperiment(int currentPage, int pageSize) {
        PageHelper.startPage( currentPage, pageSize );
        List<Experiment> experiments = this.experimentMapper.findAllExperiments();
        PageInfo<Experiment> pageInfo = new PageInfo<>( experiments );
        return new QueryResponse<>( true, "查询成功！", experiments, pageInfo.getTotal() );
    }
}
