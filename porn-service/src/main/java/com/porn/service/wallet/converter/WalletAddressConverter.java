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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/wallet/converter/WalletAddressConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */