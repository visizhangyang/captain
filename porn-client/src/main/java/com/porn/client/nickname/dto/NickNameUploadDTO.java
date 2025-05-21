
package com.porn.client.nickname.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class NickNameUploadDTO
         extends BaseDTO
         {

    @ApiModelProperty("行数据")
     private List<String> lines;



    public void setLines(List<String> lines) {
        /* 16 */
        this.lines = lines;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NickNameUploadDTO;
    }



    /* 17 */
    protected NickNameUploadDTO(NickNameUploadDTOBuilder<?, ?> b) {
        super(b);
        this.lines = b.lines;
    }

    public static NickNameUploadDTOBuilder<?, ?> builder() {
        return new NickNameUploadDTOBuilderImpl();
    }

    private static final class NickNameUploadDTOBuilderImpl extends NickNameUploadDTOBuilder<NickNameUploadDTO, NickNameUploadDTOBuilderImpl> {
        private NickNameUploadDTOBuilderImpl() {
        }

        protected NickNameUploadDTOBuilderImpl self() {
            return this;
        }

        public NickNameUploadDTO build() {
            return new NickNameUploadDTO(this);
        }
    }

    public static abstract class NickNameUploadDTOBuilder<C extends NickNameUploadDTO, B extends NickNameUploadDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B lines(List<String> lines) {
            this.lines = lines;
            return self();
        }

        private List<String> lines;

        protected abstract B self();

        public abstract C build();

    }

    public NickNameUploadDTO(List<String> lines) {
        /* 18 */
        this.lines = lines;

    }


    public NickNameUploadDTO() {
    }



    public List<String> getLines() {
        /* 23 */
        return this.lines;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/nickname/dto/NickNameUploadDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */