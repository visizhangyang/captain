
package com.porn.client.transfer.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.time.LocalDateTime;







 public class TransferQueryDTO
         extends BaseDTO {

    @ApiModelProperty("源账户ID")
     private Long srcAccountId;

    @ApiModelProperty("开始时间")
     private LocalDateTime startTime;



    public void setSrcAccountId(Long srcAccountId) {
        /* 17 */
        this.srcAccountId = srcAccountId;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }


    protected boolean canEqual(Object other) {
        return other instanceof TransferQueryDTO;
    }



    /* 18 */
    protected TransferQueryDTO(TransferQueryDTOBuilder<?, ?> b) {
        super(b);
        this.srcAccountId = b.srcAccountId;
        this.startTime = b.startTime;
    }

    public static TransferQueryDTOBuilder<?, ?> builder() {
        return new TransferQueryDTOBuilderImpl();
    }

    private static final class TransferQueryDTOBuilderImpl extends TransferQueryDTOBuilder<TransferQueryDTO, TransferQueryDTOBuilderImpl> {
        private TransferQueryDTOBuilderImpl() {
        }

        protected TransferQueryDTOBuilderImpl self() {
            return this;
        }

        public TransferQueryDTO build() {
            return new TransferQueryDTO(this);
        }
    }

    public static abstract class TransferQueryDTOBuilder<C extends TransferQueryDTO, B extends TransferQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long srcAccountId;

        public B srcAccountId(Long srcAccountId) {
            this.srcAccountId = srcAccountId;
            return self();
        }

        private LocalDateTime startTime;

        public B startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public TransferQueryDTO(Long srcAccountId, LocalDateTime startTime) {
        /* 19 */
        this.srcAccountId = srcAccountId;
        this.startTime = startTime;

    }


    public TransferQueryDTO() {
    }



    public Long getSrcAccountId() {
        /* 24 */
        return this.srcAccountId;

    }


    public LocalDateTime getStartTime() {
        /* 27 */
        return this.startTime;

    }

}


