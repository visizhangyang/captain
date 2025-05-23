package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class AccountSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("账户名称")
    private String name;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("密码")
    private String loginPwd;
    @ApiModelProperty("收款地址")
    private String receiveAddress;
    @ApiModelProperty("QQ号码")
    private String qq;
    @ApiModelProperty("微信号码")
    private String wechat;
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("当前账户的推广码")
    private String promotionCode;
    @ApiModelProperty("当前账户的父推荐码")
    private String parentPromotionCode;
    @ApiModelProperty("总余额")
    private BigDecimal totalBalance;

    @ApiModelProperty("可用余额")
    private BigDecimal availableBalance;
    @ApiModelProperty("冻结余额")
    private BigDecimal freezeBalance;
    @ApiModelProperty("账户级别")
    private Integer accountLevel;
    @ApiModelProperty("下级是否可见, 默认不可见, 1-可见, 0-不可见 com.porn.client.common.enums.EnableStatusEnum")
    private Integer subVisit;
    @ApiModelProperty("status_")
    private Integer status;
    @ApiModelProperty("账户类型, com.porn.client.account.enums.AccountTypeEnum")
    private Integer accountType;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("父ID")
    private Long parentId;
    @ApiModelProperty("重点关注, 默认不关注, 1-关注, 0-不关注 com.porn.client.common.enums.EnableStatusEnum")
    private Integer keynoteFollow;
    @ApiModelProperty("自动搬砖, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer autoWork;
    @ApiModelProperty("设备id")
    private String deviceId;
    @ApiModelProperty("相册状态, 判断当前相册状态, PhotoStatusEnum")
    private Integer photoStatus;
    @ApiModelProperty("上传状态, 上传状态, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer uploadStatus;

    protected AccountSaveOrUpdateDTO(AccountSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.nickName = b.nickName;
        this.avatar = b.avatar;
        this.loginPwd = b.loginPwd;
        this.receiveAddress = b.receiveAddress;
        this.qq = b.qq;
        this.wechat = b.wechat;
        this.phone = b.phone;
        this.promotionCode = b.promotionCode;
        this.parentPromotionCode = b.parentPromotionCode;
        this.totalBalance = b.totalBalance;
        this.availableBalance = b.availableBalance;
        this.freezeBalance = b.freezeBalance;
        this.accountLevel = b.accountLevel;
        this.subVisit = b.subVisit;
        this.status = b.status;
        this.accountType = b.accountType;
        this.remark = b.remark;
        this.parentId = b.parentId;
        this.keynoteFollow = b.keynoteFollow;
        this.autoWork = b.autoWork;
        this.deviceId = b.deviceId;
        this.photoStatus = b.photoStatus;
        this.uploadStatus = b.uploadStatus;
    }

    public AccountSaveOrUpdateDTO(String name, String nickName, String avatar, String loginPwd, String receiveAddress, String qq, String wechat, String phone, String promotionCode, String parentPromotionCode, BigDecimal totalBalance, BigDecimal availableBalance, BigDecimal freezeBalance, Integer accountLevel, Integer subVisit, Integer status, Integer accountType, String remark, Long parentId, Integer keynoteFollow, Integer autoWork, String deviceId, Integer photoStatus, Integer uploadStatus) {

        this.name = name;
        this.nickName = nickName;
        this.avatar = avatar;
        this.loginPwd = loginPwd;
        this.receiveAddress = receiveAddress;
        this.qq = qq;
        this.wechat = wechat;
        this.phone = phone;
        this.promotionCode = promotionCode;
        this.parentPromotionCode = parentPromotionCode;
        this.totalBalance = totalBalance;
        this.availableBalance = availableBalance;
        this.freezeBalance = freezeBalance;
        this.accountLevel = accountLevel;
        this.subVisit = subVisit;
        this.status = status;
        this.accountType = accountType;
        this.remark = remark;
        this.parentId = parentId;
        this.keynoteFollow = keynoteFollow;
        this.autoWork = autoWork;
        this.deviceId = deviceId;
        this.photoStatus = photoStatus;
        this.uploadStatus = uploadStatus;

    }

    public AccountSaveOrUpdateDTO() {
    }

    public static AccountSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new AccountSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountSaveOrUpdateDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {

        return this.nickName;

    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {

        return this.avatar;

    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLoginPwd() {

        return this.loginPwd;

    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getReceiveAddress() {

        return this.receiveAddress;

    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getQq() {

        return this.qq;

    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {

        return this.wechat;

    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPhone() {

        return this.phone;

    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPromotionCode() {

        return this.promotionCode;

    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getParentPromotionCode() {

        return this.parentPromotionCode;

    }

    public void setParentPromotionCode(String parentPromotionCode) {
        this.parentPromotionCode = parentPromotionCode;
    }

    public BigDecimal getTotalBalance() {

        return this.totalBalance;

    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public BigDecimal getAvailableBalance() {

        return this.availableBalance;

    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getFreezeBalance() {

        return this.freezeBalance;

    }

    public void setFreezeBalance(BigDecimal freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    public Integer getAccountLevel() {

        return this.accountLevel;

    }

    public void setAccountLevel(Integer accountLevel) {
        this.accountLevel = accountLevel;
    }

    public Integer getSubVisit() {

        return this.subVisit;

    }

    public void setSubVisit(Integer subVisit) {
        this.subVisit = subVisit;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAccountType() {

        return this.accountType;

    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getRemark() {

        return this.remark;

    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getParentId() {

        return this.parentId;

    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getKeynoteFollow() {

        return this.keynoteFollow;

    }

    public void setKeynoteFollow(Integer keynoteFollow) {
        this.keynoteFollow = keynoteFollow;
    }

    public Integer getAutoWork() {

        return this.autoWork;

    }

    public void setAutoWork(Integer autoWork) {
        this.autoWork = autoWork;
    }

    public String getDeviceId() {

        return this.deviceId;

    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getPhotoStatus() {

        return this.photoStatus;

    }

    public void setPhotoStatus(Integer photoStatus) {
        this.photoStatus = photoStatus;
    }

    public Integer getUploadStatus() {

        return this.uploadStatus;

    }

    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    private static final class AccountSaveOrUpdateDTOBuilderImpl extends AccountSaveOrUpdateDTOBuilder<AccountSaveOrUpdateDTO, AccountSaveOrUpdateDTOBuilderImpl> {
        private AccountSaveOrUpdateDTOBuilderImpl() {
        }

        protected AccountSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public AccountSaveOrUpdateDTO build() {
            return new AccountSaveOrUpdateDTO(this);
        }
    }

    public static abstract class AccountSaveOrUpdateDTOBuilder<C extends AccountSaveOrUpdateDTO, B extends AccountSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;
        private String nickName;
        private String avatar;
        private String loginPwd;
        private String receiveAddress;
        private String qq;
        private String wechat;
        private String phone;
        private String promotionCode;
        private String parentPromotionCode;
        private BigDecimal totalBalance;
        private BigDecimal availableBalance;
        private BigDecimal freezeBalance;
        private Integer accountLevel;
        private Integer subVisit;
        private Integer status;
        private Integer accountType;
        private String remark;
        private Long parentId;
        private Integer keynoteFollow;
        private Integer autoWork;
        private String deviceId;
        private Integer photoStatus;
        private Integer uploadStatus;

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B nickName(String nickName) {
            this.nickName = nickName;
            return self();
        }

        public B avatar(String avatar) {
            this.avatar = avatar;
            return self();
        }

        public B loginPwd(String loginPwd) {
            this.loginPwd = loginPwd;
            return self();
        }

        public B receiveAddress(String receiveAddress) {
            this.receiveAddress = receiveAddress;
            return self();
        }

        public B qq(String qq) {
            this.qq = qq;
            return self();
        }

        public B wechat(String wechat) {
            this.wechat = wechat;
            return self();
        }

        public B phone(String phone) {
            this.phone = phone;
            return self();
        }

        public B promotionCode(String promotionCode) {
            this.promotionCode = promotionCode;
            return self();
        }

        public B parentPromotionCode(String parentPromotionCode) {
            this.parentPromotionCode = parentPromotionCode;
            return self();
        }

        public B totalBalance(BigDecimal totalBalance) {
            this.totalBalance = totalBalance;
            return self();
        }

        public B availableBalance(BigDecimal availableBalance) {
            this.availableBalance = availableBalance;
            return self();
        }

        public B freezeBalance(BigDecimal freezeBalance) {
            this.freezeBalance = freezeBalance;
            return self();
        }

        public B accountLevel(Integer accountLevel) {
            this.accountLevel = accountLevel;
            return self();
        }

        public B subVisit(Integer subVisit) {
            this.subVisit = subVisit;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B accountType(Integer accountType) {
            this.accountType = accountType;
            return self();
        }

        public B remark(String remark) {
            this.remark = remark;
            return self();
        }

        public B parentId(Long parentId) {
            this.parentId = parentId;
            return self();
        }

        public B keynoteFollow(Integer keynoteFollow) {
            this.keynoteFollow = keynoteFollow;
            return self();
        }

        public B autoWork(Integer autoWork) {
            this.autoWork = autoWork;
            return self();
        }

        public B deviceId(String deviceId) {
            this.deviceId = deviceId;
            return self();
        }

        public B photoStatus(Integer photoStatus) {
            this.photoStatus = photoStatus;
            return self();
        }

        public B uploadStatus(Integer uploadStatus) {
            this.uploadStatus = uploadStatus;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

