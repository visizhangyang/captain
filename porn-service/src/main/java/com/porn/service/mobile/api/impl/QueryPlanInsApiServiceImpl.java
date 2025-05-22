
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
         implements ApiService<PlanInsExtVo>
         {
    /*  33 */   private static final Logger log = LoggerFactory.getLogger(QueryPlanInsApiServiceImpl.class);




    @Autowired
     private PlanInsApiService planInsApiService;



    @Autowired
     private PlanInsConverter planInsConverter;



    @Autowired
     private PlanApiService planApiService;



    @Autowired
     private StreamApiService streamApiService;





    public PlanInsExtVo cmd(CmdRequestDTO cmdRequestDTO) {
        /*  55 */
        PlanInsQueryDTO planInsQueryDTO = PlanInsQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).status(PlanInsStatusEnum.PROGRESSING.getStatus()).build();
        /*  56 */
        PlanInsVo planInsVo = this.planInsApiService.queryPlanIns(planInsQueryDTO);
        /*  57 */
        if (ObjectUtil.isEmpty(planInsVo)) {
            /*  58 */
            return PlanInsExtVo.builder()
/*  59 */.existsPlan(CommonConst.IZERO)
/*  60 */.build();

        }
        /*  62 */
        PlanInsExtVo planInsExtVo = this.planInsConverter.toPlanInsExtVo(planInsVo);
        /*  63 */
        PlanVo planVo = this.planApiService.queryPlan(((PlanQueryDTO.PlanQueryDTOBuilder) PlanQueryDTO.builder().id(planInsExtVo.getPlanId())).build());

        /*  65 */
        if (planInsExtVo.getEndTime().compareTo(LocalDateTimeUtil.now()) >= 0) {

            /*  67 */
            planInsExtVo.setTotalProgress(Long.valueOf(LocalDateTimeUtil.between(planInsExtVo.getStartTime(), planInsExtVo.getEndTime()).toMinutes()));
            /*  68 */
            planInsExtVo.setCurProgress(Long.valueOf(LocalDateTimeUtil.between(planInsExtVo.getStartTime(), LocalDateTimeUtil.now()).toMinutes()));

        } else {
            /*  70 */
            planInsExtVo.setTotalProgress(CommonConst.LONE);
            /*  71 */
            planInsExtVo.setCurProgress(CommonConst.LONE);

        }
        /*  73 */
        planInsExtVo.setExistsPlan(CommonConst.IONE);

        /*  75 */
        if (ObjectUtil.isNotEmpty(planVo)) {
            /*  76 */
            planInsExtVo.setPlanTitle(planVo.getTitle());
            /*  77 */
            planInsExtVo.setMinRange(planVo.getMinRange());
            /*  78 */
            planInsExtVo.setMaxRange(planVo.getMaxRange());


            /*  81 */
            LocalDateTime now = LocalDateTimeUtil.now();






            /*  88 */
            StreamQueryDTO yesterdayWorkStreamQueryDTO = StreamQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).typeList(Arrays.asList(new Integer[]{StreamTypeEnum.PLAN_PROFIT.getType()})).flag(StreamTypeEnum.PLAN_PROFIT.getFlag()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.offset(now, -1L, ChronoUnit.DAYS))).endTime(LocalDateTimeUtil.beginOfDay(now)).build();
            /*  89 */
            BigDecimal yesterdayWorkIncome = this.streamApiService.statisticsTotalProxyProfit(yesterdayWorkStreamQueryDTO);
            /*  90 */
            planInsExtVo.setYesterdayProfit((BigDecimal) ObjectUtil.defaultIfNull(yesterdayWorkIncome, BigDecimal.ZERO));








            /*  99 */
            StreamQueryDTO totalWorkStreamQueryDTO = StreamQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).typeList(Arrays.asList(new Integer[]{StreamTypeEnum.PLAN_PROFIT.getType()})).flag(StreamTypeEnum.PLAN_PROFIT.getFlag()).startTime(planInsVo.getStartTime()).endTime(now).build();
            /* 100 */
            BigDecimal totalWorkIncome = this.streamApiService.statisticsTotalProxyProfit(totalWorkStreamQueryDTO);
            /* 101 */
            planInsExtVo.setTotalProfit((BigDecimal) ObjectUtil.defaultIfNull(totalWorkIncome, BigDecimal.ZERO));

        }

        /* 104 */
        return planInsExtVo;

    }



    public String getApi() {
        /* 108 */
        return "api_queryplanins";

    }

}


