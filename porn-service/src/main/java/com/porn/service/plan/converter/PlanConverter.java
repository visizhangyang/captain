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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/plan/converter/PlanConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */