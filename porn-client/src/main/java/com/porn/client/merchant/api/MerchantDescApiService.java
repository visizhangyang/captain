package com.porn.client.merchant.api;

import com.porn.client.merchant.dto.MerchantDescQueryDTO;
import com.porn.client.merchant.dto.MerchantDescSaveOrUpdateDTO;
import com.porn.client.merchant.vo.MerchantDescVo;

import java.util.List;

public interface MerchantDescApiService {
    MerchantDescVo queryMerchantDesc(MerchantDescQueryDTO paramMerchantDescQueryDTO);

    List<MerchantDescVo> queryMerchantDescList(MerchantDescQueryDTO paramMerchantDescQueryDTO);

    MerchantDescVo saveOrUpdate(MerchantDescSaveOrUpdateDTO paramMerchantDescSaveOrUpdateDTO);
}


