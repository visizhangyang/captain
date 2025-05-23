package com.porn.client.notice.api;

import com.porn.client.notice.dto.NoticeAccountBatchSaveDTO;
import com.porn.client.notice.dto.NoticeAccountQueryDTO;
import com.porn.client.notice.dto.NoticeAccountSaveOrUpdateDTO;
import com.porn.client.notice.vo.NoticeAccountVo;

import java.util.List;

public interface NoticeAccountApiService {
    NoticeAccountVo queryNoticeAccount(NoticeAccountQueryDTO paramNoticeAccountQueryDTO);

    List<NoticeAccountVo> queryNoticeAccountList(NoticeAccountQueryDTO paramNoticeAccountQueryDTO);

    Boolean batchSave(NoticeAccountBatchSaveDTO paramNoticeAccountBatchSaveDTO);

    Boolean saveOrUpdate(NoticeAccountSaveOrUpdateDTO paramNoticeAccountSaveOrUpdateDTO);
}

