
package com.porn.service.plan.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.plan.api.PlanApiService;
import com.porn.client.plan.dto.PlanDeleteDTO;
import com.porn.client.plan.dto.PlanQueryDTO;
import com.porn.client.plan.dto.PlanQueryPageDTO;
import com.porn.client.plan.dto.PlanSaveOrUpdateDTO;
import com.porn.client.plan.vo.PlanVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.plan.converter.PlanConverter;
import com.porn.service.plan.dao.entity.PlanDO;
import com.porn.service.plan.dao.mapper.PlanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;































@Service

@Transactional(rollbackFor = {Exception.class})
 public class PlanApiServiceImpl implements PlanApiService {
    /*  34 */   private static final Logger log = LoggerFactory.getLogger(PlanApiServiceImpl.class);



    @Autowired
     private PlanConverter planConverter;



    @Autowired
     private PlanMapper planMapper;





    public PlanVo queryPlan(PlanQueryDTO planQueryDTO) {
        /*  47 */
        List<PlanVo> planVoList = queryPlanList(planQueryDTO);
        /*  48 */
        return ObjectUtil.isEmpty(planVoList) ? null : planVoList.get(0);

    }










    public List<PlanVo> queryPlanList(PlanQueryDTO planQueryDTO) {
        /*  59 */
        List<PlanDO> planList = ChainWrappers.lambdaQueryChain(planMapper)
                .eq(ObjectUtil.isNotEmpty(planQueryDTO.getId()), BaseDO::getId, planQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(planQueryDTO.getLangType()), PlanDO::getLangType, planQueryDTO.getLangType())
                .like(ObjectUtil.isNotEmpty(planQueryDTO.getLkTitle()), PlanDO::getTitle, planQueryDTO.getLkTitle())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByAsc(PlanDO::getSortNo)
                .list();
        /*  60 */
        if (ObjectUtil.isEmpty(planList)) {
            /*  61 */
            return Collections.emptyList();

        }
        /*  63 */
        List<PlanVo> planVoList = this.planConverter.toPlanVoList(planList);
        /*  64 */
        return planVoList;

    }




    public PageVo<PlanVo> queryPage(PlanQueryPageDTO planQueryPageDTO) {
        /*  69 */
        Page page = new Page(planQueryPageDTO.getPageStart().intValue(), planQueryPageDTO.getPageSize().intValue(), true);
        /*  70 */
        LambdaQueryChainWrapper<PlanDO> lambdaQueryChainWrapper = ChainWrappers.lambdaQueryChain(planMapper)
                .eq(ObjectUtil.isNotEmpty(planQueryPageDTO.getLangType()), PlanDO::getLangType, planQueryPageDTO.getLangType())
        /*  73 */.like(ObjectUtil.isNotEmpty(planQueryPageDTO.getLkTitle()), PlanDO::getTitle, planQueryPageDTO.getLkTitle())
        /*  74 */.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
        /*  75 */.orderByAsc(PlanDO::getSortNo);
        /*  71 */
        /*  76 */
        IPage<PlanDO> planPage = this.planMapper.selectPage((IPage) page, lambdaQueryChainWrapper);
        /*  77 */
        List<PlanVo> planVoList = this.planConverter.toPlanVoList(planPage.getRecords());
        /*  78 */
        return PageVo.<PlanVo>builder()
/*  79 */.pageStart(planQueryPageDTO.getPageStart())
/*  80 */.pageSize(planQueryPageDTO.getPageSize())
/*  81 */.total(Long.valueOf(planPage.getTotal()))
/*  82 */.data(planVoList)
/*  83 */.build();

    }




    public PlanVo saveOrUpdate(PlanSaveOrUpdateDTO planSaveOrUpdateDTO) {
        /*  88 */
        if (ObjectUtil.isEmpty(planSaveOrUpdateDTO.getId())) {
            /*  89 */
            PlanDO planDO = this.planConverter.toPlanDO(planSaveOrUpdateDTO);
            /*  90 */
            if (ObjectUtil.isEmpty(planDO.getSortNo())) {
                /*  91 */
                planDO.setSortNo(CommonConst.IONE);

            }
            /*  93 */
            planDO.setDesc(StrUtil.emptyToDefault(planDO.getDesc(), "").trim());

            /*  95 */
            if (this.planMapper.insert(planDO) <= 0) {
                /*  96 */
                throw new BusinessException("创建计划信息失败.");

            }
            /*  98 */
            return queryPlan(((PlanQueryDTO.PlanQueryDTOBuilder) PlanQueryDTO.builder().id(planDO.getId())).build());

        }














        /* 114 */
        boolean rs =   ChainWrappers.lambdaUpdateChain(planMapper)
                .set(ObjectUtil.isNotEmpty(planSaveOrUpdateDTO.getTitle()), PlanDO::getTitle, StrUtil.emptyToDefault(planSaveOrUpdateDTO.getTitle(), "").trim())
                .set(ObjectUtil.isNotEmpty(planSaveOrUpdateDTO.getMinRange()), PlanDO::getMinRange, planSaveOrUpdateDTO.getMinRange())
                .set(ObjectUtil.isNotEmpty(planSaveOrUpdateDTO.getMaxRange()), PlanDO::getMaxRange, planSaveOrUpdateDTO.getMaxRange())
                .set(ObjectUtil.isNotEmpty(planSaveOrUpdateDTO.getFree()), PlanDO::getFree, planSaveOrUpdateDTO.getFree())
                .set(ObjectUtil.isNotEmpty(planSaveOrUpdateDTO.getDesc()), PlanDO::getDesc, StrUtil.emptyToDefault(planSaveOrUpdateDTO.getDesc(), "").trim())
                .set(ObjectUtil.isNotEmpty(planSaveOrUpdateDTO.getRevenue()), PlanDO::getRevenue, planSaveOrUpdateDTO.getRevenue())
                .set(ObjectUtil.isNotEmpty(planSaveOrUpdateDTO.getDays()), PlanDO::getDays, planSaveOrUpdateDTO.getDays())
                .set(ObjectUtil.isNotEmpty(planSaveOrUpdateDTO.getLangType()), PlanDO::getLangType, planSaveOrUpdateDTO.getLangType())
                .set(ObjectUtil.isNotEmpty(planSaveOrUpdateDTO.getSortNo()), PlanDO::getSortNo, planSaveOrUpdateDTO.getSortNo())
                .set(ObjectUtil.isNotEmpty(planSaveOrUpdateDTO.getExtraBonus()), PlanDO::getExtraBonus, planSaveOrUpdateDTO.getExtraBonus())
                .eq(BaseDO::getId, planSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /* 115 */
        if (!rs) {
            /* 116 */
            throw new BusinessException("更新计划信息失败.");

        }
        /* 118 */
        return queryPlan(((PlanQueryDTO.PlanQueryDTOBuilder) PlanQueryDTO.builder().id(planSaveOrUpdateDTO.getId())).build());

    }





    public Boolean delete(PlanDeleteDTO planDeleteDTO) {
        /* 124 */
        return  ChainWrappers.lambdaUpdateChain(planMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, planDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 128 */.update();

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/plan/impl/PlanApiServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */