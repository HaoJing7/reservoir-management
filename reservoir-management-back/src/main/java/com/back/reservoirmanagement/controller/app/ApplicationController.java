package com.back.reservoirmanagement.controller.app;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.dto.ApplicationSubmitDTO;
import com.back.reservoirmanagement.pojo.vo.ApplicationVO;
import com.back.reservoirmanagement.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/app/application")
@RestController
@Api(tags = "小程序端申请模块和消息模块接口")
@Slf4j
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    /**
     * 提交申请
     * @param applicationSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    @ApiOperation("提交申请")
    public Result applicationSubmit(@RequestBody ApplicationSubmitDTO applicationSubmitDTO){
        log.info("申请信息数据：{}",applicationSubmitDTO);
        applicationService.submit(applicationSubmitDTO);
        return Result.success();
    }

    /**
     * 查看历史申请
     * @return
     */
    @PostMapping("/list")
    @ApiOperation("查看历史申请")
    public Result<List<ApplicationVO>> list(){
        List<ApplicationVO> list = applicationService.historicalList();
        log.info("获取历史数据:{}",list);
        return Result.success(list);
    }

}
