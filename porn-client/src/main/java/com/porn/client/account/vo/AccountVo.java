/*     */
package com.porn.client.account.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountVo extends BaseVo {
    @ApiModelProperty("账户名称")
    private String name;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("头像")
    private String avatarUrl;
    @ApiModelProperty("收款地址")
    private String receiveAddress;
    @ApiModelProperty("QQ号码")
    /*     */ private String qq;
    @ApiModelProperty("微信号码")
    /*     */ private String wechat;
    @ApiModelProperty("手机号码")
    /*     */ private String phone;
    @ApiModelProperty("当前账户的推广码")
    /*     */ private String promotionCode;
    @ApiModelProperty("当前账户的父推荐码")
    /*     */ private String parentPromotionCode;
    @ApiModelProperty("总余额")
    /*     */ private BigDecimal totalBalance;
    @ApiModelProperty("可用余额")
    /*     */ private BigDecimal availableBalance;
    @ApiModelProperty("冻结余额")
    /*     */ private BigDecimal freezeBalance;
    @ApiModelProperty("账户级别")
    /*     */ private Integer accountLevel;
    @ApiModelProperty("状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    /*     */ private Integer status;
    @ApiModelProperty("搬砖权限, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    /*     */ private Integer workStatus;
    @ApiModelProperty("提现权限, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    /*     */ private Integer withdrawStatus;
    @ApiModelProperty("转账权限, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    /*     */ private Integer transferStatus;
    @ApiModelProperty("账户类型, com.porn.client.account.enums.AccountTypeEnum")
    /*     */ private Integer accountType;
    @ApiModelProperty("搬砖自动到账, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    /*  16 */ private Integer workAutoAudit;

    @ApiModelProperty("下级是否可见, 默认不可见, 1-可见, 0-不可见 com.porn.client.common.enums.EnableStatusEnum")
    private Integer subVisit;
    @ApiModelProperty("上级账户")
    private String parentAccount;
    @ApiModelProperty("直推人数")
    private Integer directPromotionCount;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("父ID")
    private Long parentId;
    @ApiModelProperty("重点关注, 默认不关注, 1-关注, 0-不关注 com.porn.client.common.enums.EnableStatusEnum")
    private Integer keynoteFollow;
    @ApiModelProperty("自动搬砖, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer autoWork;
    @ApiModelProperty("搬砖总收入")
    private BigDecimal workIncome;
    @ApiModelProperty("分成总收入")
    private BigDecimal proxyIncome;
    @ApiModelProperty("充值收入")
    private BigDecimal rechargeIncome;
    @ApiModelProperty("转账收入")
    private BigDecimal transferIncome;
    @ApiModelProperty("其他收入, 余U宝, 红包")
    private BigDecimal otherIncome;
    @ApiModelProperty("抽奖收入")
    private BigDecimal rewardIncome;
    @ApiModelProperty("总收入, 搬砖 + 分成 + (转账, 红包, 利息....)")
    private BigDecimal totalIncome;
    @ApiModelProperty("设备id")
    private String deviceId;
    @ApiModelProperty("相册状态, 判断当前相册状态, PhotoStatusEnum")
    private Integer photoStatus;
    @ApiModelProperty("上传状态, 上传状态, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer uploadStatus;
    @ApiModelProperty("计划当前是否使用中, 上传状态, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer planStatus;
    @ApiModelProperty("计划状态, 上传状态, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer planEnableStatus;
    @ApiModelProperty("抽奖权限, 默认启用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer rewardEnableStatus;

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/vo/AccountVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */