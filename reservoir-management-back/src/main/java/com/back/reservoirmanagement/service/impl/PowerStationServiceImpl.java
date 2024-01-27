package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.mapper.PowerStationMapper;
import com.back.reservoirmanagement.pojo.entity.PowerStation;
import com.back.reservoirmanagement.service.PowerStationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PowerStationServiceImpl extends ServiceImpl<PowerStationMapper, PowerStation> implements PowerStationService {
}
