
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.AbstractDTO;




 public class UserNewPwdDTO
         extends AbstractDTO {

    @ApiModelProperty("密码")
     private String pwd;

    @ApiModelProperty("新密码")
     private String newPwd;



    public void setPwd(String pwd) {
        /* 15 */
        this.pwd = pwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserNewPwdDTO;
    }



    /* 16 */
    protected UserNewPwdDTO(UserNewPwdDTOBuilder<?, ?> b) {
        super(b);
        this.pwd = b.pwd;
        this.newPwd = b.newPwd;
    }

    public static UserNewPwdDTOBuilder<?, ?> builder() {
        return new UserNewPwdDTOBuilderImpl();
    }

    private static final class UserNewPwdDTOBuilderImpl extends UserNewPwdDTOBuilder<UserNewPwdDTO, UserNewPwdDTOBuilderImpl> {
        private UserNewPwdDTOBuilderImpl() {
        }

        protected UserNewPwdDTOBuilderImpl self() {
            return this;
        }

        public UserNewPwdDTO build() {
            return new UserNewPwdDTO(this);
        }
    }

    public static abstract class UserNewPwdDTOBuilder<C extends UserNewPwdDTO, B extends UserNewPwdDTOBuilder<C, B>> extends AbstractDTO.AbstractDTOBuilder<C, B> {
        private String pwd;

        public B pwd(String pwd) {
            this.pwd = pwd;
            return self();
        }

        private String newPwd;

        public B newPwd(String newPwd) {
            this.newPwd = newPwd;
            return self();
        }

        protected abstract B self();

        public abstract C build();


    }

    public UserNewPwdDTO() {
    }

    public UserNewPwdDTO(String pwd, String newPwd) {
        /* 18 */
        this.pwd = pwd;
        this.newPwd = newPwd;

    }



    public String getPwd() {
        /* 22 */
        return this.pwd;

    }


    public String getNewPwd() {
        /* 25 */
        return this.newPwd;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/user/dto/UserNewPwdDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */