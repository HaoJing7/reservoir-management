package com.back.reservoirmanagement.controller.admin;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.service.DispatchService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:tan hao
 * Date: 2024-06-24 18:57
 * Description:
 */
@RestController
@RequestMapping("/admin/dispatch")
@Slf4j
public class DispatchController {

    @Autowired
    private DispatchService dispatchService;

    @PostMapping("/execute")
    public Result<?> executeAlgorithm(
            @RequestParam("file") MultipartFile file,
            @RequestParam("algorithm") Integer algorithm,
            @RequestParam("particleCount") Integer particleCount,
            @RequestParam("iterationCount") Integer iterationCount) {

        String url = dispatchService.executeAlgorithm(file, algorithm, particleCount, iterationCount);
        log.info(url);
        return Result.success(url);
    }

}


