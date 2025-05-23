package com.porn.client.photo.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

public class PhotoQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String lkAccountName;

    @ApiModelProperty("app端唯一")
    private String localIdentifier;

    protected PhotoQueryPageDTO(PhotoQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.lkAccountName = b.lkAccountName;
        this.localIdentifier = b.localIdentifier;
    }

    public PhotoQueryPageDTO(Long accountId, String lkAccountName, String localIdentifier) {

        this.accountId = accountId;
        this.lkAccountName = lkAccountName;
        this.localIdentifier = localIdentifier;

    }

    public PhotoQueryPageDTO() {
    }

    public static PhotoQueryPageDTOBuilder<?, ?> builder() {
        return new PhotoQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof PhotoQueryPageDTO;
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

    private static final class PhotoQueryPageDTOBuilderImpl extends PhotoQueryPageDTOBuilder<PhotoQueryPageDTO, PhotoQueryPageDTOBuilderImpl> {
        private PhotoQueryPageDTOBuilderImpl() {
        }

        protected PhotoQueryPageDTOBuilderImpl self() {
            return this;
        }

        public PhotoQueryPageDTO build() {
            return new PhotoQueryPageDTO(this);
        }
    }

    public static abstract class PhotoQueryPageDTOBuilder<C extends PhotoQueryPageDTO, B extends PhotoQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
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

