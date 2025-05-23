package com.porn.service.reward.converter;

import com.porn.client.reward.vo.RewardBalanceVo;
import com.porn.service.reward.dao.entity.RewardBalanceDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RewardBalanceConverter {
    RewardBalanceVo toRewardBalanceVo(RewardBalanceDO paramRewardBalanceDO);

    List<RewardBalanceVo> toRewardBalanceVoList(List<RewardBalanceDO> paramList);
}

