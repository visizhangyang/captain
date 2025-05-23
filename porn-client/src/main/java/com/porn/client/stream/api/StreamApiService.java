package com.porn.client.stream.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.dto.StreamQueryPageDTO;
import com.porn.client.stream.dto.StreamSaveOrUpdateDTO;
import com.porn.client.stream.vo.StreamVo;

import java.math.BigDecimal;
import java.util.List;

public interface StreamApiService {
    StreamVo queryStream(StreamQueryDTO paramStreamQueryDTO);

    List<StreamVo> queryStreamList(StreamQueryDTO paramStreamQueryDTO);

    PageVo<StreamVo> queryPage(StreamQueryPageDTO paramStreamQueryPageDTO);

    StreamVo saveOrUpdate(StreamSaveOrUpdateDTO paramStreamSaveOrUpdateDTO);

    BigDecimal statisticsTotalProxyProfit(StreamQueryDTO paramStreamQueryDTO);
}

