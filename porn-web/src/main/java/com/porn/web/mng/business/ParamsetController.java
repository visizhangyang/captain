package com.porn.web.mng.business;

import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.dto.ParamsetSaveOrUpdateDTO;
import com.porn.client.paramset.vo.ParamsetVo;
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


@Api(tags = {"后台管理-业务管理-参数设置"})
@RestController
@RequestMapping({"/mng/business/paramset"})
public class ParamsetController
        extends BaseController {
    @Autowired
    private ParamsetApiService paramsetApiService;

    @ApiOperation("查询订单")
    @PostMapping({"/queryParamset"})
    public Msg<ParamsetVo> queryParamset(@RequestBody ParamsetQueryDTO paramsetQueryDTO) {
        Objects.requireNonNull(this.paramsetApiService);
        return Msg.executeService(paramsetQueryDTO, this.paramsetApiService::queryParamset);
    }

    @ApiOperation("新增或更新")
    @PostMapping({"/saveOrUpdate"})
    public Msg<ParamsetVo> saveOrUpdate(@RequestBody ParamsetSaveOrUpdateDTO paramsetSaveOrUpdateDTO) {
        Objects.requireNonNull(this.paramsetApiService);
        return Msg.executeService(paramsetSaveOrUpdateDTO, this.paramsetApiService::saveOrUpdate);
    }
}

