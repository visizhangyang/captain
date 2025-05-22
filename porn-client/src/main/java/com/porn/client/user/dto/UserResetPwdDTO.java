
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;








 public class UserResetPwdDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof UserResetPwdDTO;
    }



    /* 15 */
    protected UserResetPwdDTO(UserResetPwdDTOBuilder<?, ?> b) {
        super(b);
    }

    public static UserResetPwdDTOBuilder<?, ?> builder() {
        return new UserResetPwdDTOBuilderImpl();
    }

    private static final class UserResetPwdDTOBuilderImpl extends UserResetPwdDTOBuilder<UserResetPwdDTO, UserResetPwdDTOBuilderImpl> {
        protected UserResetPwdDTOBuilderImpl self() {
            return this;
        }

        private UserResetPwdDTOBuilderImpl() {
        }

        public UserResetPwdDTO build() {
            return new UserResetPwdDTO(this);
        }
    }

    public static abstract class UserResetPwdDTOBuilder<C extends UserResetPwdDTO, B extends UserResetPwdDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public UserResetPwdDTO() {
    }

}


