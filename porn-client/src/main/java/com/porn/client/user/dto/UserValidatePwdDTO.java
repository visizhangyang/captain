
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.AbstractDTO;





 public class UserValidatePwdDTO
         extends AbstractDTO
         {

    @ApiModelProperty("密码")
     private String password;



    public void setPassword(String password) {
        /* 15 */
        this.password = password;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserValidatePwdDTO;
    }



    /* 16 */
    protected UserValidatePwdDTO(UserValidatePwdDTOBuilder<?, ?> b) {
        super(b);
        this.password = b.password;
    }

    public static UserValidatePwdDTOBuilder<?, ?> builder() {
        return new UserValidatePwdDTOBuilderImpl();
    }

    private static final class UserValidatePwdDTOBuilderImpl extends UserValidatePwdDTOBuilder<UserValidatePwdDTO, UserValidatePwdDTOBuilderImpl> {
        private UserValidatePwdDTOBuilderImpl() {
        }

        protected UserValidatePwdDTOBuilderImpl self() {
            return this;
        }

        public UserValidatePwdDTO build() {
            return new UserValidatePwdDTO(this);
        }
    }

    public static abstract class UserValidatePwdDTOBuilder<C extends UserValidatePwdDTO, B extends UserValidatePwdDTOBuilder<C, B>> extends AbstractDTO.AbstractDTOBuilder<C, B> {
        public B password(String password) {
            this.password = password;
            return self();
        }

        private String password;

        protected abstract B self();

        public abstract C build();


    }

    public UserValidatePwdDTO() {
    }

    public UserValidatePwdDTO(String password) {
        /* 18 */
        this.password = password;

    }



    public String getPassword() {
        /* 22 */
        return this.password;

    }

}


