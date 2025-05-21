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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/workrobot/converter/WorkrobotConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */