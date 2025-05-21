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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/notice/converter/NoticeAccountConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */