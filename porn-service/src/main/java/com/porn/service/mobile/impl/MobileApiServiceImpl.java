
package com.porn.service.mobile.impl;



import cn.hutool.core.codec.Base64;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.common.exceptions.AuthException;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.mobile.api.MobileApiService;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.MobileDTO;
import com.porn.client.mobile.enums.EncryptTypeEnum;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.mobile.encrypt.EncryptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;






















@Service
 public class MobileApiServiceImpl
         implements MobileApiService
         {
    /* 27 */   private static final Logger log = LoggerFactory.getLogger(MobileApiServiceImpl.class);



    @Autowired

    @Qualifier("encryptService")
     private EncryptService encryptService;



    @Autowired

    @Qualifier("apiService")
     private ApiService apiService;





    public String request(MobileDTO mobileDTO) {
        /* 42 */
        EncryptTypeEnum encryptTypeEnum = EncryptTypeEnum.queryByType(mobileDTO.getType());
        /* 43 */
        if (ObjectUtil.isEmpty(encryptTypeEnum)) {
            /* 44 */
            throw new RuntimeException("未知类型异常.");

        }


        try {
            /* 48 */
            String reqJsonStr = Base64.decodeStr(this.encryptService.decrypt(encryptTypeEnum.toString(), mobileDTO.getBody()));

            /* 50 */
            CmdRequestDTO cmdRequestDTO = (CmdRequestDTO) JSON.parseObject(reqJsonStr, CmdRequestDTO.class);
            /* 51 */
            cmdRequestDTO.setMobileExtDTO(mobileDTO.getMobileExtDTO());

            /* 53 */
            String rs = this.encryptService.encrypt(encryptTypeEnum.toString(), (String) this.apiService.cmd(cmdRequestDTO));
            /* 54 */
            return rs;
            /* 55 */
        } catch (BusinessException e) {
            /* 56 */
            log.error("app请求异常, 异常[{}].", ExceptionUtil.getRootCause((Throwable) e));
            /* 57 */
            throw e;
            /* 58 */
        } catch (AuthException e) {
            /* 59 */
            log.error("app请求异常, 异常[{}].", ExceptionUtil.getRootCause((Throwable) e));
            /* 60 */
            throw e;
            /* 61 */
        } catch (Exception e) {
            /* 62 */
            log.error("app请求异常, 异常[{}].", ExceptionUtil.getRootCause(e));

            /* 64 */
            throw new RuntimeException("未知类型异常");

        }

    }

}


