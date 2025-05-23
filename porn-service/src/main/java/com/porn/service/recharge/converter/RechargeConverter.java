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

