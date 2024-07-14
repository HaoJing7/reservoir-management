package com.back.reservoirmanagement.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:tan hao
 * Date: 2024-07-13 22:13
 * Description:
 */
public class AlgorithmUtil {

    // TODO 看是否能在前端把参数文件转化成列表，然后传送给后端
    // TODO 只要结果文件，然后对结果文件进行添加行头操作
    // TODO 将修改后的结果文件上传到阿里云OSS，然后删除结果文件，并返回网址

    // 调用算法
    public static void dispatch() {
        Process proc;
        double[] data1 = new double[]{50, 50, 50, 60, 60, 100, 100, 100, 60, 60, 50, 50};
        double[] data2 = new double[]{20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
        double[] data3 = new double[]{0.7, 100, 10, 10000, 80, 10, 100000000, 50, 15, 20};
        int[] data4 = new int[]{100, 200};

        List<Double> parameter1 = Arrays.stream(data1).boxed().collect(Collectors.toList());
        List<Double> parameter2 = Arrays.stream(data2).boxed().collect(Collectors.toList());
        List<Double> parameter3 = Arrays.stream(data3).boxed().collect(Collectors.toList());
        List<Integer> parameter4 = Arrays.stream(data4).boxed().collect(Collectors.toList());

        try {
            String[] args = new String[] { "python", "E:\\workspace\\my-project\\reservoir-management\\reservoir-management-back\\src\\main\\resources\\python\\nsga2.py", parameter1.toString(), parameter2.toString(), parameter3.toString(), parameter4.toString()};
            proc = Runtime.getRuntime().exec(args);// 执行py文件
            // 用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
