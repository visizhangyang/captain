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
        implements ApiService<WithdrawVo> {

    private final Lock lock = new ReentrantLock();
    @Autowired
    private WithdrawApiService withdrawApiService;
    @Autowired
    private AccountApiService accountApiService;
    @Autowired
    private DingdingMsgSender dingdingMsgSender;
    @Autowired
    private RedisTemplate redisTemplate;

    public WithdrawVo cmd(CmdRequestDTO cmdRequestDTO) {

        try {

            this.lock.lock();

            WithdrawSaveOrUpdateDTO withdrawSaveOrUpdateDTO = (WithdrawSaveOrUpdateDTO) JSON.parseObject(cmdRequestDTO.getData(), WithdrawSaveOrUpdateDTO.class);

            if (ObjectUtil.isEmpty(withdrawSaveOrUpdateDTO.getWalletCode())) {

                withdrawSaveOrUpdateDTO.setWalletCode(WalletChainEnum.TRON.getCode());

            }


            validate(cmdRequestDTO.getAccountVo());


            AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build();

            AccountVo accountVo = this.accountApiService.queryAccount(accountQueryDTO);

            if (ObjectUtil.isEmpty(accountVo)) {

                throw new BusinessException("账户信息不存在.");

            }


            if (ObjectUtil.isEmpty(withdrawSaveOrUpdateDTO.getTradePwd())) {

                throw new BusinessException("交易密码不能为空.");

            }


            AccountValidatePwdDTO accountValidatePwdDTO = ((AccountValidatePwdDTO.AccountValidatePwdDTOBuilder) AccountValidatePwdDTO.builder().id(accountVo.getId())).type(AccountValidateTypeEnum.TRADE_PWD.getType()).pwd(withdrawSaveOrUpdateDTO.getTradePwd()).build();

            if (!this.accountApiService.validatePwd(accountValidatePwdDTO)) {

                throw new BusinessException("交易密码不正确.");

            }


            if (accountVo.getAvailableBalance().compareTo(withdrawSaveOrUpdateDTO.getTotalAmount()) < 0) {

                throw new BusinessException("账户余额不足.");

            }


            withdrawSaveOrUpdateDTO.setAccountId(cmdRequestDTO.getAccountVo().getId());

            withdrawSaveOrUpdateDTO.setAccountName(cmdRequestDTO.getAccountVo().getName());

            withdrawSaveOrUpdateDTO.setStatus(WithdrawStatusEnum.EXAMINEING.getStatus());

            withdrawSaveOrUpdateDTO.setWalletCode(withdrawSaveOrUpdateDTO.getWalletCode());

            withdrawSaveOrUpdateDTO.setAccountRemark(accountVo.getRemark());

            withdrawSaveOrUpdateDTO.setWithdrawNo(genWithdrawNo());

            WithdrawVo withdrawVo = this.withdrawApiService.saveOrUpdate(withdrawSaveOrUpdateDTO);


            AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(accountVo.getId())).operateAmount(withdrawSaveOrUpdateDTO.getTotalAmount()).amountType(AmountTypeEnum.SUBAVAILABLE_ADDFREEZE.getType()).bizId(withdrawVo.getId()).streamTypeEnum(StreamTypeEnum.WITHDRAW_LOCK).build();

            AccountVo newAccountVo = this.accountApiService.operateAmount(accountAmountOperateDTO);


            this.dingdingMsgSender.sendMsg(
                    ProxyMsgDTO.builder()
                            .accountId(withdrawVo.getAccountId())
                            .msg("账户[" + newAccountVo.getName() + "]发起提现, 请及时审核")
                            .build());


            return withdrawVo;

        } finally {

            this.lock.unlock();

        }

    }

    protected String genWithdrawNo() {

        LocalDateTime now = LocalDateTimeUtil.now();

        String year = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "yyyy");

        String month = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "MM");

        String day = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "dd");

        String randNum = String.format("%04d", new Object[]{Integer.valueOf((new Random()).nextInt(1000))});

        String incKey = "withdraw_key" + LocalDateTimeUtil.format(now, "yyyyMMdd");

        Long incNum = this.redisTemplate.opsForValue().increment(incKey);

        this.redisTemplate.expire(incKey, 24L, TimeUnit.HOURS);

        return StrUtil.join("", new Object[]{"NO", randNum, month, year, day, String.valueOf(Math.abs(incNum.longValue()))});

    }

    protected void validate(AccountVo accountVo) {

        AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(accountVo.getId())).build();

        AccountVo dbAccountVo = this.accountApiService.queryAccount(accountQueryDTO);

        if (ObjectUtil.isEmpty(dbAccountVo)) {

            throw new BusinessException("账户信息不存在.");

        }


        if (EnableStatusEnum.DISABLED.getStatus().equals(dbAccountVo.getWithdrawStatus())) {

            throw new BusinessException("你已被禁止提现");

        }

    }


    public String getApi() {

        return "api_createwithdraw";

    }

}

