package com.porn.client.message.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.message.dto.*;
import com.porn.client.message.vo.MessageVo;

import java.util.List;

public interface MessageApiService {
    MessageVo queryMessage(MessageQueryDTO paramMessageQueryDTO);

    List<MessageVo> queryMessageList(MessageQueryDTO paramMessageQueryDTO);

    PageVo<MessageVo> queryPage(MessageQueryPageDTO paramMessageQueryPageDTO);

    MessageVo saveOrUpdate(MessageSaveOrUpdateDTO paramMessageSaveOrUpdateDTO);

    Boolean batchDelete(MessageBatchDeleteDTO paramMessageBatchDeleteDTO);

    List<String> adminMsg(AdminMessageDTO paramAdminMessageDTO);
}

