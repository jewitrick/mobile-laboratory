package com.gcsj.laboratory.service;

import com.gcsj.laboratory.mapper.ConsumeMapper;
import com.gcsj.laboratory.pojo.Consume;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CommonResponse<Consume> insertConsume(Consume consume) {
        int i = this.consumeMapper.insert(consume);
        if (i==1){
            return new CommonResponse<>(true,"添加耗材信息成功",null);
        }
        return new CommonResponse<>(false,"添加耗材信息失败",null);
    }
}
