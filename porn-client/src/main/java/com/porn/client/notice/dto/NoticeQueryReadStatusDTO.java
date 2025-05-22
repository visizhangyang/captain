
package com.porn.client.notice.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class NoticeQueryReadStatusDTO extends BaseDTO {
    
    @ApiModelProperty("账户ID")
     private Long accountId;
    
    @ApiModelProperty("读取类型, 0-未读, 1-已读")
     private Integer readType;

    
    
    public void setAccountId(Long accountId) {
        /* 15 */
        this.accountId = accountId;
    }

    public void setReadType(Integer readType) {
        this.readType = readType;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NoticeQueryReadStatusDTO;
    }



    /* 16 */
    protected NoticeQueryReadStatusDTO(NoticeQueryReadStatusDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.readType = b.readType;
    }

    public static NoticeQueryReadStatusDTOBuilder<?, ?> builder() {
        return new NoticeQueryReadStatusDTOBuilderImpl();
    }

    private static final class NoticeQueryReadStatusDTOBuilderImpl extends NoticeQueryReadStatusDTOBuilder<NoticeQueryReadStatusDTO, NoticeQueryReadStatusDTOBuilderImpl> {
        private NoticeQueryReadStatusDTOBuilderImpl() {
        }

        protected NoticeQueryReadStatusDTOBuilderImpl self() {
            return this;
        }

        public NoticeQueryReadStatusDTO build() {
            return new NoticeQueryReadStatusDTO(this);
        }
    }

    public static abstract class NoticeQueryReadStatusDTOBuilder<C extends NoticeQueryReadStatusDTO, B extends NoticeQueryReadStatusDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private Integer readType;

        public B readType(Integer readType) {
            this.readType = readType;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public NoticeQueryReadStatusDTO(Long accountId, Integer readType) {
        /* 17 */
        this.accountId = accountId;
        this.readType = readType;
        
    }

    
    public NoticeQueryReadStatusDTO() {
    }

    
    
    public Long getAccountId() {
        /* 22 */
        return this.accountId;
        
    }

    
    public Integer getReadType() {
        /* 25 */
        return this.readType;
        
    }
    
}


