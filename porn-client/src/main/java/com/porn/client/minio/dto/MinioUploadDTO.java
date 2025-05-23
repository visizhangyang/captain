package com.porn.client.minio.dto;

import com.porn.client.common.req.ServiceRequest;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MinioUploadDTO extends ServiceRequest implements Serializable {

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件大小")
    private Long fileSize;

    @ApiModelProperty("内容类型")
    private String contentType;

    @ApiModelProperty("文件内容")
    private byte[] fileBytes;

    protected MinioUploadDTO(MinioUploadDTOBuilder<?, ?> b) {
        super(b);
        this.fileName = b.fileName;
        this.fileSize = b.fileSize;
        this.contentType = b.contentType;
        this.fileBytes = b.fileBytes;
    }

    public MinioUploadDTO(String fileName, Long fileSize, String contentType, byte[] fileBytes) {

        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.fileBytes = fileBytes;

    }

    public MinioUploadDTO() {
    }

    public static MinioUploadDTOBuilder<?, ?> builder() {
        return new MinioUploadDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MinioUploadDTO;
    }

    public String getFileName() {

        return this.fileName;

    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {

        return this.fileSize;

    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getContentType() {

        return this.contentType;

    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getFileBytes() {

        return this.fileBytes;

    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
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
        private String contentType;
        private byte[] fileBytes;

        public B fileName(String fileName) {
            this.fileName = fileName;
            return self();
        }

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

}

