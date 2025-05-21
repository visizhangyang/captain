package com.porn.client.workrobot.api;

import com.porn.client.workrobot.dto.WorkrobotQueryDTO;
import com.porn.client.workrobot.dto.WorkrobotSaveOrUpdateDTO;
import com.porn.client.workrobot.vo.WorkrobotVo;

import java.util.List;

public interface WorkrobotApiService {
    WorkrobotVo queryWorkrobot(WorkrobotQueryDTO paramWorkrobotQueryDTO);

    List<WorkrobotVo> queryWorkrobotList(WorkrobotQueryDTO paramWorkrobotQueryDTO);

    WorkrobotVo saveOrUpdate(WorkrobotSaveOrUpdateDTO paramWorkrobotSaveOrUpdateDTO);

    Boolean batchCreate(List<WorkrobotSaveOrUpdateDTO> paramList);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/workrobot/api/WorkrobotApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */