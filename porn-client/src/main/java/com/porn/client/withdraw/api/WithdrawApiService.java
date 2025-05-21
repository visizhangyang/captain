package com.porn.client.withdraw.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.withdraw.dto.*;
import com.porn.client.withdraw.vo.WithdrawVo;

import java.util.List;

public interface WithdrawApiService {
    WithdrawVo queryWithdraw(WithdrawQueryDTO paramWithdrawQueryDTO);

    List<WithdrawVo> queryWithdrawList(WithdrawQueryDTO paramWithdrawQueryDTO);

    PageVo<WithdrawVo> queryPage(WithdrawQueryPageDTO paramWithdrawQueryPageDTO);

    PageVo<WithdrawVo> queryProxyPage(ProxyWithdrawQueryPageDTO paramProxyWithdrawQueryPageDTO);

    WithdrawVo pass(WithdrawPassDTO paramWithdrawPassDTO);

    Boolean cancel(WithdrawCancelDTO paramWithdrawCancelDTO);

    Boolean delete(WithdrawDeleteDTO paramWithdrawDeleteDTO);

    WithdrawVo saveOrUpdate(WithdrawSaveOrUpdateDTO paramWithdrawSaveOrUpdateDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/withdraw/api/WithdrawApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */