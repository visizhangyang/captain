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
        implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(RewardCron.class);


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

        List<RewardRuleVo> rewardRuleVoList = this.rewardRuleApiService.queryRewardRuleList(
                RewardRuleQueryDTO.builder()
                        .langType(LangTypeEnum.ZH.getType())
                        .build());


        if (ObjectUtil.isEmpty(rewardRuleVoList)) {

            return;

        }


        AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().accountType(AccountTypeEnum.NORMAL.getType()).build();

        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);

        if (ObjectUtil.isEmpty(accountVoList)) {

            return;

        }

        for (RewardRuleVo rewardRuleVo : rewardRuleVoList) {

            for (AccountVo accountVo : accountVoList) {

                try {

                    doCheckLrantLottery(rewardRuleVo, accountVo);

                } catch (Exception e) {

                    log.error(e.getMessage(), e);

                }

            }

        }

    }

    protected void doCheckLrantLottery(RewardRuleVo rewardRuleVo, AccountVo accountVo) {

        TaskProgressVo taskProgressVo = TaskProgressVo.builder().name(rewardRuleVo.getName()).subName(rewardRuleVo.getSubName()).ruleType(rewardRuleVo.getRuleType()).ruleImgUrl(rewardRuleVo.getRuleImgUrl()).totalNum(rewardRuleVo.getTotalAmount().setScale(0)).rewardNum(rewardRuleVo.getRewardNum()).expireTime(LocalDateTimeUtil.format(LocalDateTimeUtil.endOfDay(LocalDateTime.now()), "yyyy-MM-dd HH:mm:ss")).build();

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startTime = LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.offset(now, -1L, ChronoUnit.DAYS));

        LocalDateTime endTime = LocalDateTimeUtil.endOfDay(LocalDateTimeUtil.offset(now, -1L, ChronoUnit.DAYS));

        if (RuleTypeEnum.WORK_RULE.getType().equals(rewardRuleVo.getRuleType())) {


            OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().accountId(accountVo.getId()).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).startTime(startTime).endTime(endTime).build();

            BigDecimal curNum = this.orderApiService.sumOrderAmount(orderQueryDTO);

            taskProgressVo.setCurNum(curNum);

        } else if (RuleTypeEnum.PROMOTION_RULE.getType().equals(rewardRuleVo.getRuleType())) {

            AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().parentId(accountVo.getId()).build();

            List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);

            if (ObjectUtil.isEmpty(accountVoList)) {

                taskProgressVo.setCurNum(BigDecimal.ZERO);

            } else {

                List<Long> childIdList = (List<Long>) accountVoList.stream().map(BaseVo::getId).collect(Collectors.toList());

                OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().orderStatus(OrderStatusEnum.CONFIRED.getStatus()).startTime(startTime).endTime(endTime).accountIdList(childIdList).build();

                BigDecimal curNum = this.orderApiService.sumOrderAmount(orderQueryDTO);

                taskProgressVo.setCurNum(curNum);

            }

        } else if (RuleTypeEnum.RECHARGE_RULE.getType().equals(rewardRuleVo.getRuleType())) {

            RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().accountId(accountVo.getId()).status(RechargeStatusEnum.PAY_SUCCESS.getStatus()).startTime(startTime).endTime(endTime).build();

            BigDecimal curNum = this.rechargeApiService.sumRechargeAmount(rechargeQueryDTO);

            taskProgressVo.setCurNum(curNum);

        } else if (RuleTypeEnum.BALANCE_RULE.getType().equals(rewardRuleVo.getRuleType())) {

            this.redisTemplate.opsForValue().set(String.format("REWARDRECORD:%s:%s", new Object[]{String.valueOf(accountVo.getId()), LocalDateTimeUtil.format(startTime, "yyyyMMdd")}), accountVo.getAvailableBalance().stripTrailingZeros().toPlainString(), 2L, TimeUnit.DAYS);

            taskProgressVo.setCurNum(accountVo.getAvailableBalance());

        }

        if (taskProgressVo.getCurNum().compareTo(rewardRuleVo.getTotalAmount()) >= 0) {

            ((RewardCron) this.applicationContext.getBean(RewardCron.class)).doCheckIssue(accountVo, rewardRuleVo, startTime);

        }

    }

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void doCheckIssue(AccountVo accountVo, RewardRuleVo rewardRuleVo, LocalDateTime startTime) {

        QueryRewardRecordDTO queryRewardRecordDTO = QueryRewardRecordDTO.builder().accountId(accountVo.getId()).type(RewardRecordTypeEnum.ADD.getType()).bizType(rewardRuleVo.getRuleType()).bizId(LocalDateTimeUtil.format(startTime, "yyyyMMdd")).build();

        RewardRecordVo rewardRecordVo = this.rewardRecordApiService.queryRewardRecord(queryRewardRecordDTO);

        if (ObjectUtil.isEmpty(rewardRecordVo)) {


            OperateRewardBalanceDTO operateRewardBalanceDTO = OperateRewardBalanceDTO.builder().accountId(accountVo.getId()).operateCount(new BigDecimal(rewardRuleVo.getRewardNum().intValue())).type(RewardRecordTypeEnum.ADD.getType()).bizType(rewardRuleVo.getRuleType()).bizId(LocalDateTimeUtil.format(startTime, "yyyyMMdd")).build();

            this.rewardBalanceApiService.operateRewardBalance(operateRewardBalanceDTO);

        }

    }


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

    }

}

