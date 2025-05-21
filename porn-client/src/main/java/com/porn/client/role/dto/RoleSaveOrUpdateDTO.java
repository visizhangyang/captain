
package com.porn.client.role.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class RoleSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("用户名")
     private String name;

    @ApiModelProperty("状态")
     private Integer status;

    @ApiModelProperty("描述")
     private String description;


    /* 15 */
    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RoleSaveOrUpdateDTO;
    }



    /* 16 */
    protected RoleSaveOrUpdateDTO(RoleSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.status = b.status;
        this.description = b.description;
    }

    public static RoleSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RoleSaveOrUpdateDTOBuilderImpl();
    }

    private static final class RoleSaveOrUpdateDTOBuilderImpl extends RoleSaveOrUpdateDTOBuilder<RoleSaveOrUpdateDTO, RoleSaveOrUpdateDTOBuilderImpl> {
        private RoleSaveOrUpdateDTOBuilderImpl() {
        }

        protected RoleSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public RoleSaveOrUpdateDTO build() {
            return new RoleSaveOrUpdateDTO(this);
        }
    }

    public static abstract class RoleSaveOrUpdateDTOBuilder<C extends RoleSaveOrUpdateDTO, B extends RoleSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;

        public B name(String name) {
            this.name = name;
            return self();
        }

        private Integer status;
        private String description;

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B description(String description) {
            this.description = description;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public RoleSaveOrUpdateDTO(String name, Integer status, String description) {
        /* 17 */
        this.name = name;
        this.status = status;
        this.description = description;

    }


    public RoleSaveOrUpdateDTO() {
    }



    public String getName() {
        /* 22 */
        return this.name;

    }


    public Integer getStatus() {
        /* 25 */
        return this.status;

    }


    public String getDescription() {
        /* 28 */
        return this.description;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/role/dto/RoleSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */