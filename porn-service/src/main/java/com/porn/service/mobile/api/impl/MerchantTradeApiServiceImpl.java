
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.common.vo.PageVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.MerchantTradeRequestDTO;
import com.porn.client.mobile.vo.MerchantTradeItemVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryPageDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.mobile.converter.MobileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



























@Service
 public class MerchantTradeApiServiceImpl
         implements ApiService<List<MerchantTradeItemVo>>
         {
    
    @Autowired
     private OrderApiService orderApiService;
    
    @Autowired
     private MobileConverter mobileConverter;

    
    
    public List<MerchantTradeItemVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 38 */
        MerchantTradeRequestDTO merchantTradeRequestDTO = (MerchantTradeRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), MerchantTradeRequestDTO.class);
        
        
        
        
        
        /* 44 */
        OrderQueryPageDTO orderQueryPageDTO = ((OrderQueryPageDTO.OrderQueryPageDTOBuilder) ((OrderQueryPageDTO.OrderQueryPageDTOBuilder) OrderQueryPageDTO.builder().merchantId(merchantTradeRequestDTO.getMerchantId()).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).pageStart(Integer.valueOf(1))).pageSize(Integer.valueOf(50))).build();
        /* 45 */
        PageVo<OrderVo> orderVoPage = this.orderApiService.queryPage(orderQueryPageDTO);
        /* 46 */
        List<MerchantTradeItemVo> result = new ArrayList<>();
        /* 47 */
        if (ObjectUtil.isNotEmpty(orderVoPage) &&
                /* 48 */       ObjectUtil.isNotEmpty(orderVoPage.getData())) {
            /* 49 */
            result = this.mobileConverter.toMerchantTradeItemVoList(orderVoPage.getData());
            
        }
        /* 51 */
        return result;
        
    }

    
    
    public String getApi() {
        /* 55 */
        return "api_merchanttrade";
        
    }
    
}


