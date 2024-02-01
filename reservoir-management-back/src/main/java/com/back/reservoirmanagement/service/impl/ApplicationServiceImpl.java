package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.common.context.BaseContext;
import com.back.reservoirmanagement.mapper.ApplicationMapper;
import com.back.reservoirmanagement.mapper.PowerStationMapper;
import com.back.reservoirmanagement.mapper.ReservoirMapper;
import com.back.reservoirmanagement.mapper.UserMapper;
import com.back.reservoirmanagement.pojo.dto.ApplicationPageQueryDTO;
import com.back.reservoirmanagement.pojo.dto.ApplicationSubmitDTO;
import com.back.reservoirmanagement.pojo.entity.Application;
import com.back.reservoirmanagement.pojo.entity.PowerStation;
import com.back.reservoirmanagement.pojo.entity.Reservoir;
import com.back.reservoirmanagement.pojo.entity.User;
import com.back.reservoirmanagement.pojo.vo.ApplicationDetailVO;
import com.back.reservoirmanagement.pojo.vo.ApplicationVO;
import com.back.reservoirmanagement.service.ApplicationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Author:tan hao
 * Date: 2024-01-27 01:10
 * Description:
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReservoirMapper reservoirMapper;

    @Autowired
    private PowerStationMapper powerStationMapper;

    @Override
    public Page<Application> getApplicationList(ApplicationPageQueryDTO dto) {
        Page<Application> page = new Page<>(dto.getPage(), dto.getPageSize());
        // 将dto中的字符串转换成LocalDateTime
        LocalDateTime beginTime = null;
        LocalDateTime endTime = null;
        if (dto.getBeginTime() != null && dto.getEndTime() != null) {
            beginTime = LocalDateTime.parse(dto.getBeginTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            endTime = LocalDateTime.parse(dto.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        LambdaQueryWrapper<Application> queryWrapper = new LambdaQueryWrapper<>();
        // 姓名模糊查询  类型精准查询  开始结束时间范围查询  status精准查询 -1 代表所有
        queryWrapper
                .like(!dto.getName().isEmpty(), Application::getEmployeeRealname, dto.getName())
                .eq(dto.getType() != null, Application::getType, dto.getType())
                .eq(dto.getStatus() != null && dto.getStatus() != -1, Application::getStatus, dto.getStatus())
                .between(beginTime != null, Application::getCreateTime, beginTime, endTime);
        return applicationMapper.selectPage(page, queryWrapper);
    }

    @Override
    public ApplicationDetailVO getApplicationDetail(Integer id) {
        Application application = applicationMapper.selectById(id);
        User user = userMapper.selectById(application.getEmployeeId());
        ApplicationDetailVO detailVO = new ApplicationDetailVO();
        BeanUtils.copyProperties(user, detailVO);
        Reservoir reservoir = reservoirMapper.selectById(application.getReservoirId());
        detailVO.setReservoirName(reservoir.getName());
        PowerStation powerStation = powerStationMapper.selectById(application.getPowerStationId());
        detailVO.setPowerStationName(powerStation.getPowerStationName());
        Integer type = application.getType();
        detailVO.setType(type == 0 ? "其它" : type == 1 ? "变压器故障" : type == 2 ? "调速器故障" : type == 3 ? "水轮机故障" : "发电机组故障");
        detailVO.setContent(application.getContent());
        detailVO.setApplicationCreateTime(application.getCreateTime());
        Integer status = application.getStatus();
        detailVO.setStatus(status == 0 ? "未受理" : status == 1 ? "受理中" : "已处理");
        detailVO.setPicture(application.getPicture());
        return detailVO;
    }

    /**
     * 提交申请
     * @param applicationSubmitDTO
     */
    @Override
    public void submit(ApplicationSubmitDTO applicationSubmitDTO) {
        // 数据封装
        Application application=new Application();
        BeanUtils.copyProperties(applicationSubmitDTO,application);
        // 设置一条申请的相关信息(用户id、提交状态、申请时间)
//        application.setId(System.currentTimeMillis());
        application.setEmployeeId(BaseContext.getCurrentId());
        application.setStatus(0);
        application.setCreateTime(LocalDateTime.now());
        save(application);
    }

    /**
     * 员工用户获取历史申请
     * @return
     */
    @Override
    public List<ApplicationVO> historicalList() {
        Long userId = BaseContext.getCurrentId();
        List<ApplicationVO> list = applicationMapper.historicalList(userId);
        // 进行参数转换
        for (ApplicationVO applicationVO : list) {
            String currentStatus = applicationVO.getStatus();
            String type = applicationVO.getType();
            applicationVO.setStatus("0".equals(currentStatus)?"未受理"
                                    :"1".equals(currentStatus)?"受理中"
                                    :"2".equals(currentStatus)?"已处理":"未知");
            applicationVO.setType(
                    "1".equals(type)?"变压器故障"
                    :"2".equals(type)?"调速器故障"
                    :"3".equals(type)?"水轮机故障"
                    :"4".equals(type)?"发电机组故障":"其它");
        }
        return list;
    }
}
