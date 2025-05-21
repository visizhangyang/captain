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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/notice/converter/NoticeConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */