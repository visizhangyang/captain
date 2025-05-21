package com.porn.client.account.api;

import com.porn.client.account.dto.AccountWalletDeleteDTO;
import com.porn.client.account.dto.AccountWalletQueryDTO;
import com.porn.client.account.dto.AccountWalletQueryPageDTO;
import com.porn.client.account.dto.AccountWalletSaveOrUpdateDTO;
import com.porn.client.account.vo.AccountWalletVo;
import com.porn.client.common.vo.PageVo;

import java.util.List;

public interface AccountWalletService {
    AccountWalletVo queryAccountWallet(AccountWalletQueryDTO paramAccountWalletQueryDTO);

    List<AccountWalletVo> queryAccountWalletList(AccountWalletQueryDTO paramAccountWalletQueryDTO);

    PageVo<AccountWalletVo> queryPage(AccountWalletQueryPageDTO paramAccountWalletQueryPageDTO);

    AccountWalletVo saveOrUpdate(AccountWalletSaveOrUpdateDTO paramAccountWalletSaveOrUpdateDTO);

    boolean delete(AccountWalletDeleteDTO paramAccountWalletDeleteDTO);
}

