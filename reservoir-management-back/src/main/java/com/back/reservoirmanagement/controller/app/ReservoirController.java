package com.back.reservoirmanagement.controller.app;

import com.back.reservoirmanagement.dto.ReservoirDTO;
import com.back.reservoirmanagement.dto.ReservoirPageQueryDTO;
import com.back.reservoirmanagement.entity.Reservoir;
import com.back.reservoirmanagement.service.ReservoirService;
import com.back.reservoirmanagement.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/reservoir")
@Api(tags = "小程序端水库的查询操作")
@Slf4j
public class ReservoirController {
    @Autowired
    private ReservoirService reservoirService;


    @GetMapping("/detail")
    @ApiOperation("详细信息")
    public Result getDetailReservoir(ReservoirDTO reservoirDTO){
        log.info("请求传入的参数：{}",reservoirDTO);
        LambdaQueryWrapper<Reservoir> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(Reservoir::getNumber,reservoirDTO.getNumber());

        //在页面基于某个水库的编辑触发，故该水库一定存在，无须查找判断
        Reservoir reservoir01=reservoirService.getOne(queryWrapper);

        log.info("详细信息为：{}", reservoir01);
        return Result.success(reservoir01);
    }

    /**
     * 水库分页展示
     * @param reservoirPageQueryDTO 分页传递的信息
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("水库分页展示")//todo 这里先水库名字作为参数，后面可以弄个分类，拓展分页规则
    public Result<Page> page(ReservoirPageQueryDTO reservoirPageQueryDTO){
        log.info("分页查询参数：{}", reservoirPageQueryDTO);

        //分页构造器,传入dto的page，pageSize
        Page pageInfo = new Page(reservoirPageQueryDTO.getPage(),reservoirPageQueryDTO.getPageSize());

        LambdaQueryWrapper<Reservoir> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(reservoirPageQueryDTO.getName()),
                Reservoir::getName,reservoirPageQueryDTO.getName());
        queryWrapper.orderByAsc(Reservoir::getNumber);

        reservoirService.page(pageInfo,queryWrapper);


        return Result.success(pageInfo);
    }

}
