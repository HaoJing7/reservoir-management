package com.back.reservoirmanagement.controller.admin;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.dto.StationPageDTO;
import com.back.reservoirmanagement.pojo.entity.PowerStation;
import com.back.reservoirmanagement.service.PowerStationService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author:tan hao
 * Date: 2024-01-29 01:32
 * Description:
 */
@RestController("adminPowerStationController")
@RequestMapping("/admin/station")
@Slf4j
public class PowerStationController {
    @Autowired
    private PowerStationService powerStationService;

    /**
     * 获取电站列表
     */
    @GetMapping("/page")
    public Result<?> getStationList(StationPageDTO dto) {
        Page<PowerStation> page = powerStationService.getStationList(dto);
        return Result.success(page);
    }

    /**
     * 删除电站
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteStation(@PathVariable Integer id) {
        powerStationService.removeById(id);
        return Result.success();
    }

    /**
     * 根据id查询电站详情
     */
    @GetMapping("/{id}")
    public Result<?> getStationDetail(@PathVariable Integer id) {
        return Result.success(powerStationService.getById(id));
    }

    /**
     * 编辑电站信息
     */
    @PutMapping
    public Result<?> updateStation(@RequestBody PowerStation powerStation) {
        powerStationService.updateById(powerStation);
        return Result.success();
    }

    /**
     * 添加电站
     */
    @PostMapping
    public Result<?> addStation(@RequestBody PowerStation powerStation) {
        powerStationService.save(powerStation);
        return Result.success();
    }
}
