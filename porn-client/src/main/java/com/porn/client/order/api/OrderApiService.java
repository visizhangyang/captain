package com.porn.client.order.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.order.dto.*;
import com.porn.client.order.vo.OrderStatisticsVo;
import com.porn.client.order.vo.OrderVo;

import java.math.BigDecimal;
import java.util.List;

public interface OrderApiService {
    OrderVo queryOrder(OrderQueryDTO paramOrderQueryDTO);

    List<OrderVo> queryOrderList(OrderQueryDTO paramOrderQueryDTO);

    PageVo<OrderVo> queryPage(OrderQueryPageDTO paramOrderQueryPageDTO);

    PageVo<OrderVo> queryProxyPage(ProxyOrderQueryPageDTO paramProxyOrderQueryPageDTO);

    OrderVo saveOrUpdate(OrderSaveOrUpdateDTO paramOrderSaveOrUpdateDTO);

    Boolean delete(OrderDeleteDTO paramOrderDeleteDTO);

    OrderStatisticsVo orderStatistics(OrderStatisticsDTO paramOrderStatisticsDTO);

    Boolean audit(OrderAuditDTO paramOrderAuditDTO);

    Boolean keepAudit(OrderAuditDTO paramOrderAuditDTO);

    Boolean freeze(OrderFreezeDTO paramOrderFreezeDTO);

    Boolean unfreeze(OrderUnFreezeDTO paramOrderUnFreezeDTO);

    Boolean orderTimeOut(OrderTimeOutDTO paramOrderTimeOutDTO);

    BigDecimal sumOrderAmount(OrderQueryDTO paramOrderQueryDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/order/api/OrderApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */