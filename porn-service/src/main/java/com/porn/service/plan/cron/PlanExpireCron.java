
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
         implements ApplicationContextAware
         {
    /*  35 */   private static final Logger log = LoggerFactory.getLogger(PlanExpireCron.class);



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
        /*  58 */
        PlanInsQueryDTO planInsQueryDTO = PlanInsQueryDTO.builder().status(PlanInsStatusEnum.PROGRESSING.getStatus()).build();
        /*  59 */
        List<PlanInsVo> planInsVoList = this.planInsApiService.queryPlanInsList(planInsQueryDTO);
        /*  60 */
        if (ObjectUtil.isEmpty(planInsVoList)) {

            return;

        }
        /*  63 */
        for (PlanInsVo planInsVo : planInsVoList) {

            try {
                /*  65 */
                ((PlanExpireCron) this.applicationContext.getBean(PlanExpireCron.class)).doCheckPlanExpire(planInsVo);

                try {
                    /*  67 */
                    Thread.sleep(1000L);
                    /*  68 */
                } catch (Exception exception) {
                }


            }
            /*  71 */ catch (Exception e) {
                /*  72 */
                log.error(e.getMessage(), e);

            }

        }

    }







    public void doCheckPlanExpire(PlanInsVo planInsVo) {
        /*  82 */
        LocalDateTime now = LocalDateTime.now();
        /*  83 */
        if (now.compareTo(planInsVo.getEndTime()) < 0) {

            return;

        }
        /*  86 */
        ((PlanExpireCron) this.applicationContext.getBean(PlanExpireCron.class)).doPlanExpire(planInsVo);

    }



    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     public void doPlanExpire(PlanInsVo planInsVo) {
        /*  91 */
        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(planInsVo.getAccountId())).build());
        /*  92 */
        if (ObjectUtil.isEmpty(accountVo)) {

            return;

        }








        /* 103 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(planInsVo.getAccountId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(planInsVo.getId()).streamTypeEnum(StreamTypeEnum.PLAN_UNLOCK).operateAmount(planInsVo.getTotalInvest()).build();
        /* 104 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);





        /* 110 */
        PlanInsSaveOrUpdateDTO planInsSaveOrUpdateDTO = ((PlanInsSaveOrUpdateDTO.PlanInsSaveOrUpdateDTOBuilder) PlanInsSaveOrUpdateDTO.builder().id(planInsVo.getId())).status(PlanInsStatusEnum.COMPLETED.getStatus()).build();
        /* 111 */
        this.planInsApiService.saveOrUpdate(planInsSaveOrUpdateDTO);


        /* 114 */
        this.dingdingMsgSender.sendMsg(
                /* 115 */         ProxyMsgDTO.builder()
/* 116 */.accountId(planInsVo.getAccountId())
/* 117 */.msg("账户[" + accountVo.getName() + "], 自动搬砖计划到期, 总金额[" + planInsVo.getTotalInvest().stripTrailingZeros().toPlainString() + "], 请重点关注.")
/* 118 */.build());

    }








    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* 127 */
        this.applicationContext = applicationContext;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/plan/cron/PlanExpireCron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */