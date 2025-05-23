package com.porn.web.mng.business;

import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.ProxyAccountQueryPageDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.vo.PageVo;
import com.porn.web.common.controller.BaseController;
import com.porn.web.common.msg.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@Api(tags = {"后台管理-业务管理-代理账户管理"})
@RestController
@RequestMapping({"/mng/business/proxyaccount"})
public class ProxyAccountController
        extends BaseController {
    @Autowired
    private AccountApiService accountApiService;

    @ApiOperation("查询账户")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<AccountVo>> queryPage(@RequestBody ProxyAccountQueryPageDTO proxyAccountQueryPageDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.executeService(proxyAccountQueryPageDTO, this.accountApiService::queryProxyPage);
    }
}

