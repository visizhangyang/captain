
package com.porn.client.recommendapp.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

import java.util.List;

public class RecommendAppSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("app的logo")
     private String avatar;
    @ApiModelProperty("app英文名字")
     private String code;
    @ApiModelProperty("app中文名字")
     private String name;
    @ApiModelProperty("应用类型, AppTypeEnum")
     private String appType;

    @ApiModelProperty("应用地址")
     private String apkUrl;

    @ApiModelProperty("排序值")
     private Integer sortNo;

    @ApiModelProperty("复制标识, 0-跳转, 1-复制")
     private Integer copyFlag;

    @ApiModelProperty("类型，RecommendTypeEnum")
     private Integer recommendType;

    @ApiModelProperty("账户级别列表")
     private List<Integer> accountLevelList;


    /* 17 */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public void setCopyFlag(Integer copyFlag) {
        this.copyFlag = copyFlag;
    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }

    public void setAccountLevelList(List<Integer> accountLevelList) {
        this.accountLevelList = accountLevelList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RecommendAppSaveOrUpdateDTO;
    }



    /* 18 */
    protected RecommendAppSaveOrUpdateDTO(RecommendAppSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.avatar = b.avatar;
        this.code = b.code;
        this.name = b.name;
        this.appType = b.appType;
        this.apkUrl = b.apkUrl;
        this.sortNo = b.sortNo;
        this.copyFlag = b.copyFlag;
        this.recommendType = b.recommendType;
        this.accountLevelList = b.accountLevelList;
    }

    public static RecommendAppSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RecommendAppSaveOrUpdateDTOBuilderImpl();
    }

    private static final class RecommendAppSaveOrUpdateDTOBuilderImpl extends RecommendAppSaveOrUpdateDTOBuilder<RecommendAppSaveOrUpdateDTO, RecommendAppSaveOrUpdateDTOBuilderImpl> {
        private RecommendAppSaveOrUpdateDTOBuilderImpl() {
        }

        protected RecommendAppSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public RecommendAppSaveOrUpdateDTO build() {
            return new RecommendAppSaveOrUpdateDTO(this);
        }
    }

    public static abstract class RecommendAppSaveOrUpdateDTOBuilder<C extends RecommendAppSaveOrUpdateDTO, B extends RecommendAppSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String avatar;
        private String code;
        private String name;
        private String appType;

        public B avatar(String avatar) {
            this.avatar = avatar;
            return self();
        }

        private String apkUrl;
        private Integer sortNo;
        private Integer copyFlag;
        private Integer recommendType;
        private List<Integer> accountLevelList;

        public B code(String code) {
            this.code = code;
            return self();
        }

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B appType(String appType) {
            this.appType = appType;
            return self();
        }

        public B apkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
            return self();
        }

        public B sortNo(Integer sortNo) {
            this.sortNo = sortNo;
            return self();
        }

        public B copyFlag(Integer copyFlag) {
            this.copyFlag = copyFlag;
            return self();
        }

        public B recommendType(Integer recommendType) {
            this.recommendType = recommendType;
            return self();
        }

        public B accountLevelList(List<Integer> accountLevelList) {
            this.accountLevelList = accountLevelList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public RecommendAppSaveOrUpdateDTO(String avatar, String code, String name, String appType, String apkUrl, Integer sortNo, Integer copyFlag, Integer recommendType, List<Integer> accountLevelList) {
        /* 19 */
        this.avatar = avatar;
        this.code = code;
        this.name = name;
        this.appType = appType;
        this.apkUrl = apkUrl;
        this.sortNo = sortNo;
        this.copyFlag = copyFlag;
        this.recommendType = recommendType;
        this.accountLevelList = accountLevelList;

    }


    public RecommendAppSaveOrUpdateDTO() {
    }



    public String getAvatar() {
        /* 24 */
        return this.avatar;

    }


    public String getCode() {
        /* 27 */
        return this.code;

    }


    public String getName() {
        /* 30 */
        return this.name;

    }


    public String getAppType() {
        /* 33 */
        return this.appType;

    }


    public String getApkUrl() {
        /* 36 */
        return this.apkUrl;

    }


    public Integer getSortNo() {
        /* 39 */
        return this.sortNo;

    }


    public Integer getCopyFlag() {
        /* 42 */
        return this.copyFlag;

    }


    public Integer getRecommendType() {
        /* 45 */
        return this.recommendType;

    }


    public List<Integer> getAccountLevelList() {
        /* 48 */
        return this.accountLevelList;

    }
}


