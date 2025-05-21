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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/reward/converter/RewardRuleConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */