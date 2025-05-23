package com.porn.web.mng.business;

import com.porn.client.common.vo.PageVo;
import com.porn.client.recharge.api.RechargeApiService;
import com.porn.client.recharge.dto.ProxyRechargeQueryPageDTO;
import com.porn.client.recharge.vo.RechargeVo;
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


@Api(tags = {"后台管理-业务管理-代理充值管理"})
@RestController
@RequestMapping({"/mng/business/proxyrecharge"})
public class ProxyRechargeController
        extends BaseController {
    @Autowired
    private RechargeApiService rechargeApiService;

    @ApiOperation("分页查询")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<RechargeVo>> queryPage(@RequestBody ProxyRechargeQueryPageDTO proxyRechargeQueryPageDTO) {
        Objects.requireNonNull(this.rechargeApiService);
        return Msg.executeService(proxyRechargeQueryPageDTO, this.rechargeApiService::queryProxyPage);
    }
}

