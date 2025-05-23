package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AccountQueryDTO extends BaseDTO {
    @ApiModelProperty("账户名称")
    private String name;

    @ApiModelProperty("账户ID列表")
    private List<Long> accountIdList;

    @ApiModelProperty("推广码")
    private List<String> promotionCodeList;

    @ApiModelProperty("父推广码")
    private List<String> parentPromotionCodeList;

    @ApiModelProperty("账户级别")
    private Integer accountLevel;

    @ApiModelProperty("账户级别")
    private List<Integer> accountLevelList;

    @ApiModelProperty("账户类型, com.porn.client.account.enums.AccountTypeEnum")
    private Integer accountType;
    @ApiModelProperty("账户类型, com.porn.client.account.enums.AccountTypeEnum")
    private List<Integer> accountTypeList;
    @ApiModelProperty("父ID")
    private Long parentId;
    @ApiModelProperty("父ID列表")
    private List<Long> parentIdList;
    @ApiModelProperty("自动搬砖, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer autoWork;
    @ApiModelProperty("设备id")
    private String deviceId;
    @ApiModelProperty("相册状态, 判断当前相册状态, PhotoStatusEnum")
    private Integer photoStatus;
    @ApiModelProperty("上传状态, 上传状态, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer uploadStatus;

    protected AccountQueryDTO(AccountQueryDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.accountIdList = b.accountIdList;
        this.promotionCodeList = b.promotionCodeList;
        this.parentPromotionCodeList = b.parentPromotionCodeList;
        this.accountLevel = b.accountLevel;
        this.accountLevelList = b.accountLevelList;
        this.accountType = b.accountType;
        this.accountTypeList = b.accountTypeList;
        this.parentId = b.parentId;
        this.parentIdList = b.parentIdList;
        this.autoWork = b.autoWork;
        this.deviceId = b.deviceId;
        this.photoStatus = b.photoStatus;
        this.uploadStatus = b.uploadStatus;
    }

    public AccountQueryDTO(String name, List<Long> accountIdList, List<String> promotionCodeList, List<String> parentPromotionCodeList, Integer accountLevel, List<Integer> accountLevelList, Integer accountType, List<Integer> accountTypeList, Long parentId, List<Long> parentIdList, Integer autoWork, String deviceId, Integer photoStatus, Integer uploadStatus) {

        this.name = name;
        this.accountIdList = accountIdList;
        this.promotionCodeList = promotionCodeList;
        this.parentPromotionCodeList = parentPromotionCodeList;
        this.accountLevel = accountLevel;
        this.accountLevelList = accountLevelList;
        this.accountType = accountType;
        this.accountTypeList = accountTypeList;
        this.parentId = parentId;
        this.parentIdList = parentIdList;
        this.autoWork = autoWork;
        this.deviceId = deviceId;
        this.photoStatus = photoStatus;
        this.uploadStatus = uploadStatus;

    }

    public AccountQueryDTO() {
    }

    public static AccountQueryDTOBuilder<?, ?> builder() {
        return new AccountQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountQueryDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getAccountIdList() {

        return this.accountIdList;

    }

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }

    public List<String> getPromotionCodeList() {

        return this.promotionCodeList;

    }

    public void setPromotionCodeList(List<String> promotionCodeList) {
        this.promotionCodeList = promotionCodeList;
    }

    public List<String> getParentPromotionCodeList() {

        return this.parentPromotionCodeList;

    }

    public void setParentPromotionCodeList(List<String> parentPromotionCodeList) {
        this.parentPromotionCodeList = parentPromotionCodeList;
    }

    public Integer getAccountLevel() {

        return this.accountLevel;

    }

    public void setAccountLevel(Integer accountLevel) {
        this.accountLevel = accountLevel;
    }

    public List<Integer> getAccountLevelList() {

        return this.accountLevelList;

    }

    public void setAccountLevelList(List<Integer> accountLevelList) {
        this.accountLevelList = accountLevelList;
    }

    public Integer getAccountType() {

        return this.accountType;

    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public List<Integer> getAccountTypeList() {

        return this.accountTypeList;

    }

    public void setAccountTypeList(List<Integer> accountTypeList) {
        this.accountTypeList = accountTypeList;
    }

    public Long getParentId() {

        return this.parentId;

    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Long> getParentIdList() {

        return this.parentIdList;

    }

    public void setParentIdList(List<Long> parentIdList) {
        this.parentIdList = parentIdList;
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

    private static final class AccountQueryDTOBuilderImpl extends AccountQueryDTOBuilder<AccountQueryDTO, AccountQueryDTOBuilderImpl> {
        private AccountQueryDTOBuilderImpl() {
        }

        protected AccountQueryDTOBuilderImpl self() {
            return this;
        }

        public AccountQueryDTO build() {
            return new AccountQueryDTO(this);
        }
    }

    public static abstract class AccountQueryDTOBuilder<C extends AccountQueryDTO, B extends AccountQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;
        private List<Long> accountIdList;
        private List<String> promotionCodeList;
        private List<String> parentPromotionCodeList;
        private Integer accountLevel;
        private List<Integer> accountLevelList;
        private Integer accountType;
        private List<Integer> accountTypeList;
        private Long parentId;
        private List<Long> parentIdList;
        private Integer autoWork;
        private String deviceId;
        private Integer photoStatus;
        private Integer uploadStatus;

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B accountIdList(List<Long> accountIdList) {
            this.accountIdList = accountIdList;
            return self();
        }

        public B promotionCodeList(List<String> promotionCodeList) {
            this.promotionCodeList = promotionCodeList;
            return self();
        }

        public B parentPromotionCodeList(List<String> parentPromotionCodeList) {
            this.parentPromotionCodeList = parentPromotionCodeList;
            return self();
        }

        public B accountLevel(Integer accountLevel) {
            this.accountLevel = accountLevel;
            return self();
        }

        public B accountLevelList(List<Integer> accountLevelList) {
            this.accountLevelList = accountLevelList;
            return self();
        }

        public B accountType(Integer accountType) {
            this.accountType = accountType;
            return self();
        }

        public B accountTypeList(List<Integer> accountTypeList) {
            this.accountTypeList = accountTypeList;
            return self();
        }

        public B parentId(Long parentId) {
            this.parentId = parentId;
            return self();
        }

        public B parentIdList(List<Long> parentIdList) {
            this.parentIdList = parentIdList;
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

