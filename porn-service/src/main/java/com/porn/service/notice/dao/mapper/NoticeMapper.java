package com.porn.service.notice.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.client.notice.dto.NoticeQueryReadStatusDTO;
import com.porn.client.notice.vo.NoticeVo;
import com.porn.service.notice.dao.entity.NoticeDO;

import java.util.List;

public interface NoticeMapper extends BaseMapper<NoticeDO> {
    List<NoticeVo> queryNoticeReadStatusList(NoticeQueryReadStatusDTO paramNoticeQueryReadStatusDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/notice/dao/mapper/NoticeMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */