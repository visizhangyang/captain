package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountModifyPhotoStatusDTO extends BaseDTO {

    @ApiModelProperty("相册状态, 判断当前相册状态, PhotoStatusEnum")
    private Integer photoStatus;

    @ApiModelProperty("上传状态, 上传状态, 默认禁用, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer uploadStatus;

    protected AccountModifyPhotoStatusDTO(AccountModifyPhotoStatusDTOBuilder<?, ?> b) {
        super(b);
        this.photoStatus = b.photoStatus;
        this.uploadStatus = b.uploadStatus;
    }

    public AccountModifyPhotoStatusDTO(Integer photoStatus, Integer uploadStatus) {

        this.photoStatus = photoStatus;
        this.uploadStatus = uploadStatus;

    }

    public AccountModifyPhotoStatusDTO() {
    }

    public static AccountModifyPhotoStatusDTOBuilder<?, ?> builder() {
        return new AccountModifyPhotoStatusDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountModifyPhotoStatusDTO;
    }

    public Integer getPhotoStatus() {

        return this.photoStatus;

    }

    public void setPhotoStatus(Integer photoStatus) {

        this.photoStatus = photoStatus;
    }

    public Integer getUploadStatus() {

        return this.uploadStatus;

    }

    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    private static final class AccountModifyPhotoStatusDTOBuilderImpl extends AccountModifyPhotoStatusDTOBuilder<AccountModifyPhotoStatusDTO, AccountModifyPhotoStatusDTOBuilderImpl> {
        private AccountModifyPhotoStatusDTOBuilderImpl() {
        }

        protected AccountModifyPhotoStatusDTOBuilderImpl self() {
            return this;
        }

        public AccountModifyPhotoStatusDTO build() {
            return new AccountModifyPhotoStatusDTO(this);
        }
    }

    public static abstract class AccountModifyPhotoStatusDTOBuilder<C extends AccountModifyPhotoStatusDTO, B extends AccountModifyPhotoStatusDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer photoStatus;
        private Integer uploadStatus;

        public B photoStatus(Integer photoStatus) {
            this.photoStatus = photoStatus;
            return self();
        }

        public B uploadStatus(Integer uploadStatus) {
            this.uploadStatus = uploadStatus;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

