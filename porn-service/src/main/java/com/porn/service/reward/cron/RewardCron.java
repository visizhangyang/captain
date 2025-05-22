
package com.porn.service.reward.cron;



import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AccountTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.enums.LangTypeEnum;
import com.porn.client.common.vo.BaseVo;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;














































@Component
 public class RewardCron
         implements ApplicationContextAware
         {
    /*  51 */   private static final Logger log = LoggerFactory.getLogger(RewardCron.class);



    @Autowired
     private AccountApiService accountApiService;



    @Autowired
     private RewardBalanceApiService rewardBalanceApiService;


    @Autowired
     private RewardRecordApiService rewardRecordApiService;


    @Autowired
     private RewardRuleApiService rewardRuleApiService;


    @Autowired
     private OrderApiService orderApiService;


    @Autowired
     private RechargeApiService rechargeApiService;


    @Autowired
     private RedisTemplate redisTemplate;

       private ApplicationContext applicationContext;




    @Scheduled(cron = "0 30 12 * * ?")
     public void doCompare() {
        /*  81 */
        List<RewardRuleVo> rewardRuleVoList = this.rewardRuleApiService.queryRewardRuleList(
                /*  82 */         RewardRuleQueryDTO.builder()
/*  83 */.langType(LangTypeEnum.ZH.getType())
/*  84 */.build());

        /*  86 */
        if (ObjectUtil.isEmpty(rewardRuleVoList)) {

            return;

        }



        /*  92 */
        AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().accountType(AccountTypeEnum.NORMAL.getType()).build();
        /*  93 */
        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);
        /*  94 */
        if (ObjectUtil.isEmpty(accountVoList)) {

            return;

        }
        /*  97 */
        for (RewardRuleVo rewardRuleVo : rewardRuleVoList) {
            /*  98 */
            for (AccountVo accountVo : accountVoList) {


                try {
                    /* 101 */
                    doCheckLrantLottery(rewardRuleVo, accountVo);
                    /* 102 */
                } catch (Exception e) {
                    /* 103 */
                    log.error(e.getMessage(), e);

                }

            }

        }

    }
















    protected void doCheckLrantLottery(RewardRuleVo rewardRuleVo, AccountVo accountVo) {
        /* 123 */
        TaskProgressVo taskProgressVo = TaskProgressVo.builder().name(rewardRuleVo.getName()).subName(rewardRuleVo.getSubName()).ruleType(rewardRuleVo.getRuleType()).ruleImgUrl(rewardRuleVo.getRuleImgUrl()).totalNum(rewardRuleVo.getTotalAmount().setScale(0)).rewardNum(rewardRuleVo.getRewardNum()).expireTime(LocalDateTimeUtil.format(LocalDateTimeUtil.endOfDay(LocalDateTime.now()), "yyyy-MM-dd HH:mm:ss")).build();

        /* 125 */
        LocalDateTime now = LocalDateTime.now();
        /* 126 */
        LocalDateTime startTime = LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.offset(now, -1L, ChronoUnit.DAYS));
        /* 127 */
        LocalDateTime endTime = LocalDateTimeUtil.endOfDay(LocalDateTimeUtil.offset(now, -1L, ChronoUnit.DAYS));

        /* 129 */
        if (RuleTypeEnum.WORK_RULE.getType().equals(rewardRuleVo.getRuleType())) {






            /* 136 */
            OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().accountId(accountVo.getId()).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).startTime(startTime).endTime(endTime).build();
            /* 137 */
            BigDecimal curNum = this.orderApiService.sumOrderAmount(orderQueryDTO);
            /* 138 */
            taskProgressVo.setCurNum(curNum);
            /* 139 */
        } else if (RuleTypeEnum.PROMOTION_RULE.getType().equals(rewardRuleVo.getRuleType())) {



            /* 143 */
            AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().parentId(accountVo.getId()).build();
            /* 144 */
            List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);
            /* 145 */
            if (ObjectUtil.isEmpty(accountVoList)) {
                /* 146 */
                taskProgressVo.setCurNum(BigDecimal.ZERO);

            } else {

                /* 149 */
                List<Long> childIdList = (List<Long>) accountVoList.stream().map(BaseVo::getId).collect(Collectors.toList());





                /* 155 */
                OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().orderStatus(OrderStatusEnum.CONFIRED.getStatus()).startTime(startTime).endTime(endTime).accountIdList(childIdList).build();
                /* 156 */
                BigDecimal curNum = this.orderApiService.sumOrderAmount(orderQueryDTO);
                /* 157 */
                taskProgressVo.setCurNum(curNum);

            }
            /* 159 */
        } else if (RuleTypeEnum.RECHARGE_RULE.getType().equals(rewardRuleVo.getRuleType())) {





            /* 165 */
            RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().accountId(accountVo.getId()).status(RechargeStatusEnum.PAY_SUCCESS.getStatus()).startTime(startTime).endTime(endTime).build();
            /* 166 */
            BigDecimal curNum = this.rechargeApiService.sumRechargeAmount(rechargeQueryDTO);
            /* 167 */
            taskProgressVo.setCurNum(curNum);
            /* 168 */
        } else if (RuleTypeEnum.BALANCE_RULE.getType().equals(rewardRuleVo.getRuleType())) {

            /* 170 */
            this.redisTemplate.opsForValue().set(String.format("REWARDRECORD:%s:%s", new Object[]{String.valueOf(accountVo.getId()), LocalDateTimeUtil.format(startTime, "yyyyMMdd")}), accountVo.getAvailableBalance().stripTrailingZeros().toPlainString(), 2L, TimeUnit.DAYS);
            /* 171 */
            taskProgressVo.setCurNum(accountVo.getAvailableBalance());

        }

        /* 174 */
        if (taskProgressVo.getCurNum().compareTo(rewardRuleVo.getTotalAmount()) >= 0) {
            /* 175 */
            ((RewardCron) this.applicationContext.getBean(RewardCron.class)).doCheckIssue(accountVo, rewardRuleVo, startTime);

        }

    }












    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     public void doCheckIssue(AccountVo accountVo, RewardRuleVo rewardRuleVo, LocalDateTime startTime) {
        /* 190 */
        QueryRewardRecordDTO queryRewardRecordDTO = QueryRewardRecordDTO.builder().accountId(accountVo.getId()).type(RewardRecordTypeEnum.ADD.getType()).bizType(rewardRuleVo.getRuleType()).bizId(LocalDateTimeUtil.format(startTime, "yyyyMMdd")).build();
        /* 191 */
        RewardRecordVo rewardRecordVo = this.rewardRecordApiService.queryRewardRecord(queryRewardRecordDTO);
        /* 192 */
        if (ObjectUtil.isEmpty(rewardRecordVo)) {






            /* 199 */
            OperateRewardBalanceDTO operateRewardBalanceDTO = OperateRewardBalanceDTO.builder().accountId(accountVo.getId()).operateCount(new BigDecimal(rewardRuleVo.getRewardNum().intValue())).type(RewardRecordTypeEnum.ADD.getType()).bizType(rewardRuleVo.getRuleType()).bizId(LocalDateTimeUtil.format(startTime, "yyyyMMdd")).build();
            /* 200 */
            this.rewardBalanceApiService.operateRewardBalance(operateRewardBalanceDTO);

        }

    }







    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* 209 */
        this.applicationContext = applicationContext;

    }

}


