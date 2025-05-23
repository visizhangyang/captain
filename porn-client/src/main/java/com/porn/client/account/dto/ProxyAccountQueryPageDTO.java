package com.porn.client.account.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

public class ProxyAccountQueryPageDTO extends BasePageDTO {
    @ApiModelProperty("用户ID")
    private Long id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("名称模糊")
    private String lkName;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("账户级别")
    private Integer accountLevel;

    @ApiModelProperty("账户级别")
    private List<Integer> accountLevelList;

    @ApiModelProperty("账户类型, com.porn.client.account.enums.AccountTypeEnum")
    private Integer accountType;

    @ApiModelProperty("账户类型, com.porn.client.account.enums.AccountTypeEnum")
    private List<Integer> accountTypeList;

    @ApiModelProperty("当前账户的推广码")
    private String lkPromotionCode;
    @ApiModelProperty("当前账户的父推荐码")
    private String lkParentPromotionCode;
    @ApiModelProperty("备注")
    private String lkRemark;
    @ApiModelProperty("注册开始时间")
    private LocalDateTime createTimeStart;
    @ApiModelProperty("注册开始时间")
    private LocalDateTime createTimeEnd;
    @ApiModelProperty("自动搬砖, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer autoWork;
    @ApiModelProperty("设备id")
    private String deviceId;
    @ApiModelProperty("设备id搜索")
    private String lkDeviceId;
    @ApiModelProperty("相册状态, 判断当前相册状态, PhotoStatusEnum")
    private Integer photoStatus;
    @ApiModelProperty("上传状态, 上传状态, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer uploadStatus;

    protected ProxyAccountQueryPageDTO(ProxyAccountQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.id = b.id;
        this.name = b.name;
        this.lkName = b.lkName;
        this.status = b.status;
        this.accountLevel = b.accountLevel;
        this.accountLevelList = b.accountLevelList;
        this.accountType = b.accountType;
        this.accountTypeList = b.accountTypeList;
        this.lkPromotionCode = b.lkPromotionCode;
        this.lkParentPromotionCode = b.lkParentPromotionCode;
        this.lkRemark = b.lkRemark;
        this.createTimeStart = b.createTimeStart;
        this.createTimeEnd = b.createTimeEnd;
        this.autoWork = b.autoWork;
        this.deviceId = b.deviceId;
        this.lkDeviceId = b.lkDeviceId;
        this.photoStatus = b.photoStatus;
        this.uploadStatus = b.uploadStatus;
    }

    public ProxyAccountQueryPageDTO(Long id, String name, String lkName, Integer status, Integer accountLevel, List<Integer> accountLevelList, Integer accountType, List<Integer> accountTypeList, String lkPromotionCode, String lkParentPromotionCode, String lkRemark, LocalDateTime createTimeStart, LocalDateTime createTimeEnd, Integer autoWork, String deviceId, String lkDeviceId, Integer photoStatus, Integer uploadStatus) {

        this.id = id;
        this.name = name;
        this.lkName = lkName;
        this.status = status;
        this.accountLevel = accountLevel;
        this.accountLevelList = accountLevelList;
        this.accountType = accountType;
        this.accountTypeList = accountTypeList;
        this.lkPromotionCode = lkPromotionCode;
        this.lkParentPromotionCode = lkParentPromotionCode;
        this.lkRemark = lkRemark;
        this.createTimeStart = createTimeStart;
        this.createTimeEnd = createTimeEnd;
        this.autoWork = autoWork;
        this.deviceId = deviceId;
        this.lkDeviceId = lkDeviceId;
        this.photoStatus = photoStatus;
        this.uploadStatus = uploadStatus;

    }

    public ProxyAccountQueryPageDTO() {
    }

    public static ProxyAccountQueryPageDTOBuilder<?, ?> builder() {
        return new ProxyAccountQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProxyAccountQueryPageDTO;
    }

    public Long getId() {

        return this.id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLkName() {

        return this.lkName;

    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getLkPromotionCode() {

        return this.lkPromotionCode;

    }

    public void setLkPromotionCode(String lkPromotionCode) {
        this.lkPromotionCode = lkPromotionCode;
    }

    public String getLkParentPromotionCode() {

        return this.lkParentPromotionCode;

    }

    public void setLkParentPromotionCode(String lkParentPromotionCode) {
        this.lkParentPromotionCode = lkParentPromotionCode;
    }

    public String getLkRemark() {

        return this.lkRemark;

    }

    public void setLkRemark(String lkRemark) {
        this.lkRemark = lkRemark;
    }

    public LocalDateTime getCreateTimeStart() {

        return this.createTimeStart;

    }

    public void setCreateTimeStart(LocalDateTime createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public LocalDateTime getCreateTimeEnd() {

        return this.createTimeEnd;

    }

    public void setCreateTimeEnd(LocalDateTime createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
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

    public String getLkDeviceId() {

        return this.lkDeviceId;

    }

    public void setLkDeviceId(String lkDeviceId) {
        this.lkDeviceId = lkDeviceId;
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

    private static final class ProxyAccountQueryPageDTOBuilderImpl extends ProxyAccountQueryPageDTOBuilder<ProxyAccountQueryPageDTO, ProxyAccountQueryPageDTOBuilderImpl> {
        private ProxyAccountQueryPageDTOBuilderImpl() {
        }

        protected ProxyAccountQueryPageDTOBuilderImpl self() {
            return this;
        }

        public ProxyAccountQueryPageDTO build() {
            return new ProxyAccountQueryPageDTO(this);
        }
    }

    public static abstract class ProxyAccountQueryPageDTOBuilder<C extends ProxyAccountQueryPageDTO, B extends ProxyAccountQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Long id;
        private String name;
        private String lkName;
        private Integer status;
        private Integer accountLevel;
        private List<Integer> accountLevelList;
        private Integer accountType;
        private List<Integer> accountTypeList;
        private String lkPromotionCode;
        private String lkParentPromotionCode;
        private String lkRemark;
        private LocalDateTime createTimeStart;
        private LocalDateTime createTimeEnd;
        private Integer autoWork;
        private String deviceId;
        private String lkDeviceId;
        private Integer photoStatus;
        private Integer uploadStatus;

        public B id(Long id) {
            this.id = id;
            return self();
        }

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
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

        public B lkPromotionCode(String lkPromotionCode) {
            this.lkPromotionCode = lkPromotionCode;
            return self();
        }

        public B lkParentPromotionCode(String lkParentPromotionCode) {
            this.lkParentPromotionCode = lkParentPromotionCode;
            return self();
        }

        public B lkRemark(String lkRemark) {
            this.lkRemark = lkRemark;
            return self();
        }

        public B createTimeStart(LocalDateTime createTimeStart) {
            this.createTimeStart = createTimeStart;
            return self();
        }

        public B createTimeEnd(LocalDateTime createTimeEnd) {
            this.createTimeEnd = createTimeEnd;
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

        public B lkDeviceId(String lkDeviceId) {
            this.lkDeviceId = lkDeviceId;
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

