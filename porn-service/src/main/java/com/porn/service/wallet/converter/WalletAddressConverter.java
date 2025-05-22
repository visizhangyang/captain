package com.porn.service.wallet.converter;

import com.porn.client.wallet.vo.WalletAddressVo;
import com.porn.service.wallet.dao.entity.WalletAddressDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletAddressConverter {
    WalletAddressVo toWalletAddressVo(WalletAddressDO paramWalletAddressDO);

    List<WalletAddressVo> toWalletAddressVoList(List<WalletAddressDO> paramList);
}


