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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/reward/converter/RewardRecordConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */