package com.porn.service.plan.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
    private static final Logger log = LoggerFactory.getLogger(PlanApiServiceImpl.class);


    @Autowired
    private PlanConverter planConverter;


    @Autowired
    private PlanMapper planMapper;


    public PlanVo queryPlan(PlanQueryDTO planQueryDTO) {

        List<PlanVo> planVoList = queryPlanList(planQueryDTO);

        return ObjectUtil.isEmpty(planVoList) ? null : planVoList.get(0);

    }

    public List<PlanVo> queryPlanList(PlanQueryDTO planQueryDTO) {

        List<PlanDO> planList = ChainWrappers.lambdaQueryChain(planMapper)
                .eq(ObjectUtil.isNotEmpty(planQueryDTO.getId()), BaseDO::getId, planQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(planQueryDTO.getLangType()), PlanDO::getLangType, planQueryDTO.getLangType())
                .like(ObjectUtil.isNotEmpty(planQueryDTO.getLkTitle()), PlanDO::getTitle, planQueryDTO.getLkTitle())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByAsc(PlanDO::getSortNo)
                .list();

        if (ObjectUtil.isEmpty(planList)) {

            return Collections.emptyList();

        }

        List<PlanVo> planVoList = this.planConverter.toPlanVoList(planList);

        return planVoList;

    }

    public PageVo<PlanVo> queryPage(PlanQueryPageDTO planQueryPageDTO) {

        Page page = new Page(planQueryPageDTO.getPageStart().intValue(), planQueryPageDTO.getPageSize().intValue(), true);


        LambdaQueryWrapper<PlanDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(planQueryPageDTO.getLangType()), PlanDO::getLangType, planQueryPageDTO.getLangType());
        wrapper.like(ObjectUtil.isNotEmpty(planQueryPageDTO.getLkTitle()), PlanDO::getTitle, planQueryPageDTO.getLkTitle());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByAsc(PlanDO::getSortNo);


        IPage<PlanDO> planPage = this.planMapper.selectPage((IPage) page, wrapper);

        List<PlanVo> planVoList = this.planConverter.toPlanVoList(planPage.getRecords());

        return PageVo.<PlanVo>builder()
                .pageStart(planQueryPageDTO.getPageStart())
                .pageSize(planQueryPageDTO.getPageSize())
                .total(Long.valueOf(planPage.getTotal()))
                .data(planVoList)
                .build();

    }

    public PlanVo saveOrUpdate(PlanSaveOrUpdateDTO planSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(planSaveOrUpdateDTO.getId())) {

            PlanDO planDO = this.planConverter.toPlanDO(planSaveOrUpdateDTO);

            if (ObjectUtil.isEmpty(planDO.getSortNo())) {

                planDO.setSortNo(CommonConst.IONE);

            }

            planDO.setDesc(StrUtil.emptyToDefault(planDO.getDesc(), "").trim());


            if (this.planMapper.insert(planDO) <= 0) {

                throw new BusinessException("创建计划信息失败.");

            }

            return queryPlan(((PlanQueryDTO.PlanQueryDTOBuilder) PlanQueryDTO.builder().id(planDO.getId())).build());

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(planMapper)
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

        if (!rs) {

            throw new BusinessException("更新计划信息失败.");

        }

        return queryPlan(((PlanQueryDTO.PlanQueryDTOBuilder) PlanQueryDTO.builder().id(planSaveOrUpdateDTO.getId())).build());

    }


    public Boolean delete(PlanDeleteDTO planDeleteDTO) {

        return ChainWrappers.lambdaUpdateChain(planMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, planDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }

}

