
package com.porn.client.config.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class ProxyConfigDeleteDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof ProxyConfigDeleteDTO;
    }



    /* 14 */
    protected ProxyConfigDeleteDTO(ProxyConfigDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public static ProxyConfigDeleteDTOBuilder<?, ?> builder() {
        return new ProxyConfigDeleteDTOBuilderImpl();
    }

    private static final class ProxyConfigDeleteDTOBuilderImpl extends ProxyConfigDeleteDTOBuilder<ProxyConfigDeleteDTO, ProxyConfigDeleteDTOBuilderImpl> {
        protected ProxyConfigDeleteDTOBuilderImpl self() {
            return this;
        }

        private ProxyConfigDeleteDTOBuilderImpl() {
        }

        public ProxyConfigDeleteDTO build() {
            return new ProxyConfigDeleteDTO(this);
        }
    }

    public static abstract class ProxyConfigDeleteDTOBuilder<C extends ProxyConfigDeleteDTO, B extends ProxyConfigDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public ProxyConfigDeleteDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/config/dto/ProxyConfigDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */