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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/merchant/api/MerchantApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */