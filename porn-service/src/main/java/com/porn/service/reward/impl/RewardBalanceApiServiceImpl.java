package com.porn.service.reward.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
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
    private static final Logger log = LoggerFactory.getLogger(RewardBalanceApiServiceImpl.class);


    @Autowired
    private RewardBalanceMapper rewardBalanceMapper;


    @Autowired
    private RewardBalanceConverter rewardBalanceConverter;


    @Autowired
    private RewardRecordApiService rewardRecordApiService;

    public RewardBalanceVo queryRewardBalance(RewardBalanceQueryDTO rewardBalanceQueryDTO) {

        List<RewardBalanceVo> rewardBalanceVoList = queryRewardBalanceList(rewardBalanceQueryDTO);

        return ObjectUtil.isEmpty(rewardBalanceVoList) ? null : rewardBalanceVoList.get(0);

    }


    public List<RewardBalanceVo> queryRewardBalanceList(RewardBalanceQueryDTO rewardBalanceQueryDTO) {

        List<RewardBalanceDO> rewardBalanceList = ChainWrappers.lambdaQueryChain(rewardBalanceMapper)
                .eq(ObjectUtil.isNotEmpty(rewardBalanceQueryDTO.getId()), BaseDO::getId, rewardBalanceQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(rewardBalanceQueryDTO.getAccountId()), RewardBalanceDO::getAccountId, rewardBalanceQueryDTO.getAccountId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();

        if (ObjectUtil.isEmpty(rewardBalanceList)) {

            return Collections.emptyList();

        }

        List<RewardBalanceVo> rewardBalanceVoList = this.rewardBalanceConverter.toRewardBalanceVoList(rewardBalanceList);

        return rewardBalanceVoList;

    }


    public RewardBalanceVo saveOrUpdate(RewardBalanceSaveOrUpdateDTO rewardBalanceSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(rewardBalanceSaveOrUpdateDTO.getId())) {


            RewardBalanceDO rewardBalanceDO = RewardBalanceDO.builder().accountId(rewardBalanceSaveOrUpdateDTO.getAccountId()).availableCount(BigDecimal.ZERO).build();

            if (this.rewardBalanceMapper.insert(rewardBalanceDO) <= 0) {

                throw new BusinessException("保存抽奖信息失败.");

            }

            return queryRewardBalance(((RewardBalanceQueryDTO.RewardBalanceQueryDTOBuilder) RewardBalanceQueryDTO.builder().id(rewardBalanceDO.getId())).build());

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(rewardBalanceMapper)
                .set(RewardBalanceDO::getAccountId, rewardBalanceSaveOrUpdateDTO.getAccountId())
                .set(RewardBalanceDO::getAvailableCount, rewardBalanceSaveOrUpdateDTO.getAvailableCount())
                .eq(BaseDO::getId, rewardBalanceSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新抽奖信息失败.");

        }

        return queryRewardBalance(((RewardBalanceQueryDTO.RewardBalanceQueryDTOBuilder) RewardBalanceQueryDTO.builder().id(rewardBalanceSaveOrUpdateDTO.getId())).build());

    }

    public RewardBalanceVo operateRewardBalance(OperateRewardBalanceDTO operateRewardBalanceDTO) {

        RewardBalanceQueryDTO rewardBalanceQueryDTO = ((RewardBalanceQueryDTO.RewardBalanceQueryDTOBuilder) RewardBalanceQueryDTO.builder().id(operateRewardBalanceDTO.getId())).accountId(operateRewardBalanceDTO.getAccountId()).build();

        RewardBalanceVo rewardBalanceVo = queryRewardBalance(rewardBalanceQueryDTO);

        if (ObjectUtil.isEmpty(rewardBalanceVo)) {


            RewardBalanceSaveOrUpdateDTO rewardBalanceSaveOrUpdateDTO1 = RewardBalanceSaveOrUpdateDTO.builder().accountId(operateRewardBalanceDTO.getAccountId()).availableCount(BigDecimal.ZERO).build();

            rewardBalanceVo = saveOrUpdate(rewardBalanceSaveOrUpdateDTO1);

        }

        BigDecimal beforeCount = rewardBalanceVo.getAvailableCount();

        BigDecimal afterCount = BigDecimal.ZERO;

        if (RewardRecordTypeEnum.ADD.getType().equals(operateRewardBalanceDTO.getType())) {

            afterCount = NumberUtil.add(beforeCount, operateRewardBalanceDTO.getOperateCount());

        } else if (RewardRecordTypeEnum.SUB.getType().equals(operateRewardBalanceDTO.getType())) {


            if (beforeCount.compareTo(operateRewardBalanceDTO.getOperateCount()) < 0) {

                throw new BusinessException("可转次数不够.");

            }

            afterCount = NumberUtil.sub(beforeCount, operateRewardBalanceDTO.getOperateCount());

        }


        RewardBalanceSaveOrUpdateDTO rewardBalanceSaveOrUpdateDTO = ((RewardBalanceSaveOrUpdateDTO.RewardBalanceSaveOrUpdateDTOBuilder) RewardBalanceSaveOrUpdateDTO.builder().id(rewardBalanceVo.getId())).availableCount(afterCount).accountId(rewardBalanceVo.getAccountId()).build();

        saveOrUpdate(rewardBalanceSaveOrUpdateDTO);


        RewardRecordSaveOrUpdateDTO rewardRecordSaveOrUpdateDTO = RewardRecordSaveOrUpdateDTO.builder().accountId(operateRewardBalanceDTO.getAccountId()).beforeAvailableCount(beforeCount).afterAvailableCount(afterCount).operateCount(operateRewardBalanceDTO.getOperateCount()).type(operateRewardBalanceDTO.getType()).bizType(operateRewardBalanceDTO.getBizType()).bizId(operateRewardBalanceDTO.getBizId()).build();

        this.rewardRecordApiService.saveOrUpdate(rewardRecordSaveOrUpdateDTO);


        return queryRewardBalance(((RewardBalanceQueryDTO.RewardBalanceQueryDTOBuilder) RewardBalanceQueryDTO.builder().id(rewardBalanceVo.getId())).build());

    }

}