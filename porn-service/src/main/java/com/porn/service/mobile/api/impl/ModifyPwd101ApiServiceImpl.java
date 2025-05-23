package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountModifyPwdDTO;
import com.porn.client.account.enums.AccountModifyPwdTypeEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.ModifyPwdApiRequestDTO;
import com.porn.client.mobile.enums.CaptchaTypeEnum;
import com.porn.client.mobile.enums.ModifyPwdTypeEnum;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ModifyPwd101ApiServiceImpl
        implements ApiService<String> {

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private RedisTemplate redisTemplate;


    public String cmd(CmdRequestDTO cmdRequestDTO) {

        ModifyPwdApiRequestDTO modifyPwdApiRequestDTO = (ModifyPwdApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), ModifyPwdApiRequestDTO.class);

        CaptchaTypeEnum captchaTypeEnum = CaptchaTypeEnum.queryByType(modifyPwdApiRequestDTO.getType());

        if (ObjectUtil.isEmpty(captchaTypeEnum)) {

            throw new BusinessException("未知类型操作.");

        }

        String cacheCaptcha = (String) this.redisTemplate.opsForValue().get(String.format(captchaTypeEnum.getCacheKeyFormate(), new Object[]{StrUtil.emptyToDefault(modifyPwdApiRequestDTO.getCaptchaToken(), "")}));

        if (ObjectUtil.isEmpty(cacheCaptcha) ||
                !cacheCaptcha.equalsIgnoreCase(modifyPwdApiRequestDTO.getCaptcha())) {

            throw new BusinessException("验证码错误.");

        }

        this.redisTemplate.delete(String.format(captchaTypeEnum.getCacheKeyFormate(), new Object[]{StrUtil.emptyToDefault(modifyPwdApiRequestDTO.getCaptchaToken(), "")}));


        AccountModifyPwdDTO accountModifyPwdDTO = ((AccountModifyPwdDTO.AccountModifyPwdDTOBuilder) AccountModifyPwdDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).oldPwd(modifyPwdApiRequestDTO.getOldPwd()).type(ModifyPwdTypeEnum.LOGINPWD.getType().equals(modifyPwdApiRequestDTO.getType()) ? AccountModifyPwdTypeEnum.LOGIN_PWD.getType() : AccountModifyPwdTypeEnum.TRADE_PWD.getType()).checkPwd(false).newPwd(modifyPwdApiRequestDTO.getNewPwd()).build();

        if (!this.accountApiService.modifyPwd(accountModifyPwdDTO)) {

            throw new BusinessException("更新密码失败.");

        }

        return "success";

    }


    public String getVersion() {

        return "1.0.1";

    }


    public String getApi() {

        return "api_modifypwd";

    }

}

