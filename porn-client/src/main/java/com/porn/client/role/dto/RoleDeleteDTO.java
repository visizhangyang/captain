
package com.porn.client.role.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class RoleDeleteDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof RoleDeleteDTO;
    }



    /* 14 */
    protected RoleDeleteDTO(RoleDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public static RoleDeleteDTOBuilder<?, ?> builder() {
        return new RoleDeleteDTOBuilderImpl();
    }

    private static final class RoleDeleteDTOBuilderImpl extends RoleDeleteDTOBuilder<RoleDeleteDTO, RoleDeleteDTOBuilderImpl> {
        protected RoleDeleteDTOBuilderImpl self() {
            return this;
        }

        private RoleDeleteDTOBuilderImpl() {
        }

        public RoleDeleteDTO build() {
            return new RoleDeleteDTO(this);
        }
    }

    public static abstract class RoleDeleteDTOBuilder<C extends RoleDeleteDTO, B extends RoleDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public RoleDeleteDTO() {
    }

}


