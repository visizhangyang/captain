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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/api/RewardBalanceApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */