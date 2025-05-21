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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/api/AccountExtApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */