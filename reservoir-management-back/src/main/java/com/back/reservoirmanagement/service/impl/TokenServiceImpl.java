package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.pojo.entity.Token;
import com.back.reservoirmanagement.mapper.TokenMapper;
import com.back.reservoirmanagement.service.TokenService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author:tan hao
 * Date: 2023-10-23 00:11
 * Description:
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {
    @Autowired
    TokenMapper tokenMapper;


    @Override
    public Map<String, Object> getToken(Integer userId) {
        String key = "user:" + UUID.randomUUID();
        Token token = new Token();
        token.setUserId(userId);
        token.setToken(key);
        tokenMapper.insert(token);
        Map<String, Object> data = new HashMap<>();
        data.put("token", key);
        return data;
    }

    @Override
    public Integer getUserIdByToken(String token) {
        LambdaQueryWrapper<Token> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Token::getToken, token);
        return tokenMapper.selectOne(queryWrapper).getUserId();
    }

    @Override
    public void deleteToken(String token) {
        LambdaQueryWrapper<Token> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Token::getToken, token);
        tokenMapper.delete(queryWrapper);
    }
}


