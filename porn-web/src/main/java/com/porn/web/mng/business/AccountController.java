package com.porn.web.mng.business;

import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.api.AccountExtApiService;
import com.porn.client.account.api.AccountWalletService;
import com.porn.client.account.dto.*;
import com.porn.client.account.vo.AccountExtVo;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.account.vo.AccountWalletVo;
import com.porn.client.common.vo.PageVo;
import com.porn.client.mobile.vo.MyTeamTreeVo;
import com.porn.client.plan.api.PlanInsApiService;
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

@Api(tags = {"后台管理-业务管理-账户管理"})
@RestController
@RequestMapping({"/mng/business/account"})
public class AccountController extends BaseController {
    @Autowired
    private AccountApiService accountApiService;
    @Autowired
    private AccountExtApiService accountExtApiService;
    @Autowired
    private AccountWalletService accountWalletService;
    @Autowired
    private PlanInsApiService planInsApiService;

    @ApiOperation("查询账户")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<AccountVo>> queryPage(@RequestBody AccountQueryPageDTO accountQueryPageDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountQueryPageDTO, PageVo<AccountVo>>executeService(accountQueryPageDTO, this.accountApiService::queryPage);
    }

    @ApiOperation("新增或更新")
    @PostMapping({"/saveOrUpdate"})
    public Msg<AccountVo> saveOrUpdate(@RequestBody AccountSaveOrUpdateDTO accountSaveOrUpdateDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountSaveOrUpdateDTO, AccountVo>executeService(accountSaveOrUpdateDTO, this.accountApiService::saveOrUpdate);
    }

    @ApiOperation("启用或禁用")
    @PostMapping({"/enableOrDisable"})
    public Msg<Boolean> enableOrDisable(@RequestBody AccountEnableOrDisableDTO accountEnableOrDisableDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountEnableOrDisableDTO, Boolean>executeService(accountEnableOrDisableDTO, this.accountApiService::enableOrDisable);
    }

    @ApiOperation("删除")
    @PostMapping({"/delete"})
    public Msg<Boolean> delete(@RequestBody AccountDeleteDTO accountDeleteDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountDeleteDTO, Boolean>executeService(accountDeleteDTO, this.accountApiService::delete);
    }

    @ApiOperation("重置密码")
    @PostMapping({"/resetPwd"})
    public Msg<Boolean> resetPwd(@RequestBody AccountResetPwdDTO accountResetPwdDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountResetPwdDTO, Boolean>executeService(accountResetPwdDTO, this.accountApiService::resetPwd);
    }

    @ApiOperation("冻结或解冻资金")
    @PostMapping({"/freeze"})
    public Msg<Boolean> freeze(@RequestBody AccountFreezeDTO accountFreezeDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountFreezeDTO, Boolean>executeService(accountFreezeDTO, this.accountApiService::freeze);
    }

    @ApiOperation("创建机器人")
    @PostMapping({"/createRobot"})
    public Msg<Boolean> createRobot(@RequestBody AccountRobotCreateDTO accountRobotCreateDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountRobotCreateDTO, Boolean>executeService(accountRobotCreateDTO, this.accountApiService::createRobot);
    }

    @ApiOperation("更新注册事件")
    @PostMapping({"/updateRegTime"})
    public Msg<Boolean> updateRegTime(@RequestBody AccountUpdateCreateTimeDTO accountUpdateCreateTimeDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountUpdateCreateTimeDTO, Boolean>executeService(accountUpdateCreateTimeDTO, this.accountApiService::updateRegTime);
    }

    @ApiOperation("更新备注")
    @PostMapping({"/updateRemark"})
    public Msg<Boolean> updateRemark(@RequestBody AccountUpdateRemarkDTO accountUpdateRemarkDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountUpdateRemarkDTO, Boolean>executeService(accountUpdateRemarkDTO, this.accountApiService::updateRemark);
    }

    @ApiOperation("加减金额")
    @PostMapping({"/addOrSub"})
    public Msg<Boolean> addOrSub(@RequestBody AccountAddOrSubDTO accountAddOrSubDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountAddOrSubDTO, Boolean>executeService(accountAddOrSubDTO, this.accountApiService::addOrSub);
    }

    @ApiOperation("账户级别")
    @PostMapping({"/updateAccountLevel"})
    public Msg<Boolean> updateAccountLevel(@RequestBody AccountLevelDTO accountLevelDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountLevelDTO, Boolean>executeService(accountLevelDTO, this.accountApiService::updateAccountLevel);
    }

    @ApiOperation("批量账户级别")
    @PostMapping({"/batchUpdateAccountLevel"})
    public Msg<Boolean> batchUpdateAccountLevel(@RequestBody AccountBatchLevelDTO accountBatchLevelDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountBatchLevelDTO, Boolean>executeService(accountBatchLevelDTO, this.accountApiService::batchUpdateAccountLevel);
    }

    @ApiOperation("代理树")
    @PostMapping({"/proxyTree"})
    public Msg<List<MyTeamTreeVo>> proxyTree(@RequestBody AccountProxyDTO accountProxyDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountProxyDTO, List<MyTeamTreeVo>>executeService(accountProxyDTO, this.accountApiService::proxyTree);
    }

    @ApiOperation("调整父节点")
    @PostMapping({"/changeParent"})
    public Msg<Boolean> changeParent(@RequestBody AccountChangeParentDTO accountChangeParentDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountChangeParentDTO, Boolean>executeService(accountChangeParentDTO, this.accountApiService::changeParent);
    }

    @ApiOperation("调整账号")
    @PostMapping({"/changeName"})
    public Msg<Boolean> changeName(@RequestBody AccountChangeNameDTO accountChangeNameDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountChangeNameDTO, Boolean>executeService(accountChangeNameDTO, this.accountApiService::changeName);
    }

    @ApiOperation("清空收款地址")
    @PostMapping({"/clearReceiveAddr"})
    public Msg<Boolean> clearReceiveAddr(@RequestBody AccountclearReceiveAddrDTO accountclearReceiveAddrDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountclearReceiveAddrDTO, Boolean>executeService(accountclearReceiveAddrDTO, this.accountApiService::clearReceiveAddr);
    }

    @ApiOperation("查询账户扩展信息")
    @PostMapping({"/queryAccountExt"})
    public Msg<AccountExtVo> queryAccountExt(@RequestBody AccountExtQueryDTO accountExtQueryDTO) {
        Objects.requireNonNull(this.accountExtApiService);
        return Msg.<AccountExtQueryDTO, AccountExtVo>executeService(accountExtQueryDTO, this.accountExtApiService::queryAccountExt);
    }

    @ApiOperation("保存或更新扩展信息")
    @PostMapping({"/saveOrUpdateExt"})
    public Msg<AccountExtVo> saveOrUpdateExt(@RequestBody AccountExtSaveOrUpdateDTO accountExtSaveOrUpdateDTO) {
        Objects.requireNonNull(this.accountExtApiService);
        return Msg.<AccountExtSaveOrUpdateDTO, AccountExtVo>executeService(accountExtSaveOrUpdateDTO, this.accountExtApiService::saveOrUpdate);
    }

    @ApiOperation("查询账户历史地址")
    @PostMapping({"/queryHisWalletPage"})
    public Msg<PageVo<AccountWalletVo>> queryHisWalletPage(@RequestBody AccountWalletQueryPageDTO accountWalletQueryPageDTO) {
        Objects.requireNonNull(this.accountWalletService);
        return Msg.<AccountWalletQueryPageDTO, PageVo<AccountWalletVo>>executeService(accountWalletQueryPageDTO, this.accountWalletService::queryPage);
    }

    @ApiOperation("停止计划")
    @PostMapping({"/forceStopPlan"})
    public Msg<Boolean> forceStopPlan(@RequestBody AccountForceStopPlanDTO accountForceStopPlanDTO) {
        Objects.requireNonNull(this.accountApiService);
        return Msg.<AccountForceStopPlanDTO, Boolean>executeService(accountForceStopPlanDTO, this.accountApiService::forceStopPlan);
    }
}

