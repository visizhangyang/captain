
package com.porn.client.imglib.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class ImageLibBatchDeleteDTO
         extends BaseDTO
         {

    @ApiModelProperty("id列表")
     private List<Long> idList;



    public void setIdList(List<Long> idList) {
        /* 16 */
        this.idList = idList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ImageLibBatchDeleteDTO;
    }



    /* 17 */
    protected ImageLibBatchDeleteDTO(ImageLibBatchDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
    }

    public static ImageLibBatchDeleteDTOBuilder<?, ?> builder() {
        return new ImageLibBatchDeleteDTOBuilderImpl();
    }

    private static final class ImageLibBatchDeleteDTOBuilderImpl extends ImageLibBatchDeleteDTOBuilder<ImageLibBatchDeleteDTO, ImageLibBatchDeleteDTOBuilderImpl> {
        private ImageLibBatchDeleteDTOBuilderImpl() {
        }

        protected ImageLibBatchDeleteDTOBuilderImpl self() {
            return this;
        }

        public ImageLibBatchDeleteDTO build() {
            return new ImageLibBatchDeleteDTO(this);
        }
    }

    public static abstract class ImageLibBatchDeleteDTOBuilder<C extends ImageLibBatchDeleteDTO, B extends ImageLibBatchDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        private List<Long> idList;

        protected abstract B self();

        public abstract C build();

    }

    public ImageLibBatchDeleteDTO(List<Long> idList) {
        /* 18 */
        this.idList = idList;

    }


    public ImageLibBatchDeleteDTO() {
    }



    public List<Long> getIdList() {
        /* 23 */
        return this.idList;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/imglib/dto/ImageLibBatchDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */