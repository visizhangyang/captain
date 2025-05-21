package com.porn.service.account.converter;

import com.porn.client.account.dto.AccountExtSaveOrUpdateDTO;
import com.porn.client.account.vo.AccountExtVo;
import com.porn.service.account.dao.entity.AccountExtDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountExtConverter {
    AccountExtVo toAccountExtVo(AccountExtDO paramAccountExtDO);

    List<AccountExtVo> toAccountExtVoList(List<AccountExtDO> paramList);

    AccountExtDO toAccountExtDO(AccountExtSaveOrUpdateDTO paramAccountExtSaveOrUpdateDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/account/converter/AccountExtConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */