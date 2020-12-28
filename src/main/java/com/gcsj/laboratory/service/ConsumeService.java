package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.ConsumeMapper;
import com.gcsj.laboratory.pojo.Consume;
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

    public List<Consume> getAllConsumes() {
        return this.consumeMapper.selectAll();
    }
}
