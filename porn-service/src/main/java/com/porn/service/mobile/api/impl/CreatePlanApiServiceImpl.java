
package com.porn.service.mobile.api.impl;



import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.plan.api.PlanApiService;
import com.porn.client.plan.api.PlanInsApiService;
import com.porn.client.plan.dto.PlanInsCreateDTO;
import com.porn.client.plan.dto.PlanInsQueryDTO;
import com.porn.client.plan.dto.PlanInsSaveOrUpdateDTO;
import com.porn.client.plan.dto.PlanQueryDTO;
import com.porn.client.plan.enums.PlanInsStatusEnum;
import com.porn.client.plan.vo.PlanInsVo;
import com.porn.client.plan.vo.PlanVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;








































@Service
 public class CreatePlanApiServiceImpl
         implements ApiService<PlanInsVo>
         {

    @Autowired
     private PlanApiService planApiService;

    @Autowired
     private PlanInsApiService planInsApiService;

    @Autowired
     private AccountApiService accountApiService;

    @Autowired
     private DingdingMsgSender dingdingMsgSender;



    public PlanInsVo cmd(CmdRequestDTO cmdRequestDTO) {
        /*  55 */
        PlanInsCreateDTO planInsCreateDTO = (PlanInsCreateDTO) JSON.parseObject(cmdRequestDTO.getData(), PlanInsCreateDTO.class);

        /*  57 */
        PlanInsVo planInsVo = this.planInsApiService.queryPlanIns(
                /*  58 */         PlanInsQueryDTO.builder()
/*  59 */.accountId(cmdRequestDTO.getAccountVo().getId())
/*  60 */.status(PlanInsStatusEnum.PROGRESSING.getStatus())
/*  61 */.build());

        /*  63 */
        if (ObjectUtil.isNotEmpty(planInsVo)) {
            /*  64 */
            throw new BusinessException("已经存在进行中的计划.");

        }
        /*  66 */
        PlanVo planVo = this.planApiService.queryPlan((
                /*  67 */         (PlanQueryDTO.PlanQueryDTOBuilder) PlanQueryDTO.builder()
/*  68 */.id(planInsCreateDTO.getPlanId()))
/*  69 */.build());

        /*  71 */
        if (ObjectUtil.isEmpty(planVo)) {
            /*  72 */
            throw new BusinessException("计划信息不存在.");

        }




        /*  78 */
        AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build();
        /*  79 */
        AccountVo accountVo = this.accountApiService.queryAccount(accountQueryDTO);
        /*  80 */
        if (ObjectUtil.isEmpty(accountVo)) {
            /*  81 */
            throw new BusinessException("用户信息不存在.");

        }

        /*  84 */
        if (accountVo.getAvailableBalance().compareTo(NumberUtil.add(planInsCreateDTO.getTotalInvest(), planVo.getFree())) < 0)
             {
            /*  86 */
            throw new BusinessException("余额不足.");

        }


        /*  90 */
        planInsVo = this.planInsApiService.saveOrUpdate(
                /*  91 */         PlanInsSaveOrUpdateDTO.builder()
/*  92 */.accountId(cmdRequestDTO.getAccountVo().getId())
/*  93 */.planId(planVo.getId())
/*  94 */.yesterdayProfit(BigDecimal.ZERO)
/*  95 */.totalProfit(BigDecimal.ZERO)
/*  96 */.totalInvest(planInsCreateDTO.getTotalInvest())
/*  97 */.startTime(LocalDateTimeUtil.now())
/*  98 */.endTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), Long.valueOf(planVo.getDays().intValue()).longValue(), ChronoUnit.DAYS))
/*  99 */.status(PlanInsStatusEnum.PROGRESSING.getStatus())
/* 100 */.extraBonus(planVo.getExtraBonus())
/* 101 */.build());









        /* 111 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).amountType(AmountTypeEnum.SUBTOTAL_SUBAVAILABLE.getType()).bizId(planInsVo.getId()).streamTypeEnum(StreamTypeEnum.PLAN_LOCK).operateAmount(planInsCreateDTO.getTotalInvest()).build();
        /* 112 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);








        /* 121 */
        accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).amountType(AmountTypeEnum.SUBTOTAL_SUBAVAILABLE.getType()).bizId(planInsVo.getId()).streamTypeEnum(StreamTypeEnum.PLAN_FREE).operateAmount(planVo.getFree()).build();
        /* 122 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);


        /* 125 */
        this.dingdingMsgSender.sendMsg(
                /* 126 */         ProxyMsgDTO.builder()
/* 127 */.accountId(accountVo.getId())
/* 128 */.msg("账户[" + accountVo.getName() + "], 金额[" + planInsVo.getTotalInvest().stripTrailingZeros().toPlainString() + "], 开启自动搬砖计划, 请注意观察.")
/* 129 */.build());



        /* 133 */
        return planInsVo;

    }




    public String getApi() {
        /* 138 */
        return "api_createplan";

    }

}


