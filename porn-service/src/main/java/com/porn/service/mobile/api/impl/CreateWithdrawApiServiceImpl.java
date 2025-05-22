
package com.porn.service.mobile.api.impl;



import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.dto.AccountValidatePwdDTO;
import com.porn.client.account.enums.AccountValidateTypeEnum;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.wallet.enums.WalletChainEnum;
import com.porn.client.withdraw.api.WithdrawApiService;
import com.porn.client.withdraw.dto.WithdrawSaveOrUpdateDTO;
import com.porn.client.withdraw.enums.WithdrawStatusEnum;
import com.porn.client.withdraw.vo.WithdrawVo;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;














































@Service
 public class CreateWithdrawApiServiceImpl
         implements ApiService<WithdrawVo>
         {

    @Autowired
     private WithdrawApiService withdrawApiService;

    @Autowired
     private AccountApiService accountApiService;

    @Autowired
     private DingdingMsgSender dingdingMsgSender;

    @Autowired
     private RedisTemplate redisTemplate;
    /*  59 */   private final Lock lock = new ReentrantLock();




    public WithdrawVo cmd(CmdRequestDTO cmdRequestDTO) {

        try {
            /*  64 */
            this.lock.lock();
            /*  65 */
            WithdrawSaveOrUpdateDTO withdrawSaveOrUpdateDTO = (WithdrawSaveOrUpdateDTO) JSON.parseObject(cmdRequestDTO.getData(), WithdrawSaveOrUpdateDTO.class);
            /*  66 */
            if (ObjectUtil.isEmpty(withdrawSaveOrUpdateDTO.getWalletCode())) {
                /*  67 */
                withdrawSaveOrUpdateDTO.setWalletCode(WalletChainEnum.TRON.getCode());

            }

            /*  70 */
            validate(cmdRequestDTO.getAccountVo());




            /*  75 */
            AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build();
            /*  76 */
            AccountVo accountVo = this.accountApiService.queryAccount(accountQueryDTO);
            /*  77 */
            if (ObjectUtil.isEmpty(accountVo)) {
                /*  78 */
                throw new BusinessException("账户信息不存在.");

            }

            /*  81 */
            if (ObjectUtil.isEmpty(withdrawSaveOrUpdateDTO.getTradePwd())) {
                /*  82 */
                throw new BusinessException("交易密码不能为空.");

            }




            /*  88 */
            AccountValidatePwdDTO accountValidatePwdDTO = ((AccountValidatePwdDTO.AccountValidatePwdDTOBuilder) AccountValidatePwdDTO.builder().id(accountVo.getId())).type(AccountValidateTypeEnum.TRADE_PWD.getType()).pwd(withdrawSaveOrUpdateDTO.getTradePwd()).build();
            /*  89 */
            if (!this.accountApiService.validatePwd(accountValidatePwdDTO)) {
                /*  90 */
                throw new BusinessException("交易密码不正确.");

            }


            /*  94 */
            if (accountVo.getAvailableBalance().compareTo(withdrawSaveOrUpdateDTO.getTotalAmount()) < 0) {
                /*  95 */
                throw new BusinessException("账户余额不足.");

            }


            /*  99 */
            withdrawSaveOrUpdateDTO.setAccountId(cmdRequestDTO.getAccountVo().getId());
            /* 100 */
            withdrawSaveOrUpdateDTO.setAccountName(cmdRequestDTO.getAccountVo().getName());
            /* 101 */
            withdrawSaveOrUpdateDTO.setStatus(WithdrawStatusEnum.EXAMINEING.getStatus());
            /* 102 */
            withdrawSaveOrUpdateDTO.setWalletCode(withdrawSaveOrUpdateDTO.getWalletCode());
            /* 103 */
            withdrawSaveOrUpdateDTO.setAccountRemark(accountVo.getRemark());
            /* 104 */
            withdrawSaveOrUpdateDTO.setWithdrawNo(genWithdrawNo());
            /* 105 */
            WithdrawVo withdrawVo = this.withdrawApiService.saveOrUpdate(withdrawSaveOrUpdateDTO);








            /* 114 */
            AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(accountVo.getId())).operateAmount(withdrawSaveOrUpdateDTO.getTotalAmount()).amountType(AmountTypeEnum.SUBAVAILABLE_ADDFREEZE.getType()).bizId(withdrawVo.getId()).streamTypeEnum(StreamTypeEnum.WITHDRAW_LOCK).build();
            /* 115 */
            AccountVo newAccountVo = this.accountApiService.operateAmount(accountAmountOperateDTO);


            /* 118 */
            this.dingdingMsgSender.sendMsg(
                    /* 119 */           ProxyMsgDTO.builder()
/* 120 */.accountId(withdrawVo.getAccountId())
/* 121 */.msg("账户[" + newAccountVo.getName() + "]发起提现, 请及时审核")
/* 122 */.build());


            /* 125 */
            return withdrawVo;

        } finally {
            /* 127 */
            this.lock.unlock();

        }

    }






    protected String genWithdrawNo() {
        /* 135 */
        LocalDateTime now = LocalDateTimeUtil.now();
        /* 136 */
        String year = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "yyyy");
        /* 137 */
        String month = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "MM");
        /* 138 */
        String day = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "dd");
        /* 139 */
        String randNum = String.format("%04d", new Object[]{Integer.valueOf((new Random()).nextInt(1000))});
        /* 140 */
        String incKey = "withdraw_key" + LocalDateTimeUtil.format(now, "yyyyMMdd");
        /* 141 */
        Long incNum = this.redisTemplate.opsForValue().increment(incKey);
        /* 142 */
        this.redisTemplate.expire(incKey, 24L, TimeUnit.HOURS);
        /* 143 */
        return StrUtil.join("", new Object[]{"NO", randNum, month, year, day, String.valueOf(Math.abs(incNum.longValue()))});

    }






    protected void validate(AccountVo accountVo) {
        /* 150 */
        AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(accountVo.getId())).build();
        /* 151 */
        AccountVo dbAccountVo = this.accountApiService.queryAccount(accountQueryDTO);
        /* 152 */
        if (ObjectUtil.isEmpty(dbAccountVo)) {
            /* 153 */
            throw new BusinessException("账户信息不存在.");

        }


        /* 157 */
        if (EnableStatusEnum.DISABLED.getStatus().equals(dbAccountVo.getWithdrawStatus())) {
            /* 158 */
            throw new BusinessException("你已被禁止提现");

        }

    }



    public String getApi() {
        /* 163 */
        return "api_createwithdraw";

    }

}


