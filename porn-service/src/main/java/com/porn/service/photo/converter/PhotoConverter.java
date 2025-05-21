package com.porn.service.photo.converter;

import com.porn.client.photo.vo.PhotoVo;
import com.porn.service.photo.dao.entity.PhotoDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhotoConverter {
    PhotoVo toPhotoVo(PhotoDO paramPhotoDO);

    List<PhotoVo> toPhotoVoList(List<PhotoDO> paramList);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/photo/converter/PhotoConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */