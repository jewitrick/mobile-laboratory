package com.gcsj.laboratory.controller;

import com.gcsj.laboratory.pojo.Experiment;
import com.gcsj.laboratory.pojo.resp.CommonResponse;
import com.gcsj.laboratory.pojo.resp.QueryResponse;
import com.gcsj.laboratory.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExperimentController {

    @Autowired
    private ExperimentService experimentService;

    @GetMapping("/getAllExperiment")
    public List<Experiment> selectAllExperiment() {
        return experimentService.selectAllExperiment();
    }

    @GetMapping("/getPageExperiment/{currentPage}/{pageSize}")
    public QueryResponse<Experiment> selectPageExperiment(@PathVariable("currentPage") int currentPage,
                                                          @PathVariable("pageSize") int pageSize,
                                                          @RequestParam(required = false) Long type_id) {
        return experimentService.selectPageExperiment( currentPage, pageSize, type_id );
    }

    @PostMapping("/addExperiment")
    public CommonResponse<Experiment> insertExperiment(@RequestBody Experiment experiment) {
        return experimentService.insertExperiment( experiment );
    }

    //删除实验信息
    @DeleteMapping("/deleteExperiment")
    public int deleteById(Long id) {
        return experimentService.deleteById( id );
    }

    //根据id查询实验信息
    @GetMapping("/getExperimentDataById/{id}")
    public Experiment selectById(@PathVariable Long id) {
        return experimentService.selectById( id );
    }

    //修改实验信息
    @PostMapping("/updateExperiment/{id}")
    public CommonResponse<Experiment> updateExperiment(@PathVariable Long id, @RequestBody Experiment updateExperiment) {
        return experimentService.updateExperiment( id, updateExperiment );
    }
}
