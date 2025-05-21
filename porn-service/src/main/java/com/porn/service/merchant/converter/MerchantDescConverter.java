package com.porn.service.merchant.converter;

import com.porn.client.merchant.dto.MerchantDescSaveOrUpdateDTO;
import com.porn.client.merchant.vo.MerchantDescVo;
import com.porn.service.merchant.dao.entity.MerchantDescDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MerchantDescConverter {
    MerchantDescVo toMerchantDescVo(MerchantDescDO paramMerchantDescDO);

    List<MerchantDescVo> toMerchantDescVoList(List<MerchantDescDO> paramList);

    MerchantDescDO toMerchantDescDO(MerchantDescSaveOrUpdateDTO paramMerchantDescSaveOrUpdateDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/merchant/converter/MerchantDescConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */