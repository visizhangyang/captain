
package com.porn.service.withdraw.cron;



import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.withdraw.api.WithdrawApiService;
import com.porn.client.withdraw.dto.WithdrawQueryDTO;
import com.porn.client.withdraw.dto.WithdrawSaveOrUpdateDTO;
import com.porn.client.withdraw.enums.WithdrawStatusEnum;
import com.porn.client.withdraw.vo.WithdrawVo;
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

import java.util.Arrays;
import java.util.List;





























@Component
 public class WithdrawCron
         implements ApplicationContextAware
         {
    /*  34 */   private static final Logger log = LoggerFactory.getLogger(WithdrawCron.class);


       private static final int EXPIRE_MIN = 30;



    @Autowired
     private WithdrawApiService withdrawApiService;



    @Autowired
     private ParamsetApiService paramsetApiService;



    @Autowired
     private AccountApiService accountApiService;


       private ApplicationContext applicationContext;





    @Scheduled(cron = "0/5 * * * * ?")
     public void doCompare() {
        /*  58 */
        WithdrawQueryDTO withdrawQueryDTO = WithdrawQueryDTO.builder().statusList(Arrays.asList(new Integer[]{WithdrawStatusEnum.EXAMINEING.getStatus()})).build();
        /*  59 */
        List<WithdrawVo> withdrawVoList = this.withdrawApiService.queryWithdrawList(withdrawQueryDTO);
        /*  60 */
        if (ObjectUtil.isEmpty(withdrawVoList)) {

            return;

        }


        /*  65 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        /*  68 */
        for (WithdrawVo withdrawVo : withdrawVoList) {
            /*  69 */
            if (isExpire(withdrawVo, paramsetVo)) {

                try {
                    /*  71 */
                    ((WithdrawCron) this.applicationContext.getBean(WithdrawCron.class)).doExpire(withdrawVo);
                    /*  72 */
                } catch (Exception e) {
                    /*  73 */
                    log.error(e.getMessage(), e);

                }

            }

        }

    }









    protected boolean isExpire(WithdrawVo withdrawVo, ParamsetVo paramsetVo) {
        /*  86 */
        return

                /*  88 */       (LocalDateTimeUtil.between(withdrawVo.getCreateTime(), LocalDateTimeUtil.now()).toMinutes() > (ObjectUtil.isEmpty(paramsetVo) ? 30L : ((Integer) ObjectUtil.defaultIfNull(paramsetVo.getWithdrawMatchTime(), Integer.valueOf(30))).intValue()));

    }















    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     public void doExpire(WithdrawVo withdrawVo) {
        /* 105 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(withdrawVo.getAccountId())).operateAmount(withdrawVo.getTotalAmount()).amountType(AmountTypeEnum.ADDAVAILABLE_SUBFREEZE.getType()).bizId(withdrawVo.getId()).streamTypeEnum(StreamTypeEnum.WITHDRAW_UNLOCK).build();
        /* 106 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);





        /* 112 */
        WithdrawSaveOrUpdateDTO withdrawSaveOrUpdateDTO = ((WithdrawSaveOrUpdateDTO.WithdrawSaveOrUpdateDTOBuilder) WithdrawSaveOrUpdateDTO.builder().id(withdrawVo.getId())).status(WithdrawStatusEnum.TIMEOUT.getStatus()).build();
        /* 113 */
        this.withdrawApiService.saveOrUpdate(withdrawSaveOrUpdateDTO);

    }







    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* 121 */
        this.applicationContext = applicationContext;

    }

}


