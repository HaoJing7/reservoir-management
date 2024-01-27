package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.pojo.dto.ApplicationSubmitDTO;
import com.back.reservoirmanagement.pojo.entity.Application;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ApplicationService extends IService<Application> {
    /**
     * 提交申请
     * @param applicationSubmitDTO
     */
    void submit(ApplicationSubmitDTO applicationSubmitDTO);
}
