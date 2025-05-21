
package com.porn.client.stream.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BasePageDTO;

import java.time.LocalDateTime;
import java.util.List;

public class StreamQueryPageDTO extends BasePageDTO {
    @ApiModelProperty("账户ID")
     private Long accountId;
    
    @ApiModelProperty("账户ID")
     private List<Long> accountIdList;
    
    @ApiModelProperty("标识")
     private List<Integer> flagList;
    
    @ApiModelProperty("类型")
     private Integer type;
    
    @ApiModelProperty("标识")
     private Integer flag;
    
    @ApiModelProperty("订单ID")
     private Long bizId;
    
    @ApiModelProperty("开始时间")
     private LocalDateTime startTime;
    
    @ApiModelProperty("结束时间")
     private LocalDateTime endTime;

    
    /* 18 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }

    public void setFlagList(List<Integer> flagList) {
        this.flagList = flagList;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    protected boolean canEqual(Object other) {
        return other instanceof StreamQueryPageDTO;
    }



    /* 19 */
    protected StreamQueryPageDTO(StreamQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountIdList = b.accountIdList;
        this.flagList = b.flagList;
        this.type = b.type;
        this.flag = b.flag;
        this.bizId = b.bizId;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
    }

    public static StreamQueryPageDTOBuilder<?, ?> builder() {
        return new StreamQueryPageDTOBuilderImpl();
    }

    private static final class StreamQueryPageDTOBuilderImpl extends StreamQueryPageDTOBuilder<StreamQueryPageDTO, StreamQueryPageDTOBuilderImpl> {
        private StreamQueryPageDTOBuilderImpl() {
        }

        protected StreamQueryPageDTOBuilderImpl self() {
            return this;
        }

        public StreamQueryPageDTO build() {
            return new StreamQueryPageDTO(this);
        }
    }

    public static abstract class StreamQueryPageDTOBuilder<C extends StreamQueryPageDTO, B extends StreamQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Long accountId;
        private List<Long> accountIdList;
        private List<Integer> flagList;
        private Integer type;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private Integer flag;
        private Long bizId;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public B accountIdList(List<Long> accountIdList) {
            this.accountIdList = accountIdList;
            return self();
        }

        public B flagList(List<Integer> flagList) {
            this.flagList = flagList;
            return self();
        }

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        public B flag(Integer flag) {
            this.flag = flag;
            return self();
        }

        public B bizId(Long bizId) {
            this.bizId = bizId;
            return self();
        }

        public B startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return self();
        }

        public B endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public StreamQueryPageDTO(Long accountId, List<Long> accountIdList, List<Integer> flagList, Integer type, Integer flag, Long bizId, LocalDateTime startTime, LocalDateTime endTime) {
        /* 20 */
        this.accountId = accountId;
        this.accountIdList = accountIdList;
        this.flagList = flagList;
        this.type = type;
        this.flag = flag;
        this.bizId = bizId;
        this.startTime = startTime;
        this.endTime = endTime;
        
    }

    
    public StreamQueryPageDTO() {
    }

    
    
    public Long getAccountId() {
        /* 25 */
        return this.accountId;
        
    }

    
    public List<Long> getAccountIdList() {
        /* 28 */
        return this.accountIdList;
        
    }

    
    public List<Integer> getFlagList() {
        /* 31 */
        return this.flagList;
        
    }

    
    public Integer getType() {
        /* 34 */
        return this.type;
        
    }

    
    public Integer getFlag() {
        /* 37 */
        return this.flag;
        
    }

    
    public Long getBizId() {
        /* 40 */
        return this.bizId;
        
    }

    
    public LocalDateTime getStartTime() {
        /* 43 */
        return this.startTime;
        
    }

    
    public LocalDateTime getEndTime() {
        /* 46 */
        return this.endTime;
        
    }
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/stream/dto/StreamQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */