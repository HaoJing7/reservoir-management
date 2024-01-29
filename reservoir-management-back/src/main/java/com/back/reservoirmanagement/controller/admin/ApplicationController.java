package com.back.reservoirmanagement.controller.admin;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.dto.ApplicationPageQueryDTO;
import com.back.reservoirmanagement.pojo.entity.Application;
import com.back.reservoirmanagement.pojo.vo.ApplicationDetailVO;
import com.back.reservoirmanagement.service.ApplicationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author:tan hao
 * Date: 2024-01-27 01:25
 * Description:
 */
@RestController("adminApplicationController")
@RequestMapping("/admin/application")
@Slf4j
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    /**
     * 查询所有申请
     */
    @GetMapping("/page")
    public Result<?> getApplicationList(ApplicationPageQueryDTO dto) {
        Page<Application> page = applicationService.getApplicationList(dto);
        return Result.success(page);
    }

    /**
     * 查询待处理的总数
     */
    @GetMapping("/waiting/number")
    public Result<?> getWaitingNumber() {
        LambdaQueryWrapper<Application> queryWrapper = new LambdaQueryWrapper<>();
        Long number = applicationService.count(queryWrapper.eq(Application::getStatus, 0));
        return Result.success(number);
    }

    /**
     * 查询处理中的总数
     */
    @GetMapping("/processing/number")
    public Result<?> getProcessingNumber() {
        LambdaQueryWrapper<Application> queryWrapper = new LambdaQueryWrapper<>();
        Long number = applicationService.count(queryWrapper.eq(Application::getStatus, 1));
        return Result.success(number);
    }

    /**
     * 更改状态
     */
    @PutMapping("/status")
    public Result<?> changeStatus(@RequestBody Application application) {
        application.setStatus(application.getStatus() + 1);
        applicationService.updateById(application);
        return Result.success();
    }

    /**
     * 获取申请详情
     */
    @GetMapping("/detail/{id}")
    public Result<?> getApplicationDetail(@PathVariable Integer id) {
        ApplicationDetailVO detailVO = applicationService.getApplicationDetail(id);
        return Result.success(detailVO);
    }
}
