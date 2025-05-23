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
        implements MobileApiService {
    private static final Logger log = LoggerFactory.getLogger(MobileApiServiceImpl.class);


    @Autowired

    @Qualifier("encryptService")
    private EncryptService encryptService;


    @Autowired

    @Qualifier("apiService")
    private ApiService apiService;


    public String request(MobileDTO mobileDTO) {

        EncryptTypeEnum encryptTypeEnum = EncryptTypeEnum.queryByType(mobileDTO.getType());

        if (ObjectUtil.isEmpty(encryptTypeEnum)) {

            throw new RuntimeException("未知类型异常.");

        }

        try {

            String reqJsonStr = Base64.decodeStr(this.encryptService.decrypt(encryptTypeEnum.toString(), mobileDTO.getBody()));

            CmdRequestDTO cmdRequestDTO = (CmdRequestDTO) JSON.parseObject(reqJsonStr, CmdRequestDTO.class);

            cmdRequestDTO.setMobileExtDTO(mobileDTO.getMobileExtDTO());

            String rs = this.encryptService.encrypt(encryptTypeEnum.toString(), (String) this.apiService.cmd(cmdRequestDTO));

            return rs;

        } catch (BusinessException e) {

            log.error("app请求异常, 异常[{}].", ExceptionUtil.getRootCause((Throwable) e));

            throw e;

        } catch (AuthException e) {

            log.error("app请求异常, 异常[{}].", ExceptionUtil.getRootCause((Throwable) e));

            throw e;

        } catch (Exception e) {

            log.error("app请求异常, 异常[{}].", ExceptionUtil.getRootCause(e));

            throw new RuntimeException("未知类型异常");

        }

    }

}

