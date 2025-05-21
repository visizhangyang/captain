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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/autowork/api/AutoWorkApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */