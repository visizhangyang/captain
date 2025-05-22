package com.porn.client.log.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.log.dto.LoginLogQueryPageDTO;
import com.porn.client.log.dto.LoginLogSaveDTO;
import com.porn.client.log.vo.LoginLogVo;

public interface LoginLogApiService {
    boolean save(LoginLogSaveDTO paramLoginLogSaveDTO);

    PageVo<LoginLogVo> queryPage(LoginLogQueryPageDTO paramLoginLogQueryPageDTO);
}


