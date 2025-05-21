package com.porn.service.recharge.converter;

import com.porn.client.recharge.dto.ProxyRechargeQueryPageDTO;
import com.porn.client.recharge.dto.RechargeQueryPageDTO;
import com.porn.client.recharge.vo.RechargeVo;
import com.porn.service.recharge.dao.entity.RechargeDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RechargeConverter {
    RechargeVo toRechargeVo(RechargeDO paramRechargeDO);

    List<RechargeVo> toRechargeVoList(List<RechargeDO> paramList);

    RechargeQueryPageDTO toRechargeQueryPageDTO(ProxyRechargeQueryPageDTO paramProxyRechargeQueryPageDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/recharge/converter/RechargeConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */