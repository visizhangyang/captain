
package com.porn.service.account.dao.entity;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.*;import lombok.experimental.*;


import java.math.BigDecimal;

@TableName("porn_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AccountDO extends BaseDO {
    @TableField("name")
     private String name;
    @TableField("nick_name")
     private String nickName;
    @TableField("avatar")
     private String avatar;
    @TableField("login_pwd")
     private String loginPwd;
    @TableField("trade_pwd")
     private String tradePwd;
    @TableField("receive_address")
     private String receiveAddress;
    @TableField("qq")
     private String qq;
    @TableField("wechat")
     private String wechat;
    @TableField("phone")
     private String phone;
    @TableField("promotion_code")
     private String promotionCode;
    @TableField("parent_promotion_code")
     private String parentPromotionCode;
    @TableField("total_balance")
     private BigDecimal totalBalance;
    @TableField("available_balance")
     private BigDecimal availableBalance;
    @TableField("freeze_balance")
     private BigDecimal freezeBalance;
    @TableField("account_level")
    /*  17 */ private Integer accountLevel;

    @TableField("status_")
    private Integer status;
    @TableField("work_status")
    private Integer workStatus;
    @TableField("withdraw_status")
    private Integer withdrawStatus;
    @TableField("transfer_status")
    private Integer transferStatus;
    @TableField("workauto_audit")
    private Integer workAutoAudit;
    @TableField("sub_visit")
    private Integer subVisit;
    @TableField("account_type")
    private Integer accountType;
    @TableField("remark")
    private String remark;
    @TableField("parent_id")
    private Long parentId;
    @TableField("keynote_follow")
    private Integer keynoteFollow;
    @TableField("auto_work")
    private Integer autoWork;
    @TableField("device_id")
    private String deviceId;
    @TableField("photo_status")
    private Integer photoStatus;
    @TableField("upload_status")
    private Integer uploadStatus;
    @TableField("plan_enable_status")
    private Integer planEnableStatus;
    @TableField("reward_enable_status")
    private Integer rewardEnableStatus;

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/account/dao/entity/AccountDO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */