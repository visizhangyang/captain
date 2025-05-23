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

