package com.porn.service.config.converter;

import com.porn.client.config.dto.*;
import com.porn.client.config.vo.ConfigVo;
import com.porn.service.config.dao.entity.ConfigDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConfigConverter {
    ConfigVo toConfigVo(ConfigDO paramConfigDO);

    List<ConfigVo> toConfigVoList(List<ConfigDO> paramList);

    ConfigDO toConfigDO(ConfigSaveOrUpdateDTO paramConfigSaveOrUpdateDTO);

    ConfigQueryPageDTO toConfigQueryPageDTO(ProxyConfigQueryPageDTO paramProxyConfigQueryPageDTO);

    ConfigSaveOrUpdateDTO toConfigSaveOrUpdateDTO(ProxyConfigSaveOrUpdateDTO paramProxyConfigSaveOrUpdateDTO);

    ConfigDeleteDTO toConfigDeleteDTO(ProxyConfigDeleteDTO paramProxyConfigDeleteDTO);
}


