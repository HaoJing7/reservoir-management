package com.back.reservoirmanagement.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author:tan hao
 * Date: 2024-07-13 22:13
 * Description:
 */
public class AlgorithmUtil {

    // 调用算法（参数为算法参数类）
    public static void dispatch(AlgorithmParameter algorithmParameter) {
        Process proc;

        try {
            // 将参数转换为字符串列表
            String parameter1 = algorithmParameter.getMonthlyDemand().toString().replace(" ", "");
            String parameter2 = algorithmParameter.getMonthlyInflow().toString().replace(" ", "");
            String parameter3 = algorithmParameter.getReservoirParams().toString().replace(" ", "");
            String particleCount = algorithmParameter.getParticleCount().toString();
            String iterationCount = algorithmParameter.getIterationCount().toString();

            String fileUrl = "E:\\workspace\\my-project\\reservoir-management\\reservoir-management-back\\src\\main\\resources\\python\\moead.py";


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
    }
}
