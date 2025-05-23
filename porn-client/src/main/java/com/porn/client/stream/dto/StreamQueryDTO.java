package com.porn.client.stream.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

public class StreamQueryDTO extends BaseDTO {
    @ApiModelProperty("账户ID")
    private Long accountId;
    @ApiModelProperty("账户ID")
    private List<Long> accountIdList;
    @ApiModelProperty("标识")
    private List<Integer> flagList;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("类型")
    private List<Integer> typeList;

    @ApiModelProperty("标识")
    private Integer flag;

    @ApiModelProperty("订单ID")
    private Long bizId;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    protected StreamQueryDTO(StreamQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountIdList = b.accountIdList;
        this.flagList = b.flagList;
        this.type = b.type;
        this.typeList = b.typeList;
        this.flag = b.flag;
        this.bizId = b.bizId;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
    }

    public StreamQueryDTO(Long accountId, List<Long> accountIdList, List<Integer> flagList, Integer type, List<Integer> typeList, Integer flag, Long bizId, LocalDateTime startTime, LocalDateTime endTime) {

        this.accountId = accountId;
        this.accountIdList = accountIdList;
        this.flagList = flagList;
        this.type = type;
        this.typeList = typeList;
        this.flag = flag;
        this.bizId = bizId;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public StreamQueryDTO() {
    }

    public static StreamQueryDTOBuilder<?, ?> builder() {
        return new StreamQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof StreamQueryDTO;
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

    public List<Integer> getTypeList() {

        return this.typeList;

    }

    public void setTypeList(List<Integer> typeList) {
        this.typeList = typeList;
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

    private static final class StreamQueryDTOBuilderImpl extends StreamQueryDTOBuilder<StreamQueryDTO, StreamQueryDTOBuilderImpl> {
        private StreamQueryDTOBuilderImpl() {
        }

        protected StreamQueryDTOBuilderImpl self() {
            return this;
        }

        public StreamQueryDTO build() {
            return new StreamQueryDTO(this);
        }
    }

    public static abstract class StreamQueryDTOBuilder<C extends StreamQueryDTO, B extends StreamQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private List<Long> accountIdList;
        private List<Integer> flagList;
        private Integer type;
        private List<Integer> typeList;
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

        public B typeList(List<Integer> typeList) {
            this.typeList = typeList;
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

