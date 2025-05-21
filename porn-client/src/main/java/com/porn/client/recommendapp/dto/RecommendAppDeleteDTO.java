
package com.porn.client.recommendapp.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;








 public class RecommendAppDeleteDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof RecommendAppDeleteDTO;
    }



    /* 15 */
    protected RecommendAppDeleteDTO(RecommendAppDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public static RecommendAppDeleteDTOBuilder<?, ?> builder() {
        return new RecommendAppDeleteDTOBuilderImpl();
    }

    private static final class RecommendAppDeleteDTOBuilderImpl extends RecommendAppDeleteDTOBuilder<RecommendAppDeleteDTO, RecommendAppDeleteDTOBuilderImpl> {
        protected RecommendAppDeleteDTOBuilderImpl self() {
            return this;
        }

        private RecommendAppDeleteDTOBuilderImpl() {
        }

        public RecommendAppDeleteDTO build() {
            return new RecommendAppDeleteDTO(this);
        }
    }

    public static abstract class RecommendAppDeleteDTOBuilder<C extends RecommendAppDeleteDTO, B extends RecommendAppDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public RecommendAppDeleteDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/recommendapp/dto/RecommendAppDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */