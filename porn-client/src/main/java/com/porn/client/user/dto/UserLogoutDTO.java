
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class UserLogoutDTO
         extends BaseDTO
         {
    
    @ApiModelProperty("token")
     private String token;

    
    
    public void setToken(String token) {
        /* 15 */
        this.token = token;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserLogoutDTO;
    }



    /* 16 */
    protected UserLogoutDTO(UserLogoutDTOBuilder<?, ?> b) {
        super(b);
        this.token = b.token;
    }

    public static UserLogoutDTOBuilder<?, ?> builder() {
        return new UserLogoutDTOBuilderImpl();
    }

    private static final class UserLogoutDTOBuilderImpl extends UserLogoutDTOBuilder<UserLogoutDTO, UserLogoutDTOBuilderImpl> {
        private UserLogoutDTOBuilderImpl() {
        }

        protected UserLogoutDTOBuilderImpl self() {
            return this;
        }

        public UserLogoutDTO build() {
            return new UserLogoutDTO(this);
        }
    }

    public static abstract class UserLogoutDTOBuilder<C extends UserLogoutDTO, B extends UserLogoutDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B token(String token) {
            this.token = token;
            return self();
        }

        private String token;

        protected abstract B self();

        public abstract C build();

    }

    public UserLogoutDTO(String token) {
        /* 17 */
        this.token = token;
        
    }

    
    public UserLogoutDTO() {
    }

    
    
    public String getToken() {
        /* 22 */
        return this.token;
        
    }
    
}


