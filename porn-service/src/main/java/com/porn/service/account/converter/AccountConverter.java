package com.porn.service.account.converter;

import com.porn.client.account.dto.AccountQueryPageDTO;
import com.porn.client.account.dto.AccountSaveOrUpdateDTO;
import com.porn.client.account.dto.ProxyAccountQueryPageDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.service.account.dao.entity.AccountDO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/account/converter/AccountConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */