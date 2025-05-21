package com.porn.service.goods.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.client.goods.dto.GoodsQueryPageDTO;
import com.porn.client.goods.vo.GoodsVo;
import com.porn.service.goods.dao.entity.GoodsDO;

import java.util.List;

public interface GoodsMapper extends BaseMapper<GoodsDO> {
    List<GoodsVo> groupRandGoodsList(GoodsQueryPageDTO paramGoodsQueryPageDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/goods/dao/mapper/GoodsMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */