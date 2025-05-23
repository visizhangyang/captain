package com.porn.client.merchant.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.merchant.dto.*;
import com.porn.client.merchant.vo.MerchantVo;

import java.util.List;

public interface MerchantApiService {
    MerchantVo queryMerchant(MerchantQueryDTO paramMerchantQueryDTO);

    List<MerchantVo> queryMerchantList(MerchantQueryDTO paramMerchantQueryDTO);

    PageVo<MerchantVo> queryPage(MerchantQueryPageDTO paramMerchantQueryPageDTO);

    Boolean enableOrDisable(MerchantEnableOrDisableDTO paramMerchantEnableOrDisableDTO);

    MerchantVo saveOrUpdate(MerchantSaveOrUpdateDTO paramMerchantSaveOrUpdateDTO);

    Boolean delete(MerchantDeleteDTO paramMerchantDeleteDTO);

    Boolean createRobot(MerchantRobotCreateDTO paramMerchantRobotCreateDTO);

    Boolean confirmOrder(MerchantConfirmOrderDTO paramMerchantConfirmOrderDTO);
}

