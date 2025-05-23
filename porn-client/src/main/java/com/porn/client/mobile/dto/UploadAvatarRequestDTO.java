package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class UploadAvatarRequestDTO
        implements Serializable {

    @ApiModelProperty("图像路径, 不是全路径")
    private String avatarPath;

    protected UploadAvatarRequestDTO(UploadAvatarRequestDTOBuilder<?, ?> b) {
        this.avatarPath = b.avatarPath;
    }

    public UploadAvatarRequestDTO(String avatarPath) {

        this.avatarPath = avatarPath;

    }

    public UploadAvatarRequestDTO() {
    }

    public static UploadAvatarRequestDTOBuilder<?, ?> builder() {
        return new UploadAvatarRequestDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UploadAvatarRequestDTO;
    }

    public String getAvatarPath() {

        return this.avatarPath;

    }

    public void setAvatarPath(String avatarPath) {

        this.avatarPath = avatarPath;
    }

    private static final class UploadAvatarRequestDTOBuilderImpl extends UploadAvatarRequestDTOBuilder<UploadAvatarRequestDTO, UploadAvatarRequestDTOBuilderImpl> {
        private UploadAvatarRequestDTOBuilderImpl() {
        }

        protected UploadAvatarRequestDTOBuilderImpl self() {
            return this;
        }

        public UploadAvatarRequestDTO build() {
            return new UploadAvatarRequestDTO(this);
        }
    }

    public static abstract class UploadAvatarRequestDTOBuilder<C extends UploadAvatarRequestDTO, B extends UploadAvatarRequestDTOBuilder<C, B>> {
        private String avatarPath;

        public B avatarPath(String avatarPath) {
            this.avatarPath = avatarPath;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

