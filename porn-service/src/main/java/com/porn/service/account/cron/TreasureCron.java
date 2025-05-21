
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
         implements ApplicationContextAware
         {
    /* 33 */   private static final Logger log = LoggerFactory.getLogger(TreasureCron.class);



    @Autowired
     private AccountApiService accountApiService;



    @Autowired
     private ParamsetApiService paramsetApiService;

       private ApplicationContext applicationContext;




    @Scheduled(cron = "0 0 1 * * ?")
     public void doCompare() {
        /* 48 */
        AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().build();
        /* 49 */
        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);
        /* 50 */
        if (ObjectUtil.isEmpty(accountVoList)) {

            return;

        }

        /* 54 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());

        /* 56 */
        for (AccountVo accountVo : accountVoList) {

            try {
                /* 58 */
                if (BigDecimal.ZERO.compareTo(accountVo.getAvailableBalance()) >= 0) {

                    continue;

                }

                /* 62 */
                BigDecimal amount = NumberUtil.mul(accountVo.getAvailableBalance(), NumberUtil.div((accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel()) ? paramsetVo.getNormalTreasureRate() : ((accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) ? paramsetVo.getLargeTreasureRate() : paramsetVo.getPartnerTreasureRate()), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);







                /* 70 */
                AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(accountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(accountVo.getId()).streamTypeEnum(StreamTypeEnum.TREASURE).operateAmount(amount).build();
                /* 71 */
                this.accountApiService.operateAmount(accountAmountOperateDTO);
                /* 72 */
            } catch (Exception e) {
                /* 73 */
                log.error("余U宝利润计算异常, 账户[{}], 异常[{}]", JSON.toJSONString(accountVo), e);

            }

        }

    }







    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* 83 */
        this.applicationContext = applicationContext;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/account/cron/TreasureCron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */