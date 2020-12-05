package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.Consume;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.service.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
