
package com.porn.client.minio.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.req.ServiceRequest;


import java.io.Serializable;








 public class MinioDownloadDTO
         extends ServiceRequest
         implements Serializable
         {

    @ApiModelProperty("文件路径")
     private String filePath;



    public void setFilePath(String filePath) {
        /* 18 */
        this.filePath = filePath;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MinioDownloadDTO;
    }



    /* 19 */
    protected MinioDownloadDTO(MinioDownloadDTOBuilder<?, ?> b) {
        super(b);
        this.filePath = b.filePath;
    }

    public static MinioDownloadDTOBuilder<?, ?> builder() {
        return new MinioDownloadDTOBuilderImpl();
    }

    private static final class MinioDownloadDTOBuilderImpl extends MinioDownloadDTOBuilder<MinioDownloadDTO, MinioDownloadDTOBuilderImpl> {
        private MinioDownloadDTOBuilderImpl() {
        }

        protected MinioDownloadDTOBuilderImpl self() {
            return this;
        }

        public MinioDownloadDTO build() {
            return new MinioDownloadDTO(this);
        }
    }

    public static abstract class MinioDownloadDTOBuilder<C extends MinioDownloadDTO, B extends MinioDownloadDTOBuilder<C, B>> extends ServiceRequest.ServiceRequestBuilder<C, B> {
        public B filePath(String filePath) {
            this.filePath = filePath;
            return self();
        }

        private String filePath;

        protected abstract B self();

        public abstract C build();

    }

    public MinioDownloadDTO(String filePath) {
        /* 20 */
        this.filePath = filePath;

    }



    public MinioDownloadDTO() {
    }



    public String getFilePath() {
        /* 26 */
        return this.filePath;

    }

}


