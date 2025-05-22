
package com.porn.client.minio.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;






 public class MinioPrevDTO
         implements Serializable
         {

    @ApiModelProperty("文件路径")
     private String filePath;



    public void setFilePath(String filePath) {
        /* 15 */
        this.filePath = filePath;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MinioPrevDTO;
    }



    /* 16 */
    protected MinioPrevDTO(MinioPrevDTOBuilder<?, ?> b) {
        this.filePath = b.filePath;
    }

    public static MinioPrevDTOBuilder<?, ?> builder() {
        return new MinioPrevDTOBuilderImpl();
    }

    private static final class MinioPrevDTOBuilderImpl extends MinioPrevDTOBuilder<MinioPrevDTO, MinioPrevDTOBuilderImpl> {
        private MinioPrevDTOBuilderImpl() {
        }

        protected MinioPrevDTOBuilderImpl self() {
            return this;
        }

        public MinioPrevDTO build() {
            return new MinioPrevDTO(this);
        }
    }

    public static abstract class MinioPrevDTOBuilder<C extends MinioPrevDTO, B extends MinioPrevDTOBuilder<C, B>> {
        public B filePath(String filePath) {
            this.filePath = filePath;
            return self();
        }

        private String filePath;

        protected abstract B self();

        public abstract C build();

    }

    public MinioPrevDTO(String filePath) {
        /* 17 */
        this.filePath = filePath;

    }


    public MinioPrevDTO() {
    }



    public String getFilePath() {
        /* 22 */
        return this.filePath;

    }

}


