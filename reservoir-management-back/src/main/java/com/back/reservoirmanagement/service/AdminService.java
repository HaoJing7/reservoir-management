package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.pojo.dto.UserLoginDTO;
import com.back.reservoirmanagement.pojo.entity.Admin;
import com.back.reservoirmanagement.pojo.vo.AdminInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<Admin> {

    Admin login(UserLoginDTO userLoginDTO);

    AdminInfoVO getInfoById();

    void updatePassword(String password);
}
