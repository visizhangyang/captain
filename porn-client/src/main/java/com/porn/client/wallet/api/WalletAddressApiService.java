package com.porn.client.wallet.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.wallet.dto.*;
import com.porn.client.wallet.vo.WalletAddressVo;

import java.util.List;

public interface WalletAddressApiService {
    WalletAddressVo queryWalletAddress(WalletAddressQueryDTO paramWalletAddressQueryDTO);

    List<WalletAddressVo> queryWalletAddressList(WalletAddressQueryDTO paramWalletAddressQueryDTO);

    PageVo<WalletAddressVo> queryPage(WalletAddressQueryPageDTO paramWalletAddressQueryPageDTO);

    Boolean enableOrDisable(WalletAddressEnableOrDisableDTO paramWalletAddressEnableOrDisableDTO);

    WalletAddressVo saveOrUpdate(WalletAddressSaveOrUpdateDTO paramWalletAddressSaveOrUpdateDTO);

    Boolean delete(WalletAddressDeleteDTO paramWalletAddressDeleteDTO);

    Boolean updateRemark(WalletAddressUpdateRemarkDTO paramWalletAddressUpdateRemarkDTO);

    List<String> queryWalletChainList(WalletChainQueryDTO paramWalletChainQueryDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/wallet/api/WalletAddressApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */