package com.porn.service.imglib.converter;

import com.porn.client.imglib.vo.ImageLibVo;
import com.porn.service.imglib.dao.entity.ImageLibDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageLibConverter {
    ImageLibVo toImageLibVo(ImageLibDO paramImageLibDO);

    List<ImageLibVo> toImageLibVoList(List<ImageLibDO> paramList);
}


