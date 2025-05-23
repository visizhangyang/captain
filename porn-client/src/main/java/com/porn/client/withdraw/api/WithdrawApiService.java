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

