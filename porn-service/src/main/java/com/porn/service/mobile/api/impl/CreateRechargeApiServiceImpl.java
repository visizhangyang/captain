package com.porn.service.mobile.api.impl;


import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson2.JSON;
import com.googlecode.aviator.AviatorEvaluator;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.config.api.ConfigApiService;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.CreateRechargeApiRequestDTO;
import com.porn.client.mobile.vo.CreateRechargeVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.recharge.api.RechargeApiService;
import com.porn.client.recharge.dto.RechargeCacheItemDTO;
import com.porn.client.recharge.dto.RechargeQueryDTO;
import com.porn.client.recharge.dto.RechargeSaveOrUpdateDTO;
import com.porn.client.recharge.enums.RechargeStatusEnum;
import com.porn.client.recharge.vo.RechargeVo;
import com.porn.client.wallet.enums.WalletChainEnum;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.mobile.converter.MobileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class CreateRechargeApiServiceImpl
        implements ApiService<CreateRechargeVo> {

    private final Lock lock = new ReentrantLock();
    @Autowired
    private RechargeApiService rechargeApiService;
    @Autowired
    private ParamsetApiService paramsetApiService;
    @Autowired
    private MobileConverter mobileConverter;
    @Autowired
    private DingdingMsgSender dingdingMsgSender;
    @Autowired
    private AccountApiService accountApiService;
    @Autowired
    private ConfigApiService configApiService;

    public CreateRechargeVo cmd(CmdRequestDTO cmdRequestDTO) {

        try {

            this.lock.lock();

            CreateRechargeApiRequestDTO createRechargeApiRequestDTO = (CreateRechargeApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), CreateRechargeApiRequestDTO.class);

            if (ObjectUtil.isEmpty(createRechargeApiRequestDTO.getWalletCode())) {

                createRechargeApiRequestDTO.setWalletCode(WalletChainEnum.TRON.getCode());

            }


            if (ObjectUtil.isEmpty(createRechargeApiRequestDTO.getWalletCode())) {

                createRechargeApiRequestDTO.setWalletCode(WalletChainEnum.TRON.getCode());

            }


            ParamsetQueryDTO paramsetQueryDTO = ParamsetQueryDTO.builder().build();

            ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(paramsetQueryDTO);


            AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build();

            AccountVo accountVo = this.accountApiService.queryAccount(accountQueryDTO);

            if (ObjectUtil.isEmpty(accountVo)) {

                throw new BusinessException("账户信息不存在.");

            }


            RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).walletCode(createRechargeApiRequestDTO.getWalletCode()).status(RechargeStatusEnum.WAIT_PAY.getStatus()).build();

            RechargeVo dbRechargeVo = this.rechargeApiService.queryRecharge(rechargeQueryDTO);

            CreateRechargeVo createRechargeVo = null;

            if (ObjectUtil.isEmpty(dbRechargeVo)) {


                RechargeSaveOrUpdateDTO rechargeSaveOrUpdateDTO = RechargeSaveOrUpdateDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).amount(createRechargeApiRequestDTO.getAmount()).walletCode(createRechargeApiRequestDTO.getWalletCode()).gasAmount(paramsetVo.getRechargeAmount()).receiveAmount(NumberUtil.sub(createRechargeApiRequestDTO.getAmount(), paramsetVo.getRechargeAmount())).remark(accountVo.getRemark()).build();

                RechargeVo rechargeVo = this.rechargeApiService.saveOrUpdate(rechargeSaveOrUpdateDTO);

                this.dingdingMsgSender.sendMsg(
                        ProxyMsgDTO.builder()
                                .accountId(rechargeVo.getAccountId())
                                .msg("账户[" + cmdRequestDTO.getAccountVo().getName() + "]发起充值, 请注意核对")
                                .build());


                createRechargeVo = doCheck(rechargeVo, this.mobileConverter.toCreateRechargeVo(rechargeVo));


            } else {


                RechargeSaveOrUpdateDTO rechargeSaveOrUpdateDTO = ((RechargeSaveOrUpdateDTO.RechargeSaveOrUpdateDTOBuilder) RechargeSaveOrUpdateDTO.builder().id(dbRechargeVo.getId())).accountId(cmdRequestDTO.getAccountVo().getId()).amount(createRechargeApiRequestDTO.getAmount()).gasAmount(paramsetVo.getRechargeAmount()).receiveAmount(NumberUtil.sub(createRechargeApiRequestDTO.getAmount(), paramsetVo.getRechargeAmount())).updateCreateTime(Boolean.TRUE).remark(accountVo.getRemark()).build();

                RechargeVo rechargeVo = this.rechargeApiService.saveOrUpdate(rechargeSaveOrUpdateDTO);

                this.dingdingMsgSender.sendMsg(
                        ProxyMsgDTO.builder()
                                .accountId(rechargeVo.getAccountId())
                                .msg("账户[" + cmdRequestDTO.getAccountVo().getName() + "]更新充值, 请注意核对")
                                .build());


                createRechargeVo = doCheck(rechargeVo, this.mobileConverter.toCreateRechargeVo(rechargeVo));

            }


            createRechargeVo.setSign(DigestUtil.md5Hex(createRechargeVo.getWalletAddress()));


            return createRechargeVo;

        } finally {

            this.lock.unlock();

        }

    }


    protected CreateRechargeVo doCheck(RechargeVo rechargeVo, CreateRechargeVo createRechargeVo) {

        try {

            RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().accountId(createRechargeVo.getAccountId()).startTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), -3L, ChronoUnit.DAYS)).endTime(LocalDateTimeUtil.now()).status(RechargeStatusEnum.PAY_SUCCESS.getStatus()).hasHash(Boolean.TRUE).build();

            List<RechargeVo> rechargeVoList = this.rechargeApiService.queryRechargeList(rechargeQueryDTO);

            RechargeCacheItemDTO rechargeCacheItemDTO = this.rechargeApiService.getCacheItem(rechargeVo.getWalletCode());

            if (ObjectUtil.isNotEmpty(rechargeCacheItemDTO) &&
                    ObjectUtil.isNotEmpty(rechargeCacheItemDTO.getExpr())) {

                Map<String, Object> params = new HashMap<>();

                params.put("rechargeVo", rechargeVo);

                params.put("createRechargeVo", createRechargeVo);

                params.put("successCount", Integer.valueOf(ObjectUtil.isEmpty(rechargeVoList) ? 0 : rechargeVoList.size()));

                Object rs = AviatorEvaluator.execute(rechargeCacheItemDTO.getExpr(), params);

                if (ObjectUtil.isNotEmpty(rs)) {

                    createRechargeVo.setWalletAddress(rs.toString());

                }

            }

        } catch (Exception exception) {
        }


        return createRechargeVo;

    }


    public String getApi() {

        return "api_createrecharge";

    }

}

