package com.porn.service.log.converter;

import com.porn.client.log.vo.LoginLogVo;
import com.porn.service.log.dao.entity.LoginLogDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoginLogConverter {
    LoginLogVo toLoginLogVo(LoginLogDO paramLoginLogDO);

    List<LoginLogVo> toLoginLogVoList(List<LoginLogDO> paramList);
}


