
package com.porn.client.inline.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class InlineQueryDTO
         extends BaseDTO
         {
    

    protected boolean canEqual(Object other) {
        return other instanceof InlineQueryDTO;
    }



    /* 14 */
    protected InlineQueryDTO(InlineQueryDTOBuilder<?, ?> b) {
        super(b);
    }

    public static InlineQueryDTOBuilder<?, ?> builder() {
        return new InlineQueryDTOBuilderImpl();
    }

    private static final class InlineQueryDTOBuilderImpl extends InlineQueryDTOBuilder<InlineQueryDTO, InlineQueryDTOBuilderImpl> {
        protected InlineQueryDTOBuilderImpl self() {
            return this;
        }

        private InlineQueryDTOBuilderImpl() {
        }

        public InlineQueryDTO build() {
            return new InlineQueryDTO(this);
        }
    }

    public static abstract class InlineQueryDTOBuilder<C extends InlineQueryDTO, B extends InlineQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        
        
        
        protected abstract B self();

        
        
        public abstract C build();
    }

    
    
    
    public InlineQueryDTO() {
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/inline/dto/InlineQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */