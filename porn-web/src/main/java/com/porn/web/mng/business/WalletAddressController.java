package com.porn.web.mng.business;

import com.porn.client.common.vo.PageVo;
import com.porn.client.wallet.api.WalletAddressApiService;
import com.porn.client.wallet.dto.*;
import com.porn.client.wallet.vo.WalletAddressVo;
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

@Api(tags = {"后台管理-业务管理-收款地址管理"})
@RestController
@RequestMapping({"/mng/business/walletaddress"})
public class WalletAddressController extends BaseController {
    @Autowired
    private WalletAddressApiService walletAddressApiService;

    @ApiOperation("查询钱包")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<WalletAddressVo>> queryPage(@RequestBody WalletAddressQueryPageDTO walletAddressQueryPageDTO) {
        Objects.requireNonNull(this.walletAddressApiService);
        return Msg.executeService(walletAddressQueryPageDTO, this.walletAddressApiService::queryPage);
    }

    @ApiOperation("启用或禁用")
    @PostMapping({"/enableOrDisable"})
    public Msg<Boolean> enableOrDisable(@RequestBody WalletAddressEnableOrDisableDTO walletAddressEnableOrDisableDTO) {
        Objects.requireNonNull(this.walletAddressApiService);
        return Msg.executeService(walletAddressEnableOrDisableDTO, this.walletAddressApiService::enableOrDisable);
    }

    @ApiOperation("新增或更新地址")
    @PostMapping({"/saveOrUpdate"})
    public Msg<WalletAddressVo> saveOrUpdate(@RequestBody WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO) {
        Objects.requireNonNull(this.walletAddressApiService);
        return Msg.executeService(walletAddressSaveOrUpdateDTO, this.walletAddressApiService::saveOrUpdate);
    }

    @ApiOperation("删除")
    @PostMapping({"/delete"})
    public Msg<Boolean> delete(@RequestBody WalletAddressDeleteDTO walletAddressDeleteDTO) {
        Objects.requireNonNull(this.walletAddressApiService);
        return Msg.executeService(walletAddressDeleteDTO, this.walletAddressApiService::delete);
    }

    @ApiOperation("更新备注")
    @PostMapping({"/updateRemark"})
    public Msg<Boolean> updateRemark(@RequestBody WalletAddressUpdateRemarkDTO walletAddressUpdateRemarkDTO) {
        Objects.requireNonNull(this.walletAddressApiService);
        return Msg.executeService(walletAddressUpdateRemarkDTO, this.walletAddressApiService::updateRemark);
    }
}

