package com.porn.service.notice.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.client.notice.dto.NoticeQueryReadStatusDTO;
import com.porn.client.notice.vo.NoticeVo;
import com.porn.service.notice.dao.entity.NoticeDO;

import java.util.List;

public interface NoticeMapper extends BaseMapper<NoticeDO> {
    List<NoticeVo> queryNoticeReadStatusList(NoticeQueryReadStatusDTO paramNoticeQueryReadStatusDTO);
}

