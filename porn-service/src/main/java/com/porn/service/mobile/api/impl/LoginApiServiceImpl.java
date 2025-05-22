
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountOfflineDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.dto.AccountValidatePwdDTO;
import com.porn.client.account.dto.AccountcModifyDeviceIdDTO;
import com.porn.client.account.enums.AccountTypeEnum;
import com.porn.client.account.enums.AccountValidateTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.LoginApiRequestDTO;
import com.porn.client.mobile.dto.MobileExtDTO;
import com.porn.client.mobile.enums.CaptchaTypeEnum;
import com.porn.client.mobile.vo.LoginAccountVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.stream.vo.StreamVo;
import com.porn.service.common.utils.ClientUtil;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.mobile.converter.MobileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;















































@Service
 public class LoginApiServiceImpl
         implements ApiService<LoginAccountVo>
         {

    @Autowired
     private AccountApiService accountApiService;

    @Autowired
     private RedisTemplate redisTemplate;

    @Autowired
     private MobileConverter mobileConverter;

    @Autowired
     private StreamApiService streamApiService;

    @Autowired
     private DingdingMsgSender dingdingMsgSender;



    public LoginAccountVo cmd(CmdRequestDTO cmdRequestDTO) {
        /*  64 */
        LoginApiRequestDTO loginApiRequestDTO = (LoginApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), LoginApiRequestDTO.class);

        /*  66 */
        String cacheCaptcha = (String) this.redisTemplate.opsForValue().get(String.format(CaptchaTypeEnum.LOGIN.getCacheKeyFormate(), new Object[]{StrUtil.emptyToDefault(loginApiRequestDTO.getCaptchaToken(), "")}));
        /*  67 */
        if (ObjectUtil.isEmpty(cacheCaptcha) ||
                /*  68 */       !cacheCaptcha.equalsIgnoreCase(loginApiRequestDTO.getCaptcha())) {
            /*  69 */
            throw new BusinessException("验证码错误.");

        }
        /*  71 */
        this.redisTemplate.delete(String.format(CaptchaTypeEnum.REGISTER.getCacheKeyFormate(), new Object[]{StrUtil.emptyToDefault(loginApiRequestDTO.getCaptchaToken(), "")}));

        /*  73 */
        AccountVo accountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().name(loginApiRequestDTO.getName()).build());
        /*  74 */
        if (ObjectUtil.isEmpty(accountVo)) {
            /*  75 */
            throw new BusinessException("用户信息不存在.");

        }
        /*  77 */
        if (!EnableStatusEnum.ENABLE.getStatus().equals(accountVo.getStatus())) {
            /*  78 */
            throw new BusinessException("用户已禁用.");

        }

        /*  81 */
        if (!this.accountApiService.validatePwd(((AccountValidatePwdDTO.AccountValidatePwdDTOBuilder) AccountValidatePwdDTO.builder().id(accountVo.getId())).type(AccountValidateTypeEnum.LOGIN_PWD.getType()).pwd(loginApiRequestDTO.getLoginPwd()).build())) {
            /*  82 */
            throw new BusinessException("密码不正确.");

        }

        /*  85 */
        if (!AccountTypeEnum.NORMAL.getType().equals(accountVo.getAccountType())) {
            /*  86 */
            throw new BusinessException("用户信息不存在.");

        }

        /*  89 */
        String token = genToken(accountVo);
        /*  90 */
        LoginAccountVo loginAccountVo = this.mobileConverter.toLoginAccountVo(accountVo);
        /*  91 */
        loginAccountVo.setToken(token);







        /*  99 */
        StreamQueryDTO streamQueryDTO = StreamQueryDTO.builder().accountId(loginAccountVo.getId()).flag(StreamTypeEnum.REDENVELOPE.getFlag()).type(StreamTypeEnum.REDENVELOPE.getType()).bizId(Long.valueOf(-1L)).build();
        /* 100 */
        StreamVo streamVo = this.streamApiService.queryStream(streamQueryDTO);
        /* 101 */
        loginAccountVo.setReceiveRedPack(Integer.valueOf(ObjectUtil.isEmpty(streamVo) ? 0 : 1));





        /* 107 */
        AccountOfflineDTO accountOfflineDTO = ((AccountOfflineDTO.AccountOfflineDTOBuilder) AccountOfflineDTO.builder().id(loginAccountVo.getId())).activeToken(token).build();
        /* 108 */
        this.accountApiService.offlineOthers(accountOfflineDTO);


        /* 111 */
        if (ObjectUtil.isEmpty(accountVo.getDeviceId()) &&
                /* 112 */       ObjectUtil.isNotEmpty(loginApiRequestDTO.getDeviceId())) {
            /* 113 */
            this.accountApiService.modifyDeviceId(((AccountcModifyDeviceIdDTO.AccountcModifyDeviceIdDTOBuilder) AccountcModifyDeviceIdDTO.builder().id(accountVo.getId())).deviceId(loginApiRequestDTO.getDeviceId()).build());

        }


        /* 117 */
        syncLoginUserAgent(cmdRequestDTO, accountVo);

        /* 119 */
        return loginAccountVo;

    }







    protected void syncLoginUserAgent(CmdRequestDTO cmdRequestDTO, AccountVo accountVo) {
        /* 127 */
        MobileExtDTO mobileExtDTO = cmdRequestDTO.getMobileExtDTO();
        /* 128 */
        if (ObjectUtil.isEmpty(mobileExtDTO) ||
                /* 129 */       ObjectUtil.isEmpty(mobileExtDTO.getUserAgent())) {

            return;

        }
        /* 132 */
        UserAgent userAgent = UserAgentUtil.parse(mobileExtDTO.getUserAgent());

        /* 134 */
        this.dingdingMsgSender.sendMsg("账户[" + accountVo
/* 135 */.getName() + "]登录成功, 是否移动端[" + (
                /* 136 */         userAgent.isMobile() ? "是" : "否") + "], 平台[" + userAgent
/* 137 */.getPlatform().getName() + "], 系统[" + userAgent
/* 138 */.getOs().getName() + "], 浏览器[" + userAgent
/* 139 */.getBrowser().getName() + "], 引擎[" + userAgent
/* 140 */.getEngine().getName() + "], 是否可疑[" + (ClientUtil.isBadClient(userAgent) ? "是, 请上报" : "否") + "].");

    }









    protected String genToken(AccountVo accountVo) {
        /* 150 */
        String token = IdUtil.simpleUUID();
        /* 151 */
        this.redisTemplate.opsForValue().set(String.format("account:session:%s", new Object[]{token}), JSON.toJSONString(accountVo));
        /* 152 */
        return token;

    }



    public String getApi() {
        /* 156 */
        return "api_login";

    }



    public boolean validateToken() {
        /* 160 */
        return false;

    }

}


