package com.porn.client.reward.api;

import com.porn.client.reward.dto.OperateRewardBalanceDTO;
import com.porn.client.reward.dto.RewardBalanceQueryDTO;
import com.porn.client.reward.dto.RewardBalanceSaveOrUpdateDTO;
import com.porn.client.reward.vo.RewardBalanceVo;

import java.util.List;

public interface RewardBalanceApiService {
    RewardBalanceVo queryRewardBalance(RewardBalanceQueryDTO paramRewardBalanceQueryDTO);

    List<RewardBalanceVo> queryRewardBalanceList(RewardBalanceQueryDTO paramRewardBalanceQueryDTO);

    RewardBalanceVo saveOrUpdate(RewardBalanceSaveOrUpdateDTO paramRewardBalanceSaveOrUpdateDTO);

    RewardBalanceVo operateRewardBalance(OperateRewardBalanceDTO paramOperateRewardBalanceDTO);
}

