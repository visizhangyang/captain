
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
         implements ApiService<String>, BeanPostProcessor
         {
    /*  31 */   private static final Logger log = LoggerFactory.getLogger(ApiServiceImpl.class);




    /*  36 */   private final Map<String, Map<String, ApiService>> apiMap = new ConcurrentHashMap<>();


    @Autowired
     private RedisTemplate redisTemplate;




    public String cmd(CmdRequestDTO cmdRequestDTO) {
        /*  43 */
        ApiService apiService = getApiService(cmdRequestDTO);

        /*  45 */
        if (apiService.validateToken()) {

            /*  47 */
            AccountVo accountVo = getToken(cmdRequestDTO.getToken());
            /*  48 */
            if (ObjectUtil.isEmpty(accountVo)) {
                /*  49 */
                throw new AuthException("token验证失败.");

            }
            /*  51 */
            cmdRequestDTO.setAccountVo(accountVo);
            /*  52 */
        } else if (ObjectUtil.isNotEmpty(cmdRequestDTO.getToken())) {

            /*  54 */
            AccountVo accountVo = getToken(cmdRequestDTO.getToken());
            /*  55 */
            if (ObjectUtil.isNotEmpty(accountVo)) {
                /*  56 */
                cmdRequestDTO.setAccountVo(accountVo);

            }

        }
        /*  59 */
        Object resultObj = apiService.cmd(cmdRequestDTO);
        /*  60 */
        String resultJson = Base64.encode(JSON.toJSONString(resultObj, new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect}));




        /*  65 */
        return resultJson;

    }








    protected AccountVo getToken(String token) {
        /*  74 */
        if (ObjectUtil.isEmpty(token)) {
            /*  75 */
            return null;

        }
        /*  77 */
        String accountStr = (String) this.redisTemplate.opsForValue().get(String.format("account:session:%s", new Object[]{token}));
        /*  78 */
        return ObjectUtil.isEmpty(accountStr) ? null : (AccountVo) JSON.parseObject(accountStr, AccountVo.class);

    }










    protected ApiService getApiService(CmdRequestDTO cmdRequestDTO) {
        /*  89 */
        Map<String, ApiService> map = this.apiMap.get(cmdRequestDTO.getApi());
        /*  90 */
        if (ObjectUtil.isEmpty(map)) {
            /*  91 */
            return (ApiService) ((Map) this.apiMap.get("api_default")).get("1.0.0");

        }
        /*  93 */
        ApiService apiService = map.get(cmdRequestDTO.getVersion());
        /*  94 */
        return ObjectUtil.isEmpty(apiService) ? (ApiService) ((Map) this.apiMap.get("api_default")).get("1.0.0") : apiService;

    }




    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        /*  99 */
        if (bean instanceof ApiService && !(bean instanceof ApiServiceImpl)) {

            /* 101 */
            ApiService apiService = (ApiService) bean;
            /* 102 */
            Map<String, ApiService> map = this.apiMap.getOrDefault(apiService.getApi(), new ConcurrentHashMap<>());
            /* 103 */
            map.put(apiService.getVersion(), apiService);
            /* 104 */
            this.apiMap.put(apiService.getApi(), map);

        }
        /* 106 */
        return bean;

    }

}


