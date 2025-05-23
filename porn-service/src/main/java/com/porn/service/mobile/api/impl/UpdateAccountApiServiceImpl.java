package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HtmlUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.api.AccountWalletService;
import com.porn.client.account.dto.AccountSaveOrUpdateDTO;
import com.porn.client.account.dto.AccountWalletQueryDTO;
import com.porn.client.account.dto.AccountWalletSaveOrUpdateDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.account.vo.AccountWalletVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.SaveReceiveAddressApiRequestDTO;
import com.porn.client.wallet.enums.WalletChainEnum;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateAccountApiServiceImpl
        implements ApiService<String> {

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private AccountWalletService accountWalletService;

    @Autowired
    private DingdingMsgSender dingdingMsgSender;


    public String cmd(CmdRequestDTO cmdRequestDTO) {

        SaveReceiveAddressApiRequestDTO saveReceiveAddressApiRequestDTO = (SaveReceiveAddressApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), SaveReceiveAddressApiRequestDTO.class);

        if (ObjectUtil.isEmpty(saveReceiveAddressApiRequestDTO.getWalletCode())) {

            saveReceiveAddressApiRequestDTO.setWalletCode(WalletChainEnum.TRON.getCode());

        }


        AccountWalletQueryDTO accountWalletQueryDTO = AccountWalletQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).build();

        List<AccountWalletVo> accountWalletVoList = this.accountWalletService.queryAccountWalletList(accountWalletQueryDTO);

        String newReceiveAddress = null;

        if (ObjectUtil.isEmpty(accountWalletVoList)) {


            newReceiveAddress = HtmlUtil.escape(saveReceiveAddressApiRequestDTO.getReceiveAddress());

        } else if (ObjectUtil.isNotEmpty(saveReceiveAddressApiRequestDTO.getDefaultAddress()) && saveReceiveAddressApiRequestDTO
                .getDefaultAddress().booleanValue()) {

            newReceiveAddress = HtmlUtil.escape(saveReceiveAddressApiRequestDTO.getReceiveAddress());

        }


        AccountSaveOrUpdateDTO accountSaveOrUpdateDTO = ((AccountSaveOrUpdateDTO.AccountSaveOrUpdateDTOBuilder) AccountSaveOrUpdateDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).receiveAddress(newReceiveAddress).promotionCode(saveReceiveAddressApiRequestDTO.getPromotionCode()).qq(HtmlUtil.escape(saveReceiveAddressApiRequestDTO.getQq())).wechat(HtmlUtil.escape(saveReceiveAddressApiRequestDTO.getWechat())).phone(HtmlUtil.escape(saveReceiveAddressApiRequestDTO.getPhone())).nickName(HtmlUtil.escape(saveReceiveAddressApiRequestDTO.getNickName())).build();

        AccountVo accountVo = this.accountApiService.saveOrUpdate(accountSaveOrUpdateDTO);


        if (ObjectUtil.isNotEmpty(saveReceiveAddressApiRequestDTO.getReceiveAddress())) {


            this.accountWalletService.saveOrUpdate(
                    AccountWalletSaveOrUpdateDTO.builder()
                            .accountId(cmdRequestDTO.getAccountVo().getId())
                            .walletCode(saveReceiveAddressApiRequestDTO.getWalletCode())
                            .address(saveReceiveAddressApiRequestDTO.getReceiveAddress())
                            .build());


            this.dingdingMsgSender.sendMsg(
                    ProxyMsgDTO.builder()
                            .accountId(accountVo.getId())
                            .msg("账户[" + accountVo.getName() + "]更新收款地址, 请注意.")
                            .build());

        }


        return "success";

    }

    public String getApi() {

        return "api_updateaccount";

    }

}

