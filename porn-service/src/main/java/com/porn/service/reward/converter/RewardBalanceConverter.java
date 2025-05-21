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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/reward/converter/RewardBalanceConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */