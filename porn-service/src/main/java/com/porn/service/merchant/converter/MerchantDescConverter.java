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

