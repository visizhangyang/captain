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

