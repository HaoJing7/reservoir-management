package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.common.context.BaseContext;
import com.back.reservoirmanagement.mapper.ApplicationMapper;
import com.back.reservoirmanagement.pojo.dto.ApplicationSubmitDTO;
import com.back.reservoirmanagement.pojo.entity.Application;
import com.back.reservoirmanagement.service.ApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper,Application> implements ApplicationService {

    /**
     * 提交申请
     * @param applicationSubmitDTO
     */
    @Override
    public void submit(ApplicationSubmitDTO applicationSubmitDTO) {
        // 数据封装
        Application application=new Application();
        BeanUtils.copyProperties(applicationSubmitDTO,application);
        // 设置一条申请的相关信息(用户id、提交状态、申请时间)
//        application.setId(System.currentTimeMillis());
        application.setEmployeeId(BaseContext.getCurrentId());
        application.setStatus(0);
        application.setCreateTime(LocalDateTime.now());
        save(application);
    }
}
