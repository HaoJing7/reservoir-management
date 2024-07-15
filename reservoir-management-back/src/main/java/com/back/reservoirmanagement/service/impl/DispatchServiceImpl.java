package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.common.exception.ParameterErrorException;
import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.common.utils.AlgorithmParameter;
import com.back.reservoirmanagement.common.utils.AlgorithmUtil;
import com.back.reservoirmanagement.service.DispatchService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:tan hao
 * Date: 2024-06-24 19:00
 * Description:
 */
@Slf4j
@Service
public class DispatchServiceImpl implements DispatchService {
    @Override
    public String executeAlgorithm(MultipartFile file, Integer algorithm, Integer particleCount, Integer iterationCount) {
        try {
            // 读取文件并解析
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            // 提取每月需水量信息
            List<Double> monthlyDemand = new ArrayList<>();
            for (int col = 1; col <= 12; col++) {
                Cell cell = sheet.getRow(2).getCell(col);
                if (cell != null) monthlyDemand.add((Double) cell.getNumericCellValue());
            }

            // 提取每月水库平均入库流量
            List<Double> monthlyInflow = new ArrayList<>();
            for (int col = 1; col <= 12; col++) {
                Cell cell = sheet.getRow(7).getCell(col);
                if (cell != null) monthlyInflow.add((Double) cell.getNumericCellValue());
            }

            // 提取水库参数信息
            List<Double> reservoirParams = new ArrayList<>();
            for (int col = 0; col < 10; col++) {
                Cell cell = sheet.getRow(12).getCell(col);
                if (cell != null) reservoirParams.add((Double) cell.getNumericCellValue());
            }

            if (monthlyDemand.size() != 12 || monthlyInflow.size() != 12 || reservoirParams.size() != 10) {
                throw new ParameterErrorException("参数数量不足！");
            }

            // 输出提取的参数
            log.info("Monthly Demand: {}", monthlyDemand);
            log.info("Monthly Inflow: {}", monthlyInflow);
            log.info("Reservoir Params: {}", reservoirParams);

            // 这里添加执行算法的逻辑
            log.info("Executing algorithm: {}", algorithm);
            log.info("Particle count: {}", particleCount);
            log.info("Iteration count: {}", iterationCount);

            AlgorithmParameter algorithmParameter = AlgorithmParameter.builder()
                    .algorithm(algorithm)
                    .particleCount(particleCount)
                    .iterationCount(iterationCount)
                    .monthlyDemand(monthlyDemand)
                    .monthlyInflow(monthlyInflow)
                    .reservoirParams(reservoirParams)
                    .build();

            AlgorithmUtil.dispatch(algorithmParameter);
            // 模拟返回结果文件的 URL
            return "https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E4%BC%98%E5%8C%96%E7%BB%93%E6%9E%9C.xlsx";


        } catch (Exception e) {
            log.error("Error reading file:", e);
            throw new ParameterErrorException("文件上传处理失败！");
        }
    }
}
