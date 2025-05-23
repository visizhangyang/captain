package com.porn.service.account.converter;

import com.porn.client.account.dto.AccountQueryPageDTO;
import com.porn.client.account.dto.AccountSaveOrUpdateDTO;
import com.porn.client.account.dto.ProxyAccountQueryPageDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.service.account.dao.entity.AccountDO;
import org.mapstruct.Mapper;

import java.util.List;

//@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false))
@Mapper(componentModel = "spring")
public interface AccountConverter {

//    AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

    AccountVo toAccountVo(AccountDO paramAccountDO);

    List<AccountVo> toAccountVoList(List<AccountDO> paramList);

    AccountDO toAccountDO(AccountSaveOrUpdateDTO paramAccountSaveOrUpdateDTO);

    AccountQueryPageDTO toAccountQueryPageDTO(ProxyAccountQueryPageDTO paramProxyAccountQueryPageDTO);
}

