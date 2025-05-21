package com.porn.service.goods.converter;

import com.porn.client.goods.dto.GoodsSaveOrUpdateDTO;
import com.porn.client.goods.vo.GoodsVo;
import com.porn.service.goods.dao.entity.GoodsDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GoodsConverter {
    GoodsVo toGoodsVo(GoodsDO paramGoodsDO);

    List<GoodsVo> toGoodsVoList(List<GoodsDO> paramList);

    GoodsDO toGoodsDO(GoodsSaveOrUpdateDTO paramGoodsSaveOrUpdateDTO);
}

