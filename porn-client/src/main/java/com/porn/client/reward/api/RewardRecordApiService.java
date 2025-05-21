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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/api/RewardRecordApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */