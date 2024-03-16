package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.pojo.dto.ApplicationPageQueryDTO;
import com.back.reservoirmanagement.pojo.dto.ApplicationSubmitDTO;
import com.back.reservoirmanagement.pojo.entity.Application;
import com.back.reservoirmanagement.pojo.vo.ApplicationDetailVO;
import com.back.reservoirmanagement.pojo.vo.ApplicationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ApplicationService extends IService<Application> {
    Page<Application> getApplicationList(ApplicationPageQueryDTO dto);

    ApplicationDetailVO getApplicationDetail(Integer id);

    /**
     * 提交申请
     * @param applicationSubmitDTO
     */
    void submit(ApplicationSubmitDTO applicationSubmitDTO);

    /**
     * 员工用户获取历史申请
     * @return
     */
    List<ApplicationVO> historicalList();
}
