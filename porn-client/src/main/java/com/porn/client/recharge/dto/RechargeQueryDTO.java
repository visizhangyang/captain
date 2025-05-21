
package com.porn.client.recharge.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;


import java.time.LocalDateTime;
import java.util.List;

 public class RechargeQueryDTO extends BaseDTO {
    @ApiModelProperty("账户ID")
     private Long accountId;
    
    @ApiModelProperty("钱包编码")
     private String walletCode;
    
    @ApiModelProperty("状态, RechargeStatusEnum")
     private Integer status;
    
    @ApiModelProperty("hash值列表")
     private List<String> hashList;
    
    @ApiModelProperty("存在hash")
     private Boolean hasHash;
    
    @ApiModelProperty("开始时间")
     private LocalDateTime startTime;
    
    @ApiModelProperty("结束时间")
     private LocalDateTime endTime;

    
    /* 17 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setHashList(List<String> hashList) {
        this.hashList = hashList;
    }

    public void setHasHash(Boolean hasHash) {
        this.hasHash = hasHash;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RechargeQueryDTO;
    }



    /* 18 */
    protected RechargeQueryDTO(RechargeQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.walletCode = b.walletCode;
        this.status = b.status;
        this.hashList = b.hashList;
        this.hasHash = b.hasHash;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
    }

    public static RechargeQueryDTOBuilder<?, ?> builder() {
        return new RechargeQueryDTOBuilderImpl();
    }

    private static final class RechargeQueryDTOBuilderImpl extends RechargeQueryDTOBuilder<RechargeQueryDTO, RechargeQueryDTOBuilderImpl> {
        private RechargeQueryDTOBuilderImpl() {
        }

        protected RechargeQueryDTOBuilderImpl self() {
            return this;
        }

        public RechargeQueryDTO build() {
            return new RechargeQueryDTO(this);
        }
    }

    public static abstract class RechargeQueryDTOBuilder<C extends RechargeQueryDTO, B extends RechargeQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private String walletCode;
        private Integer status;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private List<String> hashList;
        private Boolean hasHash;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public B walletCode(String walletCode) {
            this.walletCode = walletCode;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B hashList(List<String> hashList) {
            this.hashList = hashList;
            return self();
        }

        public B hasHash(Boolean hasHash) {
            this.hasHash = hasHash;
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

    public RechargeQueryDTO(Long accountId, String walletCode, Integer status, List<String> hashList, Boolean hasHash, LocalDateTime startTime, LocalDateTime endTime) {
        /* 19 */
        this.accountId = accountId;
        this.walletCode = walletCode;
        this.status = status;
        this.hashList = hashList;
        this.hasHash = hasHash;
        this.startTime = startTime;
        this.endTime = endTime;
        
    }

    
    public RechargeQueryDTO() {
    }

    
    
    public Long getAccountId() {
        /* 24 */
        return this.accountId;
        
    }

    
    public String getWalletCode() {
        /* 27 */
        return this.walletCode;
        
    }

    
    public Integer getStatus() {
        /* 30 */
        return this.status;
        
    }

    
    public List<String> getHashList() {
        /* 33 */
        return this.hashList;
        
    }

    
    public Boolean getHasHash() {
        /* 36 */
        return this.hasHash;
        
    }

    
    public LocalDateTime getStartTime() {
        /* 39 */
        return this.startTime;
        
    }

    
    public LocalDateTime getEndTime() {
        /* 42 */
        return this.endTime;
        
    }
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/recharge/dto/RechargeQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */