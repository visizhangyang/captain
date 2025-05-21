
package com.porn.client.role.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class RoleEnableOrDisableDTO
         extends BaseDTO
         {

    @ApiModelProperty("状态, 1-启用, 0-禁用")
     private Integer status;



    public void setStatus(Integer status) {
        /* 15 */
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RoleEnableOrDisableDTO;
    }



    /* 16 */
    protected RoleEnableOrDisableDTO(RoleEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.status = b.status;
    }

    public static RoleEnableOrDisableDTOBuilder<?, ?> builder() {
        return new RoleEnableOrDisableDTOBuilderImpl();
    }

    private static final class RoleEnableOrDisableDTOBuilderImpl extends RoleEnableOrDisableDTOBuilder<RoleEnableOrDisableDTO, RoleEnableOrDisableDTOBuilderImpl> {
        private RoleEnableOrDisableDTOBuilderImpl() {
        }

        protected RoleEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public RoleEnableOrDisableDTO build() {
            return new RoleEnableOrDisableDTO(this);
        }
    }

    public static abstract class RoleEnableOrDisableDTOBuilder<C extends RoleEnableOrDisableDTO, B extends RoleEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B status(Integer status) {
            this.status = status;
            return self();
        }

        private Integer status;

        protected abstract B self();

        public abstract C build();

    }

    public RoleEnableOrDisableDTO(Integer status) {
        /* 17 */
        this.status = status;

    }


    public RoleEnableOrDisableDTO() {
    }



    public Integer getStatus() {
        /* 22 */
        return this.status;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/role/dto/RoleEnableOrDisableDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */