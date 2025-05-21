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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/imglib/converter/ImageLibConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */