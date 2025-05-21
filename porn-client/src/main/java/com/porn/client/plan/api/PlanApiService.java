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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/plan/api/PlanApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */