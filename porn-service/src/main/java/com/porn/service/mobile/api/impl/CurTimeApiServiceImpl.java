
package com.porn.service.mobile.api.impl;



import cn.hutool.core.date.LocalDateTimeUtil;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.CurTimeVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;















@Service
 public class CurTimeApiServiceImpl
         implements ApiService<CurTimeVo>
         {
    
    public CurTimeVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 21 */
        return CurTimeVo.builder()
/* 22 */.curDateTime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"))
/* 23 */.build();
        
    }

    
    
    public String getApi() {
        /* 27 */
        return "api_curtime";
        
    }
    
}


