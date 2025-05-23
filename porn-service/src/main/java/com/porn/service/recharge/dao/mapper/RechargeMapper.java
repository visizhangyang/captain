package com.porn.service.recharge.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.client.recharge.dto.RechargeQueryDTO;
import com.porn.service.recharge.dao.entity.RechargeDO;

import java.math.BigDecimal;

public interface RechargeMapper extends BaseMapper<RechargeDO> {
    BigDecimal sumRechargeAmount(RechargeQueryDTO paramRechargeQueryDTO);
}

