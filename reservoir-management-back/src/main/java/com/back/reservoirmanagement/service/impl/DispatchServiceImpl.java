package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.common.exception.ParameterErrorException;
import com.back.reservoirmanagement.common.utils.AlgorithmParameter;
import com.back.reservoirmanagement.common.utils.AlgorithmUtil;
import com.back.reservoirmanagement.mapper.FileUrlMapper;
import com.back.reservoirmanagement.pojo.entity.FileUrl;
import com.back.reservoirmanagement.service.DispatchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private FileUrlMapper fileUrlMapper;

    /**
     * 管理端调用方法
     * @param file
     * @param algorithm
     * @param particleCount
     * @param iterationCount
     * @return 文件url
     */
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

            return getFileUrl(algorithmParameter);

        } catch (Exception e) {
            log.error("Error reading file:", e);
            throw new ParameterErrorException("文件上传处理失败！");
        }
    }


    /**
     * 小程序调用功能算法方法
     *
     * @param algorithmParameter
     * @return 文件的url，可通过这个文件url下载文件
     */
    @Override
    public String executeByApp(AlgorithmParameter algorithmParameter) {
        return getFileUrl(algorithmParameter);
    }

    /**
     * 调用算法并返回文件的url
     * @param algorithmParameter
     * @return 文件的url
     */
    private @NotNull String getFileUrl(AlgorithmParameter algorithmParameter) {
        // 调用算法并返回结果文件的URL
        String fileURL = AlgorithmUtil.dispatch(algorithmParameter);

        // 获取当前的时间作为文件名
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedNow = now.format(formatter);

        // 把文件URL存入数据库，当成日志使用
        fileUrlMapper.insert(new FileUrl(null, formattedNow, fileURL));

        return fileURL;
    }
}
