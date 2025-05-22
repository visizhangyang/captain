package com.porn.service.stream.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.service.stream.dao.entity.StreamDO;

import java.math.BigDecimal;

public interface StreamMapper extends BaseMapper<StreamDO> {
    BigDecimal statisticsTotalProxyProfit(StreamQueryDTO paramStreamQueryDTO);
}


