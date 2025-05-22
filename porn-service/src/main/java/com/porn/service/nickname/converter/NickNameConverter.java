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


