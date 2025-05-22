package com.porn.client.account.api;

import com.porn.client.account.dto.AccountExtQueryDTO;
import com.porn.client.account.dto.AccountExtSaveOrUpdateDTO;
import com.porn.client.account.vo.AccountExtVo;

import java.util.List;

public interface AccountExtApiService {
    AccountExtVo queryAccountExt(AccountExtQueryDTO paramAccountExtQueryDTO);

    List<AccountExtVo> queryAccountExtList(AccountExtQueryDTO paramAccountExtQueryDTO);

    AccountExtVo saveOrUpdate(AccountExtSaveOrUpdateDTO paramAccountExtSaveOrUpdateDTO);
}


