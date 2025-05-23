package com.porn.client.minio.dto;

import com.porn.client.common.req.ServiceRequest;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class PrevFileDTO
        extends ServiceRequest
        implements Serializable {

    @ApiModelProperty("文件预览")
    private String filePath;

    protected PrevFileDTO(PrevFileDTOBuilder<?, ?> b) {
        super(b);
        this.filePath = b.filePath;
    }

    public PrevFileDTO(String filePath) {

        this.filePath = filePath;

    }

    public PrevFileDTO() {
    }

    public static PrevFileDTOBuilder<?, ?> builder() {
        return new PrevFileDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof PrevFileDTO;
    }

    public String getFilePath() {

        return this.filePath;

    }

    public void setFilePath(String filePath) {

        this.filePath = filePath;
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
        private String filePath;

        public B filePath(String filePath) {
            this.filePath = filePath;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

