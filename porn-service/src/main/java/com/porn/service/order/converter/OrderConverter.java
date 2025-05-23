package com.porn.service.order.converter;

import com.porn.client.order.dto.OrderQueryPageDTO;
import com.porn.client.order.dto.OrderSaveOrUpdateDTO;
import com.porn.client.order.dto.ProxyOrderQueryPageDTO;
import com.porn.client.order.vo.OrderVo;
import com.porn.service.order.dao.entity.OrderDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderConverter {
    OrderVo toOrderVo(OrderDO paramOrderDO);

    List<OrderVo> toOrderVoList(List<OrderDO> paramList);

    OrderDO toOrderDO(OrderSaveOrUpdateDTO paramOrderSaveOrUpdateDTO);

    OrderQueryPageDTO toOrderQueryPageDTO(ProxyOrderQueryPageDTO paramProxyOrderQueryPageDTO);
}

