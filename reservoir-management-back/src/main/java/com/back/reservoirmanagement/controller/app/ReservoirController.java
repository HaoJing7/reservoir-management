package com.back.reservoirmanagement.controller.app;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.dto.ReservoirQueryDTO;
import com.back.reservoirmanagement.pojo.entity.Reservoir;
import com.back.reservoirmanagement.pojo.vo.ReservoirDefaultVO;
import com.back.reservoirmanagement.pojo.vo.ReservoirSimpleVO;
import com.back.reservoirmanagement.service.ReservoirService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        log.info("请求传入的参数：{}",id);

        //在页面基于某个水库的编辑触发，故该水库一定存在，无须查找判断
        Reservoir reservoir=reservoirService.getById(id);

        log.info("详细信息为：{}", reservoir);
        return Result.success(reservoir);
    }

    @ApiOperation("搜索与分类")
    @GetMapping("/query")
    public Result getByQueryAndSort(ReservoirQueryDTO reservoirQueryDTO){
        log.info("查询参数：{}", reservoirQueryDTO);

        int page = reservoirQueryDTO.getPage();
        if (page == 0) {
            page = 1;  // 如果没有提供 page 参数，那么默认为 1
        }

        int size=4;    //因为数据少，先规定页面大小为4
        Page<Reservoir> pageInfo = new Page<>(page, size);

        //条件构造 按名称模糊查询、水库类型查询
        LambdaQueryWrapper<Reservoir> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(reservoirQueryDTO.getName()!=null,
                        Reservoir::getName, reservoirQueryDTO.getName())
                .eq(reservoirQueryDTO.getType()!=0,
                        Reservoir::getType, reservoirQueryDTO.getType())
                .orderByAsc(Reservoir::getId);

        //分页查询
        reservoirService.page(pageInfo, queryWrapper);

        //将Reservoir对象转换为ReservoirDefaultVO对象
        List<ReservoirDefaultVO> voList = pageInfo.getRecords().stream().map(reservoir -> {
            ReservoirDefaultVO vo = new ReservoirDefaultVO();
            BeanUtils.copyProperties(reservoir, vo);
            return vo;
        }).collect(Collectors.toList());

        //创建一个新的Page对象，用于存储ReservoirDefaultVO对象
        Page<ReservoirDefaultVO> voPage = new Page<>(pageInfo.getCurrent(), pageInfo.getSize(), pageInfo.getTotal());
        voPage.setRecords(voList);

        return Result.success(voPage);
    }
    /**
     * 点击水库信息默认调用的接口
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

        //用于存储Reservoir对象
        Page<Reservoir> pageInfo = new Page<>(page, size);
        //条件构造
        LambdaQueryWrapper<Reservoir> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Reservoir::getId);
        //分页查询
        reservoirService.page(pageInfo, queryWrapper);

        //将Reservoir对象转换为ReservoirDefaultVO对象
        List<ReservoirDefaultVO> voList = pageInfo.getRecords().stream().map(reservoir -> {
            ReservoirDefaultVO vo = new ReservoirDefaultVO();
            BeanUtils.copyProperties(reservoir, vo);
            return vo;
        }).collect(Collectors.toList());

        //创建一个新的Page对象，用于存储ReservoirDefaultVO对象
        Page<ReservoirDefaultVO> voPage = new Page<>(pageInfo.getCurrent(), pageInfo.getSize(), pageInfo.getTotal());
        voPage.setRecords(voList);

        return Result.success(voPage);
    }

    @ApiOperation("获取水库简单信息")
    @GetMapping("/simple")
    public Result<List<ReservoirSimpleVO>> simpleInfo(){
        // 获取水库信息
        List<Reservoir> list = reservoirService.list();
        // 封装水库简单信息
        List<ReservoirSimpleVO> simpleInfoList = list.stream().map(reservoir -> {
            ReservoirSimpleVO simpleInfo = new ReservoirSimpleVO();
            BeanUtils.copyProperties(reservoir, simpleInfo);
            return simpleInfo;
        }).collect(Collectors.toList());
        return Result.success(simpleInfoList);
    }
}
