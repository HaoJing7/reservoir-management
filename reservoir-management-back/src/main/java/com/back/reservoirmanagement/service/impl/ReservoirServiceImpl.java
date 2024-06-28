package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.mapper.ApplicationMapper;
import com.back.reservoirmanagement.mapper.HydrologyMapper;
import com.back.reservoirmanagement.pojo.dto.ReservoirPageDTO;
import com.back.reservoirmanagement.pojo.entity.Application;
import com.back.reservoirmanagement.pojo.entity.Hydrology;
import com.back.reservoirmanagement.pojo.entity.Reservoir;
import com.back.reservoirmanagement.mapper.ReservoirMapper;
import com.back.reservoirmanagement.service.ReservoirService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 水库的crud操作
 */
@Service
@Slf4j
public class ReservoirServiceImpl extends ServiceImpl<ReservoirMapper, Reservoir> implements ReservoirService {

    @Autowired
    private ReservoirMapper reservoirMapper;

    @Autowired
    private HydrologyMapper hydrologyMapper;

    @Autowired
    private ApplicationMapper applicationMapper;

    /**
     * 管理端获取水库列表
     */
    @Override
    public Page<Reservoir> getReservoirList(ReservoirPageDTO dto) {
        Page<Reservoir> page = new Page<>(dto.getPage(), dto.getPageSize());
        LambdaQueryWrapper<Reservoir> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!dto.getNumber().isEmpty(), Reservoir::getNumber, dto.getNumber())
                .like(!dto.getName().isEmpty(), Reservoir::getName, dto.getName())
                .eq(dto.getType() != null, Reservoir::getType, dto.getType());
        reservoirMapper.selectPage(page, queryWrapper);
        return page;
    }

    /**
     * 获取水文数据
     */
    @Override
    public Hydrology getHydrology() {
        return hydrologyMapper.selectById(1);
    }

    /**
     * 修改水文数据
     */
    @Override
    public void updateHydrology(Hydrology hydrology) {
        hydrologyMapper.updateById(hydrology);
    }

    @Override
    public void deleteReservoir(Integer id) {
        reservoirMapper.deleteById(id);
        LambdaQueryWrapper<Application> applicationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        applicationLambdaQueryWrapper.eq(Application::getReservoirId, id);
        applicationMapper.delete(applicationLambdaQueryWrapper);
    }
}
