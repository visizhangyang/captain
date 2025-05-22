
package com.porn.service.mobile.api.impl;



import cn.hutool.core.date.LocalDateTimeUtil;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.transfer.api.TransferApiService;
import com.porn.client.transfer.dto.TransferQueryDTO;
import com.porn.client.transfer.vo.TransferVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
























@Service
 public class QueryTransferApiServiceImpl
         implements ApiService<List<TransferVo>>
         {

    @Autowired
     private TransferApiService transferApiService;



    public List<TransferVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 33 */
        TransferQueryDTO transferQueryDTO = TransferQueryDTO.builder().srcAccountId(cmdRequestDTO.getAccountVo().getId()).startTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), -7L, ChronoUnit.DAYS)).build();
        /* 34 */
        return this.transferApiService.queryTransferList(transferQueryDTO);

    }



    public String getApi() {
        /* 38 */
        return "api_querytransfer";

    }

}


