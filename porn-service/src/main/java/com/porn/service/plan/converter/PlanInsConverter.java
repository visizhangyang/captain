package com.porn.service.plan.converter;

import com.porn.client.plan.dto.PlanInsSaveOrUpdateDTO;
import com.porn.client.plan.vo.PlanInsExtVo;
import com.porn.client.plan.vo.PlanInsVo;
import com.porn.service.plan.dao.entity.PlanInsDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanInsConverter {
    PlanInsVo toPlanInsVo(PlanInsDO paramPlanInsDO);

    List<PlanInsVo> toPlanInsVoList(List<PlanInsDO> paramList);

    PlanInsDO toPlanInsDO(PlanInsSaveOrUpdateDTO paramPlanInsSaveOrUpdateDTO);

    PlanInsExtVo toPlanInsExtVo(PlanInsVo paramPlanInsVo);
}


