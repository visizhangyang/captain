
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
         implements ApiService<List<TaskProgressVo>>
         {

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
        /*  77 */
        RewardRuleQueryDTO rewardRuleQueryDTO = (RewardRuleQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), RewardRuleQueryDTO.class);
        /*  78 */
        if (ObjectUtil.isEmpty(rewardRuleQueryDTO.getLangTypeName())) {

            /*  80 */
            rewardRuleQueryDTO.setLangType(LangTypeEnum.ZH.getType());
            /*  81 */
        } else if (ObjectUtil.isEmpty(LangTypeEnum.queryByTag(rewardRuleQueryDTO.getLangTypeName()))) {

            /*  83 */
            rewardRuleQueryDTO.setLangType(LangTypeEnum.EN.getType());

        } else {

            /*  86 */
            LangTypeEnum langTypeEnum = LangTypeEnum.queryByTag(rewardRuleQueryDTO.getLangTypeName());
            /*  87 */
            rewardRuleQueryDTO.setLangType(langTypeEnum.getType());

        }
        /*  89 */
        List<RewardRuleVo> rewardRuleVoList = this.rewardRuleApiService.queryRewardRuleList(rewardRuleQueryDTO);
        /*  90 */
        if (ObjectUtil.isEmpty(rewardRuleVoList)) {
            /*  91 */
            return Collections.emptyList();

        }
        /*  93 */
        List<TaskProgressVo> result = new ArrayList<>();
        /*  94 */
        for (RewardRuleVo rewardRuleVo : rewardRuleVoList) {








            /* 103 */
            TaskProgressVo taskProgressVo = TaskProgressVo.builder().name(rewardRuleVo.getName()).subName(rewardRuleVo.getSubName()).ruleType(rewardRuleVo.getRuleType()).ruleImgUrl(rewardRuleVo.getRuleImgUrl()).totalNum(rewardRuleVo.getTotalAmount().setScale(0)).rewardNum(rewardRuleVo.getRewardNum()).expireTime(LocalDateTimeUtil.format(LocalDateTimeUtil.endOfDay(LocalDateTime.now()), "yyyy-MM-dd HH:mm:ss")).build();

            /* 105 */
            calcCurNum(cmdRequestDTO.getAccountVo(), rewardRuleVo, taskProgressVo);
            /* 106 */
            result.add(taskProgressVo);

        }
        /* 108 */
        return result;

    }








    public void calcCurNum(AccountVo accountVo, RewardRuleVo rewardRuleVo, TaskProgressVo taskProgressVo) {
        /* 117 */
        if (RuleTypeEnum.WORK_RULE.getType().equals(rewardRuleVo.getRuleType())) {





            /* 123 */
            OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().accountId(accountVo.getId()).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).build();
            /* 124 */
            BigDecimal curNum = this.orderApiService.sumOrderAmount(orderQueryDTO);
            /* 125 */
            taskProgressVo.setCurNum(curNum);
            /* 126 */
        } else if (RuleTypeEnum.PROMOTION_RULE.getType().equals(rewardRuleVo.getRuleType())) {



            /* 130 */
            AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().parentId(accountVo.getId()).build();
            /* 131 */
            List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);
            /* 132 */
            if (ObjectUtil.isEmpty(accountVoList)) {
                /* 133 */
                taskProgressVo.setCurNum(BigDecimal.ZERO);

            } else {

                /* 136 */
                List<Long> childIdList = (List<Long>) accountVoList.stream().map(BaseVo::getId).collect(Collectors.toList());




                /* 141 */
                OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().orderStatus(OrderStatusEnum.CONFIRED.getStatus()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).accountIdList(childIdList).build();
                /* 142 */
                BigDecimal curNum = this.orderApiService.sumOrderAmount(orderQueryDTO);
                /* 143 */
                taskProgressVo.setCurNum(curNum);

            }
            /* 145 */
        } else if (RuleTypeEnum.RECHARGE_RULE.getType().equals(rewardRuleVo.getRuleType())) {




            /* 150 */
            RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().accountId(accountVo.getId()).status(RechargeStatusEnum.PAY_SUCCESS.getStatus()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).build();
            /* 151 */
            BigDecimal curNum = this.rechargeApiService.sumRechargeAmount(rechargeQueryDTO);
            /* 152 */
            taskProgressVo.setCurNum(curNum);
            /* 153 */
        } else if (RuleTypeEnum.BALANCE_RULE.getType().equals(rewardRuleVo.getRuleType())) {

            /* 155 */
            taskProgressVo.setCurNum(ObjectUtil.isEmpty(accountVo.getAvailableBalance()) ? BigDecimal.ZERO : accountVo.getAvailableBalance());

        }


        /* 159 */
        if (taskProgressVo.getCurNum().compareTo(rewardRuleVo.getTotalAmount()) >= 0) {
            /* 160 */
            doCheckIssue(accountVo, rewardRuleVo);

        }

    }












    protected void doCheckIssue(AccountVo accountVo, RewardRuleVo rewardRuleVo) {
        /* 174 */
        QueryRewardRecordDTO queryRewardRecordDTO = QueryRewardRecordDTO.builder().accountId(accountVo.getId()).type(RewardRecordTypeEnum.ADD.getType()).bizType(rewardRuleVo.getRuleType()).bizId(LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "yyyyMMdd")).build();
        /* 175 */
        RewardRecordVo rewardRecordVo = this.rewardRecordApiService.queryRewardRecord(queryRewardRecordDTO);
        /* 176 */
        if (ObjectUtil.isEmpty(rewardRecordVo)) {






            /* 183 */
            OperateRewardBalanceDTO operateRewardBalanceDTO = OperateRewardBalanceDTO.builder().accountId(accountVo.getId()).operateCount(new BigDecimal(rewardRuleVo.getRewardNum().intValue())).type(RewardRecordTypeEnum.ADD.getType()).bizType(rewardRuleVo.getRuleType()).bizId(LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "yyyyMMdd")).build();
            /* 184 */
            this.rewardBalanceApiService.operateRewardBalance(operateRewardBalanceDTO);

        }

    }




    public String getApi() {
        /* 190 */
        return "api_querytaskprogress";

    }

}


