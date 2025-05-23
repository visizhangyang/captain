package com.porn.service.notice.converter;

import com.porn.client.notice.dto.NoticeSaveOrUpdateDTO;
import com.porn.client.notice.vo.NoticeVo;
import com.porn.service.notice.dao.entity.NoticeDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoticeConverter {
    NoticeVo toNoticeVo(NoticeDO paramNoticeDO);

    List<NoticeVo> toNoticeVoList(List<NoticeDO> paramList);

    NoticeDO toNoticeDO(NoticeSaveOrUpdateDTO paramNoticeSaveOrUpdateDTO);
}

