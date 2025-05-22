
package com.porn.service.mobile.encrypt.impl;



import com.porn.service.mobile.encrypt.EncryptService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;















@Service("encryptService")
 public class EncryptServiceImpl
         implements EncryptService, BeanPostProcessor
         {
    /* 20 */   private final Map<String, EncryptService> encryptServiceMap = new ConcurrentHashMap<>();

    
    
    
    public String decrypt(String type, String srcData) {
        /* 24 */
        EncryptService encryptService = this.encryptServiceMap.get(type);
        /* 25 */
        return encryptService.decrypt(type, srcData);
        
    }

    
    
    public String encrypt(String type, String srcData) {
        /* 29 */
        EncryptService encryptService = this.encryptServiceMap.get(type);
        /* 30 */
        return encryptService.encrypt(type, srcData);
        
    }

    
    
    
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        /* 35 */
        if (bean instanceof EncryptService && !(bean instanceof EncryptServiceImpl)) {
            
            /* 37 */
            EncryptService encryptService = (EncryptService) bean;
            /* 38 */
            this.encryptServiceMap.put(encryptService.getType(), encryptService);
            
        }
        /* 40 */
        return bean;
        
    }
    
}


