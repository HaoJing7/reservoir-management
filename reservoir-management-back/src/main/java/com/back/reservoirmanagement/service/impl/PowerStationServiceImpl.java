package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.mapper.ApplicationMapper;
import com.back.reservoirmanagement.mapper.PowerStationMapper;
import com.back.reservoirmanagement.pojo.dto.StationPageDTO;
import com.back.reservoirmanagement.pojo.entity.Application;
import com.back.reservoirmanagement.pojo.entity.PowerStation;
import com.back.reservoirmanagement.pojo.entity.Reservoir;
import com.back.reservoirmanagement.service.PowerStationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerStationServiceImpl extends ServiceImpl<PowerStationMapper, PowerStation> implements PowerStationService {
    @Autowired
    private PowerStationMapper powerStationMapper;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    public Page<PowerStation> getStationList(StationPageDTO dto) {
        Page<PowerStation> page = new Page<>(dto.getPage(), dto.getPageSize());
        LambdaQueryWrapper<PowerStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!dto.getNumber().isEmpty(), PowerStation::getPowerStationNumber, dto.getNumber())
                .like(!dto.getName().isEmpty(), PowerStation::getPowerStationName, dto.getName())
                .eq(dto.getType() != null, PowerStation::getType, dto.getType());
        powerStationMapper.selectPage(page, queryWrapper);
        return page;
    }

    @Override
    public void deleteStation(Integer id) {
        powerStationMapper.deleteById(id);
        // 删除与水库相关的数据（application数据）
        LambdaQueryWrapper<Application> applicationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        applicationLambdaQueryWrapper.eq(Application::getPowerStationId, id);
        applicationMapper.delete(applicationLambdaQueryWrapper);
    }
}
