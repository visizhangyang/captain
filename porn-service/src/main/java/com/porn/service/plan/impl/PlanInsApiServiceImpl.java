
package com.porn.service.plan.impl;



import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.plan.api.PlanInsApiService;
import com.porn.client.plan.dto.PlanInsQueryDTO;
import com.porn.client.plan.dto.PlanInsSaveOrUpdateDTO;
import com.porn.client.plan.vo.PlanInsVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.plan.converter.PlanInsConverter;
import com.porn.service.plan.dao.entity.PlanInsDO;
import com.porn.service.plan.dao.mapper.PlanInsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;































@Service

@Transactional(rollbackFor = {Exception.class})
 public class PlanInsApiServiceImpl
         implements PlanInsApiService
         {
    /*  37 */   private static final Logger log = LoggerFactory.getLogger(PlanInsApiServiceImpl.class);




    @Autowired
     private PlanInsConverter planInsConverter;



    @Autowired
     private PlanInsMapper planInsMapper;





    public PlanInsVo queryPlanIns(PlanInsQueryDTO planInsQueryDTO) {
        /*  51 */
        List<PlanInsVo> planInsVoList = queryPlanInsList(planInsQueryDTO);
        /*  52 */
        return ObjectUtil.isEmpty(planInsVoList) ? null : planInsVoList.get(0);

    }











    public List<PlanInsVo> queryPlanInsList(PlanInsQueryDTO planInsQueryDTO) {
        /*  64 */
        List<PlanInsDO> planInsList =  ChainWrappers.lambdaQueryChain(planInsMapper)
                .eq(ObjectUtil.isNotEmpty(planInsQueryDTO.getId()), BaseDO::getId, planInsQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(planInsQueryDTO.getAccountId()), PlanInsDO::getAccountId, planInsQueryDTO.getAccountId())
                .in(ObjectUtil.isNotEmpty(planInsQueryDTO.getAccountIdList()), PlanInsDO::getAccountId, planInsQueryDTO.getAccountIdList())
                .eq(ObjectUtil.isNotEmpty(planInsQueryDTO.getPlanId()), PlanInsDO::getPlanId, planInsQueryDTO.getPlanId())
                .eq(ObjectUtil.isNotEmpty(planInsQueryDTO.getStatus()), PlanInsDO::getStatus, planInsQueryDTO.getStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();
        /*  65 */
        if (ObjectUtil.isEmpty(planInsList)) {
            /*  66 */
            return Collections.emptyList();

        }
        /*  68 */
        List<PlanInsVo> planInsVoList = this.planInsConverter.toPlanInsVoList(planInsList);
        /*  69 */
        return planInsVoList;

    }




    public PlanInsVo saveOrUpdate(PlanInsSaveOrUpdateDTO planInsSaveOrUpdateDTO) {
        /*  74 */
        if (ObjectUtil.isEmpty(planInsSaveOrUpdateDTO.getId())) {
            /*  75 */
            PlanInsDO planInsDO = this.planInsConverter.toPlanInsDO(planInsSaveOrUpdateDTO);
            /*  76 */
            if (ObjectUtil.isEmpty(planInsDO.getStatus())) {
                /*  77 */
                planInsDO.setStatus(CommonConst.IZERO);

            }

            /*  80 */
            if (this.planInsMapper.insert(planInsDO) <= 0) {
                /*  81 */
                throw new BusinessException("创建计划实例信息失败.");

            }
            /*  83 */
            return queryPlanIns(((PlanInsQueryDTO.PlanInsQueryDTOBuilder) PlanInsQueryDTO.builder().id(planInsDO.getId())).build());

        }













        /*  98 */
        boolean rs =  ChainWrappers.lambdaUpdateChain(planInsMapper)
                .set(ObjectUtil.isNotEmpty(planInsSaveOrUpdateDTO.getAccountId()), PlanInsDO::getAccountId, planInsSaveOrUpdateDTO.getAccountId())
                .set(ObjectUtil.isNotEmpty(planInsSaveOrUpdateDTO.getPlanId()), PlanInsDO::getPlanId, planInsSaveOrUpdateDTO.getPlanId())
                .set(ObjectUtil.isNotEmpty(planInsSaveOrUpdateDTO.getYesterdayProfit()), PlanInsDO::getYesterdayProfit, planInsSaveOrUpdateDTO.getYesterdayProfit())
                .set(ObjectUtil.isNotEmpty(planInsSaveOrUpdateDTO.getTotalProfit()), PlanInsDO::getTotalProfit, planInsSaveOrUpdateDTO.getTotalProfit())
                .set(ObjectUtil.isNotEmpty(planInsSaveOrUpdateDTO.getTotalInvest()), PlanInsDO::getTotalInvest, planInsSaveOrUpdateDTO.getTotalInvest())
                .set(ObjectUtil.isNotEmpty(planInsSaveOrUpdateDTO.getStartTime()), PlanInsDO::getStartTime, planInsSaveOrUpdateDTO.getStartTime())
                .set(ObjectUtil.isNotEmpty(planInsSaveOrUpdateDTO.getEndTime()), PlanInsDO::getEndTime, planInsSaveOrUpdateDTO.getEndTime())
                .set(ObjectUtil.isNotEmpty(planInsSaveOrUpdateDTO.getStatus()), PlanInsDO::getStatus, planInsSaveOrUpdateDTO.getStatus())
                .set(ObjectUtil.isNotEmpty(planInsSaveOrUpdateDTO.getExtraBonus()), PlanInsDO::getExtraBonus, planInsSaveOrUpdateDTO.getExtraBonus())
                .eq(BaseDO::getId, planInsSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /*  99 */
        if (!rs) {
            /* 100 */
            throw new BusinessException("更新计划实例信息失败.");

        }
        /* 102 */
        return queryPlanIns(((PlanInsQueryDTO.PlanInsQueryDTOBuilder) PlanInsQueryDTO.builder().id(planInsSaveOrUpdateDTO.getId())).build());

    }

}

