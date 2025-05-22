package com.porn.service.workrobot.converter;

import com.porn.client.workrobot.dto.WorkrobotSaveOrUpdateDTO;
import com.porn.client.workrobot.vo.WorkrobotVo;
import com.porn.service.workrobot.dao.entity.WorkrobotDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkrobotConverter {
    WorkrobotVo toWorkrobotVo(WorkrobotDO paramWorkrobotDO);

    WorkrobotDO toWorkrobotDO(WorkrobotSaveOrUpdateDTO paramWorkrobotSaveOrUpdateDTO);

    List<WorkrobotVo> toWorkrobotVoList(List<WorkrobotDO> paramList);
}


