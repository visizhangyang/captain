package com.porn.client.autowork.api;

import com.porn.client.autowork.dto.AutoWorkQueryDTO;
import com.porn.client.autowork.dto.AutoWorkSaveOrUpdateDTO;
import com.porn.client.autowork.vo.AutoWorkVo;

import java.util.List;

public interface AutoWorkApiService {
    AutoWorkVo queryAutoWork(AutoWorkQueryDTO paramAutoWorkQueryDTO);

    List<AutoWorkVo> queryAutoWorkList(AutoWorkQueryDTO paramAutoWorkQueryDTO);

    AutoWorkVo saveOrUpdate(AutoWorkSaveOrUpdateDTO paramAutoWorkSaveOrUpdateDTO);
}


