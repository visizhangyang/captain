
package com.porn.client.minio.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.req.ServiceRequest;


import java.io.Serializable;






 public class PrevFileDTO
         extends ServiceRequest
         implements Serializable
         {

    @ApiModelProperty("文件预览")
     private String filePath;



    public void setFilePath(String filePath) {
        /* 16 */
        this.filePath = filePath;
    }


    protected boolean canEqual(Object other) {
        return other instanceof PrevFileDTO;
    }



    /* 17 */
    protected PrevFileDTO(PrevFileDTOBuilder<?, ?> b) {
        super(b);
        this.filePath = b.filePath;
    }

    public static PrevFileDTOBuilder<?, ?> builder() {
        return new PrevFileDTOBuilderImpl();
    }

    private static final class PrevFileDTOBuilderImpl extends PrevFileDTOBuilder<PrevFileDTO, PrevFileDTOBuilderImpl> {
        private PrevFileDTOBuilderImpl() {
        }

        protected PrevFileDTOBuilderImpl self() {
            return this;
        }

        public PrevFileDTO build() {
            return new PrevFileDTO(this);
        }
    }

    public static abstract class PrevFileDTOBuilder<C extends PrevFileDTO, B extends PrevFileDTOBuilder<C, B>> extends ServiceRequest.ServiceRequestBuilder<C, B> {
        public B filePath(String filePath) {
            this.filePath = filePath;
            return self();
        }

        private String filePath;

        protected abstract B self();

        public abstract C build();

    }

    public PrevFileDTO(String filePath) {
        /* 18 */
        this.filePath = filePath;

    }



    public PrevFileDTO() {
    }



    public String getFilePath() {
        /* 24 */
        return this.filePath;

    }

}


