package com.porn.web.mng.business;

import com.porn.client.common.entity.Pair;
import com.porn.client.desc.api.DescApiService;
import com.porn.client.desc.dto.DescQueryDTO;
import com.porn.client.desc.dto.DescSaveOrUpdateDTO;
import com.porn.client.desc.enums.DescTypeEnum;
import com.porn.client.desc.vo.DescVo;
import com.porn.web.common.controller.BaseController;
import com.porn.web.common.msg.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Api(tags = {"后台管理-业务管理-描述管理"})
@RestController
@RequestMapping({"/mng/business/desc"})
public class DescController
        extends BaseController {
    @Autowired
    private DescApiService descApiService;

    @ApiOperation("查询描述类型")
    @PostMapping({"/queryDescTypes"})
    public Msg<List<Pair>> queryDescList() {
        return Msg.success(Arrays.<DescTypeEnum>stream(DescTypeEnum.values()).map(dt -> Pair.of(dt.getDescription(), dt.getType())).collect(Collectors.toList()));
    }

    @ApiOperation("查询")
    @PostMapping({"/queryDescList"})
    public Msg<List<DescVo>> queryDescList(@RequestBody DescQueryDTO descQueryDTO) {
        Objects.requireNonNull(this.descApiService);
        return Msg.executeService(descQueryDTO, this.descApiService::queryDescList);
    }

    @ApiOperation("新增或更新")
    @PostMapping({"/batchCreate"})
    public Msg<Boolean> saveOrUpdate(@RequestBody List<DescSaveOrUpdateDTO> descSaveOrUpdateDTOList) {
        Objects.requireNonNull(this.descApiService);
        return Msg.executeSimpleService(descSaveOrUpdateDTOList, this.descApiService::batchCreate);
    }
}

