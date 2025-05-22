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


