package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.ExperimentApply;
import com.gcsj.laboratory.resp.CommonResponse;
import com.gcsj.laboratory.resp.QueryResponse;
import com.gcsj.laboratory.service.ExperimentApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Colin
 * @version 1.0.0
 * @description 实验申请记录控制层
 * @date 2020/12/2 22:37
 */
@RestController
@RequestMapping("experimentApply")
public class ExperimentApplyController {

    @Autowired
    private ExperimentApplyService experimentApplyService;

    //教育局查询所有学校的实验申请信息
    @GetMapping("/{id}/page/{currentPage}/{pageSize}")
    QueryResponse<ExperimentApply> findPage(@PathVariable("currentPage") int currentPage,
                                            @PathVariable("pageSize") int pageSize) {
        return this.experimentApplyService.findPage(currentPage, pageSize);
    }

    //学校查询本校的实验申请信息
    //教育局查询其所管辖的学校申请的实验信息
    @GetMapping("/{id}/{currentPage}/{pageSize}")
    QueryResponse<ExperimentApply> findPageByUserId(@PathVariable("id") Long id,
                                                    @PathVariable("currentPage") int currentPage,
                                                    @PathVariable("pageSize") int pageSize,
                                                    @RequestParam(required = false) Long state) {
        return this.experimentApplyService.findPageByUserId(id, currentPage, pageSize, state);
    }

    @GetMapping("/getById//{id}")
    ExperimentApply findExperimentById(@PathVariable Long id){
        return this.experimentApplyService.findExperimentById(id);
    }

    @PostMapping("{id}")
    CommonResponse<ExperimentApply> insert(@PathVariable long id, @RequestBody ExperimentApply experimentApply) {
        return this.experimentApplyService.insert(id, experimentApply);
    }

    //教育局通过实验审核
    @PostMapping("/{id}/state/{state}")
    public CommonResponse<ExperimentApply> updateState(@PathVariable Long id, @PathVariable int state){
        return this.experimentApplyService.updateState(id,state);
    }

    //教育局驳回学校实验申请
    @PostMapping("/{id}/state/{state}/result/{result}")
    public CommonResponse<ExperimentApply> updateStateAndResult(@PathVariable Long id,
                                                  @PathVariable int state,
                                                  @PathVariable String result){
        return this.experimentApplyService.updateStateAndResult(id,state,result);
    }

}
