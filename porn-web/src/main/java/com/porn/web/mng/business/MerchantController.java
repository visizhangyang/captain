package com.porn.web.mng.business;

import com.porn.client.common.vo.PageVo;
import com.porn.client.merchant.api.MerchantApiService;
import com.porn.client.merchant.api.MerchantDescApiService;
import com.porn.client.merchant.dto.*;
import com.porn.client.merchant.enums.MerchantTypeEnum;
import com.porn.client.merchant.vo.MerchantDescVo;
import com.porn.client.merchant.vo.MerchantVo;
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

@Api(tags = {"后台管理-业务管理-商户管理"})
@RestController
@RequestMapping({"/mng/business/merchant"})
public class MerchantController
        extends BaseController {
    @Autowired
    private MerchantApiService merchantApiService;
    @Autowired
    private MerchantDescApiService merchantDescApiService;

    @ApiOperation("查询商户")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<MerchantVo>> queryPage(@RequestBody MerchantQueryPageDTO merchantQueryPageDTO) {
        Objects.requireNonNull(this.merchantApiService);
        return Msg.executeService(merchantQueryPageDTO, this.merchantApiService::queryPage);
    }

    @ApiOperation("启用或禁用")
    @PostMapping({"/enableOrDisable"})
    public Msg<Boolean> enableOrDisable(@RequestBody MerchantEnableOrDisableDTO merchantEnableOrDisableDTO) {
        Objects.requireNonNull(this.merchantApiService);
        return Msg.executeService(merchantEnableOrDisableDTO, this.merchantApiService::enableOrDisable);
    }

    @ApiOperation("新增或更新")
    @PostMapping({"/saveOrUpdate"})
    public Msg<MerchantVo> saveOrUpdate(@RequestBody MerchantSaveOrUpdateDTO merchantSaveOrUpdateDTO) {
        merchantSaveOrUpdateDTO.setMerchantType(MerchantTypeEnum.NORMAL.getType());
        Objects.requireNonNull(this.merchantApiService);
        return Msg.executeService(merchantSaveOrUpdateDTO, this.merchantApiService::saveOrUpdate);
    }

    @ApiOperation("删除")
    @PostMapping({"/delete"})
    public Msg<Boolean> delete(@RequestBody MerchantDeleteDTO merchantDeleteDTO) {
        Objects.requireNonNull(this.merchantApiService);
        return Msg.executeService(merchantDeleteDTO, this.merchantApiService::delete);
    }

    @ApiOperation("创建机器人")
    @PostMapping({"/createRobot"})
    public Msg<Boolean> createRobot(@RequestBody MerchantRobotCreateDTO merchantRobotCreateDTO) {
        Objects.requireNonNull(this.merchantApiService);
        return Msg.executeService(merchantRobotCreateDTO, this.merchantApiService::createRobot);
    }

    @ApiOperation("新增或更新")
    @PostMapping({"/saveOrUpdateDesc"})
    public Msg<MerchantDescVo> saveOrUpdateDesc(@RequestBody MerchantDescSaveOrUpdateDTO merchantDescSaveOrUpdateDTO) {
        Objects.requireNonNull(this.merchantDescApiService);
        return Msg.executeService(merchantDescSaveOrUpdateDTO, this.merchantDescApiService::saveOrUpdate);
    }

    @ApiOperation("查询商户描述")
    @PostMapping({"/queryDesc"})
    public Msg<MerchantDescVo> queryDesc(@RequestBody MerchantDescQueryDTO merchantDescQueryDTO) {
        Objects.requireNonNull(this.merchantDescApiService);
        return Msg.executeService(merchantDescQueryDTO, this.merchantDescApiService::queryMerchantDesc);
    }
}

