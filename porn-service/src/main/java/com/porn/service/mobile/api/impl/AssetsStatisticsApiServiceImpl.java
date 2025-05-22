
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.AssetsStatisticsVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;































@Service
 public class AssetsStatisticsApiServiceImpl
         implements ApiService<AssetsStatisticsVo>
         {
    
    @Autowired
     private OrderApiService orderApiService;
    
    @Autowired
     private StreamApiService streamApiService;

    
    
    public AssetsStatisticsVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 42 */
        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).build();
        /* 43 */
        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);
        /* 44 */
        BigDecimal totalOrderAmount = BigDecimal.ZERO;
        /* 45 */
        BigDecimal totalOrderFree = BigDecimal.ZERO;
        /* 46 */
        BigDecimal totalTeamAmount = BigDecimal.ZERO;
        /* 47 */
        if (ObjectUtil.isNotEmpty(orderVoList)) {
            /* 48 */
            for (OrderVo orderVo : orderVoList) {
                /* 49 */
                totalOrderAmount = NumberUtil.add(totalOrderAmount, orderVo.getOrderAmount());
                /* 50 */
                totalOrderFree = NumberUtil.add(totalOrderFree, orderVo.getFreeAmount());
                
            }
            
        }
        
        
        
        
        
        
        /* 59 */
        StreamQueryDTO streamQueryDTO = StreamQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).typeList(Arrays.asList(new Integer[]{StreamTypeEnum.PROXY_1.getType(), StreamTypeEnum.PROXY_2.getType(), StreamTypeEnum.PROXY_3.getType()})).flag(StreamTypeEnum.PROXY_1.getFlag()).build();
        /* 60 */
        totalTeamAmount = this.streamApiService.statisticsTotalProxyProfit(streamQueryDTO);
        
        /* 62 */
        return AssetsStatisticsVo.builder()
/* 63 */.totalOrderCount(Long.valueOf(ObjectUtil.isEmpty(orderVoList) ? 0L : orderVoList.size()))
/* 64 */.totalOrderAmount(totalOrderAmount)
/* 65 */.totalOrderFree(totalOrderFree)
/* 66 */.totalTeamAmount(ObjectUtil.isEmpty(totalTeamAmount) ? BigDecimal.ZERO : totalTeamAmount)
/* 67 */.build();
        
    }

    
    
    public String getApi() {
        /* 71 */
        return "api_assetsstatistics";
        
    }
    
}


