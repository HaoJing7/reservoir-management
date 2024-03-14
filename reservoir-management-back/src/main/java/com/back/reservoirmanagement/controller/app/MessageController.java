package com.back.reservoirmanagement.controller.app;


import com.back.reservoirmanagement.common.context.BaseContext;
import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.dto.ApplicationSubmitDTO;
import com.back.reservoirmanagement.pojo.dto.MessageQueryDTO;
import com.back.reservoirmanagement.pojo.entity.Message;
import com.back.reservoirmanagement.pojo.vo.ReservoirDefaultVO;
import com.back.reservoirmanagement.pojo.vo.SimpleMessageVO;
import com.back.reservoirmanagement.service.MessageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/simpleList")
    @ApiOperation("返回所有消息的缩略形式")
    public Result<List<SimpleMessageVO>> list(){
        // 获取所有消息
        LambdaQueryWrapper<Message> queryWrapper=new LambdaQueryWrapper<>();
        // 消息排序: 级别、是否完成、是否已读、创建时间
        queryWrapper.eq(Message::getEmployeeId, BaseContext.getCurrentId());
        queryWrapper.orderByDesc(Message::getLevel)
                .orderByDesc(Message::getFinished)
                .orderByAsc(Message::getChecked)
                .orderByDesc(Message::getCreateTime);
        List<Message> list = messageService.list(queryWrapper);

        // 将所有的消息转为缩略形式
        List<SimpleMessageVO> voList =convert(list);
        log.info("voList内容:{}",voList);

        return Result.success(voList);
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

    /**
     *
     * @param messageQueryDTO
     * @return
     */
    @PostMapping("/query")
    @ApiOperation("消息的条件查询")
    public Result queryByTitle(@RequestBody MessageQueryDTO messageQueryDTO){
        // 模糊查询
        log.info("查询条件：{}",messageQueryDTO);
        LambdaQueryWrapper<Message> queryWrapper=new LambdaQueryWrapper<>();
        String title = messageQueryDTO.getTitle();
        queryWrapper.like(StringUtils.isNoneEmpty(title),Message::getTitle,title);
        List<Message> list = messageService.list(queryWrapper);
        // 将查询所得的消息转为缩略形式
        List<SimpleMessageVO> voList =convert(list);
        return Result.success(voList);
    }

    @PostMapping("/confirm")
    @ApiOperation("消息确认完成")
    public Result confirm(Long id){

        log.info("消息任务完成：{}",id);
        Message mes=new Message();
        mes.setId(id);
        mes.setFinished(MESSAGE_FINISHED);
        messageService.updateById(mes);
        return Result.success();
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
