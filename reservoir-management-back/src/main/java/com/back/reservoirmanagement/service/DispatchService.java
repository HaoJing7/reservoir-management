package com.back.reservoirmanagement.service;

import org.springframework.web.multipart.MultipartFile;

public interface DispatchService {
    String executeAlgorithm(MultipartFile file, Integer algorithm, Integer particleCount, Integer iterationCount);
}
