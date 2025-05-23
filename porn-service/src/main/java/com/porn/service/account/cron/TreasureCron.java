package com.porn.service.account.cron;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AccountLevelEnum;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class TreasureCron
        implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(TreasureCron.class);


    @Autowired
    private AccountApiService accountApiService;


    @Autowired
    private ParamsetApiService paramsetApiService;

    private ApplicationContext applicationContext;

    @Scheduled(cron = "0 0 1 * * ?")
    public void doCompare() {

        AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().build();

        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);

        if (ObjectUtil.isEmpty(accountVoList)) {

            return;

        }

        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());

        for (AccountVo accountVo : accountVoList) {

            try {

                if (BigDecimal.ZERO.compareTo(accountVo.getAvailableBalance()) >= 0) {

                    continue;

                }

                BigDecimal amount = NumberUtil.mul(accountVo.getAvailableBalance(), NumberUtil.div((accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel()) ? paramsetVo.getNormalTreasureRate() : ((accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) ? paramsetVo.getLargeTreasureRate() : paramsetVo.getPartnerTreasureRate()), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

                AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(accountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(accountVo.getId()).streamTypeEnum(StreamTypeEnum.TREASURE).operateAmount(amount).build();

                this.accountApiService.operateAmount(accountAmountOperateDTO);

            } catch (Exception e) {

                log.error("余U宝利润计算异常, 账户[{}], 异常[{}]", JSON.toJSONString(accountVo), e);

            }

        }

    }


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

    }

}

