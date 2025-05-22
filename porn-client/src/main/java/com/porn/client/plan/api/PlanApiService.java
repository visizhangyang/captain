package com.porn.client.plan.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.plan.dto.PlanDeleteDTO;
import com.porn.client.plan.dto.PlanQueryDTO;
import com.porn.client.plan.dto.PlanQueryPageDTO;
import com.porn.client.plan.dto.PlanSaveOrUpdateDTO;
import com.porn.client.plan.vo.PlanVo;

import java.util.List;

public interface PlanApiService {
    PlanVo queryPlan(PlanQueryDTO paramPlanQueryDTO);

    List<PlanVo> queryPlanList(PlanQueryDTO paramPlanQueryDTO);

    PageVo<PlanVo> queryPage(PlanQueryPageDTO paramPlanQueryPageDTO);

    PlanVo saveOrUpdate(PlanSaveOrUpdateDTO paramPlanSaveOrUpdateDTO);

    Boolean delete(PlanDeleteDTO paramPlanDeleteDTO);
}


