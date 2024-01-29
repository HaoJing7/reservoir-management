package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.mapper.PowerStationMapper;
import com.back.reservoirmanagement.pojo.dto.StationPageDTO;
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
}
