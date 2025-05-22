
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.dto.AccountSaveOrUpdateDTO;
import com.porn.client.account.enums.AccountTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.MobileExtDTO;
import com.porn.client.mobile.dto.RegisterApiRequestDTO;
import com.porn.client.mobile.enums.CaptchaTypeEnum;
import com.porn.client.mobile.vo.LoginAccountVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.stream.vo.StreamVo;
import com.porn.service.common.utils.ClientUtil;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.mobile.converter.MobileConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;






































@Service
 public class RegisterApiServiceImpl
         implements ApiService<LoginAccountVo>
         {
    /*  43 */   private static final Logger log = LoggerFactory.getLogger(RegisterApiServiceImpl.class);



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
        /*  65 */
        RegisterApiRequestDTO registerApiRequestDTO = (RegisterApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), RegisterApiRequestDTO.class);

        /*  67 */
        String cacheCaptcha = (String) this.redisTemplate.opsForValue().get(String.format(CaptchaTypeEnum.REGISTER.getCacheKeyFormate(), new Object[]{StrUtil.emptyToDefault(registerApiRequestDTO.getCaptchaToken(), "")}));
        /*  68 */
        if (ObjectUtil.isEmpty(cacheCaptcha) ||
                /*  69 */       !cacheCaptcha.equalsIgnoreCase(registerApiRequestDTO.getCaptcha())) {
            /*  70 */
            throw new BusinessException("验证码错误.");

        }
        /*  72 */
        this.redisTemplate.delete(String.format(CaptchaTypeEnum.REGISTER.getCacheKeyFormate(), new Object[]{StrUtil.emptyToDefault(registerApiRequestDTO.getCaptchaToken(), "")}));

        /*  74 */
        AccountVo accountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().name(registerApiRequestDTO.getName()).build());
        /*  75 */
        if (ObjectUtil.isNotEmpty(accountVo)) {
            /*  76 */
            throw new BusinessException("账户已存在.");

        }
        /*  78 */
        if (ObjectUtil.isEmpty(registerApiRequestDTO.getParentPromotionCode())) {
            /*  79 */
            throw new BusinessException("推荐码不能为空.");

        }

        /*  82 */
        AccountVo parentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().promotionCodeList(Arrays.asList(new String[]{registerApiRequestDTO.getParentPromotionCode()})).build());
        /*  83 */
        if (ObjectUtil.isEmpty(parentAccountVo)) {
            /*  84 */
            throw new BusinessException("推荐码信息不存在.");

        }


        /*  88 */
        registerApiRequestDTO.setName(HtmlUtil.escape(registerApiRequestDTO.getName()));
        /*  89 */
        registerApiRequestDTO.setNickName(HtmlUtil.escape(registerApiRequestDTO.getNickName()));


        /*  92 */
        AccountSaveOrUpdateDTO accountSaveOrUpdateDTO = this.mobileConverter.toAccountSaveOrUpdateDTO(registerApiRequestDTO);
        /*  93 */
        accountSaveOrUpdateDTO.setAccountType(AccountTypeEnum.NORMAL.getType());
        /*  94 */
        accountSaveOrUpdateDTO.setParentId(parentAccountVo.getId());
        /*  95 */
        accountSaveOrUpdateDTO.setDeviceId(registerApiRequestDTO.getDeviceId());
        /*  96 */
        AccountVo regAccountVo = this.accountApiService.saveOrUpdate(accountSaveOrUpdateDTO);
        /*  97 */
        LoginAccountVo loginAccountVo = this.mobileConverter.toLoginAccountVo(regAccountVo);
        /*  98 */
        loginAccountVo.setToken(genToken(regAccountVo));






        /* 105 */
        StreamQueryDTO streamQueryDTO = StreamQueryDTO.builder().accountId(loginAccountVo.getId()).flag(StreamTypeEnum.REDENVELOPE.getFlag()).type(StreamTypeEnum.REDENVELOPE.getType()).bizId(Long.valueOf(-1L)).build();
        /* 106 */
        StreamVo streamVo = this.streamApiService.queryStream(streamQueryDTO);
        /* 107 */
        loginAccountVo.setReceiveRedPack(Integer.valueOf(ObjectUtil.isEmpty(streamVo) ? 0 : 1));


        /* 110 */
        this.dingdingMsgSender.sendMsg(
                /* 111 */         ProxyMsgDTO.builder()
/* 112 */.accountId(regAccountVo.getId())
/* 113 */.msg("账户[" + regAccountVo.getName() + "], 设备ID[" + StrUtil.emptyToDefault(registerApiRequestDTO.getDeviceId(), "") + "], 注册成功, 请关注.")
/* 114 */.build());




        try {
            /* 119 */
            syncRegisterUserAgent(cmdRequestDTO, (AccountVo) loginAccountVo);
            /* 120 */
        } catch (Exception e) {
            /* 121 */
            log.error(e.getMessage(), e);

        }

        /* 124 */
        return loginAccountVo;

    }







    protected void syncRegisterUserAgent(CmdRequestDTO cmdRequestDTO, AccountVo accountVo) {
        /* 132 */
        MobileExtDTO mobileExtDTO = cmdRequestDTO.getMobileExtDTO();
        /* 133 */
        if (ObjectUtil.isEmpty(mobileExtDTO) ||
                /* 134 */       ObjectUtil.isEmpty(mobileExtDTO.getUserAgent())) {

            return;

        }
        /* 137 */
        UserAgent userAgent = UserAgentUtil.parse(mobileExtDTO.getUserAgent());

        /* 139 */
        this.dingdingMsgSender.sendMsg("账户[" + accountVo
/* 140 */.getName() + "]注册成功, 是否移动端[" + (
                /* 141 */         userAgent.isMobile() ? "是" : "否") + "], 平台[" + userAgent
/* 142 */.getPlatform().getName() + "], 系统[" + userAgent
/* 143 */.getOs().getName() + "], 浏览器[" + userAgent
/* 144 */.getBrowser().getName() + "], 引擎[" + userAgent
/* 145 */.getEngine().getName() + "], 引擎[" + userAgent
/* 146 */.getEngine().getName() + "], 是否可疑[" + (ClientUtil.isBadClient(userAgent) ? "是, 请上报" : "否") + "].");

    }








    protected String genToken(AccountVo accountVo) {
        /* 155 */
        String token = IdUtil.simpleUUID();
        /* 156 */
        this.redisTemplate.opsForValue().set(String.format("account:session:%s", new Object[]{token}), JSON.toJSONString(accountVo));
        /* 157 */
        return token;

    }



    public String getApi() {
        /* 161 */
        return "api_register";

    }



    public boolean validateToken() {
        /* 165 */
        return false;

    }

}


