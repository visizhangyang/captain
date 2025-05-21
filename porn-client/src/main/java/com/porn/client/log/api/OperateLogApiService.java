package com.porn.client.log.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.log.dto.OperateLogQueryPageDTO;
import com.porn.client.log.dto.OperateLogSaveDTO;
import com.porn.client.log.vo.OperateLogVo;

public interface OperateLogApiService {
    boolean save(OperateLogSaveDTO paramOperateLogSaveDTO);

    PageVo<OperateLogVo> queryPage(OperateLogQueryPageDTO paramOperateLogQueryPageDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/log/api/OperateLogApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */