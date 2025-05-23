package com.porn.service.reward.converter;

import com.porn.client.reward.dto.RewardRuleSaveOrUpdateDTO;
import com.porn.client.reward.dto.TurntableSaveOrUpdateDTO;
import com.porn.client.reward.vo.RewardRuleVo;
import com.porn.client.reward.vo.TurntableVo;
import com.porn.service.reward.dao.entity.RewardRuleDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RewardRuleConverter {
    RewardRuleVo toRewardRuleVo(RewardRuleDO paramRewardRuleDO);

    List<RewardRuleVo> toRewardRuleVoList(List<RewardRuleDO> paramList);

    RewardRuleDO toRewardRuleDO(RewardRuleSaveOrUpdateDTO paramRewardRuleSaveOrUpdateDTO);

    TurntableVo toTurntableVo(TurntableSaveOrUpdateDTO paramTurntableSaveOrUpdateDTO);
}

