package com.back.reservoirmanagement.mapper;

import com.back.reservoirmanagement.pojo.entity.Application;
import com.back.reservoirmanagement.pojo.vo.ApplicationVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplicationMapper extends BaseMapper<Application> {
    List<ApplicationVO> historicalList(Long userId);
}
