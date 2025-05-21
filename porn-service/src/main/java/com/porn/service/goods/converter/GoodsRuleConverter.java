package com.porn.service.goods.converter;

import com.porn.client.goods.dto.GoodsRuleSaveOrUpdateDTO;
import com.porn.client.goods.vo.GoodsRuleVo;
import com.porn.service.goods.dao.entity.GoodsRuleDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GoodsRuleConverter {
    GoodsRuleVo toGoodsRuleVo(GoodsRuleDO paramGoodsRuleDO);

    List<GoodsRuleVo> toGoodsRuleVoList(List<GoodsRuleDO> paramList);

    GoodsRuleDO toGoodsRuleDO(GoodsRuleSaveOrUpdateDTO paramGoodsRuleSaveOrUpdateDTO);
}
