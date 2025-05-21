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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/stream/converter/StreamConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */