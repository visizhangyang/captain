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
        implements ApiService<PlanInsVo> {

    @Autowired
    private PlanApiService planApiService;

    @Autowired
    private PlanInsApiService planInsApiService;

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private DingdingMsgSender dingdingMsgSender;


    public PlanInsVo cmd(CmdRequestDTO cmdRequestDTO) {

        PlanInsCreateDTO planInsCreateDTO = (PlanInsCreateDTO) JSON.parseObject(cmdRequestDTO.getData(), PlanInsCreateDTO.class);


        PlanInsVo planInsVo = this.planInsApiService.queryPlanIns(
                PlanInsQueryDTO.builder()
                        .accountId(cmdRequestDTO.getAccountVo().getId())
                        .status(PlanInsStatusEnum.PROGRESSING.getStatus())
                        .build());


        if (ObjectUtil.isNotEmpty(planInsVo)) {

            throw new BusinessException("已经存在进行中的计划.");

        }

        PlanVo planVo = this.planApiService.queryPlan((
                (PlanQueryDTO.PlanQueryDTOBuilder) PlanQueryDTO.builder()
                        .id(planInsCreateDTO.getPlanId()))
                .build());


        if (ObjectUtil.isEmpty(planVo)) {

            throw new BusinessException("计划信息不存在.");

        }


        AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build();

        AccountVo accountVo = this.accountApiService.queryAccount(accountQueryDTO);

        if (ObjectUtil.isEmpty(accountVo)) {

            throw new BusinessException("用户信息不存在.");

        }


        if (accountVo.getAvailableBalance().compareTo(NumberUtil.add(planInsCreateDTO.getTotalInvest(), planVo.getFree())) < 0) {

            throw new BusinessException("余额不足.");

        }


        planInsVo = this.planInsApiService.saveOrUpdate(
                PlanInsSaveOrUpdateDTO.builder()
                        .accountId(cmdRequestDTO.getAccountVo().getId())
                        .planId(planVo.getId())
                        .yesterdayProfit(BigDecimal.ZERO)
                        .totalProfit(BigDecimal.ZERO)
                        .totalInvest(planInsCreateDTO.getTotalInvest())
                        .startTime(LocalDateTimeUtil.now())
                        .endTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), Long.valueOf(planVo.getDays().intValue()).longValue(), ChronoUnit.DAYS))
                        .status(PlanInsStatusEnum.PROGRESSING.getStatus())
                        .extraBonus(planVo.getExtraBonus())
                        .build());


        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).amountType(AmountTypeEnum.SUBTOTAL_SUBAVAILABLE.getType()).bizId(planInsVo.getId()).streamTypeEnum(StreamTypeEnum.PLAN_LOCK).operateAmount(planInsCreateDTO.getTotalInvest()).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);


        accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).amountType(AmountTypeEnum.SUBTOTAL_SUBAVAILABLE.getType()).bizId(planInsVo.getId()).streamTypeEnum(StreamTypeEnum.PLAN_FREE).operateAmount(planVo.getFree()).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);


        this.dingdingMsgSender.sendMsg(
                ProxyMsgDTO.builder()
                        .accountId(accountVo.getId())
                        .msg("账户[" + accountVo.getName() + "], 金额[" + planInsVo.getTotalInvest().stripTrailingZeros().toPlainString() + "], 开启自动搬砖计划, 请注意观察.")
                        .build());


        return planInsVo;

    }

    public String getApi() {

        return "api_createplan";

    }

}

