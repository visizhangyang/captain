
package com.porn.service.mobile.api.impl;



import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.config.api.ConfigApiService;
import com.porn.client.config.dto.ConfigQueryDTO;
import com.porn.client.config.vo.ConfigVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

























@Service
 public class QueryConfigApiService
         implements ApiService<Map<String, String>>
         {

    @Autowired
     private ConfigApiService configApiService;



    public Map<String, String> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 34 */
        ConfigQueryDTO configQueryDTO = (ConfigQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), ConfigQueryDTO.class);
        /* 35 */
        configQueryDTO.setAccountId(CommonConst.LZERO);
        /* 36 */
        List<ConfigVo> configVoList = this.configApiService.queryConfigList(configQueryDTO);
        /* 37 */
        return ObjectUtil.isEmpty(configVoList) ? MapUtil.empty() : (Map<String, String>) configVoList.stream().collect(Collectors.toMap(ConfigVo::getConfigCode, ConfigVo::getConfigValue, (x, y) -> x));

    }



    public String getApi() {
        /* 41 */
        return "api_queryconfig";

    }

}


