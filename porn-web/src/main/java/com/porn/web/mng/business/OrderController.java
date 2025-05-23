package com.porn.web.mng.business;

import com.porn.client.common.vo.PageVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.*;
import com.porn.client.order.vo.OrderVo;
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

@Api(tags = {"后台管理-业务管理-订单管理"})
@RestController
@RequestMapping({"/mng/business/order"})
public class OrderController
        extends BaseController {
    @Autowired
    private OrderApiService orderApiService;

    @ApiOperation("查询订单")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<OrderVo>> queryPage(@RequestBody OrderQueryPageDTO orderQueryPageDTO) {
        Objects.requireNonNull(this.orderApiService);
        return Msg.executeService(orderQueryPageDTO, this.orderApiService::queryPage);
    }

    @ApiOperation("审核通过")
    @PostMapping({"/audit"})
    public Msg<Boolean> audit(@RequestBody OrderAuditDTO orderAuditDTO) {
        Objects.requireNonNull(this.orderApiService);
        return Msg.executeService(orderAuditDTO, this.orderApiService::audit);
    }

    @ApiOperation("直接通过")
    @PostMapping({"/keepAudit"})
    public Msg<Boolean> keepAudit(@RequestBody OrderAuditDTO orderAuditDTO) {
        Objects.requireNonNull(this.orderApiService);
        return Msg.executeService(orderAuditDTO, this.orderApiService::keepAudit);
    }

    @ApiOperation("冻结")
    @PostMapping({"/freeze"})
    public Msg<Boolean> freeze(@RequestBody OrderFreezeDTO orderFreezeDTO) {
        Objects.requireNonNull(this.orderApiService);
        return Msg.executeService(orderFreezeDTO, this.orderApiService::freeze);
    }

    @ApiOperation("解冻")
    @PostMapping({"/unfreeze"})
    public Msg<Boolean> unfreeze(@RequestBody OrderUnFreezeDTO orderUnFreezeDTO) {
        Objects.requireNonNull(this.orderApiService);
        return Msg.executeService(orderUnFreezeDTO, this.orderApiService::unfreeze);
    }

    @ApiOperation("删除")
    @PostMapping({"/delete"})
    public Msg<Boolean> delete(@RequestBody OrderDeleteDTO orderDeleteDTO) {
        Objects.requireNonNull(this.orderApiService);
        return Msg.executeService(orderDeleteDTO, this.orderApiService::delete);
    }

    @ApiOperation("手工超时")
    @PostMapping({"/orderTimeOut"})
    public Msg<Boolean> orderTimeOut(@RequestBody OrderTimeOutDTO orderTimeOutDTO) {
        Objects.requireNonNull(this.orderApiService);
        return Msg.executeService(orderTimeOutDTO, this.orderApiService::orderTimeOut);
    }
}

