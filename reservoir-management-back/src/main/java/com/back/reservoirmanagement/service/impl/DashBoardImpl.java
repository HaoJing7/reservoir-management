package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.mapper.ApplicationMapper;
import com.back.reservoirmanagement.mapper.MessageMapper;
import com.back.reservoirmanagement.mapper.RecordMapper;
import com.back.reservoirmanagement.pojo.entity.Application;
import com.back.reservoirmanagement.pojo.entity.Message;
import com.back.reservoirmanagement.pojo.entity.Record;
import com.back.reservoirmanagement.pojo.vo.DashBoardDataVO;
import com.back.reservoirmanagement.service.DashBoardService;
import com.back.reservoirmanagement.service.EmployeeService;
import com.back.reservoirmanagement.service.PowerStationService;
import com.back.reservoirmanagement.service.ReservoirService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.RescaleOp;
import java.util.*;

/**
 * Author:tan hao
 * Date: 2024-06-18 22:03
 * Description:
 */
@Service
public class DashBoardImpl implements DashBoardService {

    @Autowired
    private ReservoirService reservoirService;

    @Autowired
    private PowerStationService stationService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private RecordMapper recordMapper;

    // 随机生成数据
    private List<Integer> generateList() {
        int size = 12;
        int month = new Date().getMonth() + 1;
        List<Integer> list = new ArrayList<>(size);
        Random rand = new Random();
        // Define the ranges
        int lowRangeMin = 400;
        int lowRangeMax = 600;
        int highRangeMin = 600;
        int highRangeMax = 800;

        // Generate values for the list
        for (int i = 0; i < size; i++) {
            if (i >= month - 1) {
                list.add(0);
            } else if (i >= 4 && i <= 9) {
                // For indexes 4 to 9, use the high range
                list.add(rand.nextInt(highRangeMax - highRangeMin + 1) + highRangeMin);
            } else {
                // For all other indexes, use the low range
                list.add(rand.nextInt(lowRangeMax - lowRangeMin + 1) + lowRangeMin);
            }
        }
        return list;
    }

    // 获取首页数据
    @Override
    public DashBoardDataVO getData() {
        LambdaQueryWrapper<Application> queryWaiting = new LambdaQueryWrapper<>();
        queryWaiting.eq(Application::getStatus, 0);
        LambdaQueryWrapper<Application> queryProcessing = new LambdaQueryWrapper<>();
        queryProcessing.eq(Application::getStatus, 1);
        Long waiting = applicationMapper.selectCount(queryWaiting);
        Long processing = applicationMapper.selectCount(queryProcessing);

        List<Integer> list = generateList();
        // 获取元素总和
        int sum = list.stream().mapToInt(Integer::intValue).filter(x -> x != 0).sum();
        int avg = sum / (new Date().getMonth() + 1);
        // 获取最大值
        int max = list.stream().mapToInt(Integer::intValue).max().orElse(0);
        // 获取最小值
        int min = list.stream().mapToInt(Integer::intValue).filter(x -> x != 0).min().orElse(0);
        return DashBoardDataVO.builder()
                .reservoir(reservoirService.count())
                .station(stationService.count())
                .employee(employeeService.count())
                .waiting(waiting)
                .processing(processing)
                .list(list)
                .sumPower(sum)
                .maxPower(max)
                .minPower(min)
                .avgPower(avg)
                .build();
    }

    // 获得所有备忘录记录
    @Override
    public List<Record> getRecord() {
        return recordMapper.selectList(null);
    }

    // 根据id删除备忘录
    @Override
    public void deleteRecordById(Integer id) {
        recordMapper.deleteById(id);
    }

    // 添加备忘录
    @Override
    public void addRecord(Record record) {
        recordMapper.insert(record);
    }
}
