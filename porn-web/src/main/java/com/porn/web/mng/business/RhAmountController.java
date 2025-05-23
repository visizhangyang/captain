package com.porn.web.mng.business;

import com.porn.client.rhamount.api.RhAmountApiService;
import com.porn.client.rhamount.dto.RhAmountQueryDTO;
import com.porn.client.rhamount.dto.RhAmountSaveOrUpdateDTO;
import com.porn.client.rhamount.vo.RhAmountVo;
import com.porn.web.common.controller.BaseController;
import com.porn.web.common.msg.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


@Api(tags = {"后台管理-业务管理-充值金额"})
@RestController
@RequestMapping({"/mng/business/rhamount"})
public class RhAmountController
        extends BaseController {
    @Autowired
    private RhAmountApiService rhAmountApiService;

    @ApiOperation("查询")
    @PostMapping({"/queryRhAmountList"})
    public Msg<List<RhAmountVo>> queryRhAmountList(@RequestBody RhAmountQueryDTO rhAmountQueryDTO) {
        Objects.requireNonNull(this.rhAmountApiService);
        return Msg.executeService(rhAmountQueryDTO, this.rhAmountApiService::queryRhAmountList);
    }

    @ApiOperation("新增或更新")
    @PostMapping({"/batchCreate"})
    public Msg<Boolean> saveOrUpdate(@RequestBody List<RhAmountSaveOrUpdateDTO> rhAmountSaveOrUpdateDTOList) {
        Objects.requireNonNull(this.rhAmountApiService);
        return Msg.executeSimpleService(rhAmountSaveOrUpdateDTOList, this.rhAmountApiService::batchCreate);
    }
}

