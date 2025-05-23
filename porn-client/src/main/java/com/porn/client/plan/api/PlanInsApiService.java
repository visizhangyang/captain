package com.porn.client.plan.api;

import com.porn.client.plan.dto.PlanInsQueryDTO;
import com.porn.client.plan.dto.PlanInsSaveOrUpdateDTO;
import com.porn.client.plan.vo.PlanInsVo;

import java.util.List;

public interface PlanInsApiService {
    PlanInsVo queryPlanIns(PlanInsQueryDTO paramPlanInsQueryDTO);

    List<PlanInsVo> queryPlanInsList(PlanInsQueryDTO paramPlanInsQueryDTO);

    PlanInsVo saveOrUpdate(PlanInsSaveOrUpdateDTO paramPlanInsSaveOrUpdateDTO);
}

