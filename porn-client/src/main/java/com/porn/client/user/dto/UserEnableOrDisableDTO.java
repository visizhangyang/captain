
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class UserEnableOrDisableDTO
         extends BaseDTO
         {

    @ApiModelProperty("状态, 1-启用, 0-禁用")
     private Integer status;



    public void setStatus(Integer status) {
        /* 15 */
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserEnableOrDisableDTO;
    }



    /* 16 */
    protected UserEnableOrDisableDTO(UserEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.status = b.status;
    }

    public static UserEnableOrDisableDTOBuilder<?, ?> builder() {
        return new UserEnableOrDisableDTOBuilderImpl();
    }

    private static final class UserEnableOrDisableDTOBuilderImpl extends UserEnableOrDisableDTOBuilder<UserEnableOrDisableDTO, UserEnableOrDisableDTOBuilderImpl> {
        private UserEnableOrDisableDTOBuilderImpl() {
        }

        protected UserEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public UserEnableOrDisableDTO build() {
            return new UserEnableOrDisableDTO(this);
        }
    }

    public static abstract class UserEnableOrDisableDTOBuilder<C extends UserEnableOrDisableDTO, B extends UserEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B status(Integer status) {
            this.status = status;
            return self();
        }

        private Integer status;

        protected abstract B self();

        public abstract C build();

    }

    public UserEnableOrDisableDTO(Integer status) {
        /* 17 */
        this.status = status;

    }


    public UserEnableOrDisableDTO() {
    }



    public Integer getStatus() {
        /* 22 */
        return this.status;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/user/dto/UserEnableOrDisableDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */