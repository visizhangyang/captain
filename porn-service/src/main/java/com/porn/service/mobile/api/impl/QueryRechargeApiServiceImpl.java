
package com.porn.service.mobile.api.impl;



import cn.hutool.core.date.LocalDateTimeUtil;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.recharge.api.RechargeApiService;
import com.porn.client.recharge.dto.RechargeQueryDTO;
import com.porn.client.recharge.vo.RechargeVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;























@Service
 public class QueryRechargeApiServiceImpl
         implements ApiService<List<RechargeVo>>
         {

    @Autowired
     private RechargeApiService rechargeApiService;



    public List<RechargeVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 32 */
        RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).startTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), -7L, ChronoUnit.DAYS)).build();
        /* 33 */
        return this.rechargeApiService.queryRechargeList(rechargeQueryDTO);

    }



    public String getApi() {
        /* 37 */
        return "api_queryrecharge";

    }

}


