package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.mobile.dto.CaptchaApiRequestDTO;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.enums.CaptchaTypeEnum;
import com.porn.client.mobile.vo.CaptchaApiVo;
import com.porn.client.server.dto.GenCaptchaDTO;
import com.porn.service.mobile.api.ApiService;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;


@Service
public class CaptchaApiServiceImpl
        implements ApiService<CaptchaApiVo> {

    @Autowired
    private RedisTemplate redisTemplate;


    public CaptchaApiVo cmd(CmdRequestDTO cmdRequestDTO) {

        CaptchaApiRequestDTO captchaApiRequestDTO = (CaptchaApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), CaptchaApiRequestDTO.class);

        CaptchaTypeEnum captchaTypeEnum = CaptchaTypeEnum.queryByType(captchaApiRequestDTO.getType());


        SpecCaptcha specCaptcha = new SpecCaptcha(((Integer) ObjectUtil.defaultIfNull(captchaApiRequestDTO.getWidth(), GenCaptchaDTO.DEFAULT_WIDTH)).intValue(), ((Integer) ObjectUtil.defaultIfNull(captchaApiRequestDTO.getHeight(), GenCaptchaDTO.DEFAULT_WIDTH)).intValue(), ((Integer) ObjectUtil.defaultIfNull(captchaApiRequestDTO.getLen(), GenCaptchaDTO.DEFAULT_LEN)).intValue());

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        specCaptcha.out(out);

        String captchaToken = IdUtil.simpleUUID();

        this.redisTemplate.opsForValue().set(String.format(captchaTypeEnum.getCacheKeyFormate(), new Object[]{captchaToken}), specCaptcha.text(), captchaTypeEnum.getTimeout().intValue(), TimeUnit.MINUTES);

        return CaptchaApiVo.builder()
                .captchaBase64(specCaptcha.toBase64(""))
                .captchaToken(captchaToken)
                .build();

    }


    public String getApi() {

        return "api_captcha";

    }


    public boolean validateToken() {

        return false;

    }

}

