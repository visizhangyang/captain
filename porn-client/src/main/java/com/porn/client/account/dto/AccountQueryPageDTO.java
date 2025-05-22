
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BasePageDTO;

import java.time.LocalDateTime;
import java.util.List;

 public class AccountQueryPageDTO extends BasePageDTO {
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

    
    /* 18 */
    public void setId(Long id) {
        this.id = id;
    }

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
    @ApiModelProperty("账户ID列表")
    private List<Long> accountIdList;

    public void setName(String name) {
        this.name = name;
    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setAccountLevel(Integer accountLevel) {
        this.accountLevel = accountLevel;
    }

    public void setAccountLevelList(List<Integer> accountLevelList) {
        this.accountLevelList = accountLevelList;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public void setAccountTypeList(List<Integer> accountTypeList) {
        this.accountTypeList = accountTypeList;
    }

    public void setLkPromotionCode(String lkPromotionCode) {
        this.lkPromotionCode = lkPromotionCode;
    }

    public void setLkParentPromotionCode(String lkParentPromotionCode) {
        this.lkParentPromotionCode = lkParentPromotionCode;
    }

    public void setLkRemark(String lkRemark) {
        this.lkRemark = lkRemark;
    }

    public void setCreateTimeStart(LocalDateTime createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public void setCreateTimeEnd(LocalDateTime createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public void setAutoWork(Integer autoWork) {
        this.autoWork = autoWork;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setLkDeviceId(String lkDeviceId) {
        this.lkDeviceId = lkDeviceId;
    }

    public void setPhotoStatus(Integer photoStatus) {
        this.photoStatus = photoStatus;
    }

    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountQueryPageDTO;
    }



    /* 19 */
    protected AccountQueryPageDTO(AccountQueryPageDTOBuilder<?, ?> b) {
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
        this.accountIdList = b.accountIdList;
    }

    public static AccountQueryPageDTOBuilder<?, ?> builder() {
        return new AccountQueryPageDTOBuilderImpl();
    }

    private static final class AccountQueryPageDTOBuilderImpl extends AccountQueryPageDTOBuilder<AccountQueryPageDTO, AccountQueryPageDTOBuilderImpl> {
        private AccountQueryPageDTOBuilderImpl() {
        }

        protected AccountQueryPageDTOBuilderImpl self() {
            return this;
        }

        public AccountQueryPageDTO build() {
            return new AccountQueryPageDTO(this);
        }
    }

    public static abstract class AccountQueryPageDTOBuilder<C extends AccountQueryPageDTO, B extends AccountQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
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
        private List<Long> accountIdList;

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

        public B accountIdList(List<Long> accountIdList) {
            this.accountIdList = accountIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public AccountQueryPageDTO(Long id, String name, String lkName, Integer status, Integer accountLevel, List<Integer> accountLevelList, Integer accountType, List<Integer> accountTypeList, String lkPromotionCode, String lkParentPromotionCode, String lkRemark, LocalDateTime createTimeStart, LocalDateTime createTimeEnd, Integer autoWork, String deviceId, String lkDeviceId, Integer photoStatus, Integer uploadStatus, List<Long> accountIdList) {
        /* 20 */
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
        this.accountIdList = accountIdList;
        
    }

    
    public AccountQueryPageDTO() {
    }

    
    
    public Long getId() {
        /* 25 */
        return this.id;
        
    }

    
    public String getName() {
        /* 28 */
        return this.name;
        
    }

    
    public String getLkName() {
        /* 31 */
        return this.lkName;
        
    }

    
    public Integer getStatus() {
        /* 34 */
        return this.status;
        
    }

    
    public Integer getAccountLevel() {
        /* 37 */
        return this.accountLevel;
        
    }

    
    public List<Integer> getAccountLevelList() {
        /* 40 */
        return this.accountLevelList;
        
    }

    
    public Integer getAccountType() {
        /* 43 */
        return this.accountType;
        
    }

    
    public List<Integer> getAccountTypeList() {
        /* 46 */
        return this.accountTypeList;
        
    }

    
    public String getLkPromotionCode() {
        /* 49 */
        return this.lkPromotionCode;
        
    }

    
    public String getLkParentPromotionCode() {
        /* 52 */
        return this.lkParentPromotionCode;
        
    }

    
    public String getLkRemark() {
        /* 55 */
        return this.lkRemark;
        
    }

    
    public LocalDateTime getCreateTimeStart() {
        /* 58 */
        return this.createTimeStart;
        
    }

    
    public LocalDateTime getCreateTimeEnd() {
        /* 61 */
        return this.createTimeEnd;
        
    }

    
    public Integer getAutoWork() {
        /* 64 */
        return this.autoWork;
        
    }

    
    public String getDeviceId() {
        /* 67 */
        return this.deviceId;
        
    }

    
    public String getLkDeviceId() {
        /* 70 */
        return this.lkDeviceId;
        
    }

    
    public Integer getPhotoStatus() {
        /* 73 */
        return this.photoStatus;
        
    }

    
    public Integer getUploadStatus() {
        /* 76 */
        return this.uploadStatus;
        
    }

    
    public List<Long> getAccountIdList() {
        /* 79 */
        return this.accountIdList;
        
    }
}


