package com.back.reservoirmanagement.controller.app;


import com.back.reservoirmanagement.common.context.BaseContext;
import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.entity.Message;
import com.back.reservoirmanagement.service.MessageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/app/message")
@RestController
@Api(tags = "小程序端申请模块和消息模块接口")
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/list")
    @ApiOperation("获取当前用户的所有消息")
    public Result<List<Message>> list(){
        // 获取所有消息
        LambdaQueryWrapper<Message> queryWrapper=new LambdaQueryWrapper<>();
        // 消息排序: 级别、是否完成、是否已读
        queryWrapper.eq(Message::getEmployeeId, BaseContext.getCurrentId());
        queryWrapper.orderByAsc(Message::getLevel).orderByDesc(Message::getFinished).orderByAsc(Message::getChecked).orderByDesc(Message::getCreateTime);
        List<Message> list = messageService.list(queryWrapper);
        return Result.success(list);
    }

}
