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
        implements ApiService<LoginAccountVo> {
    private static final Logger log = LoggerFactory.getLogger(RegisterApiServiceImpl.class);


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

        RegisterApiRequestDTO registerApiRequestDTO = (RegisterApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), RegisterApiRequestDTO.class);


        String cacheCaptcha = (String) this.redisTemplate.opsForValue().get(String.format(CaptchaTypeEnum.REGISTER.getCacheKeyFormate(), new Object[]{StrUtil.emptyToDefault(registerApiRequestDTO.getCaptchaToken(), "")}));

        if (ObjectUtil.isEmpty(cacheCaptcha) ||
                !cacheCaptcha.equalsIgnoreCase(registerApiRequestDTO.getCaptcha())) {

            throw new BusinessException("验证码错误.");

        }

        this.redisTemplate.delete(String.format(CaptchaTypeEnum.REGISTER.getCacheKeyFormate(), new Object[]{StrUtil.emptyToDefault(registerApiRequestDTO.getCaptchaToken(), "")}));


        AccountVo accountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().name(registerApiRequestDTO.getName()).build());

        if (ObjectUtil.isNotEmpty(accountVo)) {

            throw new BusinessException("账户已存在.");

        }

        if (ObjectUtil.isEmpty(registerApiRequestDTO.getParentPromotionCode())) {

            throw new BusinessException("推荐码不能为空.");

        }


        AccountVo parentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().promotionCodeList(Arrays.asList(new String[]{registerApiRequestDTO.getParentPromotionCode()})).build());

        if (ObjectUtil.isEmpty(parentAccountVo)) {

            throw new BusinessException("推荐码信息不存在.");

        }


        registerApiRequestDTO.setName(HtmlUtil.escape(registerApiRequestDTO.getName()));

        registerApiRequestDTO.setNickName(HtmlUtil.escape(registerApiRequestDTO.getNickName()));


        AccountSaveOrUpdateDTO accountSaveOrUpdateDTO = this.mobileConverter.toAccountSaveOrUpdateDTO(registerApiRequestDTO);

        accountSaveOrUpdateDTO.setAccountType(AccountTypeEnum.NORMAL.getType());

        accountSaveOrUpdateDTO.setParentId(parentAccountVo.getId());

        accountSaveOrUpdateDTO.setDeviceId(registerApiRequestDTO.getDeviceId());

        AccountVo regAccountVo = this.accountApiService.saveOrUpdate(accountSaveOrUpdateDTO);

        LoginAccountVo loginAccountVo = this.mobileConverter.toLoginAccountVo(regAccountVo);

        loginAccountVo.setToken(genToken(regAccountVo));


        StreamQueryDTO streamQueryDTO = StreamQueryDTO.builder().accountId(loginAccountVo.getId()).flag(StreamTypeEnum.REDENVELOPE.getFlag()).type(StreamTypeEnum.REDENVELOPE.getType()).bizId(Long.valueOf(-1L)).build();

        StreamVo streamVo = this.streamApiService.queryStream(streamQueryDTO);

        loginAccountVo.setReceiveRedPack(Integer.valueOf(ObjectUtil.isEmpty(streamVo) ? 0 : 1));


        this.dingdingMsgSender.sendMsg(
                ProxyMsgDTO.builder()
                        .accountId(regAccountVo.getId())
                        .msg("账户[" + regAccountVo.getName() + "], 设备ID[" + StrUtil.emptyToDefault(registerApiRequestDTO.getDeviceId(), "") + "], 注册成功, 请关注.")
                        .build());

        try {

            syncRegisterUserAgent(cmdRequestDTO, (AccountVo) loginAccountVo);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        }

        return loginAccountVo;

    }


    protected void syncRegisterUserAgent(CmdRequestDTO cmdRequestDTO, AccountVo accountVo) {

        MobileExtDTO mobileExtDTO = cmdRequestDTO.getMobileExtDTO();

        if (ObjectUtil.isEmpty(mobileExtDTO) ||
                ObjectUtil.isEmpty(mobileExtDTO.getUserAgent())) {

            return;

        }

        UserAgent userAgent = UserAgentUtil.parse(mobileExtDTO.getUserAgent());

        this.dingdingMsgSender.sendMsg("账户[" + accountVo
                .getName() + "]注册成功, 是否移动端[" + (
                userAgent.isMobile() ? "是" : "否") + "], 平台[" + userAgent
                .getPlatform().getName() + "], 系统[" + userAgent
                .getOs().getName() + "], 浏览器[" + userAgent
                .getBrowser().getName() + "], 引擎[" + userAgent
                .getEngine().getName() + "], 引擎[" + userAgent
                .getEngine().getName() + "], 是否可疑[" + (ClientUtil.isBadClient(userAgent) ? "是, 请上报" : "否") + "].");

    }

    protected String genToken(AccountVo accountVo) {

        String token = IdUtil.simpleUUID();

        this.redisTemplate.opsForValue().set(String.format("account:session:%s", new Object[]{token}), JSON.toJSONString(accountVo));

        return token;

    }


    public String getApi() {

        return "api_register";

    }


    public boolean validateToken() {

        return false;

    }

}

