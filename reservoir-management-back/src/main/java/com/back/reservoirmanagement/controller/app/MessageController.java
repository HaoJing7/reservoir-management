package com.back.reservoirmanagement.controller.app;


import com.back.reservoirmanagement.common.context.BaseContext;
import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.dto.MessagePageQueryDTO;
import com.back.reservoirmanagement.pojo.entity.Message;
import com.back.reservoirmanagement.pojo.vo.SimpleMessageListVO;
import com.back.reservoirmanagement.pojo.vo.SimpleMessageVO;
import com.back.reservoirmanagement.service.MessageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/app/message")
@RestController
@Api(tags = "小程序端申请模块和消息模块接口")
@Slf4j
public class MessageController {

    private static int MESSAGE_CHECKED=1;
    private static int MESSAGE_FINISHED=1;

    @Autowired
    private MessageService messageService;

    @GetMapping("/query")
    @ApiOperation("返回消息的缩略形式")
    public Result<SimpleMessageListVO> list(MessagePageQueryDTO pageQueryDTO){

        List<Message> list = messageService.pageQuery(pageQueryDTO);

        // 将所有的消息转为缩略形式
        List<SimpleMessageVO> voList =convert(list);
        log.info("voList内容:{}",voList);

        LambdaQueryWrapper<Message> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Message::getEmployeeId,BaseContext.getCurrentId());

        LambdaQueryWrapper<Message> countWrapper=new LambdaQueryWrapper<>();
        countWrapper.eq(Message::getEmployeeId,BaseContext.getCurrentId());
        long count = messageService.count(countWrapper);

        return Result.success(new SimpleMessageListVO(count,voList));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation("返回具体消息的详细信息")
    public Result<Message> detail(@PathVariable Long id){
        // 根据id获取某条消息的具体内容
        LambdaQueryWrapper<Message> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Message::getEmployeeId, BaseContext.getCurrentId());
        queryWrapper.eq(Message::getId,id);

        Message detailMessage = messageService.getOne(queryWrapper);
        log.info("detailMessage内容:{}",detailMessage);
        // 点开消息就修改为已读
        detailMessage.setChecked(MESSAGE_CHECKED);
        messageService.saveOrUpdate(detailMessage);
        return Result.success(detailMessage);
    }

    @PostMapping("/check")
    @ApiOperation("消息已读")
    public Result check(Long id){

        log.info("消息任务完成：{}",id);
        Message mes=new Message();
        mes.setId(id);
        mes.setChecked(MESSAGE_CHECKED);
        messageService.updateById(mes);
        return Result.success("消息已读");
    }

    @PostMapping("/confirm")
    @ApiOperation("消息确认完成")
    public Result confirm(Long id){

        log.info("消息任务完成：{}",id);
        Message mes=new Message();
        mes.setId(id);
        mes.setFinished(MESSAGE_FINISHED);
        messageService.updateById(mes);
        return Result.success("消息任务已完成");
    }

    /**
     * 详细消息转换为缩略消息
     * @param list
     * @return
     */
    private List<SimpleMessageVO> convert(List<Message> list){
        // 将所有的消息转为缩略形式
        List<SimpleMessageVO> voList =list.stream().map(mes -> {
            SimpleMessageVO vo=new SimpleMessageVO();
            BeanUtils.copyProperties(mes,vo);
            Integer level = mes.getLevel();
            String type=(level==1?"通知":(level==2?"提示":"紧急"));
            vo.setType(type);
            return vo;
        }).collect(Collectors.toList());
        return  voList;
    }
}
