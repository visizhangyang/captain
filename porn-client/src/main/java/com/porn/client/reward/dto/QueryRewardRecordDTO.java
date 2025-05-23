package com.porn.client.reward.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class QueryRewardRecordDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("类型, RewardRecordTypeEnum")
    private Integer type;

    @ApiModelProperty("业务类型, RuleTypeEnum")
    private Integer bizType;

    @ApiModelProperty("业务ID")
    private String bizId;

    protected QueryRewardRecordDTO(QueryRewardRecordDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.type = b.type;
        this.bizType = b.bizType;
        this.bizId = b.bizId;
    }

    public QueryRewardRecordDTO(Long accountId, Integer type, Integer bizType, String bizId) {

        this.accountId = accountId;
        this.type = type;
        this.bizType = bizType;
        this.bizId = bizId;

    }

    public QueryRewardRecordDTO() {
    }

    public static QueryRewardRecordDTOBuilder<?, ?> builder() {
        return new QueryRewardRecordDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof QueryRewardRecordDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getType() {

        return this.type;

    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBizType() {

        return this.bizType;

    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getBizId() {

        return this.bizId;

    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    private static final class QueryRewardRecordDTOBuilderImpl extends QueryRewardRecordDTOBuilder<QueryRewardRecordDTO, QueryRewardRecordDTOBuilderImpl> {
        private QueryRewardRecordDTOBuilderImpl() {
        }

        protected QueryRewardRecordDTOBuilderImpl self() {
            return this;
        }

        public QueryRewardRecordDTO build() {
            return new QueryRewardRecordDTO(this);
        }
    }

    public static abstract class QueryRewardRecordDTOBuilder<C extends QueryRewardRecordDTO, B extends QueryRewardRecordDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private Integer type;
        private Integer bizType;
        private String bizId;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        public B bizType(Integer bizType) {
            this.bizType = bizType;
            return self();
        }

        public B bizId(String bizId) {
            this.bizId = bizId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

