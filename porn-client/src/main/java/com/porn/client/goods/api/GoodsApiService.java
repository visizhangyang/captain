package com.porn.client.goods.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.goods.dto.*;
import com.porn.client.goods.vo.GoodsVo;

import java.util.List;

public interface GoodsApiService {
    GoodsVo queryGoods(GoodsQueryDTO paramGoodsQueryDTO);

    List<GoodsVo> queryGoodsList(GoodsQueryDTO paramGoodsQueryDTO);

    PageVo<GoodsVo> queryPage(GoodsQueryPageDTO paramGoodsQueryPageDTO);

    GoodsVo saveOrUpdate(GoodsSaveOrUpdateDTO paramGoodsSaveOrUpdateDTO);

    Boolean batchCreate(GoodsBatchSaveDTO paramGoodsBatchSaveDTO);

    Boolean delete(GoodsDeleteDTO paramGoodsDeleteDTO);

    Integer queryGoodsCount(GoodsQueryCountDTO paramGoodsQueryCountDTO);

    List<GoodsVo> groupRandGoodsList(GoodsQueryPageDTO paramGoodsQueryPageDTO);
}
