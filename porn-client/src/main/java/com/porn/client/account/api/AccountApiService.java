package com.porn.client.account.api;

import com.porn.client.account.dto.*;
import com.porn.client.account.vo.AccountStatisticsVo;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.vo.PageVo;
import com.porn.client.mobile.vo.MyTeamTreeVo;

import java.util.List;

public interface AccountApiService {
    AccountVo queryAccount(AccountQueryDTO paramAccountQueryDTO);

    List<AccountVo> queryAccountList(AccountQueryDTO paramAccountQueryDTO);

    PageVo<AccountVo> queryPage(AccountQueryPageDTO paramAccountQueryPageDTO);

    PageVo<AccountVo> queryProxyPage(ProxyAccountQueryPageDTO paramProxyAccountQueryPageDTO);

    Boolean enableOrDisable(AccountEnableOrDisableDTO paramAccountEnableOrDisableDTO);

    Boolean delete(AccountDeleteDTO paramAccountDeleteDTO);

    Boolean resetPwd(AccountResetPwdDTO paramAccountResetPwdDTO);

    AccountVo saveOrUpdate(AccountSaveOrUpdateDTO paramAccountSaveOrUpdateDTO);

    boolean validatePwd(AccountValidatePwdDTO paramAccountValidatePwdDTO);

    boolean modifyPwd(AccountModifyPwdDTO paramAccountModifyPwdDTO);

    AccountStatisticsVo accountStatistics(AccountStatisticsDTO paramAccountStatisticsDTO);

    AccountVo operateAmount(AccountAmountOperateDTO paramAccountAmountOperateDTO);

    Boolean freeze(AccountFreezeDTO paramAccountFreezeDTO);

    Boolean createRobot(AccountRobotCreateDTO paramAccountRobotCreateDTO);

    Boolean updateRegTime(AccountUpdateCreateTimeDTO paramAccountUpdateCreateTimeDTO);

    Boolean offlineOthers(AccountOfflineDTO paramAccountOfflineDTO);

    Boolean updateRemark(AccountUpdateRemarkDTO paramAccountUpdateRemarkDTO);

    Boolean addOrSub(AccountAddOrSubDTO paramAccountAddOrSubDTO);

    Boolean updateAccountLevel(AccountLevelDTO paramAccountLevelDTO);

    Boolean batchUpdateAccountLevel(AccountBatchLevelDTO paramAccountBatchLevelDTO);

    List<MyTeamTreeVo> proxyTree(AccountProxyDTO paramAccountProxyDTO);

    Boolean changeParent(AccountChangeParentDTO paramAccountChangeParentDTO);

    Boolean clearReceiveAddr(AccountclearReceiveAddrDTO paramAccountclearReceiveAddrDTO);

    Boolean modifyDeviceId(AccountcModifyDeviceIdDTO paramAccountcModifyDeviceIdDTO);

    Boolean modifyPhotoStatus(AccountModifyPhotoStatusDTO paramAccountModifyPhotoStatusDTO);

    Boolean changeName(AccountChangeNameDTO paramAccountChangeNameDTO);

    List<Long> queryProxyTeams(AccountQueryProxyTeamsDTO paramAccountQueryProxyTeamsDTO);

    Boolean forceStopPlan(AccountForceStopPlanDTO paramAccountForceStopPlanDTO);
}

