package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.mapper.MessageMapper;
import com.back.reservoirmanagement.pojo.entity.Message;
import com.back.reservoirmanagement.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
}
