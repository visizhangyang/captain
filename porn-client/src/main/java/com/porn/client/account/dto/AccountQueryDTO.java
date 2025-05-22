
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

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


    /* 17 */
    public void setName(String name) {
        this.name = name;
    }

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

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }

    public void setPromotionCodeList(List<String> promotionCodeList) {
        this.promotionCodeList = promotionCodeList;
    }

    public void setParentPromotionCodeList(List<String> parentPromotionCodeList) {
        this.parentPromotionCodeList = parentPromotionCodeList;
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

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setParentIdList(List<Long> parentIdList) {
        this.parentIdList = parentIdList;
    }

    public void setAutoWork(Integer autoWork) {
        this.autoWork = autoWork;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setPhotoStatus(Integer photoStatus) {
        this.photoStatus = photoStatus;
    }

    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountQueryDTO;
    }



    /* 18 */
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

    public static AccountQueryDTOBuilder<?, ?> builder() {
        return new AccountQueryDTOBuilderImpl();
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

    public AccountQueryDTO(String name, List<Long> accountIdList, List<String> promotionCodeList, List<String> parentPromotionCodeList, Integer accountLevel, List<Integer> accountLevelList, Integer accountType, List<Integer> accountTypeList, Long parentId, List<Long> parentIdList, Integer autoWork, String deviceId, Integer photoStatus, Integer uploadStatus) {
        /* 19 */
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



    public String getName() {
        /* 24 */
        return this.name;

    }


    public List<Long> getAccountIdList() {
        /* 27 */
        return this.accountIdList;

    }


    public List<String> getPromotionCodeList() {
        /* 30 */
        return this.promotionCodeList;

    }


    public List<String> getParentPromotionCodeList() {
        /* 33 */
        return this.parentPromotionCodeList;

    }


    public Integer getAccountLevel() {
        /* 36 */
        return this.accountLevel;

    }


    public List<Integer> getAccountLevelList() {
        /* 39 */
        return this.accountLevelList;

    }


    public Integer getAccountType() {
        /* 42 */
        return this.accountType;

    }


    public List<Integer> getAccountTypeList() {
        /* 45 */
        return this.accountTypeList;

    }


    public Long getParentId() {
        /* 48 */
        return this.parentId;

    }


    public List<Long> getParentIdList() {
        /* 51 */
        return this.parentIdList;

    }


    public Integer getAutoWork() {
        /* 54 */
        return this.autoWork;

    }


    public String getDeviceId() {
        /* 57 */
        return this.deviceId;

    }


    public Integer getPhotoStatus() {
        /* 60 */
        return this.photoStatus;

    }


    public Integer getUploadStatus() {
        /* 63 */
        return this.uploadStatus;

    }
}


