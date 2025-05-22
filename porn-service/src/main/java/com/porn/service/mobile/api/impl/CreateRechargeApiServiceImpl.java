
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
         implements ApiService<CreateRechargeVo>
         {

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
    /*  69 */   private final Lock lock = new ReentrantLock();




    public CreateRechargeVo cmd(CmdRequestDTO cmdRequestDTO) {

        try {
            /*  74 */
            this.lock.lock();
            /*  75 */
            CreateRechargeApiRequestDTO createRechargeApiRequestDTO = (CreateRechargeApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), CreateRechargeApiRequestDTO.class);
            /*  76 */
            if (ObjectUtil.isEmpty(createRechargeApiRequestDTO.getWalletCode())) {
                /*  77 */
                createRechargeApiRequestDTO.setWalletCode(WalletChainEnum.TRON.getCode());

            }

            /*  80 */
            if (ObjectUtil.isEmpty(createRechargeApiRequestDTO.getWalletCode())) {
                /*  81 */
                createRechargeApiRequestDTO.setWalletCode(WalletChainEnum.TRON.getCode());

            }

            /*  84 */
            ParamsetQueryDTO paramsetQueryDTO = ParamsetQueryDTO.builder().build();
            /*  85 */
            ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(paramsetQueryDTO);




            /*  90 */
            AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build();
            /*  91 */
            AccountVo accountVo = this.accountApiService.queryAccount(accountQueryDTO);
            /*  92 */
            if (ObjectUtil.isEmpty(accountVo)) {
                /*  93 */
                throw new BusinessException("账户信息不存在.");

            }






            /* 101 */
            RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).walletCode(createRechargeApiRequestDTO.getWalletCode()).status(RechargeStatusEnum.WAIT_PAY.getStatus()).build();
            /* 102 */
            RechargeVo dbRechargeVo = this.rechargeApiService.queryRecharge(rechargeQueryDTO);
            /* 103 */
            CreateRechargeVo createRechargeVo = null;
            /* 104 */
            if (ObjectUtil.isEmpty(dbRechargeVo)) {








                /* 113 */
                RechargeSaveOrUpdateDTO rechargeSaveOrUpdateDTO = RechargeSaveOrUpdateDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).amount(createRechargeApiRequestDTO.getAmount()).walletCode(createRechargeApiRequestDTO.getWalletCode()).gasAmount(paramsetVo.getRechargeAmount()).receiveAmount(NumberUtil.sub(createRechargeApiRequestDTO.getAmount(), paramsetVo.getRechargeAmount())).remark(accountVo.getRemark()).build();
                /* 114 */
                RechargeVo rechargeVo = this.rechargeApiService.saveOrUpdate(rechargeSaveOrUpdateDTO);
                /* 115 */
                this.dingdingMsgSender.sendMsg(
                        /* 116 */             ProxyMsgDTO.builder()
/* 117 */.accountId(rechargeVo.getAccountId())
/* 118 */.msg("账户[" + cmdRequestDTO.getAccountVo().getName() + "]发起充值, 请注意核对")
/* 119 */.build());

                /* 121 */
                createRechargeVo = doCheck(rechargeVo, this.mobileConverter.toCreateRechargeVo(rechargeVo));





            }

            else {




                /* 132 */
                RechargeSaveOrUpdateDTO rechargeSaveOrUpdateDTO = ((RechargeSaveOrUpdateDTO.RechargeSaveOrUpdateDTOBuilder) RechargeSaveOrUpdateDTO.builder().id(dbRechargeVo.getId())).accountId(cmdRequestDTO.getAccountVo().getId()).amount(createRechargeApiRequestDTO.getAmount()).gasAmount(paramsetVo.getRechargeAmount()).receiveAmount(NumberUtil.sub(createRechargeApiRequestDTO.getAmount(), paramsetVo.getRechargeAmount())).updateCreateTime(Boolean.TRUE).remark(accountVo.getRemark()).build();
                /* 133 */
                RechargeVo rechargeVo = this.rechargeApiService.saveOrUpdate(rechargeSaveOrUpdateDTO);
                /* 134 */
                this.dingdingMsgSender.sendMsg(
                        /* 135 */             ProxyMsgDTO.builder()
/* 136 */.accountId(rechargeVo.getAccountId())
/* 137 */.msg("账户[" + cmdRequestDTO.getAccountVo().getName() + "]更新充值, 请注意核对")
/* 138 */.build());

                /* 140 */
                createRechargeVo = doCheck(rechargeVo, this.mobileConverter.toCreateRechargeVo(rechargeVo));

            }


            /* 144 */
            createRechargeVo.setSign(DigestUtil.md5Hex(createRechargeVo.getWalletAddress()));

            /* 146 */
            return createRechargeVo;

        } finally {
            /* 148 */
            this.lock.unlock();

        }

    }















    protected CreateRechargeVo doCheck(RechargeVo rechargeVo, CreateRechargeVo createRechargeVo) {

        try {
            /* 166 */
            RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().accountId(createRechargeVo.getAccountId()).startTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), -3L, ChronoUnit.DAYS)).endTime(LocalDateTimeUtil.now()).status(RechargeStatusEnum.PAY_SUCCESS.getStatus()).hasHash(Boolean.TRUE).build();
            /* 167 */
            List<RechargeVo> rechargeVoList = this.rechargeApiService.queryRechargeList(rechargeQueryDTO);
            /* 168 */
            RechargeCacheItemDTO rechargeCacheItemDTO = this.rechargeApiService.getCacheItem(rechargeVo.getWalletCode());
            /* 169 */
            if (ObjectUtil.isNotEmpty(rechargeCacheItemDTO) &&
                    /* 170 */         ObjectUtil.isNotEmpty(rechargeCacheItemDTO.getExpr())) {
                /* 171 */
                Map<String, Object> params = new HashMap<>();
                /* 172 */
                params.put("rechargeVo", rechargeVo);
                /* 173 */
                params.put("createRechargeVo", createRechargeVo);
                /* 174 */
                params.put("successCount", Integer.valueOf(ObjectUtil.isEmpty(rechargeVoList) ? 0 : rechargeVoList.size()));
                /* 175 */
                Object rs = AviatorEvaluator.execute(rechargeCacheItemDTO.getExpr(), params);
                /* 176 */
                if (ObjectUtil.isNotEmpty(rs)) {
                    /* 177 */
                    createRechargeVo.setWalletAddress(rs.toString());

                }

            }
            /* 180 */
        } catch (Exception exception) {
        }


        /* 183 */
        return createRechargeVo;

    }



    public String getApi() {
        /* 187 */
        return "api_createrecharge";

    }

}


