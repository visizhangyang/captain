
package com.porn.client.recommendapp.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class RecommendAppQueryDTO extends BaseDTO {
    
    @ApiModelProperty("应用类型, AppTypeEnum")
     private Integer appType;
    
    @ApiModelProperty("类型，RecommendTypeEnum")
     private Integer recommendType;

    
    
    public void setAppType(Integer appType) {
        /* 15 */
        this.appType = appType;
    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RecommendAppQueryDTO;
    }



    /* 16 */
    protected RecommendAppQueryDTO(RecommendAppQueryDTOBuilder<?, ?> b) {
        super(b);
        this.appType = b.appType;
        this.recommendType = b.recommendType;
    }

    public static RecommendAppQueryDTOBuilder<?, ?> builder() {
        return new RecommendAppQueryDTOBuilderImpl();
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

        public B appType(Integer appType) {
            this.appType = appType;
            return self();
        }

        private Integer recommendType;

        public B recommendType(Integer recommendType) {
            this.recommendType = recommendType;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public RecommendAppQueryDTO(Integer appType, Integer recommendType) {
        /* 17 */
        this.appType = appType;
        this.recommendType = recommendType;
        
    }

    
    public RecommendAppQueryDTO() {
    }

    
    
    public Integer getAppType() {
        /* 22 */
        return this.appType;
        
    }

    
    public Integer getRecommendType() {
        /* 25 */
        return this.recommendType;
        
    }
    
}


