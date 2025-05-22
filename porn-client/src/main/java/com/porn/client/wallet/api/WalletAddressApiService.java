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


