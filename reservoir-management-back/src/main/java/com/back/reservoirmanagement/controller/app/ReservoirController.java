package com.back.reservoirmanagement.controller.app;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.dto.ReservoirQueryDTO;
import com.back.reservoirmanagement.pojo.entity.Hydrology;
import com.back.reservoirmanagement.pojo.entity.Reservoir;
import com.back.reservoirmanagement.pojo.vo.HydrologyVO;
import com.back.reservoirmanagement.pojo.vo.ReservoirDefaultVO;
import com.back.reservoirmanagement.pojo.vo.ReservoirSimpleVO;
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
@RequestMapping("/app/reservoir")
@Api(tags = "小程序端水库的查询操作")
@Slf4j
public class ReservoirController {

    @Autowired
    private ReservoirService reservoirService;


    @GetMapping("/detail/{id}")
    @ApiOperation("详细信息")
    public Result getDetailReservoir(@PathVariable Long id){
        log.info("详细信息传入的参数：{}",id);

        //在页面基于某个水库的编辑触发，故该水库一定存在，无须查找判断
        Reservoir reservoir=reservoirService.getById(id);

        log.info("详细信息为：{}", reservoir);
        return Result.success(reservoir);
    }

    @ApiOperation("搜索与分类")
    @GetMapping("/query")
    public Result<List<ReservoirDefaultVO>> getByQueryAndSort(ReservoirQueryDTO reservoirQueryDTO){
        log.info("水库搜索与分类传入的参数：{}", reservoirQueryDTO);
        String reservoirName = reservoirQueryDTO.getName();
        String reservoirType = reservoirQueryDTO.getType();

        //条件构造 按名称模糊查询、水库类型查询
        LambdaQueryWrapper<Reservoir> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper
                .like(StringUtils.isNotBlank(reservoirName) ,Reservoir::getName, reservoirName)
                .eq(StringUtils.isNotBlank(reservoirType),Reservoir::getType,reservoirType)
                .orderByAsc(Reservoir::getId);

        List<Reservoir> reservoirList = reservoirService.list(queryWrapper);

        //将Reservoir对象转换为ReservoirDefaultVO对象
        List<ReservoirDefaultVO> voList = reservoirList.stream().map(reservoir -> {
            ReservoirDefaultVO vo = new ReservoirDefaultVO();
            BeanUtils.copyProperties(reservoir, vo);
            return vo;
        }).collect(Collectors.toList());

        return Result.success(voList);
    }
    /**
     * 点击水库信息默认调用的接口
     * @param
     * @return
     */
    @GetMapping("/default")
    @ApiOperation("默认查询")
    public Result<List<ReservoirDefaultVO>> page(){
        log.info("水库默认查询：{}");

        //条件构造
        LambdaQueryWrapper<Reservoir> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Reservoir::getId);

        List<Reservoir> list = reservoirService.list(queryWrapper);


        List<ReservoirDefaultVO> voList = list.stream().map(reservoir -> {
            ReservoirDefaultVO vo = new ReservoirDefaultVO();
            BeanUtils.copyProperties(reservoir, vo);
            return vo;
        }).collect(Collectors.toList());

        log.info("voList:{}",voList);
        return Result.success(voList);
    }


    /**
     * 申请模块的获取水库简单信息功能
     * by nongqd
     * @return
     */
    @ApiOperation("获取水库简单信息")
    @GetMapping("/simple")
    public Result<List<ReservoirSimpleVO>> simpleInfo(){
        log.info("水库简单信息:");
        // 获取水库信息
        List<Reservoir> list = reservoirService.list();
        // 封装水库简单信息
        List<ReservoirSimpleVO> simpleInfoList = list.stream().map(reservoir -> {
            ReservoirSimpleVO simpleInfo = new ReservoirSimpleVO();
            BeanUtils.copyProperties(reservoir, simpleInfo);
            return simpleInfo;
        }).collect(Collectors.toList());
        log.info("水库获取电站简单信息返回{}",simpleInfoList);
        return Result.success(simpleInfoList);
    }

    /**
     * 获取水文数据
     * @return
     */
    @ApiOperation("获取水文数据")
    @GetMapping("/hydrology")
    public Result<HydrologyVO> getHydrology() {
        Hydrology hydrology = reservoirService.getHydrology();

        return Result.success(convertToHydrologyVO(hydrology));
    }

    private HydrologyVO convertToHydrologyVO(Hydrology hydrology){
        int floodSeason = hydrology.getFloodSeason().intValue();
        int iceCover = hydrology.getPresenceOfIceCover().intValue();
        int shape = hydrology.getRiverNetworkShape().intValue();
        int value = hydrology.getRiverNavigationValue().intValue();
        return HydrologyVO.builder()
                .waterLevel(hydrology.getWaterLevel())
                .runoffVolume(hydrology.getRunoffVolume())
                .sedimentConcentration(hydrology.getSedimentConcentration())
                .floodSeason(floodSeason==1?"春":(floodSeason==2?"夏":(floodSeason==3?"秋":"冬")))
                .presenceOfIceCover(iceCover==0?"无":"有")
                .flowVelocity(hydrology.getFlowVelocity())
                .riverNetworkShape(shape==0?"未知":(shape==1?"树枝状水系":(shape==2?"向心状水系":(shape==3?"平行状水系":"网状水系"))))
                .hydropowerPotential(hydrology.getHydropowerPotential())
                .riverNavigationValue(value==0?"低":(value==1?"中":"高"))
                .build();
    }

}