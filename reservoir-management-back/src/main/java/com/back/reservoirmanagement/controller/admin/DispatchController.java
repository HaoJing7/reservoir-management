package com.back.reservoirmanagement.controller.admin;

import com.back.reservoirmanagement.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * Author:tan hao
 * Date: 2024-06-24 18:57
 * Description:
 */
@RestController
@RequestMapping("/admin/dispatch")
@Slf4j
public class DispatchController {
    @GetMapping("/cal/{sa}/{pc}/{it}")
    public Result<?> Algorithm(@PathVariable Integer sa, @PathVariable Integer pc, @PathVariable Integer it) {
        String result = "https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E5%8F%82%E6%95%B0%E6%A8%A1%E6%9D%BF.xlsx";
        return Result.success(result);
    }
}
