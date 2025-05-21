package com.porn.client.user.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.menu.dto.MenuQueryDTO;
import com.porn.client.menu.vo.MenuVo;
import com.porn.client.server.dto.GenCaptchaDTO;
import com.porn.client.user.dto.*;
import com.porn.client.user.vo.CaptchaVo;
import com.porn.client.user.vo.UserLoginVo;
import com.porn.client.user.vo.UserLogoutVo;
import com.porn.client.user.vo.UserVo;

import java.util.List;

public interface UserApiService {
    CaptchaVo genCaptcha(GenCaptchaDTO paramGenCaptchaDTO);

    PageVo<UserVo> queryPage(UserQueryPageDTO paramUserQueryPageDTO);

    PageVo<UserLoginVo> queryOnlinePage(UserOnlineQueryPageDTO paramUserOnlineQueryPageDTO);

    UserVo queryUser(UserQueryDTO paramUserQueryDTO);

    boolean enableOrDisable(UserEnableOrDisableDTO paramUserEnableOrDisableDTO);

    UserVo saveOrUpdate(UserSaveOrUpdateDTO paramUserSaveOrUpdateDTO);

    boolean delete(UserDeleteDTO paramUserDeleteDTO);

    boolean resetPwd(UserResetPwdDTO paramUserResetPwdDTO);

    boolean newPwd(UserNewPwdDTO paramUserNewPwdDTO);

    boolean validatePwd(UserValidatePwdDTO paramUserValidatePwdDTO);

    UserLoginVo login(UserLoginDTO paramUserLoginDTO);

    UserLoginVo info(UserInfoDTO paramUserInfoDTO);

    UserLogoutVo logout(UserLogoutDTO paramUserLogoutDTO);

    boolean offline(UserOfflineDTO paramUserOfflineDTO);

    Boolean authSaveOrUpdate(UserRoleAuthSaveOrUpdateDTO paramUserRoleAuthSaveOrUpdateDTO);

    List<MenuVo> queryUserMenuList(MenuQueryDTO paramMenuQueryDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/user/api/UserApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */