package com.porn.service.plan.converter;

import com.porn.client.plan.dto.PlanSaveOrUpdateDTO;
import com.porn.client.plan.vo.PlanVo;
import com.porn.service.plan.dao.entity.PlanDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanConverter {
    PlanVo toPlanVo(PlanDO paramPlanDO);

    List<PlanVo> toPlanVoList(List<PlanDO> paramList);

    PlanDO toPlanDO(PlanSaveOrUpdateDTO paramPlanSaveOrUpdateDTO);
}

