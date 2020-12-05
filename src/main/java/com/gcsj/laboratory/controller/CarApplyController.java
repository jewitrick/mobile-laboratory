package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.CarApply;
import com.gcsj.laboratory.pojo.ExperimentApply;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.gcsj.laboratory.service.CarApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @description 用车申请记录控制层
 * @date 2020/12/3 23:26
 */

@RestController
@RequestMapping("carApply")
public class CarApplyController {
    @Autowired
    private CarApplyService carApplyService;

    @PostMapping("{applyId}/{userId}")
    CommonResponse<CarApply> insert(@PathVariable("applyId") Long applyId,
                                    @PathVariable("userId") Long userId,
                                    @RequestBody CarApply carApply) {
        return this.carApplyService.insert(applyId, userId, carApply);
    }

    @GetMapping("selectAllRecord")
    List<CarApply> selectAllRecord(){
        return this.carApplyService.selectAllRecord();
    }

    //教育局查询其申请的用车信息
    //调度中心查询到所有教育局的用车申请信息
    @GetMapping("/{id}/{currentPage}/{pageSize}")
    QueryResponse<CarApply> findPageByUserId(@PathVariable("id") Long id,
                                                    @PathVariable("currentPage") int currentPage,
                                                    @PathVariable("pageSize") int pageSize,
                                                    @RequestParam(required = false) Long status) {
        return this.carApplyService.findPageByUserId(id, currentPage, pageSize, status);
    }

    @GetMapping("/getById/{id}")
    CarApply findExperimentById(@PathVariable Long id){
        return this.carApplyService.findCarApplyById(id);
    }

    //调度中心通过用车审核
    @PostMapping("/{id}/status/{status}")
    public CommonResponse<CarApply> updateState(@PathVariable Long id, @PathVariable int status){
        return this.carApplyService.updateState(id,status);
    }

    //调度中心驳回教育局的用车申请
    @PostMapping("/{id}/status/{status}/edu_result/{edu_result}")
    public CommonResponse<CarApply> updateStateAndResult(@PathVariable Long id,
                                                                @PathVariable int status,
                                                                @PathVariable String edu_result){
        return this.carApplyService.updateStateAndResult(id,status,edu_result);
    }
}
