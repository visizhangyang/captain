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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/notice/api/NoticeAccountApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */