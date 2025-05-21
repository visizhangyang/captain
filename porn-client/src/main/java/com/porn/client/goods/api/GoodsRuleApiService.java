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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/goods/api/GoodsRuleApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */