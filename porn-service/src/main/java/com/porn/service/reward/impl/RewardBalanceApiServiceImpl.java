
package com.porn.service.reward.impl;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.reward.api.RewardBalanceApiService;
import com.porn.client.reward.api.RewardRecordApiService;
import com.porn.client.reward.dto.OperateRewardBalanceDTO;
import com.porn.client.reward.dto.RewardBalanceQueryDTO;
import com.porn.client.reward.dto.RewardBalanceSaveOrUpdateDTO;
import com.porn.client.reward.dto.RewardRecordSaveOrUpdateDTO;
import com.porn.client.reward.enums.RewardRecordTypeEnum;
import com.porn.client.reward.vo.RewardBalanceVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.reward.converter.RewardBalanceConverter;
import com.porn.service.reward.dao.entity.RewardBalanceDO;
import com.porn.service.reward.dao.mapper.RewardBalanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;





























@Service

@Transactional(rollbackFor = {Exception.class})
 public class RewardBalanceApiServiceImpl implements RewardBalanceApiService {
    /*  32 */   private static final Logger log = LoggerFactory.getLogger(RewardBalanceApiServiceImpl.class);



    @Autowired
     private RewardBalanceMapper rewardBalanceMapper;



    @Autowired
     private RewardBalanceConverter rewardBalanceConverter;



    @Autowired
     private RewardRecordApiService rewardRecordApiService;




    public RewardBalanceVo queryRewardBalance(RewardBalanceQueryDTO rewardBalanceQueryDTO) {
        /*  48 */
        List<RewardBalanceVo> rewardBalanceVoList = queryRewardBalanceList(rewardBalanceQueryDTO);
        /*  49 */
        return ObjectUtil.isEmpty(rewardBalanceVoList) ? null : rewardBalanceVoList.get(0);

    }







    public List<RewardBalanceVo> queryRewardBalanceList(RewardBalanceQueryDTO rewardBalanceQueryDTO) {
        /*  57 */
        List<RewardBalanceDO> rewardBalanceList =  ChainWrappers.lambdaQueryChain(rewardBalanceMapper)
                .eq(ObjectUtil.isNotEmpty(rewardBalanceQueryDTO.getId()), BaseDO::getId, rewardBalanceQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(rewardBalanceQueryDTO.getAccountId()), RewardBalanceDO::getAccountId, rewardBalanceQueryDTO.getAccountId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();
        /*  58 */
        if (ObjectUtil.isEmpty(rewardBalanceList)) {
            /*  59 */
            return Collections.emptyList();

        }
        /*  61 */
        List<RewardBalanceVo> rewardBalanceVoList = this.rewardBalanceConverter.toRewardBalanceVoList(rewardBalanceList);
        /*  62 */
        return rewardBalanceVoList;

    }



    public RewardBalanceVo saveOrUpdate(RewardBalanceSaveOrUpdateDTO rewardBalanceSaveOrUpdateDTO) {
        /*  66 */
        if (ObjectUtil.isEmpty(rewardBalanceSaveOrUpdateDTO.getId())) {




            /*  71 */
            RewardBalanceDO rewardBalanceDO = RewardBalanceDO.builder().accountId(rewardBalanceSaveOrUpdateDTO.getAccountId()).availableCount(BigDecimal.ZERO).build();
            /*  72 */
            if (this.rewardBalanceMapper.insert(rewardBalanceDO) <= 0) {
                /*  73 */
                throw new BusinessException("保存抽奖信息失败.");

            }
            /*  75 */
            return queryRewardBalance(((RewardBalanceQueryDTO.RewardBalanceQueryDTOBuilder) RewardBalanceQueryDTO.builder().id(rewardBalanceDO.getId())).build());

        }





        /*  82 */
        boolean rs =   ChainWrappers.lambdaUpdateChain(rewardBalanceMapper)
                .set(RewardBalanceDO::getAccountId, rewardBalanceSaveOrUpdateDTO.getAccountId())
                .set(RewardBalanceDO::getAvailableCount, rewardBalanceSaveOrUpdateDTO.getAvailableCount())
                .eq(BaseDO::getId, rewardBalanceSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /*  83 */
        if (!rs) {
            /*  84 */
            throw new BusinessException("更新抽奖信息失败.");

        }
        /*  86 */
        return queryRewardBalance(((RewardBalanceQueryDTO.RewardBalanceQueryDTOBuilder) RewardBalanceQueryDTO.builder().id(rewardBalanceSaveOrUpdateDTO.getId())).build());

    }








    public RewardBalanceVo operateRewardBalance(OperateRewardBalanceDTO operateRewardBalanceDTO) {
        /*  95 */
        RewardBalanceQueryDTO rewardBalanceQueryDTO = ((RewardBalanceQueryDTO.RewardBalanceQueryDTOBuilder) RewardBalanceQueryDTO.builder().id(operateRewardBalanceDTO.getId())).accountId(operateRewardBalanceDTO.getAccountId()).build();
        /*  96 */
        RewardBalanceVo rewardBalanceVo = queryRewardBalance(rewardBalanceQueryDTO);
        /*  97 */
        if (ObjectUtil.isEmpty(rewardBalanceVo)) {




            /* 102 */
            RewardBalanceSaveOrUpdateDTO rewardBalanceSaveOrUpdateDTO1 = RewardBalanceSaveOrUpdateDTO.builder().accountId(operateRewardBalanceDTO.getAccountId()).availableCount(BigDecimal.ZERO).build();
            /* 103 */
            rewardBalanceVo = saveOrUpdate(rewardBalanceSaveOrUpdateDTO1);

        }
        /* 105 */
        BigDecimal beforeCount = rewardBalanceVo.getAvailableCount();
        /* 106 */
        BigDecimal afterCount = BigDecimal.ZERO;
        /* 107 */
        if (RewardRecordTypeEnum.ADD.getType().equals(operateRewardBalanceDTO.getType())) {
            /* 108 */
            afterCount = NumberUtil.add(beforeCount, operateRewardBalanceDTO.getOperateCount());
            /* 109 */
        } else if (RewardRecordTypeEnum.SUB.getType().equals(operateRewardBalanceDTO.getType())) {

            /* 111 */
            if (beforeCount.compareTo(operateRewardBalanceDTO.getOperateCount()) < 0) {
                /* 112 */
                throw new BusinessException("可转次数不够.");

            }
            /* 114 */
            afterCount = NumberUtil.sub(beforeCount, operateRewardBalanceDTO.getOperateCount());

        }






        /* 122 */
        RewardBalanceSaveOrUpdateDTO rewardBalanceSaveOrUpdateDTO = ((RewardBalanceSaveOrUpdateDTO.RewardBalanceSaveOrUpdateDTOBuilder) RewardBalanceSaveOrUpdateDTO.builder().id(rewardBalanceVo.getId())).availableCount(afterCount).accountId(rewardBalanceVo.getAccountId()).build();
        /* 123 */
        saveOrUpdate(rewardBalanceSaveOrUpdateDTO);










        /* 134 */
        RewardRecordSaveOrUpdateDTO rewardRecordSaveOrUpdateDTO = RewardRecordSaveOrUpdateDTO.builder().accountId(operateRewardBalanceDTO.getAccountId()).beforeAvailableCount(beforeCount).afterAvailableCount(afterCount).operateCount(operateRewardBalanceDTO.getOperateCount()).type(operateRewardBalanceDTO.getType()).bizType(operateRewardBalanceDTO.getBizType()).bizId(operateRewardBalanceDTO.getBizId()).build();
        /* 135 */
        this.rewardRecordApiService.saveOrUpdate(rewardRecordSaveOrUpdateDTO);

        /* 137 */
        return queryRewardBalance(((RewardBalanceQueryDTO.RewardBalanceQueryDTOBuilder) RewardBalanceQueryDTO.builder().id(rewardBalanceVo.getId())).build());

    }

}