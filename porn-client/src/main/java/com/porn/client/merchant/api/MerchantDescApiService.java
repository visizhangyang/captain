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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/merchant/api/MerchantDescApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */