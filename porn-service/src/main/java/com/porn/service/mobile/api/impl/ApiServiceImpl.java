package com.porn.service.mobile.api.impl;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.exceptions.AuthException;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.service.mobile.api.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service("apiService")
public class ApiServiceImpl
        implements ApiService<String>, BeanPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(ApiServiceImpl.class);

    private final Map<String, Map<String, ApiService>> apiMap = new ConcurrentHashMap<>();

    @Autowired
    private RedisTemplate redisTemplate;

    public String cmd(CmdRequestDTO cmdRequestDTO) {

        ApiService apiService = getApiService(cmdRequestDTO);


        if (apiService.validateToken()) {


            AccountVo accountVo = getToken(cmdRequestDTO.getToken());

            if (ObjectUtil.isEmpty(accountVo)) {

                throw new AuthException("token验证失败.");

            }

            cmdRequestDTO.setAccountVo(accountVo);

        } else if (ObjectUtil.isNotEmpty(cmdRequestDTO.getToken())) {


            AccountVo accountVo = getToken(cmdRequestDTO.getToken());

            if (ObjectUtil.isNotEmpty(accountVo)) {

                cmdRequestDTO.setAccountVo(accountVo);

            }

        }

        Object resultObj = apiService.cmd(cmdRequestDTO);

        String resultJson = Base64.encode(JSON.toJSONString(resultObj, new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect}));


        return resultJson;

    }

    protected AccountVo getToken(String token) {

        if (ObjectUtil.isEmpty(token)) {

            return null;

        }

        String accountStr = (String) this.redisTemplate.opsForValue().get(String.format("account:session:%s", new Object[]{token}));

        return ObjectUtil.isEmpty(accountStr) ? null : (AccountVo) JSON.parseObject(accountStr, AccountVo.class);

    }

    protected ApiService getApiService(CmdRequestDTO cmdRequestDTO) {

        Map<String, ApiService> map = this.apiMap.get(cmdRequestDTO.getApi());

        if (ObjectUtil.isEmpty(map)) {

            return (ApiService) ((Map) this.apiMap.get("api_default")).get("1.0.0");

        }

        ApiService apiService = map.get(cmdRequestDTO.getVersion());

        return ObjectUtil.isEmpty(apiService) ? (ApiService) ((Map) this.apiMap.get("api_default")).get("1.0.0") : apiService;

    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof ApiService && !(bean instanceof ApiServiceImpl)) {

            ApiService apiService = (ApiService) bean;

            Map<String, ApiService> map = this.apiMap.getOrDefault(apiService.getApi(), new ConcurrentHashMap<>());

            map.put(apiService.getVersion(), apiService);

            this.apiMap.put(apiService.getApi(), map);

        }

        return bean;

    }

}

