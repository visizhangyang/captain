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
        implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(WithdrawCron.class);

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

        WithdrawQueryDTO withdrawQueryDTO = WithdrawQueryDTO.builder().statusList(Arrays.asList(new Integer[]{WithdrawStatusEnum.EXAMINEING.getStatus()})).build();

        List<WithdrawVo> withdrawVoList = this.withdrawApiService.queryWithdrawList(withdrawQueryDTO);

        if (ObjectUtil.isEmpty(withdrawVoList)) {

            return;

        }


        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        for (WithdrawVo withdrawVo : withdrawVoList) {

            if (isExpire(withdrawVo, paramsetVo)) {

                try {

                    ((WithdrawCron) this.applicationContext.getBean(WithdrawCron.class)).doExpire(withdrawVo);

                } catch (Exception e) {

                    log.error(e.getMessage(), e);

                }

            }

        }

    }


    protected boolean isExpire(WithdrawVo withdrawVo, ParamsetVo paramsetVo) {

        return

                (LocalDateTimeUtil.between(withdrawVo.getCreateTime(), LocalDateTimeUtil.now()).toMinutes() > (ObjectUtil.isEmpty(paramsetVo) ? 30L : ((Integer) ObjectUtil.defaultIfNull(paramsetVo.getWithdrawMatchTime(), Integer.valueOf(30))).intValue()));

    }


    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void doExpire(WithdrawVo withdrawVo) {

        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(withdrawVo.getAccountId())).operateAmount(withdrawVo.getTotalAmount()).amountType(AmountTypeEnum.ADDAVAILABLE_SUBFREEZE.getType()).bizId(withdrawVo.getId()).streamTypeEnum(StreamTypeEnum.WITHDRAW_UNLOCK).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);


        WithdrawSaveOrUpdateDTO withdrawSaveOrUpdateDTO = ((WithdrawSaveOrUpdateDTO.WithdrawSaveOrUpdateDTOBuilder) WithdrawSaveOrUpdateDTO.builder().id(withdrawVo.getId())).status(WithdrawStatusEnum.TIMEOUT.getStatus()).build();

        this.withdrawApiService.saveOrUpdate(withdrawSaveOrUpdateDTO);

    }


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

    }

}

