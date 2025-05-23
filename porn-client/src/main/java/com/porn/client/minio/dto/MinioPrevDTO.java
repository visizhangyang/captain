package com.porn.client.minio.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MinioPrevDTO
        implements Serializable {

    @ApiModelProperty("文件路径")
    private String filePath;

    protected MinioPrevDTO(MinioPrevDTOBuilder<?, ?> b) {
        this.filePath = b.filePath;
    }

    public MinioPrevDTO(String filePath) {

        this.filePath = filePath;

    }

    public MinioPrevDTO() {
    }

    public static MinioPrevDTOBuilder<?, ?> builder() {
        return new MinioPrevDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MinioPrevDTO;
    }

    public String getFilePath() {

        return this.filePath;

    }

    public void setFilePath(String filePath) {

        this.filePath = filePath;
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
        private String filePath;

        public B filePath(String filePath) {
            this.filePath = filePath;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

