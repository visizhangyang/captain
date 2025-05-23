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

