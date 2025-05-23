package com.porn.client.recommendapp.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class RecommendAppQueryDTO extends BaseDTO {

    @ApiModelProperty("应用类型, AppTypeEnum")
    private Integer appType;

    @ApiModelProperty("类型，RecommendTypeEnum")
    private Integer recommendType;

    protected RecommendAppQueryDTO(RecommendAppQueryDTOBuilder<?, ?> b) {
        super(b);
        this.appType = b.appType;
        this.recommendType = b.recommendType;
    }

    public RecommendAppQueryDTO(Integer appType, Integer recommendType) {

        this.appType = appType;
        this.recommendType = recommendType;

    }

    public RecommendAppQueryDTO() {
    }

    public static RecommendAppQueryDTOBuilder<?, ?> builder() {
        return new RecommendAppQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RecommendAppQueryDTO;
    }

    public Integer getAppType() {

        return this.appType;

    }

    public void setAppType(Integer appType) {

        this.appType = appType;
    }

    public Integer getRecommendType() {

        return this.recommendType;

    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }

    private static final class RecommendAppQueryDTOBuilderImpl extends RecommendAppQueryDTOBuilder<RecommendAppQueryDTO, RecommendAppQueryDTOBuilderImpl> {
        private RecommendAppQueryDTOBuilderImpl() {
        }

        protected RecommendAppQueryDTOBuilderImpl self() {
            return this;
        }

        public RecommendAppQueryDTO build() {
            return new RecommendAppQueryDTO(this);
        }
    }

    public static abstract class RecommendAppQueryDTOBuilder<C extends RecommendAppQueryDTO, B extends RecommendAppQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer appType;
        private Integer recommendType;

        public B appType(Integer appType) {
            this.appType = appType;
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

