package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.pojo.dto.MessagePageQueryDTO;
import com.back.reservoirmanagement.pojo.entity.Message;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MessageService extends IService<Message> {
    List<Message> pageQuery(MessagePageQueryDTO messagePageQueryDTO);
}
