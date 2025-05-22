package com.porn.service.order.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.service.order.dao.entity.OrderDO;

import java.math.BigDecimal;

public interface OrderMapper extends BaseMapper<OrderDO> {
    BigDecimal sumOrderAmount(OrderQueryDTO paramOrderQueryDTO);
}


