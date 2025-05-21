
package com.porn.client.recommendapp.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BasePageDTO;





 public class RecommendAppQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("应用类型, AppTypeEnum")
     private Integer appType;

    @ApiModelProperty("模糊搜索名称")
     private String lkName;

    @ApiModelProperty("类型，RecommendTypeEnum")
     private Integer recommendType;


    /* 15 */
    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RecommendAppQueryPageDTO;
    }



    /* 16 */
    protected RecommendAppQueryPageDTO(RecommendAppQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.appType = b.appType;
        this.lkName = b.lkName;
        this.recommendType = b.recommendType;
    }

    public static RecommendAppQueryPageDTOBuilder<?, ?> builder() {
        return new RecommendAppQueryPageDTOBuilderImpl();
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

        public B appType(Integer appType) {
            this.appType = appType;
            return self();
        }

        private String lkName;
        private Integer recommendType;

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

    public RecommendAppQueryPageDTO(Integer appType, String lkName, Integer recommendType) {
        /* 17 */
        this.appType = appType;
        this.lkName = lkName;
        this.recommendType = recommendType;

    }


    public RecommendAppQueryPageDTO() {
    }



    public Integer getAppType() {
        /* 22 */
        return this.appType;

    }


    public String getLkName() {
        /* 25 */
        return this.lkName;

    }


    public Integer getRecommendType() {
        /* 28 */
        return this.recommendType;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/recommendapp/dto/RecommendAppQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */