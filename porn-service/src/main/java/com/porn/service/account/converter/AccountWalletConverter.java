package com.porn.service.account.converter;

import com.porn.client.account.dto.AccountWalletSaveOrUpdateDTO;
import com.porn.client.account.vo.AccountWalletVo;
import com.porn.service.account.dao.entity.AccountWalletDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountWalletConverter {
    AccountWalletVo toAccountWalletVo(AccountWalletDO paramAccountWalletDO);

    List<AccountWalletVo> toAccountWalletVoList(List<AccountWalletDO> paramList);

    AccountWalletDO toAccountWalletDO(AccountWalletSaveOrUpdateDTO paramAccountWalletSaveOrUpdateDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/account/converter/AccountWalletConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */