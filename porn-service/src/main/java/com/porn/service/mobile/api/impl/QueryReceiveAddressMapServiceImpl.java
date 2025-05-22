
package com.porn.service.mobile.api.impl;



import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.account.api.AccountWalletService;
import com.porn.client.account.dto.AccountWalletQueryDTO;
import com.porn.client.account.vo.AccountWalletVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

























@Service
 public class QueryReceiveAddressMapServiceImpl
         implements ApiService<Map<String, String>>
         {
    
    @Autowired
     private AccountWalletService accountWalletService;

    
    
    public Map<String, String> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 34 */
        AccountWalletQueryDTO accountWalletQueryDTO = AccountWalletQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).build();
        /* 35 */
        List<AccountWalletVo> accountWalletVoList = this.accountWalletService.queryAccountWalletList(accountWalletQueryDTO);
        /* 36 */
        return ObjectUtil.isEmpty(accountWalletVoList) ? MapUtil.empty() : (Map<String, String>) accountWalletVoList.stream().collect(Collectors.toMap(AccountWalletVo::getWalletCode, AccountWalletVo::getAddress));
        
    }

    
    
    public String getApi() {
        /* 40 */
        return "api_queryreceiveaddressmap";
        
    }
    
}


