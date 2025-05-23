package com.porn.service.log.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.vo.PageVo;
import com.porn.client.log.api.LoginLogApiService;
import com.porn.client.log.dto.LoginLogQueryPageDTO;
import com.porn.client.log.dto.LoginLogSaveDTO;
import com.porn.client.log.vo.LoginLogVo;
import com.porn.service.log.converter.LoginLogConverter;
import com.porn.service.log.dao.entity.LoginLogDO;
import com.porn.service.log.dao.mapper.LoginLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

@Transactional(rollbackFor = {Exception.class})
public class LoginLogApiServiceImpl implements LoginLogApiService {
    private static final Logger log = LoggerFactory.getLogger(LoginLogApiServiceImpl.class);


    @Autowired
    private LoginLogMapper loginLogMapper;


    @Autowired
    private LoginLogConverter loginLogConverter;

    public boolean save(LoginLogSaveDTO loginLogSaveDTO) {

        LoginLogDO loginLogDO = LoginLogDO.builder().userId(loginLogSaveDTO.getUserId()).name(loginLogSaveDTO.getName()).loginIp(loginLogSaveDTO.getLoginIp()).build();

        return (this.loginLogMapper.insert(loginLogDO) > 0);

    }


    public PageVo<LoginLogVo> queryPage(LoginLogQueryPageDTO loginLogQueryPageDTO) {
        // 创建分页对象
        Page<LoginLogDO> page = new Page<>(
                loginLogQueryPageDTO.getPageStart().intValue(),
                loginLogQueryPageDTO.getPageSize().intValue(),
                true
        );

        // 构建查询条件
        LambdaQueryWrapper<LoginLogDO> queryWrapper = Wrappers.lambdaQuery(LoginLogDO.class)
                .like(ObjectUtil.isNotEmpty(loginLogQueryPageDTO.getLkName()), LoginLogDO::getName, loginLogQueryPageDTO.getLkName())
                .le(ObjectUtil.isNotEmpty(loginLogQueryPageDTO.getEndTime()), LoginLogDO::getCreateTime, loginLogQueryPageDTO.getEndTime())
                .ge(ObjectUtil.isNotEmpty(loginLogQueryPageDTO.getStartTime()), LoginLogDO::getCreateTime, loginLogQueryPageDTO.getStartTime())
                .eq(LoginLogDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(LoginLogDO::getCreateTime);

        // 执行分页查询
        IPage<LoginLogDO> loginLogPage = loginLogMapper.selectPage(page, queryWrapper);

        // 转换结果并返回
        return PageVo.<LoginLogVo>builder()
                .pageStart(loginLogQueryPageDTO.getPageStart())
                .pageSize(loginLogQueryPageDTO.getPageSize())
                .total(loginLogPage.getTotal())
                .data(loginLogConverter.toLoginLogVoList(loginLogPage.getRecords()))
                .build();
    }

}

