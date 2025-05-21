package com.porn.service.nickname.converter;

import com.porn.client.nickname.vo.NickNameVo;
import com.porn.service.nickname.dao.entity.NickNameDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NickNameConverter {
    NickNameVo toNickNameVo(NickNameDO paramNickNameDO);

    List<NickNameVo> toNickNameVoList(List<NickNameDO> paramList);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/nickname/converter/NickNameConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */