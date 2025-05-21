
package com.porn.client.common.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;





 public abstract class BaseDTO
         extends AbstractDTO
         implements Serializable
         {

    @ApiModelProperty("主键")
     private Long id;



    public void setId(Long id) {
        /* 15 */
        this.id = id;
    }


    protected boolean canEqual(Object other) {
        return other instanceof BaseDTO;
    }



    /* 16 */
    protected BaseDTO(BaseDTOBuilder<?, ?> b) {
        super(b);
        this.id = b.id;
    }

    public static abstract class BaseDTOBuilder<C extends BaseDTO, B extends BaseDTOBuilder<C, B>> extends AbstractDTO.AbstractDTOBuilder<C, B> {
        private Long id;

        public B id(Long id) {
            this.id = id;
            return self();
        }

        protected abstract B self();


        public abstract C build();
    }

    public BaseDTO(Long id) {
        /* 17 */
        this.id = id;

    }



    public BaseDTO() {
    }



    public Long getId() {
        /* 23 */
        return this.id;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/common/dto/BaseDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */