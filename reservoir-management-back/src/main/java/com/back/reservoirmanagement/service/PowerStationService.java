package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.pojo.dto.StationPageDTO;
import com.back.reservoirmanagement.pojo.entity.PowerStation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface PowerStationService extends IService<PowerStation> {
    Page<PowerStation> getStationList(StationPageDTO dto);

    void deleteStation(Integer id);
}
