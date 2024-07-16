package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.common.utils.AlgorithmParameter;
import org.springframework.web.multipart.MultipartFile;

public interface DispatchService {
    /**
     * 管理端调用算法方法
     * @param file
     * @param algorithm
     * @param particleCount
     * @param iterationCount
     * @return 文件的url，可通过这个文件url下载文件
     */
    String executeAlgorithm(MultipartFile file, Integer algorithm, Integer particleCount, Integer iterationCount);

    /**
     * 小程序调用算法方法
     * @param algorithmParameter
     * @return 文件的url，可通过这个文件url下载文件
     */
    String executeByApp(AlgorithmParameter algorithmParameter);

}
