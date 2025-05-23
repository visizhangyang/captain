package com.porn.client.recommendapp.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

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

    public RecommendAppSaveOrUpdateDTO(String avatar, String code, String name, String appType, String apkUrl, Integer sortNo, Integer copyFlag, Integer recommendType, List<Integer> accountLevelList) {

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

    public static RecommendAppSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RecommendAppSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RecommendAppSaveOrUpdateDTO;
    }

    public String getAvatar() {

        return this.avatar;

    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCode() {

        return this.code;

    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppType() {

        return this.appType;

    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getApkUrl() {

        return this.apkUrl;

    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public Integer getSortNo() {

        return this.sortNo;

    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getCopyFlag() {

        return this.copyFlag;

    }

    public void setCopyFlag(Integer copyFlag) {
        this.copyFlag = copyFlag;
    }

    public Integer getRecommendType() {

        return this.recommendType;

    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }

    public List<Integer> getAccountLevelList() {

        return this.accountLevelList;

    }

    public void setAccountLevelList(List<Integer> accountLevelList) {
        this.accountLevelList = accountLevelList;
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
        private String apkUrl;
        private Integer sortNo;
        private Integer copyFlag;
        private Integer recommendType;
        private List<Integer> accountLevelList;

        public B avatar(String avatar) {
            this.avatar = avatar;
            return self();
        }

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
}

