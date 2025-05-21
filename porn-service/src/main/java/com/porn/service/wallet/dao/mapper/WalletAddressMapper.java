package com.porn.service.wallet.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.porn.client.wallet.dto.WalletChainQueryDTO;
import com.porn.service.wallet.dao.entity.WalletAddressDO;

import java.util.List;

public interface WalletAddressMapper extends BaseMapper<WalletAddressDO> {
    List<String> queryWalletChainList(WalletChainQueryDTO paramWalletChainQueryDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/wallet/dao/mapper/WalletAddressMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */