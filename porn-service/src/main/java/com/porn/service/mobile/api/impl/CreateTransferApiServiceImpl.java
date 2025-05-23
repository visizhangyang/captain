package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.dto.AccountValidatePwdDTO;
import com.porn.client.account.enums.AccountValidateTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.transfer.api.TransferApiService;
import com.porn.client.transfer.dto.TransferSaveOrUpdateDTO;
import com.porn.client.transfer.vo.TransferVo;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import com.porn.service.mobile.api.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Service

@Transactional(rollbackFor = {Exception.class})
public class CreateTransferApiServiceImpl
        implements ApiService<TransferVo> {
    private static final Logger log = LoggerFactory.getLogger(CreateTransferApiServiceImpl.class);
    private final Lock lock = new ReentrantLock();
    @Autowired
    private TransferApiService transferApiService;
    @Autowired
    private AccountApiService accountApiService;
    @Autowired
    private DingdingMsgSender dingdingMsgSender;

    public TransferVo cmd(CmdRequestDTO cmdRequestDTO) {

        try {

            this.lock.lock();

            TransferSaveOrUpdateDTO transferSaveOrUpdateDTO = (TransferSaveOrUpdateDTO) JSON.parseObject(cmdRequestDTO.getData(), TransferSaveOrUpdateDTO.class);


            validate(cmdRequestDTO.getAccountVo());


            if (ObjectUtil.isEmpty(transferSaveOrUpdateDTO.getTradePwd())) {

                throw new BusinessException("交易密码不能为空.");

            }


            AccountValidatePwdDTO accountValidatePwdDTO = ((AccountValidatePwdDTO.AccountValidatePwdDTOBuilder) AccountValidatePwdDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).type(AccountValidateTypeEnum.TRADE_PWD.getType()).pwd(transferSaveOrUpdateDTO.getTradePwd()).build();

            if (!this.accountApiService.validatePwd(accountValidatePwdDTO)) {

                throw new BusinessException("交易密码不正确.");

            }

            transferSaveOrUpdateDTO.setSrcAccountId(cmdRequestDTO.getAccountVo().getId());

            transferSaveOrUpdateDTO.setSrcAccountName(cmdRequestDTO.getAccountVo().getName());

            TransferVo transferVo = this.transferApiService.saveOrUpdate(transferSaveOrUpdateDTO);

            this.dingdingMsgSender.sendMsg(
                    ProxyMsgDTO.builder()
                            .accountId(transferVo.getSrcAccountId())
                            .msg("账户[" + cmdRequestDTO.getAccountVo().getName() + "]发起转账, 请及时审核")
                            .build());


            return transferVo;

        } finally {

            this.lock.unlock();

        }

    }

    protected void validate(AccountVo accountVo) {

        AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(accountVo.getId())).build();

        AccountVo dbAccountVo = this.accountApiService.queryAccount(accountQueryDTO);

        if (ObjectUtil.isEmpty(dbAccountVo)) {

            throw new BusinessException("账户信息不存在.");

        }


        if (EnableStatusEnum.DISABLED.getStatus().equals(dbAccountVo.getTransferStatus())) {

            throw new BusinessException("你已被禁止转账");

        }

    }


    public String getApi() {

        return "api_createtransfer";

    }

}

