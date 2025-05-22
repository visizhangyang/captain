
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
         implements ApiService<String>
         {

    @Autowired
     private AccountApiService accountApiService;

    @Autowired
     private AccountWalletService accountWalletService;

    @Autowired
     private DingdingMsgSender dingdingMsgSender;



    public String cmd(CmdRequestDTO cmdRequestDTO) {
        /*  45 */
        SaveReceiveAddressApiRequestDTO saveReceiveAddressApiRequestDTO = (SaveReceiveAddressApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), SaveReceiveAddressApiRequestDTO.class);
        /*  46 */
        if (ObjectUtil.isEmpty(saveReceiveAddressApiRequestDTO.getWalletCode())) {
            /*  47 */
            saveReceiveAddressApiRequestDTO.setWalletCode(WalletChainEnum.TRON.getCode());

        }




        /*  53 */
        AccountWalletQueryDTO accountWalletQueryDTO = AccountWalletQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).build();
        /*  54 */
        List<AccountWalletVo> accountWalletVoList = this.accountWalletService.queryAccountWalletList(accountWalletQueryDTO);
        /*  55 */
        String newReceiveAddress = null;
        /*  56 */
        if (ObjectUtil.isEmpty(accountWalletVoList)) {

            /*  58 */
            newReceiveAddress = HtmlUtil.escape(saveReceiveAddressApiRequestDTO.getReceiveAddress());

        }
        /*  60 */
        else if (ObjectUtil.isNotEmpty(saveReceiveAddressApiRequestDTO.getDefaultAddress()) && saveReceiveAddressApiRequestDTO
/*  61 */.getDefaultAddress().booleanValue()) {
            /*  62 */
            newReceiveAddress = HtmlUtil.escape(saveReceiveAddressApiRequestDTO.getReceiveAddress());

        }










        /*  74 */
        AccountSaveOrUpdateDTO accountSaveOrUpdateDTO = ((AccountSaveOrUpdateDTO.AccountSaveOrUpdateDTOBuilder) AccountSaveOrUpdateDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).receiveAddress(newReceiveAddress).promotionCode(saveReceiveAddressApiRequestDTO.getPromotionCode()).qq(HtmlUtil.escape(saveReceiveAddressApiRequestDTO.getQq())).wechat(HtmlUtil.escape(saveReceiveAddressApiRequestDTO.getWechat())).phone(HtmlUtil.escape(saveReceiveAddressApiRequestDTO.getPhone())).nickName(HtmlUtil.escape(saveReceiveAddressApiRequestDTO.getNickName())).build();
        /*  75 */
        AccountVo accountVo = this.accountApiService.saveOrUpdate(accountSaveOrUpdateDTO);


        /*  78 */
        if (ObjectUtil.isNotEmpty(saveReceiveAddressApiRequestDTO.getReceiveAddress())) {

            /*  80 */
            this.accountWalletService.saveOrUpdate(
                    /*  81 */           AccountWalletSaveOrUpdateDTO.builder()
/*  82 */.accountId(cmdRequestDTO.getAccountVo().getId())
/*  83 */.walletCode(saveReceiveAddressApiRequestDTO.getWalletCode())
/*  84 */.address(saveReceiveAddressApiRequestDTO.getReceiveAddress())
/*  85 */.build());

            /*  87 */
            this.dingdingMsgSender.sendMsg(
                    /*  88 */           ProxyMsgDTO.builder()
/*  89 */.accountId(accountVo.getId())
/*  90 */.msg("账户[" + accountVo.getName() + "]更新收款地址, 请注意.")
/*  91 */.build());

        }


        /*  95 */
        return "success";

    }




    public String getApi() {
        /* 100 */
        return "api_updateaccount";

    }

}


