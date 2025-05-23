package com.porn.client.recommendapp.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

public class RecommendAppQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("应用类型, AppTypeEnum")
    private Integer appType;

    @ApiModelProperty("模糊搜索名称")
    private String lkName;

    @ApiModelProperty("类型，RecommendTypeEnum")
    private Integer recommendType;

    protected RecommendAppQueryPageDTO(RecommendAppQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.appType = b.appType;
        this.lkName = b.lkName;
        this.recommendType = b.recommendType;
    }

    public RecommendAppQueryPageDTO(Integer appType, String lkName, Integer recommendType) {

        this.appType = appType;
        this.lkName = lkName;
        this.recommendType = recommendType;

    }

    public RecommendAppQueryPageDTO() {
    }

    public static RecommendAppQueryPageDTOBuilder<?, ?> builder() {
        return new RecommendAppQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RecommendAppQueryPageDTO;
    }

    public Integer getAppType() {

        return this.appType;

    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getLkName() {

        return this.lkName;

    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public Integer getRecommendType() {

        return this.recommendType;

    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }

    private static final class RecommendAppQueryPageDTOBuilderImpl extends RecommendAppQueryPageDTOBuilder<RecommendAppQueryPageDTO, RecommendAppQueryPageDTOBuilderImpl> {
        private RecommendAppQueryPageDTOBuilderImpl() {
        }

        protected RecommendAppQueryPageDTOBuilderImpl self() {
            return this;
        }

        public RecommendAppQueryPageDTO build() {
            return new RecommendAppQueryPageDTO(this);
        }
    }

    public static abstract class RecommendAppQueryPageDTOBuilder<C extends RecommendAppQueryPageDTO, B extends RecommendAppQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Integer appType;
        private String lkName;
        private Integer recommendType;

        public B appType(Integer appType) {
            this.appType = appType;
            return self();
        }

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        public B recommendType(Integer recommendType) {
            this.recommendType = recommendType;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

