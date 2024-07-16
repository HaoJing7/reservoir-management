package com.back.reservoirmanagement.controller.app;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.dto.PowerStationQueryDTO;
import com.back.reservoirmanagement.pojo.entity.PowerStation;
import com.back.reservoirmanagement.pojo.entity.Reservoir;
import com.back.reservoirmanagement.pojo.vo.PowerStationDefaultVO;
import com.back.reservoirmanagement.pojo.vo.PowerStationDetailVO;
import com.back.reservoirmanagement.pojo.vo.PowerStationSimpleVO;
import com.back.reservoirmanagement.service.PowerStationService;
import com.back.reservoirmanagement.service.ReservoirService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
        log.info("电站查看详细信息{}:",id);
        //查询电站信息
        PowerStation powerStation = powerStationService.getById(id);
        System.out.println(powerStation);

        PowerStationDetailVO powerStationDetailVO = new PowerStationDetailVO();
        BeanUtils.copyProperties(powerStation, powerStationDetailVO);//将对于的信息拷贝到vo
        if (powerStation.getIsStair()){
            powerStationDetailVO.setIsStair(1);
        }else {
            powerStationDetailVO.setIsStair(0);
        }
        //查询水库信息获取名称
        LambdaQueryWrapper<Reservoir> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Reservoir::getId,powerStation.getReservoirId());

        Reservoir reservoir = reservoirService.getOne(queryWrapper);

        powerStationDetailVO.setReservoirName(reservoir.getName());//将水库名称也拷贝到vo
        System.out.println(powerStationDetailVO);

        return Result.success(powerStationDetailVO);
    }

    /**
     * 搜索与分类
     * @param powerStationQueryDTO 可以根据名称模糊查询，根据类型|是否梯级|页码分类查询
     * @return
     */
    @ApiOperation("搜索与分类")
    @GetMapping("/query")
    public Result<List<PowerStationDefaultVO>> getByQueryAndSort(PowerStationQueryDTO powerStationQueryDTO){
        log.info("电站搜索与分类：{}", powerStationQueryDTO);
        String powerStationName = powerStationQueryDTO.getPowerStationName();
        String type = powerStationQueryDTO.getType();
        String isStair = powerStationQueryDTO.getIsStair();

        //条件构造 按名称模糊查询、电站类型、是否梯级查询
        LambdaQueryWrapper<PowerStation> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper
                .like(StringUtils.isNotBlank(powerStationName), PowerStation::getPowerStationName, powerStationName)
                .eq(StringUtils.isNotBlank(type),PowerStation::getType, type)
                .eq(StringUtils.isNotBlank(isStair), PowerStation::getIsStair, isStair)
                .orderByAsc(PowerStation::getPowerStationId);

        List<PowerStation> list = powerStationService.list(queryWrapper);

        // 将PowerStation对象转换为PowerStationDefaultVO对象
        List<PowerStationDefaultVO> voList = list.stream().map(powerStation -> {
            PowerStationDefaultVO vo = new PowerStationDefaultVO();
            BeanUtils.copyProperties(powerStation, vo);
            return vo;
        }).collect(Collectors.toList());

        return Result.success(voList);
    }

    /**
     * 点击电站信息默认调用的接口
     * @param
     * @return
     */
    @GetMapping("/default")
    @ApiOperation("默认查询")
    public Result<List<PowerStationDefaultVO> > page(){
        log.info("电站默认查询:{}");

        //条件构造
        LambdaQueryWrapper<PowerStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(PowerStation::getPowerStationId);

        List<PowerStation> list = powerStationService.list(queryWrapper);

        // 将PowerStation对象转换为PowerStationDefaultVO对象
        List<PowerStationDefaultVO> voList = list.stream().map(powerStation -> {
            PowerStationDefaultVO vo = new PowerStationDefaultVO();
            BeanUtils.copyProperties(powerStation, vo);
            return vo;
        }).collect(Collectors.toList());

        log.info("voList:{}",voList);
        return Result.success(voList);
    }

    /**
     * 申请模块的获取电站简单信息功能
     * by nongqd
     * @return
     */
    @ApiOperation("获取电站简单信息")
    @GetMapping("/simple")
    public Result<List<PowerStationSimpleVO>> simpleInfo(){
        log.info("电站获取电站简单信息");
        // 获取电站信息
        List<PowerStation> list = powerStationService.list();
        // 封装电站简单信息
        List<PowerStationSimpleVO> simpleInfoList = list.stream().map(powerstation -> {
            PowerStationSimpleVO simpleInfo = new PowerStationSimpleVO();
            BeanUtils.copyProperties(powerstation, simpleInfo);
            return simpleInfo;
        }).collect(Collectors.toList());
        log.info("电站获取电站简单信息返回{}",simpleInfoList);
        return Result.success(simpleInfoList);
    }
}