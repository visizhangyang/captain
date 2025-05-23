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

