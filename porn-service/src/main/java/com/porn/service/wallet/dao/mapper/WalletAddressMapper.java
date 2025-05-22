package com.porn.service.wallet.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.client.wallet.dto.WalletChainQueryDTO;
import com.porn.service.wallet.dao.entity.WalletAddressDO;

import java.util.List;

public interface WalletAddressMapper extends BaseMapper<WalletAddressDO> {
    List<String> queryWalletChainList(WalletChainQueryDTO paramWalletChainQueryDTO);
}


