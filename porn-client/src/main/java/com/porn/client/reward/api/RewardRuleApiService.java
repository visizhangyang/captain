package com.porn.client.reward.api;

import com.porn.client.reward.dto.RewardRuleQueryDTO;
import com.porn.client.reward.dto.RewardRuleSaveOrUpdateDTO;
import com.porn.client.reward.dto.TurntableQueryDTO;
import com.porn.client.reward.dto.TurntableSaveOrUpdateDTO;
import com.porn.client.reward.vo.RewardRuleVo;
import com.porn.client.reward.vo.TurntableVo;

import java.util.List;

public interface RewardRuleApiService {
    RewardRuleVo queryRewardRule(RewardRuleQueryDTO paramRewardRuleQueryDTO);

    List<RewardRuleVo> queryRewardRuleList(RewardRuleQueryDTO paramRewardRuleQueryDTO);

    RewardRuleVo saveOrUpdate(RewardRuleSaveOrUpdateDTO paramRewardRuleSaveOrUpdateDTO);

    TurntableVo queryTurntable(TurntableQueryDTO paramTurntableQueryDTO);

    TurntableVo saveOrUpdateTurntable(TurntableSaveOrUpdateDTO paramTurntableSaveOrUpdateDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/api/RewardRuleApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */