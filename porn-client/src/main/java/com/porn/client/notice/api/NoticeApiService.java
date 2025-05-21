package com.porn.client.notice.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.notice.dto.*;
import com.porn.client.notice.vo.NoticeVo;

import java.util.List;

public interface NoticeApiService {
    NoticeVo queryNotice(NoticeQueryDTO paramNoticeQueryDTO);

    List<NoticeVo> queryNoticeList(NoticeQueryDTO paramNoticeQueryDTO);

    PageVo<NoticeVo> queryPage(NoticeQueryPageDTO paramNoticeQueryPageDTO);

    NoticeVo saveOrUpdate(NoticeSaveOrUpdateDTO paramNoticeSaveOrUpdateDTO);

    Boolean delete(NoticeDeleteDTO paramNoticeDeleteDTO);

    Boolean updateCreateTime(NoticeUpdateCreateTimeDTO paramNoticeUpdateCreateTimeDTO);

    List<NoticeVo> queryNoticeReadStatusList(NoticeQueryReadStatusDTO paramNoticeQueryReadStatusDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/notice/api/NoticeApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */