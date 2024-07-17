package com.back.reservoirmanagement.controller.common;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.entity.FileUrl;
import com.back.reservoirmanagement.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**t
 * Author:tan hao
 * Date: 2024-07-16 22:00
 * Description:
 */
@RestController
@Slf4j
@RequestMapping({"/app/log", "/admin/log"})
@Api(tags = "日志文件模块")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/list")
    @ApiOperation("获取文件列表")
    public Result<?> getLogList() {
        List<FileUrl> list = logService.getLogList();
        return Result.success(list);
    }
}
