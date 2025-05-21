package com.porn.client.config.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.config.dto.*;
import com.porn.client.config.vo.ConfigVo;

import java.util.List;

public interface ConfigApiService {
    ConfigVo queryConfig(ConfigQueryDTO paramConfigQueryDTO);

    List<ConfigVo> queryConfigList(ConfigQueryDTO paramConfigQueryDTO);

    PageVo<ConfigVo> queryPage(ConfigQueryPageDTO paramConfigQueryPageDTO);

    ConfigVo saveOrUpdate(ConfigSaveOrUpdateDTO paramConfigSaveOrUpdateDTO);

    Boolean enableOrDisable(ConfigEnableOrDisableDTO paramConfigEnableOrDisableDTO);

    Boolean delete(ConfigDeleteDTO paramConfigDeleteDTO);

    PageVo<ConfigVo> queryProxyPage(ProxyConfigQueryPageDTO paramProxyConfigQueryPageDTO);

    ConfigVo proxySaveOrUpdate(ProxyConfigSaveOrUpdateDTO paramProxyConfigSaveOrUpdateDTO);

    Boolean proxyDelete(ProxyConfigDeleteDTO paramProxyConfigDeleteDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/config/api/ConfigApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */