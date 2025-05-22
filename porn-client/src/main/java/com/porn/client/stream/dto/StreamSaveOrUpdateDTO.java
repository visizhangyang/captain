package com.porn.client.stream.dto;
import io.swagger.annotations.ApiModelProperty;


import com.porn.client.common.dto.BaseDTO;


import java.math.BigDecimal;

 public class StreamSaveOrUpdateDTO extends BaseDTO {
    
    @ApiModelProperty("账户ID")
     private Long accountId;
    
    @ApiModelProperty("账户名称")
     private String accountName;
    
    @ApiModelProperty("操作前总余额")
     private BigDecimal beforeTotalBalance;
    
    @ApiModelProperty("操作前可用余额")
     private BigDecimal beforeAvailableBalance;
    
    @ApiModelProperty("操作前冻结余额")
     private BigDecimal beforeFreezeBalance;
    
    @ApiModelProperty("操作后总余额")
     private BigDecimal afterTotalBalance;

    
    /* 16 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @ApiModelProperty("操作后可用余额")
    private BigDecimal afterAvailableBalance;
    @ApiModelProperty("操作后冻结余额")
    private BigDecimal afterFreezeBalance;
    @ApiModelProperty("业务ID")
    private Long bizId;
    @ApiModelProperty("金额")
    private BigDecimal amount;
    @ApiModelProperty("类型, StreamTypeEnum")
    private Integer type;
    @ApiModelProperty("标识, StreamTypeEnum")
    private Integer flag;

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setBeforeTotalBalance(BigDecimal beforeTotalBalance) {
        this.beforeTotalBalance = beforeTotalBalance;
    }

    public void setBeforeAvailableBalance(BigDecimal beforeAvailableBalance) {
        this.beforeAvailableBalance = beforeAvailableBalance;
    }

    public void setBeforeFreezeBalance(BigDecimal beforeFreezeBalance) {
        this.beforeFreezeBalance = beforeFreezeBalance;
    }

    public void setAfterTotalBalance(BigDecimal afterTotalBalance) {
        this.afterTotalBalance = afterTotalBalance;
    }

    public void setAfterAvailableBalance(BigDecimal afterAvailableBalance) {
        this.afterAvailableBalance = afterAvailableBalance;
    }

    public void setAfterFreezeBalance(BigDecimal afterFreezeBalance) {
        this.afterFreezeBalance = afterFreezeBalance;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }


    protected boolean canEqual(Object other) {
        return other instanceof StreamSaveOrUpdateDTO;
    }



    /* 17 */
    protected StreamSaveOrUpdateDTO(StreamSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountName = b.accountName;
        this.beforeTotalBalance = b.beforeTotalBalance;
        this.beforeAvailableBalance = b.beforeAvailableBalance;
        this.beforeFreezeBalance = b.beforeFreezeBalance;
        this.afterTotalBalance = b.afterTotalBalance;
        this.afterAvailableBalance = b.afterAvailableBalance;
        this.afterFreezeBalance = b.afterFreezeBalance;
        this.bizId = b.bizId;
        this.amount = b.amount;
        this.type = b.type;
        this.flag = b.flag;
    }

    public static StreamSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new StreamSaveOrUpdateDTOBuilderImpl();
    }

    private static final class StreamSaveOrUpdateDTOBuilderImpl extends StreamSaveOrUpdateDTOBuilder<StreamSaveOrUpdateDTO, StreamSaveOrUpdateDTOBuilderImpl> {
        private StreamSaveOrUpdateDTOBuilderImpl() {
        }

        protected StreamSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public StreamSaveOrUpdateDTO build() {
            return new StreamSaveOrUpdateDTO(this);
        }
    }

    public static abstract class StreamSaveOrUpdateDTOBuilder<C extends StreamSaveOrUpdateDTO, B extends StreamSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private String accountName;
        private BigDecimal beforeTotalBalance;
        private BigDecimal beforeAvailableBalance;
        private BigDecimal beforeFreezeBalance;
        private BigDecimal afterTotalBalance;
        private BigDecimal afterAvailableBalance;
        private BigDecimal afterFreezeBalance;
        private Long bizId;
        private BigDecimal amount;
        private Integer type;
        private Integer flag;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B accountName(String accountName) {
            this.accountName = accountName;
            return self();
        }

        public B beforeTotalBalance(BigDecimal beforeTotalBalance) {
            this.beforeTotalBalance = beforeTotalBalance;
            return self();
        }

        public B beforeAvailableBalance(BigDecimal beforeAvailableBalance) {
            this.beforeAvailableBalance = beforeAvailableBalance;
            return self();
        }

        public B beforeFreezeBalance(BigDecimal beforeFreezeBalance) {
            this.beforeFreezeBalance = beforeFreezeBalance;
            return self();
        }

        public B afterTotalBalance(BigDecimal afterTotalBalance) {
            this.afterTotalBalance = afterTotalBalance;
            return self();
        }

        public B afterAvailableBalance(BigDecimal afterAvailableBalance) {
            this.afterAvailableBalance = afterAvailableBalance;
            return self();
        }

        public B afterFreezeBalance(BigDecimal afterFreezeBalance) {
            this.afterFreezeBalance = afterFreezeBalance;
            return self();
        }

        public B bizId(Long bizId) {
            this.bizId = bizId;
            return self();
        }

        public B amount(BigDecimal amount) {
            this.amount = amount;
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

        protected abstract B self();

        public abstract C build();

    }

    public StreamSaveOrUpdateDTO(Long accountId, String accountName, BigDecimal beforeTotalBalance, BigDecimal beforeAvailableBalance, BigDecimal beforeFreezeBalance, BigDecimal afterTotalBalance, BigDecimal afterAvailableBalance, BigDecimal afterFreezeBalance, Long bizId, BigDecimal amount, Integer type, Integer flag) {
        /* 18 */
        this.accountId = accountId;
        this.accountName = accountName;
        this.beforeTotalBalance = beforeTotalBalance;
        this.beforeAvailableBalance = beforeAvailableBalance;
        this.beforeFreezeBalance = beforeFreezeBalance;
        this.afterTotalBalance = afterTotalBalance;
        this.afterAvailableBalance = afterAvailableBalance;
        this.afterFreezeBalance = afterFreezeBalance;
        this.bizId = bizId;
        this.amount = amount;
        this.type = type;
        this.flag = flag;
        
    }

    
    public StreamSaveOrUpdateDTO() {
    }

    
    
    public Long getAccountId() {
        /* 23 */
        return this.accountId;
        
    }

    
    public String getAccountName() {
        /* 26 */
        return this.accountName;
        
    }

    
    public BigDecimal getBeforeTotalBalance() {
        /* 29 */
        return this.beforeTotalBalance;
        
    }

    
    public BigDecimal getBeforeAvailableBalance() {
        /* 32 */
        return this.beforeAvailableBalance;
        
    }

    
    public BigDecimal getBeforeFreezeBalance() {
        /* 35 */
        return this.beforeFreezeBalance;
        
    }

    
    public BigDecimal getAfterTotalBalance() {
        /* 38 */
        return this.afterTotalBalance;
        
    }

    
    public BigDecimal getAfterAvailableBalance() {
        /* 41 */
        return this.afterAvailableBalance;
        
    }

    
    public BigDecimal getAfterFreezeBalance() {
        /* 44 */
        return this.afterFreezeBalance;
        
    }

    
    public Long getBizId() {
        /* 47 */
        return this.bizId;
        
    }

    
    public BigDecimal getAmount() {
        /* 50 */
        return this.amount;
        
    }

    
    public Integer getType() {
        /* 53 */
        return this.type;
        
    }

    
    public Integer getFlag() {
        /* 56 */
        return this.flag;
        
    }
    
}


