package com.porn.web.mng.business;

import com.porn.client.common.vo.PageVo;
import com.porn.client.nickname.api.NickNameApiService;
import com.porn.client.nickname.dto.NickNameBatchDeleteDTO;
import com.porn.client.nickname.dto.NickNameQueryPageDTO;
import com.porn.client.nickname.dto.NickNameSaveOrUpdateDTO;
import com.porn.client.nickname.vo.NickNameVo;
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

@Api(tags = {"后台管理-业务管理-昵称管理"})
@RestController
@RequestMapping({"/mng/business/nickname"})
public class NickNameController
        extends BaseController {
    @Autowired
    private NickNameApiService nickNameApiService;

    @ApiOperation("查询")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<NickNameVo>> queryPage(@RequestBody NickNameQueryPageDTO nickNameQueryPageDTO) {
        Objects.requireNonNull(this.nickNameApiService);
        return Msg.executeService(nickNameQueryPageDTO, this.nickNameApiService::queryPage);
    }

    @ApiOperation("批量插入")
    @PostMapping({"/batchCreate"})
    public Msg<Boolean> batchCreate(@RequestBody List<NickNameSaveOrUpdateDTO> nickNameSaveOrUpdateDTOList) {
        Objects.requireNonNull(this.nickNameApiService);
        return Msg.executeSimpleService(nickNameSaveOrUpdateDTOList, this.nickNameApiService::batchSaveNickName);
    }

    @ApiOperation("批量删除")
    @PostMapping({"/batchDelete"})
    public Msg<Boolean> batchDelete(@RequestBody NickNameBatchDeleteDTO nickNameBatchDeleteDTO) {
        Objects.requireNonNull(this.nickNameApiService);
        return Msg.executeService(nickNameBatchDeleteDTO, this.nickNameApiService::batchDelete);
    }
}

