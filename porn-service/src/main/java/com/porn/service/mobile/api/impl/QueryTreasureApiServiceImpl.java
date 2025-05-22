
package com.porn.service.mobile.api.impl;



import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.AccountTreasureVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.stream.vo.StreamVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
























@Service
 public class QueryTreasureApiServiceImpl
         implements ApiService<AccountTreasureVo>
         {
    
    @Autowired
     private StreamApiService streamApiService;

    
    
    public AccountTreasureVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 33 */
        BigDecimal todayReceive = BigDecimal.ZERO;
        /* 34 */
        BigDecimal totalReceive = BigDecimal.ZERO;
        
        
        
        
        
        
        
        /* 42 */
        StreamQueryDTO todayStreamQueryDTO = StreamQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{cmdRequestDTO.getAccountVo().getId()})).type(StreamTypeEnum.TREASURE.getType()).flag(StreamTypeEnum.TREASURE.getFlag()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).build();
        /* 43 */
        StreamVo todayStreamVo = this.streamApiService.queryStream(todayStreamQueryDTO);
        /* 44 */
        if (ObjectUtil.isNotEmpty(todayStreamVo)) {
            /* 45 */
            todayReceive = todayStreamVo.getAmount();
            
        }
        
        
        
        
        
        
        /* 53 */
        StreamQueryDTO totalStreamQueryDTO = StreamQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{cmdRequestDTO.getAccountVo().getId()})).type(StreamTypeEnum.TREASURE.getType()).flag(StreamTypeEnum.TREASURE.getFlag()).build();
        /* 54 */
        List<StreamVo> streamVoList = this.streamApiService.queryStreamList(totalStreamQueryDTO);
        /* 55 */
        totalReceive = ObjectUtil.isEmpty(streamVoList) ? BigDecimal.ZERO : streamVoList.stream().map(StreamVo::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        
        /* 57 */
        return AccountTreasureVo.builder()
/* 58 */.todayReceive(todayReceive)
/* 59 */.totalReceive(totalReceive)
/* 60 */.build();
        
    }

    
    
    public String getApi() {
        /* 64 */
        return "api_querytreasure";
        
    }
    
}


