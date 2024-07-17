package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.mapper.FileUrlMapper;
import com.back.reservoirmanagement.pojo.entity.FileUrl;
import com.back.reservoirmanagement.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Author:tan hao
 * Date: 2024-07-16 22:10
 * Description:
 */
@Service
public class LogServiceImpl extends ServiceImpl<FileUrlMapper, FileUrl> implements LogService {
    @Autowired
    private FileUrlMapper fileUrlMapper;

    @Override
    public List<FileUrl> getLogList() {
        return fileUrlMapper.selectList(null);
    }
}
