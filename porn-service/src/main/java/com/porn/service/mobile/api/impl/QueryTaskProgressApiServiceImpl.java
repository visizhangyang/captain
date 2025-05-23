package com.porn.service.mobile.api.impl;


import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.enums.LangTypeEnum;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.TaskProgressVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.recharge.api.RechargeApiService;
import com.porn.client.recharge.dto.RechargeQueryDTO;
import com.porn.client.recharge.enums.RechargeStatusEnum;
import com.porn.client.reward.api.RewardBalanceApiService;
import com.porn.client.reward.api.RewardRecordApiService;
import com.porn.client.reward.api.RewardRuleApiService;
import com.porn.client.reward.dto.OperateRewardBalanceDTO;
import com.porn.client.reward.dto.QueryRewardRecordDTO;
import com.porn.client.reward.dto.RewardRuleQueryDTO;
import com.porn.client.reward.enums.RewardRecordTypeEnum;
import com.porn.client.reward.enums.RuleTypeEnum;
import com.porn.client.reward.vo.RewardRecordVo;
import com.porn.client.reward.vo.RewardRuleVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryTaskProgressApiServiceImpl
        implements ApiService<List<TaskProgressVo>> {

    @Autowired
    private RewardRuleApiService rewardRuleApiService;

    @Autowired
    private StreamApiService streamApiService;

    @Autowired
    private OrderApiService orderApiService;

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private RechargeApiService rechargeApiService;

    @Autowired
    private RewardRecordApiService rewardRecordApiService;

    @Autowired
    private RewardBalanceApiService rewardBalanceApiService;

    @Autowired
    private RedisTemplate redisTemplate;


    public List<TaskProgressVo> cmd(CmdRequestDTO cmdRequestDTO) {

        RewardRuleQueryDTO rewardRuleQueryDTO = (RewardRuleQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), RewardRuleQueryDTO.class);

        if (ObjectUtil.isEmpty(rewardRuleQueryDTO.getLangTypeName())) {


            rewardRuleQueryDTO.setLangType(LangTypeEnum.ZH.getType());

        } else if (ObjectUtil.isEmpty(LangTypeEnum.queryByTag(rewardRuleQueryDTO.getLangTypeName()))) {


            rewardRuleQueryDTO.setLangType(LangTypeEnum.EN.getType());

        } else {


            LangTypeEnum langTypeEnum = LangTypeEnum.queryByTag(rewardRuleQueryDTO.getLangTypeName());

            rewardRuleQueryDTO.setLangType(langTypeEnum.getType());

        }

        List<RewardRuleVo> rewardRuleVoList = this.rewardRuleApiService.queryRewardRuleList(rewardRuleQueryDTO);

        if (ObjectUtil.isEmpty(rewardRuleVoList)) {

            return Collections.emptyList();

        }

        List<TaskProgressVo> result = new ArrayList<>();

        for (RewardRuleVo rewardRuleVo : rewardRuleVoList) {


            TaskProgressVo taskProgressVo = TaskProgressVo.builder().name(rewardRuleVo.getName()).subName(rewardRuleVo.getSubName()).ruleType(rewardRuleVo.getRuleType()).ruleImgUrl(rewardRuleVo.getRuleImgUrl()).totalNum(rewardRuleVo.getTotalAmount().setScale(0)).rewardNum(rewardRuleVo.getRewardNum()).expireTime(LocalDateTimeUtil.format(LocalDateTimeUtil.endOfDay(LocalDateTime.now()), "yyyy-MM-dd HH:mm:ss")).build();

            calcCurNum(cmdRequestDTO.getAccountVo(), rewardRuleVo, taskProgressVo);

            result.add(taskProgressVo);

        }

        return result;

    }

    public void calcCurNum(AccountVo accountVo, RewardRuleVo rewardRuleVo, TaskProgressVo taskProgressVo) {

        if (RuleTypeEnum.WORK_RULE.getType().equals(rewardRuleVo.getRuleType())) {

            OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().accountId(accountVo.getId()).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).build();

            BigDecimal curNum = this.orderApiService.sumOrderAmount(orderQueryDTO);

            taskProgressVo.setCurNum(curNum);

        } else if (RuleTypeEnum.PROMOTION_RULE.getType().equals(rewardRuleVo.getRuleType())) {

            AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().parentId(accountVo.getId()).build();

            List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);

            if (ObjectUtil.isEmpty(accountVoList)) {

                taskProgressVo.setCurNum(BigDecimal.ZERO);

            } else {

                List<Long> childIdList = (List<Long>) accountVoList.stream().map(BaseVo::getId).collect(Collectors.toList());


                OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().orderStatus(OrderStatusEnum.CONFIRED.getStatus()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).accountIdList(childIdList).build();

                BigDecimal curNum = this.orderApiService.sumOrderAmount(orderQueryDTO);

                taskProgressVo.setCurNum(curNum);

            }

        } else if (RuleTypeEnum.RECHARGE_RULE.getType().equals(rewardRuleVo.getRuleType())) {


            RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().accountId(accountVo.getId()).status(RechargeStatusEnum.PAY_SUCCESS.getStatus()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).build();

            BigDecimal curNum = this.rechargeApiService.sumRechargeAmount(rechargeQueryDTO);

            taskProgressVo.setCurNum(curNum);

        } else if (RuleTypeEnum.BALANCE_RULE.getType().equals(rewardRuleVo.getRuleType())) {

            taskProgressVo.setCurNum(ObjectUtil.isEmpty(accountVo.getAvailableBalance()) ? BigDecimal.ZERO : accountVo.getAvailableBalance());

        }


        if (taskProgressVo.getCurNum().compareTo(rewardRuleVo.getTotalAmount()) >= 0) {

            doCheckIssue(accountVo, rewardRuleVo);

        }

    }

    protected void doCheckIssue(AccountVo accountVo, RewardRuleVo rewardRuleVo) {

        QueryRewardRecordDTO queryRewardRecordDTO = QueryRewardRecordDTO.builder().accountId(accountVo.getId()).type(RewardRecordTypeEnum.ADD.getType()).bizType(rewardRuleVo.getRuleType()).bizId(LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "yyyyMMdd")).build();

        RewardRecordVo rewardRecordVo = this.rewardRecordApiService.queryRewardRecord(queryRewardRecordDTO);

        if (ObjectUtil.isEmpty(rewardRecordVo)) {


            OperateRewardBalanceDTO operateRewardBalanceDTO = OperateRewardBalanceDTO.builder().accountId(accountVo.getId()).operateCount(new BigDecimal(rewardRuleVo.getRewardNum().intValue())).type(RewardRecordTypeEnum.ADD.getType()).bizType(rewardRuleVo.getRuleType()).bizId(LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "yyyyMMdd")).build();

            this.rewardBalanceApiService.operateRewardBalance(operateRewardBalanceDTO);

        }

    }

    public String getApi() {

        return "api_querytaskprogress";

    }

}

