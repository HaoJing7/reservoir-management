package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.pojo.dto.ReservoirPageDTO;
import com.back.reservoirmanagement.pojo.entity.Reservoir;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ReservoirService extends IService<Reservoir> {

    Page<Reservoir> getReservoirList(ReservoirPageDTO dto);
}
