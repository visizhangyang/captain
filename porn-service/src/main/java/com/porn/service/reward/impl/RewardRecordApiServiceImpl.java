package com.porn.service.reward.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.reward.api.RewardRecordApiService;
import com.porn.client.reward.dto.QueryRewardRecordDTO;
import com.porn.client.reward.dto.RewardRecordSaveOrUpdateDTO;
import com.porn.client.reward.vo.RewardRecordVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.reward.converter.RewardRecordConverter;
import com.porn.service.reward.dao.entity.RewardRecordDO;
import com.porn.service.reward.dao.mapper.RewardRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service

@Transactional(rollbackFor = {Exception.class})
public class RewardRecordApiServiceImpl implements RewardRecordApiService {
    private static final Logger log = LoggerFactory.getLogger(RewardRecordApiServiceImpl.class);


    @Autowired
    private RewardRecordMapper rewardRecordMapper;


    @Autowired
    private RewardRecordConverter rewardRecordConverter;


    public RewardRecordVo queryRewardRecord(QueryRewardRecordDTO queryRewardRecordDTO) {

        List<RewardRecordVo> rewardRecordVoList = queryRewardRecordList(queryRewardRecordDTO);

        return ObjectUtil.isEmpty(rewardRecordVoList) ? null : rewardRecordVoList.get(0);

    }

    public List<RewardRecordVo> queryRewardRecordList(QueryRewardRecordDTO queryRewardRecordDTO) {

        List<RewardRecordDO> rewardRecordList = ChainWrappers.lambdaQueryChain(rewardRecordMapper)
                .eq(ObjectUtil.isNotEmpty(queryRewardRecordDTO.getId()), BaseDO::getId, queryRewardRecordDTO.getId())
                .eq(ObjectUtil.isNotEmpty(queryRewardRecordDTO.getAccountId()), RewardRecordDO::getAccountId, queryRewardRecordDTO.getAccountId())
                .eq(ObjectUtil.isNotEmpty(queryRewardRecordDTO.getType()), RewardRecordDO::getType, queryRewardRecordDTO.getType())
                .eq(ObjectUtil.isNotEmpty(queryRewardRecordDTO.getBizId()), RewardRecordDO::getBizId, queryRewardRecordDTO.getBizId())
                .eq(ObjectUtil.isNotEmpty(queryRewardRecordDTO.getBizType()), RewardRecordDO::getBizType, queryRewardRecordDTO.getBizType())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();

        if (ObjectUtil.isEmpty(rewardRecordList)) {

            return Collections.emptyList();

        }

        List<RewardRecordVo> result = this.rewardRecordConverter.toRewardRecordVoList(rewardRecordList);

        return result;

    }


    public RewardRecordVo saveOrUpdate(RewardRecordSaveOrUpdateDTO rewardRecordSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(rewardRecordSaveOrUpdateDTO.getId())) {

            RewardRecordDO rewardRecordDO = this.rewardRecordConverter.toRewardRecordDO(rewardRecordSaveOrUpdateDTO);

            if (this.rewardRecordMapper.insert(rewardRecordDO) <= 0) {

                throw new BusinessException("保存抽奖流水信息失败.");

            }

            return queryRewardRecord(((QueryRewardRecordDTO.QueryRewardRecordDTOBuilder) QueryRewardRecordDTO.builder().id(rewardRecordDO.getId())).build());

        }

        boolean rs = ChainWrappers.lambdaUpdateChain(rewardRecordMapper)
                .set(RewardRecordDO::getAccountId, rewardRecordSaveOrUpdateDTO.getAccountId())
                .set(RewardRecordDO::getBeforeAvailableCount, rewardRecordSaveOrUpdateDTO.getBeforeAvailableCount())
                .set(RewardRecordDO::getAfterAvailableCount, rewardRecordSaveOrUpdateDTO.getAfterAvailableCount())
                .set(RewardRecordDO::getOperateCount, rewardRecordSaveOrUpdateDTO.getOperateCount())
                .set(RewardRecordDO::getType, rewardRecordSaveOrUpdateDTO.getType())
                .set(RewardRecordDO::getBizType, rewardRecordSaveOrUpdateDTO.getBizType())
                .set(RewardRecordDO::getBizId, rewardRecordSaveOrUpdateDTO.getBizId())
                .eq(BaseDO::getId, rewardRecordSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新抽奖流水信息失败.");

        }

        return queryRewardRecord(((QueryRewardRecordDTO.QueryRewardRecordDTOBuilder) QueryRewardRecordDTO.builder().id(rewardRecordSaveOrUpdateDTO.getId())).build());

    }

}
