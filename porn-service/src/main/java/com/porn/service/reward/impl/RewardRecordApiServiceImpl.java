
package com.porn.service.reward.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
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
    /* 27 */   private static final Logger log = LoggerFactory.getLogger(RewardRecordApiServiceImpl.class);



    @Autowired
     private RewardRecordMapper rewardRecordMapper;



    @Autowired
     private RewardRecordConverter rewardRecordConverter;





    public RewardRecordVo queryRewardRecord(QueryRewardRecordDTO queryRewardRecordDTO) {
        /* 40 */
        List<RewardRecordVo> rewardRecordVoList = queryRewardRecordList(queryRewardRecordDTO);
        /* 41 */
        return ObjectUtil.isEmpty(rewardRecordVoList) ? null : rewardRecordVoList.get(0);

    }










    public List<RewardRecordVo> queryRewardRecordList(QueryRewardRecordDTO queryRewardRecordDTO) {
        /* 52 */
        List<RewardRecordDO> rewardRecordList =  ChainWrappers.lambdaQueryChain(rewardRecordMapper)
                .eq(ObjectUtil.isNotEmpty(queryRewardRecordDTO.getId()), BaseDO::getId, queryRewardRecordDTO.getId())
        .eq(ObjectUtil.isNotEmpty(queryRewardRecordDTO.getAccountId()), RewardRecordDO::getAccountId, queryRewardRecordDTO.getAccountId())
                .eq(ObjectUtil.isNotEmpty(queryRewardRecordDTO.getType()), RewardRecordDO::getType, queryRewardRecordDTO.getType())
                .eq(ObjectUtil.isNotEmpty(queryRewardRecordDTO.getBizId()), RewardRecordDO::getBizId, queryRewardRecordDTO.getBizId())
                .eq(ObjectUtil.isNotEmpty(queryRewardRecordDTO.getBizType()), RewardRecordDO::getBizType, queryRewardRecordDTO.getBizType())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();
        /* 53 */
        if (ObjectUtil.isEmpty(rewardRecordList)) {
            /* 54 */
            return Collections.emptyList();

        }
        /* 56 */
        List<RewardRecordVo> result = this.rewardRecordConverter.toRewardRecordVoList(rewardRecordList);
        /* 57 */
        return result;

    }



    public RewardRecordVo saveOrUpdate(RewardRecordSaveOrUpdateDTO rewardRecordSaveOrUpdateDTO) {
        /* 61 */
        if (ObjectUtil.isEmpty(rewardRecordSaveOrUpdateDTO.getId())) {

            /* 63 */
            RewardRecordDO rewardRecordDO = this.rewardRecordConverter.toRewardRecordDO(rewardRecordSaveOrUpdateDTO);
            /* 64 */
            if (this.rewardRecordMapper.insert(rewardRecordDO) <= 0) {
                /* 65 */
                throw new BusinessException("保存抽奖流水信息失败.");

            }
            /* 67 */
            return queryRewardRecord(((QueryRewardRecordDTO.QueryRewardRecordDTOBuilder) QueryRewardRecordDTO.builder().id(rewardRecordDO.getId())).build());

        }











        /* 80 */
        boolean rs =  ChainWrappers.lambdaUpdateChain(rewardRecordMapper)
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
        /* 81 */
        if (!rs) {
            /* 82 */
            throw new BusinessException("更新抽奖流水信息失败.");

        }
        /* 84 */
        return queryRewardRecord(((QueryRewardRecordDTO.QueryRewardRecordDTOBuilder) QueryRewardRecordDTO.builder().id(rewardRecordSaveOrUpdateDTO.getId())).build());

    }

}
