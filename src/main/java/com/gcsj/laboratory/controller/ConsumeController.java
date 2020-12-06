package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.Consume;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.gcsj.laboratory.service.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName ConsumeController.java
 * @Description TODO
 * @createTime 2020/12/5,21:42
 */
@RestController
@RequestMapping("consume")
public class ConsumeController {

    @Autowired
    private ConsumeService consumeService;

    @PostMapping("addConsume")
    public CommonResponse<Consume> insertConsume(@RequestBody Consume consume){
        return this.consumeService.insertConsume(consume);
    }

    //获取所有的耗材信息用于遍历
    @GetMapping("getAllConsumes")
    public List<Consume> getAllConsumes(){
        return this.consumeService.getAllConsumes();
    }

    @GetMapping("{currentPage}/{pageSize}")
    public QueryResponse<Consume> selectPageConsume (@PathVariable("currentPage") int currentPage,
                                                 @PathVariable("pageSize") int pageSize) {
        return consumeService.selectPageConsume( currentPage, pageSize );
    }

    //根据id获取对应的耗材信息，用于编辑车辆信息
    @GetMapping("getConsumeById/{id}")
    public Consume selectConsumeById(@PathVariable long id){
        return consumeService.selectConsumeById(id);
    }

    //根据id删除相应的耗材信息
    @DeleteMapping("deleteById/{id}")
    public int deleteById(@PathVariable long id){
        return consumeService.deleteById(id);
    }

    //根据id更新相应的耗材信息
    @PutMapping("updateConsume/{id}")
    public CommonResponse<Consume> updateConsume(@PathVariable long id,
                                         @RequestBody Consume updateConsume){
        return this.consumeService.updateConsume(id,updateConsume);
    }
}
