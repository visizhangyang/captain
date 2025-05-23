package com.porn.service.mobile.api.impl;


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
import com.porn.client.plan.dto.PlanInsAppendDTO;
import com.porn.client.plan.dto.PlanInsQueryDTO;
import com.porn.client.plan.dto.PlanInsSaveOrUpdateDTO;
import com.porn.client.plan.vo.PlanInsVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppendPlanApiServiceImpl
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

        PlanInsAppendDTO planInsAppendDTO = (PlanInsAppendDTO) JSON.parseObject(cmdRequestDTO.getData(), PlanInsAppendDTO.class);

        PlanInsVo planInsVo = this.planInsApiService.queryPlanIns((
                (PlanInsQueryDTO.PlanInsQueryDTOBuilder) PlanInsQueryDTO.builder()
                        .id(planInsAppendDTO.getId()))
                .accountId(cmdRequestDTO.getAccountVo().getId())
                .planId(planInsAppendDTO.getPlanId())
                .build());


        if (ObjectUtil.isEmpty(planInsVo)) {

            throw new BusinessException("计划实例信息不存在.");

        }


        AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build();

        AccountVo accountVo = this.accountApiService.queryAccount(accountQueryDTO);

        if (ObjectUtil.isEmpty(accountVo)) {

            throw new BusinessException("用户信息不存在.");

        }


        if (accountVo.getAvailableBalance().compareTo(planInsAppendDTO.getAppendInvest()) < 0) {

            throw new BusinessException("余额不足.");

        }


        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).amountType(AmountTypeEnum.SUBTOTAL_SUBAVAILABLE.getType()).bizId(planInsVo.getId()).streamTypeEnum(StreamTypeEnum.PLAN_APPENDLOCK).operateAmount(planInsAppendDTO.getAppendInvest()).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);


        PlanInsVo newPlanInsVo = this.planInsApiService.saveOrUpdate((
                (PlanInsSaveOrUpdateDTO.PlanInsSaveOrUpdateDTOBuilder) PlanInsSaveOrUpdateDTO.builder()
                        .id(planInsVo.getId()))
                .totalInvest(NumberUtil.add(planInsVo.getTotalInvest(), planInsAppendDTO.getAppendInvest()))
                .build());


        this.dingdingMsgSender.sendMsg(
                ProxyMsgDTO.builder()
                        .accountId(accountVo.getId())
                        .msg("账户[" + accountVo.getName() + "], 自动搬砖计划, 增加金额[" + planInsAppendDTO.getAppendInvest().stripTrailingZeros().toPlainString() + "], 总金额[" + newPlanInsVo.getTotalInvest().stripTrailingZeros().toPlainString() + "], 请注意观察.")
                        .build());


        return newPlanInsVo;

    }

    public String getApi() {

        return "api_appendplan";

    }

}

