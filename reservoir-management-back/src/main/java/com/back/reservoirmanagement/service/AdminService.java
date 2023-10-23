package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<Admin> {
    Admin login(Admin admin);

}
