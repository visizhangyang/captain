package com.porn.service.recharge.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.client.recharge.dto.RechargeQueryDTO;
import com.porn.service.recharge.dao.entity.RechargeDO;

import java.math.BigDecimal;

public interface RechargeMapper extends BaseMapper<RechargeDO> {
    BigDecimal sumRechargeAmount(RechargeQueryDTO paramRechargeQueryDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/recharge/dao/mapper/RechargeMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */