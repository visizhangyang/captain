package com.porn.service.mobile.encrypt.impl;


import com.porn.service.mobile.encrypt.EncryptService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service("encryptService")
public class EncryptServiceImpl
        implements EncryptService, BeanPostProcessor {
    private final Map<String, EncryptService> encryptServiceMap = new ConcurrentHashMap<>();


    public String decrypt(String type, String srcData) {

        EncryptService encryptService = this.encryptServiceMap.get(type);

        return encryptService.decrypt(type, srcData);

    }


    public String encrypt(String type, String srcData) {

        EncryptService encryptService = this.encryptServiceMap.get(type);

        return encryptService.encrypt(type, srcData);

    }


    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof EncryptService && !(bean instanceof EncryptServiceImpl)) {


            EncryptService encryptService = (EncryptService) bean;

            this.encryptServiceMap.put(encryptService.getType(), encryptService);

        }

        return bean;

    }

}

