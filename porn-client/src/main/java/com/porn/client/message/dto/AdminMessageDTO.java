
package com.porn.client.message.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class AdminMessageDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof AdminMessageDTO;
    }



    /* 14 */
    protected AdminMessageDTO(AdminMessageDTOBuilder<?, ?> b) {
        super(b);
    }

    public static AdminMessageDTOBuilder<?, ?> builder() {
        return new AdminMessageDTOBuilderImpl();
    }

    private static final class AdminMessageDTOBuilderImpl extends AdminMessageDTOBuilder<AdminMessageDTO, AdminMessageDTOBuilderImpl> {
        protected AdminMessageDTOBuilderImpl self() {
            return this;
        }

        private AdminMessageDTOBuilderImpl() {
        }

        public AdminMessageDTO build() {
            return new AdminMessageDTO(this);
        }
    }

    public static abstract class AdminMessageDTOBuilder<C extends AdminMessageDTO, B extends AdminMessageDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public AdminMessageDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/message/dto/AdminMessageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */