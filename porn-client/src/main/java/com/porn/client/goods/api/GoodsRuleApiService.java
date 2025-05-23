package com.porn.client.goods.api;

import com.porn.client.goods.dto.GoodsRuleQueryDTO;
import com.porn.client.goods.dto.GoodsRuleSaveOrUpdateDTO;
import com.porn.client.goods.vo.GoodsRuleVo;

import java.util.List;

public interface GoodsRuleApiService {
    GoodsRuleVo queryGoodsRule(GoodsRuleQueryDTO paramGoodsRuleQueryDTO);

    List<GoodsRuleVo> queryGoodsRuleList(GoodsRuleQueryDTO paramGoodsRuleQueryDTO);

    GoodsRuleVo saveOrUpdate(GoodsRuleSaveOrUpdateDTO paramGoodsRuleSaveOrUpdateDTO);
}

