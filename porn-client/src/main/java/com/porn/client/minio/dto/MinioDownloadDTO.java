package com.porn.client.minio.dto;

import com.porn.client.common.req.ServiceRequest;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MinioDownloadDTO
        extends ServiceRequest
        implements Serializable {

    @ApiModelProperty("文件路径")
    private String filePath;

    protected MinioDownloadDTO(MinioDownloadDTOBuilder<?, ?> b) {
        super(b);
        this.filePath = b.filePath;
    }

    public MinioDownloadDTO(String filePath) {

        this.filePath = filePath;

    }

    public MinioDownloadDTO() {
    }

    public static MinioDownloadDTOBuilder<?, ?> builder() {
        return new MinioDownloadDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MinioDownloadDTO;
    }

    public String getFilePath() {

        return this.filePath;

    }

    public void setFilePath(String filePath) {

        this.filePath = filePath;
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
        private String filePath;

        public B filePath(String filePath) {
            this.filePath = filePath;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

