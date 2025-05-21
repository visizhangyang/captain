
package com.porn.client.imglib.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class ImageLibUpdateStatusDTO extends BaseDTO {

    @ApiModelProperty("id列表")
     private List<Long> idList;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
     private Integer status;



    public void setIdList(List<Long> idList) {
        /* 16 */
        this.idList = idList;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ImageLibUpdateStatusDTO;
    }



    /* 17 */
    protected ImageLibUpdateStatusDTO(ImageLibUpdateStatusDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
        this.status = b.status;
    }

    public static ImageLibUpdateStatusDTOBuilder<?, ?> builder() {
        return new ImageLibUpdateStatusDTOBuilderImpl();
    }

    private static final class ImageLibUpdateStatusDTOBuilderImpl extends ImageLibUpdateStatusDTOBuilder<ImageLibUpdateStatusDTO, ImageLibUpdateStatusDTOBuilderImpl> {
        private ImageLibUpdateStatusDTOBuilderImpl() {
        }

        protected ImageLibUpdateStatusDTOBuilderImpl self() {
            return this;
        }

        public ImageLibUpdateStatusDTO build() {
            return new ImageLibUpdateStatusDTO(this);
        }
    }

    public static abstract class ImageLibUpdateStatusDTOBuilder<C extends ImageLibUpdateStatusDTO, B extends ImageLibUpdateStatusDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> idList;

        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        private Integer status;

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public ImageLibUpdateStatusDTO(List<Long> idList, Integer status) {
        /* 18 */
        this.idList = idList;
        this.status = status;

    }


    public ImageLibUpdateStatusDTO() {
    }



    public List<Long> getIdList() {
        /* 23 */
        return this.idList;

    }


    public Integer getStatus() {
        /* 26 */
        return this.status;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/imglib/dto/ImageLibUpdateStatusDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */