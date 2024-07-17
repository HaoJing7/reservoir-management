package com.back.reservoirmanagement.controller.app;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.common.utils.AlgorithmParameter;
import com.back.reservoirmanagement.service.DispatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:tan hao
 * Date: 2024-07-17 10:28
 * Description:
 */
@RequestMapping("/app/algorithm")
@RestController
@Api(tags = "小程序端调用算法模块接口")
@Slf4j
public class AlgorithmController {
    @Autowired
    DispatchService dispatchService;

    /**
     * 执行小程序算法
     * @param algorithmParameter
     * @return 文件url地址 用于下载文件
     */
    @PostMapping("/execute")
    @ApiOperation("执行小程序算法")
    public Result<?> executeAlgorithm(@RequestBody AlgorithmParameter algorithmParameter) {
        String fileURL = dispatchService.executeByApp(algorithmParameter);
        return Result.success(fileURL);
    }
}
