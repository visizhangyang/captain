package com.porn.client.reward.api;

import com.porn.client.reward.dto.QueryRewardRecordDTO;
import com.porn.client.reward.dto.RewardRecordSaveOrUpdateDTO;
import com.porn.client.reward.vo.RewardRecordVo;

import java.util.List;

public interface RewardRecordApiService {
    RewardRecordVo queryRewardRecord(QueryRewardRecordDTO paramQueryRewardRecordDTO);

    List<RewardRecordVo> queryRewardRecordList(QueryRewardRecordDTO paramQueryRewardRecordDTO);

    RewardRecordVo saveOrUpdate(RewardRecordSaveOrUpdateDTO paramRewardRecordSaveOrUpdateDTO);
}


