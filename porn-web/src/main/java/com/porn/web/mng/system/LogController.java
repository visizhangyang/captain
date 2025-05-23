package com.porn.web.mng.system;

import com.porn.client.common.vo.PageVo;
import com.porn.client.log.api.LoginLogApiService;
import com.porn.client.log.api.OperateLogApiService;
import com.porn.client.log.dto.LoginLogQueryPageDTO;
import com.porn.client.log.dto.OperateLogQueryPageDTO;
import com.porn.client.log.vo.LoginLogVo;
import com.porn.client.log.vo.OperateLogVo;
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


@Api(tags = {"后台管理-系统管理-日志管理"})
@RestController
@RequestMapping({"/mng/system/log"})
public class LogController
        extends BaseController {
    @Autowired
    private LoginLogApiService loginLogApiService;
    @Autowired
    private OperateLogApiService operateLogApiService;

    @ApiOperation("用户登录日志")
    @PostMapping({"/queryLoginLogPage"})
    public Msg<PageVo<LoginLogVo>> queryLoginLogPage(@RequestBody LoginLogQueryPageDTO loginLogQueryPageDTO) {
        Objects.requireNonNull(this.loginLogApiService);
        return Msg.executeService(loginLogQueryPageDTO, this.loginLogApiService::queryPage);
    }

    @ApiOperation("用户操作日志")
    @PostMapping({"/queryOpLogPage"})
    public Msg<PageVo<OperateLogVo>> queryOpLogPage(@RequestBody OperateLogQueryPageDTO operateLogQueryPageDTO) {
        Objects.requireNonNull(this.operateLogApiService);
        return Msg.executeService(operateLogQueryPageDTO, this.operateLogApiService::queryPage);
    }
}

