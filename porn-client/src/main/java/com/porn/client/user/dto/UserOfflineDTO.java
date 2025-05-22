
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class UserOfflineDTO
         extends BaseDTO
         {

    @ApiModelProperty("token")
     private String token;



    public void setToken(String token) {
        /* 15 */
        this.token = token;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserOfflineDTO;
    }



    /* 16 */
    protected UserOfflineDTO(UserOfflineDTOBuilder<?, ?> b) {
        super(b);
        this.token = b.token;
    }

    public static UserOfflineDTOBuilder<?, ?> builder() {
        return new UserOfflineDTOBuilderImpl();
    }

    private static final class UserOfflineDTOBuilderImpl extends UserOfflineDTOBuilder<UserOfflineDTO, UserOfflineDTOBuilderImpl> {
        private UserOfflineDTOBuilderImpl() {
        }

        protected UserOfflineDTOBuilderImpl self() {
            return this;
        }

        public UserOfflineDTO build() {
            return new UserOfflineDTO(this);
        }
    }

    public static abstract class UserOfflineDTOBuilder<C extends UserOfflineDTO, B extends UserOfflineDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B token(String token) {
            this.token = token;
            return self();
        }

        private String token;

        protected abstract B self();

        public abstract C build();

    }

    public UserOfflineDTO(String token) {
        /* 17 */
        this.token = token;

    }


    public UserOfflineDTO() {
    }



    public String getToken() {
        /* 22 */
        return this.token;

    }

}


