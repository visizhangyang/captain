package com.porn.service.mobile.api.impl;


import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.plan.api.PlanApiService;
import com.porn.client.plan.api.PlanInsApiService;
import com.porn.client.plan.dto.PlanInsQueryDTO;
import com.porn.client.plan.dto.PlanQueryDTO;
import com.porn.client.plan.enums.PlanInsStatusEnum;
import com.porn.client.plan.vo.PlanInsExtVo;
import com.porn.client.plan.vo.PlanInsVo;
import com.porn.client.plan.vo.PlanVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.plan.converter.PlanInsConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Service
public class QueryPlanInsApiServiceImpl
        implements ApiService<PlanInsExtVo> {
    private static final Logger log = LoggerFactory.getLogger(QueryPlanInsApiServiceImpl.class);

    @Autowired
    private PlanInsApiService planInsApiService;


    @Autowired
    private PlanInsConverter planInsConverter;


    @Autowired
    private PlanApiService planApiService;


    @Autowired
    private StreamApiService streamApiService;


    public PlanInsExtVo cmd(CmdRequestDTO cmdRequestDTO) {

        PlanInsQueryDTO planInsQueryDTO = PlanInsQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).status(PlanInsStatusEnum.PROGRESSING.getStatus()).build();

        PlanInsVo planInsVo = this.planInsApiService.queryPlanIns(planInsQueryDTO);

        if (ObjectUtil.isEmpty(planInsVo)) {

            return PlanInsExtVo.builder()
                    .existsPlan(CommonConst.IZERO)
                    .build();

        }

        PlanInsExtVo planInsExtVo = this.planInsConverter.toPlanInsExtVo(planInsVo);

        PlanVo planVo = this.planApiService.queryPlan(((PlanQueryDTO.PlanQueryDTOBuilder) PlanQueryDTO.builder().id(planInsExtVo.getPlanId())).build());


        if (planInsExtVo.getEndTime().compareTo(LocalDateTimeUtil.now()) >= 0) {


            planInsExtVo.setTotalProgress(Long.valueOf(LocalDateTimeUtil.between(planInsExtVo.getStartTime(), planInsExtVo.getEndTime()).toMinutes()));

            planInsExtVo.setCurProgress(Long.valueOf(LocalDateTimeUtil.between(planInsExtVo.getStartTime(), LocalDateTimeUtil.now()).toMinutes()));

        } else {

            planInsExtVo.setTotalProgress(CommonConst.LONE);

            planInsExtVo.setCurProgress(CommonConst.LONE);

        }

        planInsExtVo.setExistsPlan(CommonConst.IONE);


        if (ObjectUtil.isNotEmpty(planVo)) {

            planInsExtVo.setPlanTitle(planVo.getTitle());

            planInsExtVo.setMinRange(planVo.getMinRange());

            planInsExtVo.setMaxRange(planVo.getMaxRange());


            LocalDateTime now = LocalDateTimeUtil.now();


            StreamQueryDTO yesterdayWorkStreamQueryDTO = StreamQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).typeList(Arrays.asList(new Integer[]{StreamTypeEnum.PLAN_PROFIT.getType()})).flag(StreamTypeEnum.PLAN_PROFIT.getFlag()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.offset(now, -1L, ChronoUnit.DAYS))).endTime(LocalDateTimeUtil.beginOfDay(now)).build();

            BigDecimal yesterdayWorkIncome = this.streamApiService.statisticsTotalProxyProfit(yesterdayWorkStreamQueryDTO);

            planInsExtVo.setYesterdayProfit((BigDecimal) ObjectUtil.defaultIfNull(yesterdayWorkIncome, BigDecimal.ZERO));


            StreamQueryDTO totalWorkStreamQueryDTO = StreamQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).typeList(Arrays.asList(new Integer[]{StreamTypeEnum.PLAN_PROFIT.getType()})).flag(StreamTypeEnum.PLAN_PROFIT.getFlag()).startTime(planInsVo.getStartTime()).endTime(now).build();

            BigDecimal totalWorkIncome = this.streamApiService.statisticsTotalProxyProfit(totalWorkStreamQueryDTO);

            planInsExtVo.setTotalProfit((BigDecimal) ObjectUtil.defaultIfNull(totalWorkIncome, BigDecimal.ZERO));

        }

        return planInsExtVo;

    }


    public String getApi() {

        return "api_queryplanins";

    }

}

