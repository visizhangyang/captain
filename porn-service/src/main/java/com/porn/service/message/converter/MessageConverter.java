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


