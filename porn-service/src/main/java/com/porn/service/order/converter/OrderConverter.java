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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/order/converter/OrderConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */