package com.porn.service.order.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.service.order.dao.entity.OrderDO;

import java.math.BigDecimal;

public interface OrderMapper extends BaseMapper<OrderDO> {
    BigDecimal sumOrderAmount(OrderQueryDTO paramOrderQueryDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/order/dao/mapper/OrderMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */