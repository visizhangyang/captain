package com.porn.web.mng.business;

import com.porn.client.common.vo.PageVo;
import com.porn.client.config.api.ConfigApiService;
import com.porn.client.config.dto.ConfigDeleteDTO;
import com.porn.client.config.dto.ConfigEnableOrDisableDTO;
import com.porn.client.config.dto.ConfigQueryPageDTO;
import com.porn.client.config.dto.ConfigSaveOrUpdateDTO;
import com.porn.client.config.vo.ConfigVo;
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


@Api(tags = {"后台管理-系统管理-配置管理"})
@RestController
@RequestMapping({"/mng/system/config"})
public class ConfigController
        extends BaseController {
    @Autowired
    private ConfigApiService configApiService;

    @ApiOperation("分页查询")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<ConfigVo>> queryPage(@RequestBody ConfigQueryPageDTO configQueryPageDTO) {
        Objects.requireNonNull(this.configApiService);
        return Msg.executeService(configQueryPageDTO, this.configApiService::queryPage);
    }

    @ApiOperation("新增或更新")
    @PostMapping({"/saveOrUpdate"})
    public Msg<ConfigVo> saveOrUpdate(@RequestBody ConfigSaveOrUpdateDTO configSaveOrUpdateDTO) {
        Objects.requireNonNull(this.configApiService);
        return Msg.executeService(configSaveOrUpdateDTO, this.configApiService::saveOrUpdate);
    }

    @ApiOperation("启用或禁用")
    @PostMapping({"/enableOrDisable"})
    public Msg<Boolean> enableOrDisable(@RequestBody ConfigEnableOrDisableDTO configEnableOrDisableDTO) {
        Objects.requireNonNull(this.configApiService);
        return Msg.executeService(configEnableOrDisableDTO, this.configApiService::enableOrDisable);
    }

    @ApiOperation("删除")
    @PostMapping({"/delete"})
    public Msg<Boolean> delete(@RequestBody ConfigDeleteDTO configDeleteDTO) {
        Objects.requireNonNull(this.configApiService);
        return Msg.executeService(configDeleteDTO, this.configApiService::delete);
    }
}

