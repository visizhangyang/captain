package com.porn.service.goods.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.client.goods.dto.GoodsQueryPageDTO;
import com.porn.client.goods.vo.GoodsVo;
import com.porn.service.goods.dao.entity.GoodsDO;

import java.util.List;

public interface GoodsMapper extends BaseMapper<GoodsDO> {
    List<GoodsVo> groupRandGoodsList(GoodsQueryPageDTO paramGoodsQueryPageDTO);
}

