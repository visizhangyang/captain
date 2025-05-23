package com.porn.client.photo.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class PhotoSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

    @ApiModelProperty("文件路径")
    private String filePath;

    @ApiModelProperty("app端唯一")
    private String localIdentifier;

    protected PhotoSaveOrUpdateDTO(PhotoSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountName = b.accountName;
        this.filePath = b.filePath;
        this.localIdentifier = b.localIdentifier;
    }

    public PhotoSaveOrUpdateDTO(Long accountId, String accountName, String filePath, String localIdentifier) {

        this.accountId = accountId;
        this.accountName = accountName;
        this.filePath = filePath;
        this.localIdentifier = localIdentifier;

    }

    public PhotoSaveOrUpdateDTO() {
    }

    public static PhotoSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new PhotoSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof PhotoSaveOrUpdateDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {

        return this.accountName;

    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getFilePath() {

        return this.filePath;

    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getLocalIdentifier() {

        return this.localIdentifier;

    }

    public void setLocalIdentifier(String localIdentifier) {
        this.localIdentifier = localIdentifier;
    }

    private static final class PhotoSaveOrUpdateDTOBuilderImpl extends PhotoSaveOrUpdateDTOBuilder<PhotoSaveOrUpdateDTO, PhotoSaveOrUpdateDTOBuilderImpl> {
        private PhotoSaveOrUpdateDTOBuilderImpl() {
        }

        protected PhotoSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public PhotoSaveOrUpdateDTO build() {
            return new PhotoSaveOrUpdateDTO(this);
        }
    }

    public static abstract class PhotoSaveOrUpdateDTOBuilder<C extends PhotoSaveOrUpdateDTO, B extends PhotoSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private String accountName;
        private String filePath;
        private String localIdentifier;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B accountName(String accountName) {
            this.accountName = accountName;
            return self();
        }

        public B filePath(String filePath) {
            this.filePath = filePath;
            return self();
        }

        public B localIdentifier(String localIdentifier) {
            this.localIdentifier = localIdentifier;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

