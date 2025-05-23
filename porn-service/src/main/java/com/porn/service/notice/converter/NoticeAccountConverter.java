package com.porn.service.notice.converter;

import com.porn.client.notice.dto.NoticeAccountSaveOrUpdateDTO;
import com.porn.client.notice.vo.NoticeAccountVo;
import com.porn.service.notice.dao.entity.NoticeAccountDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoticeAccountConverter {
    NoticeAccountVo toNoticeAccountVo(NoticeAccountDO paramNoticeAccountDO);

    List<NoticeAccountVo> toNoticeAccountVoList(List<NoticeAccountDO> paramList);

    NoticeAccountDO toNoticeAccountDO(NoticeAccountSaveOrUpdateDTO paramNoticeAccountSaveOrUpdateDTO);
}

