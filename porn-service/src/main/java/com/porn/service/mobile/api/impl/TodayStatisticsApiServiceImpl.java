
package com.porn.service.mobile.api.impl;



import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.TodayStatisticsVo;
import com.porn.client.notice.api.NoticeApiService;
import com.porn.client.notice.dto.NoticeQueryReadStatusDTO;
import com.porn.client.notice.vo.NoticeVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



































@Service
 public class TodayStatisticsApiServiceImpl
         implements ApiService<TodayStatisticsVo>
         {
    
    @Autowired
     private OrderApiService orderApiService;
    
    @Autowired
     private NoticeApiService noticeApiService;

    
    
    public TodayStatisticsVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 46 */
        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).orderStatusList((List) Arrays.<OrderStatusEnum>stream(OrderStatusEnum.values()).map(OrderStatusEnum::getStatus).collect(Collectors.toList())).build();
        /* 47 */
        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);
        
        
        
        
        /* 52 */
        NoticeQueryReadStatusDTO noticeQueryReadStatusDTO = NoticeQueryReadStatusDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).readType(CommonConst.IZERO).build();
        /* 53 */
        List<NoticeVo> noticeAccountVoList = this.noticeApiService.queryNoticeReadStatusList(noticeQueryReadStatusDTO);
        /* 54 */
        return TodayStatisticsVo.builder()
/* 55 */.totalAmount(ObjectUtil.isEmpty(orderVoList) ? BigDecimal.ZERO : orderVoList.stream().filter(o -> OrderStatusEnum.CONFIRED.getStatus().equals(o.getOrderStatus())).map(OrderVo::getOrderAmount).reduce(BigDecimal.ZERO, BigDecimal::add))
/* 56 */.freeAmount(ObjectUtil.isEmpty(orderVoList) ? BigDecimal.ZERO : orderVoList.stream().filter(o -> OrderStatusEnum.CONFIRED.getStatus().equals(o.getOrderStatus())).map(OrderVo::getFreeAmount).reduce(BigDecimal.ZERO, BigDecimal::add))
/* 57 */.orderCount(Integer.valueOf(ObjectUtil.isEmpty(orderVoList) ? 0 : ((List) orderVoList.stream().filter(o -> OrderStatusEnum.CONFIRED.getStatus().equals(o.getOrderStatus())).collect(Collectors.toList())).size()))
/* 58 */.tradingCount(Integer.valueOf(ObjectUtil.isEmpty(orderVoList) ? 0 : ((List) orderVoList.stream().filter(o -> (OrderStatusEnum.WAIT_PAY.getStatus().equals(o.getOrderStatus()) || OrderStatusEnum.PAY_SUCCESS.getStatus().equals(o.getOrderStatus()) || OrderStatusEnum.FREEZE.getStatus().equals(o.getOrderStatus()))).collect(Collectors.toList())).size()))
/* 59 */.waitReceiveAmount(ObjectUtil.isEmpty(orderVoList) ? BigDecimal.ZERO : orderVoList.stream().filter(o -> (OrderStatusEnum.WAIT_PAY.getStatus().equals(o.getOrderStatus()) || OrderStatusEnum.PAY_SUCCESS.getStatus().equals(o.getOrderStatus()) || OrderStatusEnum.FREEZE.getStatus().equals(o.getOrderStatus()))).map(OrderVo::getOrderAmount).reduce(BigDecimal.ZERO, BigDecimal::add))
/* 60 */.totalCount(Integer.valueOf(ObjectUtil.isEmpty(orderVoList) ? 0 : orderVoList.size()))
/* 61 */.lastOrderTime(ObjectUtil.isEmpty(orderVoList) ? "" : LocalDateTimeUtil.format(((OrderVo) orderVoList.get(0)).getCreateTime(), "yyyy-MM-dd HH:mm:ss"))
/* 62 */.unReadNoticeCount(Integer.valueOf(ObjectUtil.isEmpty(noticeAccountVoList) ? CommonConst.IZERO.intValue() : noticeAccountVoList.size()))
/* 63 */.build();
        
    }

    
    
    public String getApi() {
        /* 67 */
        return "api_todaystatistics";
        
    }
    
}


