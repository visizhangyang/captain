package com.porn.client.photo.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class PhotoQueryDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String lkAccountName;

    @ApiModelProperty("app端唯一")
    private String localIdentifier;

    protected PhotoQueryDTO(PhotoQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.lkAccountName = b.lkAccountName;
        this.localIdentifier = b.localIdentifier;
    }

    public PhotoQueryDTO(Long accountId, String lkAccountName, String localIdentifier) {

        this.accountId = accountId;
        this.lkAccountName = lkAccountName;
        this.localIdentifier = localIdentifier;

    }

    public PhotoQueryDTO() {
    }

    public static PhotoQueryDTOBuilder<?, ?> builder() {
        return new PhotoQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof PhotoQueryDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getLkAccountName() {

        return this.lkAccountName;

    }

    public void setLkAccountName(String lkAccountName) {
        this.lkAccountName = lkAccountName;
    }

    public String getLocalIdentifier() {

        return this.localIdentifier;

    }

    public void setLocalIdentifier(String localIdentifier) {
        this.localIdentifier = localIdentifier;
    }

    private static final class PhotoQueryDTOBuilderImpl extends PhotoQueryDTOBuilder<PhotoQueryDTO, PhotoQueryDTOBuilderImpl> {
        private PhotoQueryDTOBuilderImpl() {
        }

        protected PhotoQueryDTOBuilderImpl self() {
            return this;
        }

        public PhotoQueryDTO build() {
            return new PhotoQueryDTO(this);
        }
    }

    public static abstract class PhotoQueryDTOBuilder<C extends PhotoQueryDTO, B extends PhotoQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private String lkAccountName;
        private String localIdentifier;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B lkAccountName(String lkAccountName) {
            this.lkAccountName = lkAccountName;
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

