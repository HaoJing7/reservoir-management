package com.back.reservoirmanagement.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Paths;

/**
 * Author:tan hao
 * Date: 2024-07-13 22:13
 * Description:
 */
@Slf4j
public class AlgorithmUtil {

    // 调用算法（参数为算法参数类）
    public static String dispatch(AlgorithmParameter algorithmParameter) {
        Process proc;

        try {
            // 将参数转换为字符串列表
            String parameter1 = algorithmParameter.getMonthlyDemand().toString().replace(" ", "");
            String parameter2 = algorithmParameter.getMonthlyInflow().toString().replace(" ", "");
            String parameter3 = algorithmParameter.getReservoirParams().toString().replace(" ", "");
            String particleCount = algorithmParameter.getParticleCount().toString();
            String iterationCount = algorithmParameter.getIterationCount().toString();

            String fileUrl;


            if (algorithmParameter.getAlgorithm() == 0) {
                fileUrl = "E:\\workspace\\my-project\\reservoir-management\\reservoir-management-back\\src\\main\\resources\\python\\moead.py";
            } else {
                fileUrl = "E:\\workspace\\my-project\\reservoir-management\\reservoir-management-back\\src\\main\\resources\\python\\nsga2.py";
            }

            // 构建命令行参数
            String[] args = new String[]{
                    "python",
                    fileUrl,
                    parameter1,
                    parameter2,
                    parameter3,
                    particleCount,
                    iterationCount
            };

            proc = Runtime.getRuntime().exec(args); // 执行py文件
            // 用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            String currentDir = System.getProperty("user.dir");
            String csvFilePath = Paths.get(currentDir, "result", "optPop", "Phen.csv").toString();
            File file = new File(csvFilePath);
            if (file.exists()) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 提取结果的csv文件并转换格式
        convertCsvToXlsx();
        addAlgorithmParametersToSheet(algorithmParameter);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 删除输出的结果文件夹
        String currentDir = System.getProperty("user.dir");
        String dirPath = Paths.get(currentDir, "result").toString();
        File directory = new File(dirPath);
        if (deleteDirectory(directory)) {
            log.info("结果文件删除成功！");
        } else {
            log.error("结果文件删除失败！");
        }

        // 用阿里云OSS存储文件（此处先不用存储）

        // 删除结果文件
        String resultPath = Paths.get(currentDir, "优化结果.xlsx").toString();
        File resultFile = new File(resultPath);
        if (resultFile.exists()) {
            if (resultFile.delete()) {
                log.info("删除xlsx文件成功！");
            } else {
                log.error("删除xlsx文件失败！");
            }
        }
        // 暂时默认返回这个文件URL
        return "https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E4%BC%98%E5%8C%96%E7%9A%84%E7%BB%93%E6%9E%9C.xlsx";

    }

    // 转换格式
    public static void convertCsvToXlsx() {
        String currentDir = System.getProperty("user.dir");
        String csvFilePath = Paths.get(currentDir, "result", "optPop", "Phen.csv").toString();
        String objvFilePath = Paths.get(currentDir, "result", "optPop", "Objv.csv").toString();
        String xlsxFilePath = Paths.get(currentDir, "优化结果.xlsx").toString();

        try (BufferedReader phenReader = new BufferedReader(new FileReader(csvFilePath));
             BufferedReader objvReader = new BufferedReader(new FileReader(objvFilePath));
             Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(xlsxFilePath)) {

            Sheet sheet = workbook.createSheet("优化结果");

            String[] headers = {"1月平均下泄流量(m³/s)", "2月平均下泄流量(m³/s)", "3月平均下泄流量(m³/s)",
                    "4月平均下泄流量(m³/s)", "5月平均下泄流量(m³/s)", "6月平均下泄流量(m³/s)",
                    "7月平均下泄流量(m³/s)", "8月平均下泄流量(m³/s)", "9月平均下泄流量(m³/s)",
                    "10月平均下泄流量(m³/s)", "11月平均下泄流量(m³/s)", "12月平均下泄流量(m³/s)",
                    "总发电量(kwH)", "总缺水量(m³)"};
            Row headerRow = sheet.createRow(0);

            // 添加表头
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
                // 设置列宽，256*列宽表示Excel中的宽度单位
                sheet.setColumnWidth(i, 25 * 256);
            }

            String phenLine, objvLine;
            int rowNum = 1;

            // 读取CSV文件并将内容写入XLSX文件
            while ((phenLine = phenReader.readLine()) != null && (objvLine = objvReader.readLine()) != null) {
                String[] phenValues = phenLine.split(",");
                String[] objvValues = objvLine.split(",");
                Row row = sheet.createRow(rowNum++);

                // 写入Phen.csv的内容
                for (int i = 0; i < phenValues.length; i++) {
                    try {
                        double numericValue = Double.parseDouble(phenValues[i]);
                        // 保留5位小数
                        String formattedValue = String.format("%.5f", numericValue);
                        row.createCell(i).setCellValue(formattedValue);
                    } catch (NumberFormatException e) {
                        // 处理无法解析为数字的情况
                        row.createCell(i).setCellValue(phenValues[i]);
                    }
                }

                // 写入Objv.csv的内容
                for (int i = 0; i < objvValues.length; i++) {
                    try {
                        double numericValue = Double.parseDouble(objvValues[i]);
                        // 保留5位小数
                        String formattedValue = String.format("%.5f", numericValue);
                        row.createCell(phenValues.length + i).setCellValue(formattedValue);
                    } catch (NumberFormatException e) {
                        // 处理无法解析为数字的情况
                        row.createCell(phenValues.length + i).setCellValue(objvValues[i]);
                    }
                }
            }

            // 写入XLSX文件
            workbook.write(fileOut);
            System.out.println("CSV files have been converted to XLSX file successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 添加参数数据
    public static void addAlgorithmParametersToSheet(AlgorithmParameter algorithmParameter) {
        String currentDir = System.getProperty("user.dir");
        String xlsxFilePath = Paths.get(currentDir, "优化结果.xlsx").toString();

        try (FileInputStream fis = new FileInputStream(xlsxFilePath);
             Workbook workbook = new XSSFWorkbook(fis);
             FileOutputStream fos = new FileOutputStream(xlsxFilePath)) {

            Sheet sheet = workbook.createSheet("算法参数");

            // 添加算法类型、粒子数量、迭代次数
            int rowNum = 0;
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("算法类型");
            String algorithmType = algorithmParameter.getAlgorithm() == 0 ? "MOEA/D" : "NSGA-II";
            row.createCell(1).setCellValue(algorithmType);

            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("粒子数量");
            row.createCell(1).setCellValue(algorithmParameter.getParticleCount());

            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("迭代次数");
            row.createCell(1).setCellValue(algorithmParameter.getIterationCount());

            // 添加每月需水量
            rowNum++;
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("月份");
            for (int i = 0; i < 12; i++) {
                row.createCell(i + 1).setCellValue(i + 1);
            }

            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("需水量 (m³/s)");
            for (int i = 0; i < 12; i++) {
                row.createCell(i + 1).setCellValue(String.format("%.5f", algorithmParameter.getMonthlyDemand().get(i)));
            }

            // 添加每月入库流量
            rowNum++;
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("月份");
            for (int i = 0; i < 12; i++) {
                row.createCell(i + 1).setCellValue(i + 1);
            }

            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("入库流量 (m³/s)");
            for (int i = 0; i < 12; i++) {
                row.createCell(i + 1).setCellValue(String.format("%.5f", algorithmParameter.getMonthlyInflow().get(i)));
            }

            // 添加水库参数信息
            rowNum++;
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("发电效率 (0-1)");
            row.createCell(1).setCellValue(String.format("%.5f", algorithmParameter.getReservoirParams().get(0)));

            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("水库最高蓄水位 (m)");
            row.createCell(1).setCellValue(String.format("%.5f", algorithmParameter.getReservoirParams().get(1)));

            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("水库最低蓄水位 (m)");
            row.createCell(1).setCellValue(String.format("%.5f", algorithmParameter.getReservoirParams().get(2)));

            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("水库面积 (10⁴m²)");
            row.createCell(1).setCellValue(String.format("%.5f", algorithmParameter.getReservoirParams().get(3)));

            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("最大下泄流量 (m³/s)");
            row.createCell(1).setCellValue(String.format("%.5f", algorithmParameter.getReservoirParams().get(4)));

            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("最小下泄流量 (m³/s)");
            row.createCell(1).setCellValue(String.format("%.5f", algorithmParameter.getReservoirParams().get(5)));

            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("输出最小值 (kW)");
            row.createCell(1).setCellValue(String.format("%.5f", algorithmParameter.getReservoirParams().get(6)));

            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("起调水位 (m)");
            row.createCell(1).setCellValue(String.format("%.5f", algorithmParameter.getReservoirParams().get(7)));

            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("末调水位 (m)");
            row.createCell(1).setCellValue(String.format("%.5f", algorithmParameter.getReservoirParams().get(8)));

            // 保存XLSX文件
            workbook.write(fos);
            System.out.println("Algorithm parameters have been added to the XLSX file successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 递归删除目录及其所有内容
    public static boolean deleteDirectory(File dir) {
        if (!dir.exists()) {
            return false;
        }

        // 删除目录中的所有文件和子目录
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        // 删除目录本身
        return dir.delete();
    }

}
