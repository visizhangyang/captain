
package com.porn.service.mobile.api.impl;



import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.account.api.AccountExtApiService;
import com.porn.client.account.dto.AccountExtQueryDTO;
import com.porn.client.account.vo.AccountExtVo;
import com.porn.client.mobile.dto.AccountExtConfigDTO;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;























@Service
 public class QueryAccountExtApiServiceImpl
         implements ApiService<Map<String, String>>
         {
    
    @Autowired
     private AccountExtApiService accountExtApiService;

    
    
    public Map<String, String> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 32 */
        AccountExtConfigDTO accountExtConfigDTO = (AccountExtConfigDTO) JSON.parseObject(cmdRequestDTO.getData(), AccountExtConfigDTO.class);
        
        
        
        
        /* 37 */
        AccountExtQueryDTO accountExtQueryDTO = AccountExtQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).extTypeList(accountExtConfigDTO.getExtTypeList()).extKeyList(accountExtConfigDTO.getExtKeyList()).build();
        /* 38 */
        List<AccountExtVo> accountExtVoList = this.accountExtApiService.queryAccountExtList(accountExtQueryDTO);
        /* 39 */
        return ObjectUtil.isEmpty(accountExtVoList) ? MapUtil.empty() : (Map<String, String>) accountExtVoList.stream().collect(Collectors.toMap(AccountExtVo::getExtKey, AccountExtVo::getExtValue, (x, y) -> x));
        
    }

    
    
    public String getApi() {
        /* 43 */
        return "api_queryaccountconfig";
        
    }
    
}


