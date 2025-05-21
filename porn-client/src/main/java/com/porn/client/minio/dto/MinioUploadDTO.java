
package com.porn.client.minio.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.req.ServiceRequest;


import java.io.Serializable;
import java.util.Arrays;




 public class MinioUploadDTO extends ServiceRequest implements Serializable {

    @ApiModelProperty("文件名称")
     private String fileName;

    @ApiModelProperty("文件大小")
     private Long fileSize;

    @ApiModelProperty("内容类型")
     private String contentType;

    @ApiModelProperty("文件内容")
     private byte[] fileBytes;


    /* 16 */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MinioUploadDTO;
    }



    /* 17 */
    protected MinioUploadDTO(MinioUploadDTOBuilder<?, ?> b) {
        super(b);
        this.fileName = b.fileName;
        this.fileSize = b.fileSize;
        this.contentType = b.contentType;
        this.fileBytes = b.fileBytes;
    }

    public static MinioUploadDTOBuilder<?, ?> builder() {
        return new MinioUploadDTOBuilderImpl();
    }

    private static final class MinioUploadDTOBuilderImpl extends MinioUploadDTOBuilder<MinioUploadDTO, MinioUploadDTOBuilderImpl> {
        private MinioUploadDTOBuilderImpl() {
        }

        protected MinioUploadDTOBuilderImpl self() {
            return this;
        }

        public MinioUploadDTO build() {
            return new MinioUploadDTO(this);
        }
    }

    public static abstract class MinioUploadDTOBuilder<C extends MinioUploadDTO, B extends MinioUploadDTOBuilder<C, B>> extends ServiceRequest.ServiceRequestBuilder<C, B> {
        private String fileName;
        private Long fileSize;

        public B fileName(String fileName) {
            this.fileName = fileName;
            return self();
        }

        private String contentType;
        private byte[] fileBytes;

        public B fileSize(Long fileSize) {
            this.fileSize = fileSize;
            return self();
        }

        public B contentType(String contentType) {
            this.contentType = contentType;
            return self();
        }

        public B fileBytes(byte[] fileBytes) {
            this.fileBytes = fileBytes;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public MinioUploadDTO(String fileName, Long fileSize, String contentType, byte[] fileBytes) {
        /* 18 */
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.fileBytes = fileBytes;

    }



    public MinioUploadDTO() {
    }



    public String getFileName() {
        /* 24 */
        return this.fileName;

    }


    public Long getFileSize() {
        /* 27 */
        return this.fileSize;

    }


    public String getContentType() {
        /* 30 */
        return this.contentType;

    }


    public byte[] getFileBytes() {
        /* 33 */
        return this.fileBytes;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/minio/dto/MinioUploadDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */