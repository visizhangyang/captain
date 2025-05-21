
package com.porn.client.role.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BasePageDTO;





 public class RoleQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("模糊匹配名称")
     private String lkName;

    @ApiModelProperty("状态")
     private Integer status;



    public void setLkName(String lkName) {
        /* 15 */
        this.lkName = lkName;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RoleQueryPageDTO;
    }



    /* 16 */
    protected RoleQueryPageDTO(RoleQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkName = b.lkName;
        this.status = b.status;
    }

    public static RoleQueryPageDTOBuilder<?, ?> builder() {
        return new RoleQueryPageDTOBuilderImpl();
    }

    private static final class RoleQueryPageDTOBuilderImpl extends RoleQueryPageDTOBuilder<RoleQueryPageDTO, RoleQueryPageDTOBuilderImpl> {
        private RoleQueryPageDTOBuilderImpl() {
        }

        protected RoleQueryPageDTOBuilderImpl self() {
            return this;
        }

        public RoleQueryPageDTO build() {
            return new RoleQueryPageDTO(this);
        }
    }

    public static abstract class RoleQueryPageDTOBuilder<C extends RoleQueryPageDTO, B extends RoleQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String lkName;

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        private Integer status;

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public RoleQueryPageDTO(String lkName, Integer status) {
        /* 17 */
        this.lkName = lkName;
        this.status = status;

    }


    public RoleQueryPageDTO() {
    }



    public String getLkName() {
        /* 22 */
        return this.lkName;

    }


    public Integer getStatus() {
        /* 25 */
        return this.status;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/role/dto/RoleQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */