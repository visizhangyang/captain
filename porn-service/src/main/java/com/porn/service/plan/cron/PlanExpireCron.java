package com.porn.service.plan.cron;


import cn.hutool.core.util.ObjectUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.plan.api.PlanInsApiService;
import com.porn.client.plan.dto.PlanInsQueryDTO;
import com.porn.client.plan.dto.PlanInsSaveOrUpdateDTO;
import com.porn.client.plan.enums.PlanInsStatusEnum;
import com.porn.client.plan.vo.PlanInsVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PlanExpireCron
        implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(PlanExpireCron.class);


    @Autowired
    private PlanInsApiService planInsApiService;


    @Autowired
    private ParamsetApiService paramsetApiService;


    @Autowired
    private AccountApiService accountApiService;


    @Autowired
    private DingdingMsgSender dingdingMsgSender;

    private ApplicationContext applicationContext;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void doCompare() {

        PlanInsQueryDTO planInsQueryDTO = PlanInsQueryDTO.builder().status(PlanInsStatusEnum.PROGRESSING.getStatus()).build();

        List<PlanInsVo> planInsVoList = this.planInsApiService.queryPlanInsList(planInsQueryDTO);

        if (ObjectUtil.isEmpty(planInsVoList)) {

            return;

        }

        for (PlanInsVo planInsVo : planInsVoList) {

            try {

                ((PlanExpireCron) this.applicationContext.getBean(PlanExpireCron.class)).doCheckPlanExpire(planInsVo);

                try {

                    Thread.sleep(1000L);

                } catch (Exception exception) {
                }

            } catch (Exception e) {

                log.error(e.getMessage(), e);

            }

        }

    }


    public void doCheckPlanExpire(PlanInsVo planInsVo) {

        LocalDateTime now = LocalDateTime.now();

        if (now.compareTo(planInsVo.getEndTime()) < 0) {

            return;

        }

        ((PlanExpireCron) this.applicationContext.getBean(PlanExpireCron.class)).doPlanExpire(planInsVo);

    }


    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void doPlanExpire(PlanInsVo planInsVo) {

        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(planInsVo.getAccountId())).build());

        if (ObjectUtil.isEmpty(accountVo)) {

            return;

        }


        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(planInsVo.getAccountId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(planInsVo.getId()).streamTypeEnum(StreamTypeEnum.PLAN_UNLOCK).operateAmount(planInsVo.getTotalInvest()).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);

        PlanInsSaveOrUpdateDTO planInsSaveOrUpdateDTO = ((PlanInsSaveOrUpdateDTO.PlanInsSaveOrUpdateDTOBuilder) PlanInsSaveOrUpdateDTO.builder().id(planInsVo.getId())).status(PlanInsStatusEnum.COMPLETED.getStatus()).build();

        this.planInsApiService.saveOrUpdate(planInsSaveOrUpdateDTO);


        this.dingdingMsgSender.sendMsg(
                ProxyMsgDTO.builder()
                        .accountId(planInsVo.getAccountId())
                        .msg("账户[" + accountVo.getName() + "], 自动搬砖计划到期, 总金额[" + planInsVo.getTotalInvest().stripTrailingZeros().toPlainString() + "], 请重点关注.")
                        .build());

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

    }

}

