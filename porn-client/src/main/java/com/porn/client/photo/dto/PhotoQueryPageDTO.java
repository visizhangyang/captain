
package com.porn.client.photo.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BasePageDTO;





 public class PhotoQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("账户名称")
     private String lkAccountName;

    @ApiModelProperty("app端唯一")
     private String localIdentifier;


    /* 15 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setLkAccountName(String lkAccountName) {
        this.lkAccountName = lkAccountName;
    }

    public void setLocalIdentifier(String localIdentifier) {
        this.localIdentifier = localIdentifier;
    }


    protected boolean canEqual(Object other) {
        return other instanceof PhotoQueryPageDTO;
    }



    /* 16 */
    protected PhotoQueryPageDTO(PhotoQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.lkAccountName = b.lkAccountName;
        this.localIdentifier = b.localIdentifier;
    }

    public static PhotoQueryPageDTOBuilder<?, ?> builder() {
        return new PhotoQueryPageDTOBuilderImpl();
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

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private String lkAccountName;
        private String localIdentifier;

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

    public PhotoQueryPageDTO(Long accountId, String lkAccountName, String localIdentifier) {
        /* 17 */
        this.accountId = accountId;
        this.lkAccountName = lkAccountName;
        this.localIdentifier = localIdentifier;

    }


    public PhotoQueryPageDTO() {
    }



    public Long getAccountId() {
        /* 22 */
        return this.accountId;

    }


    public String getLkAccountName() {
        /* 25 */
        return this.lkAccountName;

    }


    public String getLocalIdentifier() {
        /* 28 */
        return this.localIdentifier;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/photo/dto/PhotoQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */