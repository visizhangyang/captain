
package com.porn.service.autowork.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.autowork.api.AutoWorkApiService;
import com.porn.client.autowork.dto.AutoWorkQueryDTO;
import com.porn.client.autowork.dto.AutoWorkSaveOrUpdateDTO;
import com.porn.client.autowork.vo.AutoWorkVo;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.service.autowork.converter.AutoWorkConverter;
import com.porn.service.autowork.dao.entity.AutoWorkDO;
import com.porn.service.autowork.dao.mapper.AutoWorkMapper;
import com.porn.service.common.entity.BaseDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service

@Transactional(rollbackFor = {Exception.class})
 public class AutoWorkApiServiceImpl implements AutoWorkApiService {
    /* 27 */   private static final Logger log = LoggerFactory.getLogger(AutoWorkApiServiceImpl.class);



    @Autowired
     private AutoWorkConverter autoWorkConverter;



    @Autowired
     private AutoWorkMapper autoWorkMapper;

    public AutoWorkVo queryAutoWork(AutoWorkQueryDTO autoWorkQueryDTO) {
        /* 40 */
        List<AutoWorkVo> autoWorkVoList = queryAutoWorkList(autoWorkQueryDTO);
        /* 41 */
        return ObjectUtil.isEmpty(autoWorkVoList) ? null : autoWorkVoList.get(0);

    }

    public List<AutoWorkVo> queryAutoWorkList(AutoWorkQueryDTO autoWorkQueryDTO) {
        /* 49 */
        List<AutoWorkDO> autoWorkList = ChainWrappers.lambdaQueryChain(autoWorkMapper)
                .eq(ObjectUtil.isNotEmpty(autoWorkQueryDTO.getAccountId()), AutoWorkDO::getAccountId, autoWorkQueryDTO.getAccountId())
                .eq(ObjectUtil.isNotEmpty(autoWorkQueryDTO.getId()), BaseDO::getId, autoWorkQueryDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();

        if (CollUtil.isEmpty(autoWorkList)) {
            return Collections.emptyList();
        }

        return autoWorkConverter.toAutoWorkList(autoWorkList);


    }



    public AutoWorkVo saveOrUpdate(AutoWorkSaveOrUpdateDTO dto) {
        Long id = dto.getId();

        // 保存逻辑
        if (ObjectUtil.isEmpty(id)) {
            AutoWorkDO autoWorkDO = autoWorkConverter.toAutoWorkDO(dto);
            int insertResult = autoWorkMapper.insert(autoWorkDO);
            if (insertResult <= 0) {
                throw new BusinessException("保存搬砖规则信息失败.");
            }
            return queryAutoWorkById(autoWorkDO.getId());
        }

        // 更新逻辑
        boolean updated = ChainWrappers.lambdaUpdateChain(autoWorkMapper)
                .set(AutoWorkDO::getMinWorkCount, dto.getMinWorkCount())
                .set(AutoWorkDO::getMaxWorkCount, dto.getMaxWorkCount())
                .set(AutoWorkDO::getMinWorkAmount, dto.getMinWorkAmount())
                .set(AutoWorkDO::getMaxWorkAmount, dto.getMaxWorkAmount())
                .set(AutoWorkDO::getMinWorkSpace, dto.getMinWorkSpace())
                .set(AutoWorkDO::getMaxWorkSpace, dto.getMaxWorkSpace())
                .set(AutoWorkDO::getMinWorkTime, dto.getMinWorkTime())
                .set(AutoWorkDO::getMaxWorkTime, dto.getMaxWorkTime())
                .set(AutoWorkDO::getMinLoanTime, dto.getMinLoanTime())
                .set(AutoWorkDO::getMaxLoanTime, dto.getMaxLoanTime())
                .set(AutoWorkDO::getMinCompleteTime, dto.getMinCompleteTime())
                .set(AutoWorkDO::getMaxCompleteTime, dto.getMaxCompleteTime())
                .set(BaseDO::getModifyTime, LocalDateTimeUtil.now())
                .eq(BaseDO::getId, id)
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!updated) {
            throw new BusinessException("更新搬砖规则信息失败.");
        }

        return queryAutoWorkById(id);
    }

    private AutoWorkVo queryAutoWorkById(Long id) {
        return queryAutoWork(AutoWorkQueryDTO.builder().id(id).build());
    }

}

