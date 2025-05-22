
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;






 public class UploadAvatarRequestDTO
         implements Serializable
         {

    @ApiModelProperty("图像路径, 不是全路径")
     private String avatarPath;



    public void setAvatarPath(String avatarPath) {
        /* 15 */
        this.avatarPath = avatarPath;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UploadAvatarRequestDTO;
    }



    /* 16 */
    protected UploadAvatarRequestDTO(UploadAvatarRequestDTOBuilder<?, ?> b) {
        this.avatarPath = b.avatarPath;
    }

    public static UploadAvatarRequestDTOBuilder<?, ?> builder() {
        return new UploadAvatarRequestDTOBuilderImpl();
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
        public B avatarPath(String avatarPath) {
            this.avatarPath = avatarPath;
            return self();
        }

        private String avatarPath;

        protected abstract B self();

        public abstract C build();

    }

    public UploadAvatarRequestDTO(String avatarPath) {
        /* 17 */
        this.avatarPath = avatarPath;

    }


    public UploadAvatarRequestDTO() {
    }



    public String getAvatarPath() {
        /* 22 */
        return this.avatarPath;

    }

}


