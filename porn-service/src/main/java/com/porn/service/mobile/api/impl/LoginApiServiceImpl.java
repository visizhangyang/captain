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
        implements ApiService<LoginAccountVo> {

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

        LoginApiRequestDTO loginApiRequestDTO = (LoginApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), LoginApiRequestDTO.class);


        String cacheCaptcha = (String) this.redisTemplate.opsForValue().get(String.format(CaptchaTypeEnum.LOGIN.getCacheKeyFormate(), new Object[]{StrUtil.emptyToDefault(loginApiRequestDTO.getCaptchaToken(), "")}));

        if (ObjectUtil.isEmpty(cacheCaptcha) ||
                !cacheCaptcha.equalsIgnoreCase(loginApiRequestDTO.getCaptcha())) {

            throw new BusinessException("验证码错误.");

        }

        this.redisTemplate.delete(String.format(CaptchaTypeEnum.REGISTER.getCacheKeyFormate(), new Object[]{StrUtil.emptyToDefault(loginApiRequestDTO.getCaptchaToken(), "")}));


        AccountVo accountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().name(loginApiRequestDTO.getName()).build());

        if (ObjectUtil.isEmpty(accountVo)) {

            throw new BusinessException("用户信息不存在.");

        }

        if (!EnableStatusEnum.ENABLE.getStatus().equals(accountVo.getStatus())) {

            throw new BusinessException("用户已禁用.");

        }


        if (!this.accountApiService.validatePwd(((AccountValidatePwdDTO.AccountValidatePwdDTOBuilder) AccountValidatePwdDTO.builder().id(accountVo.getId())).type(AccountValidateTypeEnum.LOGIN_PWD.getType()).pwd(loginApiRequestDTO.getLoginPwd()).build())) {

            throw new BusinessException("密码不正确.");

        }


        if (!AccountTypeEnum.NORMAL.getType().equals(accountVo.getAccountType())) {

            throw new BusinessException("用户信息不存在.");

        }


        String token = genToken(accountVo);

        LoginAccountVo loginAccountVo = this.mobileConverter.toLoginAccountVo(accountVo);

        loginAccountVo.setToken(token);


        StreamQueryDTO streamQueryDTO = StreamQueryDTO.builder().accountId(loginAccountVo.getId()).flag(StreamTypeEnum.REDENVELOPE.getFlag()).type(StreamTypeEnum.REDENVELOPE.getType()).bizId(Long.valueOf(-1L)).build();

        StreamVo streamVo = this.streamApiService.queryStream(streamQueryDTO);

        loginAccountVo.setReceiveRedPack(Integer.valueOf(ObjectUtil.isEmpty(streamVo) ? 0 : 1));


        AccountOfflineDTO accountOfflineDTO = ((AccountOfflineDTO.AccountOfflineDTOBuilder) AccountOfflineDTO.builder().id(loginAccountVo.getId())).activeToken(token).build();

        this.accountApiService.offlineOthers(accountOfflineDTO);


        if (ObjectUtil.isEmpty(accountVo.getDeviceId()) &&
                ObjectUtil.isNotEmpty(loginApiRequestDTO.getDeviceId())) {

            this.accountApiService.modifyDeviceId(((AccountcModifyDeviceIdDTO.AccountcModifyDeviceIdDTOBuilder) AccountcModifyDeviceIdDTO.builder().id(accountVo.getId())).deviceId(loginApiRequestDTO.getDeviceId()).build());

        }


        syncLoginUserAgent(cmdRequestDTO, accountVo);


        return loginAccountVo;

    }


    protected void syncLoginUserAgent(CmdRequestDTO cmdRequestDTO, AccountVo accountVo) {

        MobileExtDTO mobileExtDTO = cmdRequestDTO.getMobileExtDTO();

        if (ObjectUtil.isEmpty(mobileExtDTO) ||
                ObjectUtil.isEmpty(mobileExtDTO.getUserAgent())) {

            return;

        }

        UserAgent userAgent = UserAgentUtil.parse(mobileExtDTO.getUserAgent());


        this.dingdingMsgSender.sendMsg("账户[" + accountVo
                .getName() + "]登录成功, 是否移动端[" + (
                userAgent.isMobile() ? "是" : "否") + "], 平台[" + userAgent
                .getPlatform().getName() + "], 系统[" + userAgent
                .getOs().getName() + "], 浏览器[" + userAgent
                .getBrowser().getName() + "], 引擎[" + userAgent
                .getEngine().getName() + "], 是否可疑[" + (ClientUtil.isBadClient(userAgent) ? "是, 请上报" : "否") + "].");

    }


    protected String genToken(AccountVo accountVo) {

        String token = IdUtil.simpleUUID();

        this.redisTemplate.opsForValue().set(String.format("account:session:%s", new Object[]{token}), JSON.toJSONString(accountVo));

        return token;

    }


    public String getApi() {

        return "api_login";

    }


    public boolean validateToken() {

        return false;

    }

}

