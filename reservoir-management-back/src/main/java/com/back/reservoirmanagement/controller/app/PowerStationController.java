package com.back.reservoirmanagement.controller.app;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.dto.PowerStationQueryDTO;
import com.back.reservoirmanagement.pojo.dto.ReservoirQueryDTO;
import com.back.reservoirmanagement.pojo.entity.PowerStation;
import com.back.reservoirmanagement.pojo.entity.Reservoir;
import com.back.reservoirmanagement.pojo.vo.*;
import com.back.reservoirmanagement.service.PowerStationService;
import com.back.reservoirmanagement.service.ReservoirService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/powerstation")
@Api(tags = "小程序端电站的查询操作")
@Slf4j
public class PowerStationController {
    @Autowired
    private PowerStationService powerStationService;

    @Autowired
    private ReservoirService reservoirService;

    /**
     * 查看详细信息
     * @param id 电站id
     * @return
     */
    @ApiOperation("查看详细信息")
    @GetMapping("/detail/{id}")
    public Result getPowerStationDetail(@PathVariable Long id){
        //查询电站信息
        PowerStation powerStation = powerStationService.getById(id);

        PowerStationDetailVO powerStationDetailVO = new PowerStationDetailVO();
        BeanUtils.copyProperties(powerStation, powerStationDetailVO);//将对于的信息拷贝到vo

        //查询水库信息获取名称
        LambdaQueryWrapper<Reservoir> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Reservoir::getId,powerStation.getReservoirId());

        Reservoir reservoir = reservoirService.getOne(queryWrapper);

        powerStationDetailVO.setReservoirName(reservoir.getName());//将水库名称也拷贝到vo

        return Result.success(powerStationDetailVO);
    }

    /**
     * 搜索与分类
     * @param powerStationQueryDTO 可以根据名称模糊查询，根据类型|是否梯级|页码分类查询
     * @return
     */
    @ApiOperation("搜索与分类")
    @GetMapping("/query")
    public Result getByQueryAndSort(PowerStationQueryDTO powerStationQueryDTO){
        log.info("查询参数：{}", powerStationQueryDTO);

        int page = powerStationQueryDTO.getPage();
        if (page == 0) {
            page = 1;  // 如果没有提供 page 参数，那么默认为 1
        }

        int size=4;    //因为数据少，先规定页面大小为4
        Page<PowerStation> pageInfo = new Page<>(page, size);

        //条件构造 按名称模糊查询、电站类型、是否梯级查询
        LambdaQueryWrapper<PowerStation> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.isNoneEmpty(powerStationQueryDTO.getPowerStationName()),
                        PowerStation::getPowerStationName, powerStationQueryDTO.getPowerStationName())
                .eq(powerStationQueryDTO.getType() != 0,
                        PowerStation::getType, powerStationQueryDTO.getType())
                .eq(powerStationQueryDTO.getIsStair() != 0,
                        PowerStation::getIsStair, powerStationQueryDTO.getIsStair())
                .orderByAsc(PowerStation::getPowerStationId);

        //分页查询
        powerStationService.page(pageInfo, queryWrapper);

        // 将PowerStation对象转换为PowerStationDefaultVO对象
        List<PowerStationDefaultVO> voList = pageInfo.getRecords().stream().map(powerStation -> {
            PowerStationDefaultVO vo = new PowerStationDefaultVO();
            BeanUtils.copyProperties(powerStation, vo);
            return vo;
        }).collect(Collectors.toList());

        // 创建一个新的Page对象，用于存储PowerStationDefaultVO对象
        Page<PowerStationDefaultVO> voPage = new Page<>(pageInfo.getCurrent(), pageInfo.getSize(), pageInfo.getTotal());
        voPage.setRecords(voList);

        return Result.success(voPage);
    }

    /**
     * 点击电站信息默认调用的接口
     * @param page 页码数，默认是1显示第一页，和百度查询一样，我们提供上一页，下一页，xx页参数实现翻页
     * @return
     */
    @GetMapping("/default")
    @ApiOperation("默认查询")
    public Result<Page> page(Integer page){
        if (page == null) {
            page = 1;  // 如果没有提供 page 参数，那么默认为 1
        }
        int size = 4;  //因为数据少，先规定页面大小为4

        //用于存储PowerStation对象
        Page<PowerStation> pageInfo = new Page<>(page, size);
        //条件构造
        LambdaQueryWrapper<PowerStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(PowerStation::getPowerStationId);
        //分页查询
        powerStationService.page(pageInfo, queryWrapper);

        // 将PowerStation对象转换为PowerStationDefaultVO对象
        List<PowerStationDefaultVO> voList = pageInfo.getRecords().stream().map(powerStation -> {
            PowerStationDefaultVO vo = new PowerStationDefaultVO();
            BeanUtils.copyProperties(powerStation, vo);
            return vo;
        }).collect(Collectors.toList());

        //创建一个新的Page对象，用于存储PowerStationDefaultVO对象
        Page<PowerStationDefaultVO> voPage = new Page<>(pageInfo.getCurrent(), pageInfo.getSize(), pageInfo.getTotal());
        voPage.setRecords(voList);

        return Result.success(voPage);
    }

    @ApiOperation("获取电站简单信息")
    @GetMapping("/simple")
    public Result<List<PowerStationSimpleVO>> simpleInfo(){
        // 获取电站信息
        List<PowerStation> list = powerStationService.list();
        // 封装电站简单信息
        List<PowerStationSimpleVO> simpleInfoList = list.stream().map(powerstation -> {
            PowerStationSimpleVO simpleInfo = new PowerStationSimpleVO();
            BeanUtils.copyProperties(powerstation, simpleInfo);
            return simpleInfo;
        }).collect(Collectors.toList());
        return Result.success(simpleInfoList);
    }
}