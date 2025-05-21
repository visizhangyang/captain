package com.porn.client.desc.api;

import com.porn.client.desc.dto.DescQueryDTO;
import com.porn.client.desc.dto.DescSaveOrUpdateDTO;
import com.porn.client.desc.vo.DescVo;

import java.util.List;

public interface DescApiService {
    DescVo queryDesc(DescQueryDTO paramDescQueryDTO);

    List<DescVo> queryDescList(DescQueryDTO paramDescQueryDTO);

    DescVo saveOrUpdate(DescSaveOrUpdateDTO paramDescSaveOrUpdateDTO);

    Boolean batchCreate(List<DescSaveOrUpdateDTO> paramList);
}
