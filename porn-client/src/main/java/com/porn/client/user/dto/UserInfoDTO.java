
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class UserInfoDTO
         extends BaseDTO
         {

    @ApiModelProperty("token")
     private String token;



    public void setToken(String token) {
        /* 15 */
        this.token = token;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserInfoDTO;
    }



    /* 16 */
    protected UserInfoDTO(UserInfoDTOBuilder<?, ?> b) {
        super(b);
        this.token = b.token;
    }

    public static UserInfoDTOBuilder<?, ?> builder() {
        return new UserInfoDTOBuilderImpl();
    }

    private static final class UserInfoDTOBuilderImpl extends UserInfoDTOBuilder<UserInfoDTO, UserInfoDTOBuilderImpl> {
        private UserInfoDTOBuilderImpl() {
        }

        protected UserInfoDTOBuilderImpl self() {
            return this;
        }

        public UserInfoDTO build() {
            return new UserInfoDTO(this);
        }
    }

    public static abstract class UserInfoDTOBuilder<C extends UserInfoDTO, B extends UserInfoDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B token(String token) {
            this.token = token;
            return self();
        }

        private String token;

        protected abstract B self();

        public abstract C build();

    }

    public UserInfoDTO(String token) {
        /* 17 */
        this.token = token;

    }


    public UserInfoDTO() {
    }



    public String getToken() {
        /* 22 */
        return this.token;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/user/dto/UserInfoDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */