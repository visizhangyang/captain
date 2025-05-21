package com.porn.client.log.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.log.dto.LoginLogQueryPageDTO;
import com.porn.client.log.dto.LoginLogSaveDTO;
import com.porn.client.log.vo.LoginLogVo;

public interface LoginLogApiService {
    boolean save(LoginLogSaveDTO paramLoginLogSaveDTO);

    PageVo<LoginLogVo> queryPage(LoginLogQueryPageDTO paramLoginLogQueryPageDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/log/api/LoginLogApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */