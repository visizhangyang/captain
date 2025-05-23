package com.porn.service.reward.converter;

import com.porn.client.reward.dto.RewardRecordSaveOrUpdateDTO;
import com.porn.client.reward.vo.RewardRecordVo;
import com.porn.service.reward.dao.entity.RewardRecordDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RewardRecordConverter {
    RewardRecordVo toRewardRecordVo(RewardRecordDO paramRewardRecordDO);

    List<RewardRecordVo> toRewardRecordVoList(List<RewardRecordDO> paramList);

    RewardRecordDO toRewardRecordDO(RewardRecordSaveOrUpdateDTO paramRewardRecordSaveOrUpdateDTO);
}

