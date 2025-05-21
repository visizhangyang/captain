package com.porn.service.merchant.converter;

import com.porn.client.merchant.dto.MerchantSaveOrUpdateDTO;
import com.porn.client.merchant.vo.MerchantVo;
import com.porn.service.merchant.dao.entity.MerchantDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MerchantConverter {
    MerchantVo toMerchantVo(MerchantDO paramMerchantDO);

    List<MerchantVo> toMerchantVoList(List<MerchantDO> paramList);

    MerchantDO toMerchantDO(MerchantSaveOrUpdateDTO paramMerchantSaveOrUpdateDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/merchant/converter/MerchantConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */