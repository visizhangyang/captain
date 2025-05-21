
package com.porn.client.inline.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


 public class InlineSaveOrUpdateDTO extends BaseDTO {
    
    @ApiModelProperty("最小在线人数")
     private Long minInlineCount;
    
    @ApiModelProperty("最大搬砖金额")
     private Long maxInlineCount;
    
    @ApiModelProperty("在线时间范围(小)")
     private String minInlineTime;
    
    @ApiModelProperty("在线时间范围(大)")
     private String maxInlineTime;

    
    /* 15 */
    public void setMinInlineCount(Long minInlineCount) {
        this.minInlineCount = minInlineCount;
    }

    public void setMaxInlineCount(Long maxInlineCount) {
        this.maxInlineCount = maxInlineCount;
    }

    public void setMinInlineTime(String minInlineTime) {
        this.minInlineTime = minInlineTime;
    }

    public void setMaxInlineTime(String maxInlineTime) {
        this.maxInlineTime = maxInlineTime;
    }


    protected boolean canEqual(Object other) {
        return other instanceof InlineSaveOrUpdateDTO;
    }



    /* 16 */
    protected InlineSaveOrUpdateDTO(InlineSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.minInlineCount = b.minInlineCount;
        this.maxInlineCount = b.maxInlineCount;
        this.minInlineTime = b.minInlineTime;
        this.maxInlineTime = b.maxInlineTime;
    }

    public static InlineSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new InlineSaveOrUpdateDTOBuilderImpl();
    }

    private static final class InlineSaveOrUpdateDTOBuilderImpl extends InlineSaveOrUpdateDTOBuilder<InlineSaveOrUpdateDTO, InlineSaveOrUpdateDTOBuilderImpl> {
        private InlineSaveOrUpdateDTOBuilderImpl() {
        }

        protected InlineSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public InlineSaveOrUpdateDTO build() {
            return new InlineSaveOrUpdateDTO(this);
        }
    }

    public static abstract class InlineSaveOrUpdateDTOBuilder<C extends InlineSaveOrUpdateDTO, B extends InlineSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long minInlineCount;
        private Long maxInlineCount;

        public B minInlineCount(Long minInlineCount) {
            this.minInlineCount = minInlineCount;
            return self();
        }

        private String minInlineTime;
        private String maxInlineTime;

        public B maxInlineCount(Long maxInlineCount) {
            this.maxInlineCount = maxInlineCount;
            return self();
        }

        public B minInlineTime(String minInlineTime) {
            this.minInlineTime = minInlineTime;
            return self();
        }

        public B maxInlineTime(String maxInlineTime) {
            this.maxInlineTime = maxInlineTime;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public InlineSaveOrUpdateDTO(Long minInlineCount, Long maxInlineCount, String minInlineTime, String maxInlineTime) {
        /* 17 */
        this.minInlineCount = minInlineCount;
        this.maxInlineCount = maxInlineCount;
        this.minInlineTime = minInlineTime;
        this.maxInlineTime = maxInlineTime;
        
    }

    
    public InlineSaveOrUpdateDTO() {
    }

    
    
    public Long getMinInlineCount() {
        /* 22 */
        return this.minInlineCount;
        
    }

    
    public Long getMaxInlineCount() {
        /* 25 */
        return this.maxInlineCount;
        
    }

    
    public String getMinInlineTime() {
        /* 28 */
        return this.minInlineTime;
        
    }

    
    public String getMaxInlineTime() {
        /* 31 */
        return this.maxInlineTime;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/inline/dto/InlineSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */