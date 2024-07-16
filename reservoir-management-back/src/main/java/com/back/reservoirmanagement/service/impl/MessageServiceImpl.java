package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.common.context.BaseContext;
import com.back.reservoirmanagement.mapper.MessageMapper;
import com.back.reservoirmanagement.pojo.dto.MessagePageQueryDTO;
import com.back.reservoirmanagement.pojo.entity.Message;
import com.back.reservoirmanagement.service.MessageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Override
    public List<Message> pageQuery(MessagePageQueryDTO messagePageQueryDTO) {
        String title =messagePageQueryDTO.getTitle();
        // 获取所有消息
        LambdaQueryWrapper<Message> queryWrapper=new LambdaQueryWrapper<>();
        // 消息排序: 级别、是否完成、是否已读、创建时间
        queryWrapper.eq(Message::getEmployeeId, BaseContext.getCurrentId());
        queryWrapper.orderByDesc(Message::getLevel)
                .orderByDesc(Message::getFinished)
                .orderByDesc(Message::getCreateTime)
                .orderByAsc(Message::getChecked);
        queryWrapper.like(StringUtils.isNotBlank(title),Message::getTitle,title);
        //分页查询构造
        Page<Message> pageInfo=new Page(messagePageQueryDTO.getPage(),messagePageQueryDTO.getPageSize());

        this.page(pageInfo,queryWrapper);

        return pageInfo.getRecords();
    }
}
