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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/log/converter/LoginLogConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */