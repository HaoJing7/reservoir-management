package com.back.reservoirmanagement.controller.admin;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.entity.Record;
import com.back.reservoirmanagement.service.DashBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author:tan hao
 * Date: 2024-06-18 21:40
 * Description:
 */
@RestController
@Slf4j
@RequestMapping("/admin/dashboard")
public class DashBoardController {
    @Autowired
    private DashBoardService dashBoardService;

    // 返回首页数据（总数数据 + 年发电量模拟数据）
    @GetMapping("/data")
    public Result<?> getData() {
        return Result.success(dashBoardService.getData());
    }

    @GetMapping("/list")
    public Result<?> getRecord() {
        return Result.success(dashBoardService.getRecord());
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> deleteRecordById(@PathVariable Integer id) {
        dashBoardService.deleteRecordById(id);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> addRecord(@RequestBody Record record) {
        dashBoardService.addRecord(record);
        return Result.success();
    }
}
