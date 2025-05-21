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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/plan/api/PlanInsApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */