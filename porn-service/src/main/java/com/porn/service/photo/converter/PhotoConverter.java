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

