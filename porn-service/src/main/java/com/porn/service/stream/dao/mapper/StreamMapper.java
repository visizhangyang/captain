package com.porn.service.stream.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.service.stream.dao.entity.StreamDO;

import java.math.BigDecimal;

public interface StreamMapper extends BaseMapper<StreamDO> {
    BigDecimal statisticsTotalProxyProfit(StreamQueryDTO paramStreamQueryDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/stream/dao/mapper/StreamMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */