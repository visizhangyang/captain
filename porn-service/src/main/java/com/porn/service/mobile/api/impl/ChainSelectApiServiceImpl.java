
package com.porn.service.mobile.api.impl;



import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.wallet.api.WalletAddressApiService;
import com.porn.client.wallet.dto.WalletChainQueryDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


















@Service
 public class ChainSelectApiServiceImpl
         implements ApiService<List<String>>
         {

    @Autowired
     private WalletAddressApiService walletAddressApiService;



    public List<String> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 27 */
        WalletChainQueryDTO walletChainQueryDTO = WalletChainQueryDTO.builder().build();
        /* 28 */
        return this.walletAddressApiService.queryWalletChainList(walletChainQueryDTO);

    }



    public String getApi() {
        /* 32 */
        return "api_chainselect";

    }

}


