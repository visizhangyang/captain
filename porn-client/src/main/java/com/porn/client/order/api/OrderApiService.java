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


