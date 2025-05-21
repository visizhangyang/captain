
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;








 public class UserDeleteDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof UserDeleteDTO;
    }



    /* 15 */
    protected UserDeleteDTO(UserDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public static UserDeleteDTOBuilder<?, ?> builder() {
        return new UserDeleteDTOBuilderImpl();
    }

    private static final class UserDeleteDTOBuilderImpl extends UserDeleteDTOBuilder<UserDeleteDTO, UserDeleteDTOBuilderImpl> {
        protected UserDeleteDTOBuilderImpl self() {
            return this;
        }

        private UserDeleteDTOBuilderImpl() {
        }

        public UserDeleteDTO build() {
            return new UserDeleteDTO(this);
        }
    }

    public static abstract class UserDeleteDTOBuilder<C extends UserDeleteDTO, B extends UserDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public UserDeleteDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/user/dto/UserDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */