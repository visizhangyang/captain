
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
 public class ModifyPwdApiServiceImpl
         implements ApiService<String>
         {

    @Autowired
     private AccountApiService accountApiService;

    @Autowired
     private RedisTemplate redisTemplate;



    public String cmd(CmdRequestDTO cmdRequestDTO) {
        /* 37 */
        ModifyPwdApiRequestDTO modifyPwdApiRequestDTO = (ModifyPwdApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), ModifyPwdApiRequestDTO.class);
        /* 38 */
        CaptchaTypeEnum captchaTypeEnum = CaptchaTypeEnum.queryByType(modifyPwdApiRequestDTO.getType());
        /* 39 */
        if (ObjectUtil.isEmpty(captchaTypeEnum)) {
            /* 40 */
            throw new BusinessException("未知类型操作.");

        }

        /* 43 */
        String cacheCaptcha = (String) this.redisTemplate.opsForValue().get(String.format(captchaTypeEnum.getCacheKeyFormate(), new Object[]{StrUtil.emptyToDefault(modifyPwdApiRequestDTO.getCaptchaToken(), "")}));
        /* 44 */
        if (ObjectUtil.isEmpty(cacheCaptcha) ||
                /* 45 */       !cacheCaptcha.equalsIgnoreCase(modifyPwdApiRequestDTO.getCaptcha())) {
            /* 46 */
            throw new BusinessException("验证码错误.");

        }

        /* 49 */
        this.redisTemplate.delete(String.format(captchaTypeEnum.getCacheKeyFormate(), new Object[]{StrUtil.emptyToDefault(modifyPwdApiRequestDTO.getCaptchaToken(), "")}));








        /* 58 */
        AccountModifyPwdDTO accountModifyPwdDTO = ((AccountModifyPwdDTO.AccountModifyPwdDTOBuilder) AccountModifyPwdDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).oldPwd(modifyPwdApiRequestDTO.getOldPwd()).type(ModifyPwdTypeEnum.LOGINPWD.getType().equals(modifyPwdApiRequestDTO.getType()) ? AccountModifyPwdTypeEnum.LOGIN_PWD.getType() : AccountModifyPwdTypeEnum.TRADE_PWD.getType()).checkPwd(ModifyPwdTypeEnum.LOGINPWD.getType().equals(modifyPwdApiRequestDTO.getType())).newPwd(modifyPwdApiRequestDTO.getNewPwd()).build();
        /* 59 */
        if (!this.accountApiService.modifyPwd(accountModifyPwdDTO)) {
            /* 60 */
            throw new BusinessException("更新密码失败.");

        }
        /* 62 */
        return "success";

    }



    public String getApi() {
        /* 66 */
        return "api_modifypwd";

    }

}


