package com.porn.client.stream.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

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

    public StreamQueryPageDTO(Long accountId, List<Long> accountIdList, List<Integer> flagList, Integer type, Integer flag, Long bizId, LocalDateTime startTime, LocalDateTime endTime) {

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

    public static StreamQueryPageDTOBuilder<?, ?> builder() {
        return new StreamQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof StreamQueryPageDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public List<Long> getAccountIdList() {

        return this.accountIdList;

    }

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }

    public List<Integer> getFlagList() {

        return this.flagList;

    }

    public void setFlagList(List<Integer> flagList) {
        this.flagList = flagList;
    }

    public Integer getType() {

        return this.type;

    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFlag() {

        return this.flag;

    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Long getBizId() {

        return this.bizId;

    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public LocalDateTime getStartTime() {

        return this.startTime;

    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {

        return this.endTime;

    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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
        private Integer flag;
        private Long bizId;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

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
}

