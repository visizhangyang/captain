package com.porn.service.stream.converter;

import com.porn.client.stream.dto.StreamSaveOrUpdateDTO;
import com.porn.client.stream.vo.StreamVo;
import com.porn.service.stream.dao.entity.StreamDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StreamConverter {
    StreamVo toStreamVo(StreamDO paramStreamDO);

    List<StreamVo> toStreamVoList(List<StreamDO> paramList);

    StreamDO toStreamDO(StreamSaveOrUpdateDTO paramStreamSaveOrUpdateDTO);
}


