package com.back.reservoirmanagement.service;


import com.back.reservoirmanagement.pojo.entity.FileUrl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface LogService extends IService<FileUrl> {

    List<FileUrl> getLogList();
}
