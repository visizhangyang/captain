
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/role/dto/RoleDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */