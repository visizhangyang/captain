
package com.porn.service.log.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.vo.PageVo;
import com.porn.client.log.api.OperateLogApiService;
import com.porn.client.log.dto.OperateLogQueryPageDTO;
import com.porn.client.log.dto.OperateLogSaveDTO;
import com.porn.client.log.vo.OperateLogVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.log.converter.OperateLogConverter;
import com.porn.service.log.dao.entity.OperateLogDO;
import com.porn.service.log.dao.mapper.OperateLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

@Transactional(rollbackFor = {Exception.class})
 public class OperateLogApiServiceImpl implements OperateLogApiService {
    /* 27 */   private static final Logger log = LoggerFactory.getLogger(OperateLogApiServiceImpl.class);






    @Autowired
     private OperateLogMapper operateLogMapper;






    @Autowired
     private OperateLogConverter operateLogConverter;







    public boolean save(OperateLogSaveDTO operateLogSaveDTO) {
        /* 48 */
        OperateLogDO operateLogDO = OperateLogDO.builder().userId(operateLogSaveDTO.getUserId()).name(operateLogSaveDTO.getName()).method(operateLogSaveDTO.getMethod()).action(operateLogSaveDTO.getAction()).params(operateLogSaveDTO.getParams()).timeConsume(Long.valueOf(operateLogSaveDTO.getEndTime().longValue() - operateLogSaveDTO.getStartTime().longValue())).build();
        /* 49 */
        return (this.operateLogMapper.insert(operateLogDO) > 0);

    }



    public PageVo<OperateLogVo> queryPage(OperateLogQueryPageDTO operateLogQueryPageDTO) {
        Page<OperateLogDO> page = new Page<>(
                operateLogQueryPageDTO.getPageStart().intValue(),
                operateLogQueryPageDTO.getPageSize().intValue(),
                true
        );

        LambdaQueryWrapper<OperateLogDO> queryWrapper = new QueryWrapper<OperateLogDO>().lambda()
                .eq(ObjectUtil.isNotEmpty(operateLogQueryPageDTO.getMethod()), OperateLogDO::getMethod, operateLogQueryPageDTO.getMethod())
                .like(ObjectUtil.isNotEmpty(operateLogQueryPageDTO.getLkName()), OperateLogDO::getName, operateLogQueryPageDTO.getLkName())
                .like(ObjectUtil.isNotEmpty(operateLogQueryPageDTO.getLkAction()), OperateLogDO::getAction, operateLogQueryPageDTO.getLkAction())
                .le(ObjectUtil.isNotEmpty(operateLogQueryPageDTO.getEndTime()), BaseDO::getCreateTime, operateLogQueryPageDTO.getEndTime())
                .ge(ObjectUtil.isNotEmpty(operateLogQueryPageDTO.getStartTime()), BaseDO::getCreateTime, operateLogQueryPageDTO.getStartTime())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(BaseDO::getCreateTime);

        IPage<OperateLogDO> operateLogPage = operateLogMapper.selectPage(page, queryWrapper);

        List<OperateLogVo> voList = operateLogConverter.toOperateLogVoList(operateLogPage.getRecords());

        return PageVo.<OperateLogVo>builder()
                .pageStart(operateLogQueryPageDTO.getPageStart())
                .pageSize(operateLogQueryPageDTO.getPageSize())
                .total(operateLogPage.getTotal())
                .data(voList)
                .build();

    }

}

