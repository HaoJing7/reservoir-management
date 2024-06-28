package com.back.reservoirmanagement.controller.admin;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.dto.ReservoirPageDTO;
import com.back.reservoirmanagement.pojo.entity.Hydrology;
import com.back.reservoirmanagement.pojo.entity.Reservoir;
import com.back.reservoirmanagement.service.ReservoirService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author:tan hao
 * Date: 2024-01-28 16:56
 * Description:
 */
@RestController("adminReservoirController")
@RequestMapping("/admin/reservoir")
@Slf4j
public class ReservoirController {
    @Autowired
    ReservoirService reservoirService;

    /**
     * 获取水库列表
     */
    @GetMapping("/page")
    public Result<?> getReservoirList(ReservoirPageDTO dto) {
        Page<Reservoir> page = reservoirService.getReservoirList(dto);
        return Result.success(page);
    }

    /**
     * 删除水库
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteReservoir(@PathVariable Integer id) {
        reservoirService.deleteReservoir(id);
        return Result.success();
    }

    /**
     * 根据id查询水库详情
     */
    @GetMapping("/{id}")
    public Result<?> getDetailById(@PathVariable Integer id) {
        return Result.success(reservoirService.getById(id));
    }

    /**
     * 编辑水库信息
     */
    @PutMapping
    public Result<?> updateReservoir(@RequestBody Reservoir reservoir) {
        reservoirService.updateById(reservoir);
        return Result.success();
    }

    /**
     * 添加水库
     */
    @PostMapping
    public Result<?> addReservoir(@RequestBody Reservoir reservoir) {
        reservoirService.save(reservoir);
        return Result.success();
    }

    /**
     * 获取水文数据
     */
    @GetMapping("/hydrology")
    public Result<?> getHydrology() {
        Hydrology hydrology = reservoirService.getHydrology();
        return Result.success(hydrology);
    }

    /**
     * 修改水文数据
     */
    @PutMapping("/hydrology")
    public Result<?> updateHydrology(@RequestBody Hydrology hydrology) {
        reservoirService.updateHydrology(hydrology);
        return Result.success();
    }
}
