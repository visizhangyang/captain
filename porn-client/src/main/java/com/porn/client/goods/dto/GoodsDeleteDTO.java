
package com.porn.client.goods.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;








 public class GoodsDeleteDTO
         extends BaseDTO
         {

    @ApiModelProperty("批量删除")
     private List<Long> idList;



    public void setIdList(List<Long> idList) {
        /* 17 */
        this.idList = idList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof GoodsDeleteDTO;
    }



    /* 18 */
    protected GoodsDeleteDTO(GoodsDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
    }

    public static GoodsDeleteDTOBuilder<?, ?> builder() {
        return new GoodsDeleteDTOBuilderImpl();
    }

    private static final class GoodsDeleteDTOBuilderImpl extends GoodsDeleteDTOBuilder<GoodsDeleteDTO, GoodsDeleteDTOBuilderImpl> {
        private GoodsDeleteDTOBuilderImpl() {
        }

        protected GoodsDeleteDTOBuilderImpl self() {
            return this;
        }

        public GoodsDeleteDTO build() {
            return new GoodsDeleteDTO(this);
        }
    }

    public static abstract class GoodsDeleteDTOBuilder<C extends GoodsDeleteDTO, B extends GoodsDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        private List<Long> idList;

        protected abstract B self();

        public abstract C build();


    }

    public GoodsDeleteDTO() {
    }

    public GoodsDeleteDTO(List<Long> idList) {
        /* 20 */
        this.idList = idList;

    }



    public List<Long> getIdList() {
        /* 24 */
        return this.idList;

    }

}


