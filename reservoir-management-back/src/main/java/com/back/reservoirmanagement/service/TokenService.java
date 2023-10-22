package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.entity.Token;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface TokenService extends IService<Token> {
    Map<String, Object> getToken(Integer userId);

    Integer getUserIdByToken(String token);
}
