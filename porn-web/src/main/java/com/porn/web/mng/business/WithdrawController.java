package com.porn.web.mng.business;

import com.porn.client.common.vo.PageVo;
import com.porn.client.withdraw.api.WithdrawApiService;
import com.porn.client.withdraw.dto.WithdrawCancelDTO;
import com.porn.client.withdraw.dto.WithdrawDeleteDTO;
import com.porn.client.withdraw.dto.WithdrawPassDTO;
import com.porn.client.withdraw.dto.WithdrawQueryPageDTO;
import com.porn.client.withdraw.vo.WithdrawVo;
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


@Api(tags = {"后台管理-业务管理-提现管理"})
@RestController
@RequestMapping({"/mng/business/withdraw"})
public class WithdrawController
        extends BaseController {
    @Autowired
    private WithdrawApiService withdrawApiService;

    @ApiOperation("分页查询")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<WithdrawVo>> queryPage(@RequestBody WithdrawQueryPageDTO withdrawQueryPageDTO) {
        Objects.requireNonNull(this.withdrawApiService);
        return Msg.executeService(withdrawQueryPageDTO, this.withdrawApiService::queryPage);
    }

    @ApiOperation("分页查询")
    @PostMapping({"/pass"})
    public Msg<WithdrawVo> pass(@RequestBody WithdrawPassDTO withdrawPassDTO) {
        Objects.requireNonNull(this.withdrawApiService);
        return Msg.executeService(withdrawPassDTO, this.withdrawApiService::pass);
    }

    @ApiOperation("退回")
    @PostMapping({"/cancel"})
    public Msg<Boolean> cancel(@RequestBody WithdrawCancelDTO withdrawCancelDTO) {
        Objects.requireNonNull(this.withdrawApiService);
        return Msg.executeService(withdrawCancelDTO, this.withdrawApiService::cancel);
    }

    @ApiOperation("删除")
    @PostMapping({"/delete"})
    public Msg<Boolean> delete(@RequestBody WithdrawDeleteDTO withdrawDeleteDTO) {
        Objects.requireNonNull(this.withdrawApiService);
        return Msg.executeService(withdrawDeleteDTO, this.withdrawApiService::delete);
    }
}

