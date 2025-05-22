
package com.porn.client.photo.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


 public class PhotoSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("账户名称")
     private String accountName;

    @ApiModelProperty("文件路径")
     private String filePath;

    @ApiModelProperty("app端唯一")
     private String localIdentifier;


    /* 15 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setLocalIdentifier(String localIdentifier) {
        this.localIdentifier = localIdentifier;
    }


    protected boolean canEqual(Object other) {
        return other instanceof PhotoSaveOrUpdateDTO;
    }



    /* 16 */
    protected PhotoSaveOrUpdateDTO(PhotoSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountName = b.accountName;
        this.filePath = b.filePath;
        this.localIdentifier = b.localIdentifier;
    }

    public static PhotoSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new PhotoSaveOrUpdateDTOBuilderImpl();
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

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private String filePath;
        private String localIdentifier;

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

    public PhotoSaveOrUpdateDTO(Long accountId, String accountName, String filePath, String localIdentifier) {
        /* 17 */
        this.accountId = accountId;
        this.accountName = accountName;
        this.filePath = filePath;
        this.localIdentifier = localIdentifier;

    }


    public PhotoSaveOrUpdateDTO() {
    }



    public Long getAccountId() {
        /* 22 */
        return this.accountId;

    }


    public String getAccountName() {
        /* 25 */
        return this.accountName;

    }


    public String getFilePath() {
        /* 28 */
        return this.filePath;

    }


    public String getLocalIdentifier() {
        /* 31 */
        return this.localIdentifier;

    }

}


