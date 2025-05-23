package com.porn.client.log.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.log.dto.OperateLogQueryPageDTO;
import com.porn.client.log.dto.OperateLogSaveDTO;
import com.porn.client.log.vo.OperateLogVo;

public interface OperateLogApiService {
    boolean save(OperateLogSaveDTO paramOperateLogSaveDTO);

    PageVo<OperateLogVo> queryPage(OperateLogQueryPageDTO paramOperateLogQueryPageDTO);
}

