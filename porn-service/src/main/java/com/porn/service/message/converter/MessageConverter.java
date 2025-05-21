package com.porn.service.message.converter;

import com.porn.client.message.dto.MessageSaveOrUpdateDTO;
import com.porn.client.message.vo.MessageVo;
import com.porn.service.message.dao.entity.MessageDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageConverter {
    MessageVo toMessageVo(MessageDO paramMessageDO);

    List<MessageVo> toMessageVoList(List<MessageDO> paramList);

    MessageDO toMessageDO(MessageSaveOrUpdateDTO paramMessageSaveOrUpdateDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/message/converter/MessageConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */