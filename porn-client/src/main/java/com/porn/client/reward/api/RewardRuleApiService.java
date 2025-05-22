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


