package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.CarApplyMapper;
import com.gcsj.laboratory.mapper.ConsumeMapper;
import com.gcsj.laboratory.mapper.ExperimentApplyMapper;
import com.gcsj.laboratory.mapper.ExperimentMapper;
import com.gcsj.laboratory.pojo.CarApply;
import com.gcsj.laboratory.pojo.Consume;
import com.gcsj.laboratory.pojo.ExperimentApply;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName ConsumeService.java
 * @Description TODO
 * @createTime 2020/12/5,21:41
 */
@Service
public class ConsumeService {

    @Autowired
    private ConsumeMapper consumeMapper;

    @Autowired
    private CarApplyMapper carApplyMapper;

    @Autowired
    private ExperimentApplyMapper experimentApplyMapper;

    @Autowired
    private ExperimentMapper experimentMapper;

    @Transactional
    public CommonResponse<Consume> insertConsume(Consume consume) {
        int i = this.consumeMapper.insert(consume);
        if (i == 1) {
            return new CommonResponse<>(true, "添加耗材信息成功", null);
        }
        return new CommonResponse<>(false, "添加耗材信息失败", null);
    }

    public QueryResponse<Consume> selectPageConsume(int currentPage, int pageSize, long experiment_type_id) {
        PageHelper.startPage(currentPage, pageSize);
        List<Consume> consumes = this.consumeMapper.selectAllConsume(experiment_type_id);
        PageInfo<Consume> consumePageInfo = new PageInfo<>(consumes);
        return new QueryResponse<>(true, "查询成功", consumes, consumePageInfo.getTotal());
    }

    public Consume selectConsumeById(long id) {
        return this.consumeMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public int deleteById(long id) {
        return this.consumeMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public CommonResponse<Consume> updateConsume(long id, Consume updateConsume) {
        int i = this.consumeMapper.updateByPrimaryKey(updateConsume);
        if (i == 1) {
            return new CommonResponse<>(true, "修改耗材信息成功", null);
        }
        return new CommonResponse<>(false, "修改耗材信息失败", null);
    }

    public List<Consume> getConsumesById(long carApplyId) {
        long experimentApplyId = this.carApplyMapper.findExperimentApplyIdByCarApplyId(carApplyId);
        long experimentId = this.experimentApplyMapper.findExperimentIdById(experimentApplyId);
        long typeId = this.experimentMapper.findTypeIdByExperimentId(experimentId);  //获得实验类型id
        return this.consumeMapper.selectConsumesByExperimentTypeId(typeId);   //返回该实验类型id的耗材
    }
}
