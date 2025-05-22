
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


