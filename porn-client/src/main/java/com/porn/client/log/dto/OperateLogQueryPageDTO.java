package com.porn.client.log.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class OperateLogQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("方法")
    private String method;

    @ApiModelProperty("用户名模糊搜索")
    private String lkName;

    @ApiModelProperty("执行入口模糊搜索")
    private String lkAction;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    protected OperateLogQueryPageDTO(OperateLogQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.method = b.method;
        this.lkName = b.lkName;
        this.lkAction = b.lkAction;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
    }

    public OperateLogQueryPageDTO(String method, String lkName, String lkAction, LocalDateTime startTime, LocalDateTime endTime) {

        this.method = method;
        this.lkName = lkName;
        this.lkAction = lkAction;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public OperateLogQueryPageDTO() {
    }

    public static OperateLogQueryPageDTOBuilder<?, ?> builder() {
        return new OperateLogQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OperateLogQueryPageDTO;
    }

    public String getMethod() {

        return this.method;

    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getLkName() {

        return this.lkName;

    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public String getLkAction() {

        return this.lkAction;

    }

    public void setLkAction(String lkAction) {
        this.lkAction = lkAction;
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

    private static final class OperateLogQueryPageDTOBuilderImpl extends OperateLogQueryPageDTOBuilder<OperateLogQueryPageDTO, OperateLogQueryPageDTOBuilderImpl> {
        private OperateLogQueryPageDTOBuilderImpl() {
        }

        protected OperateLogQueryPageDTOBuilderImpl self() {
            return this;
        }

        public OperateLogQueryPageDTO build() {
            return new OperateLogQueryPageDTO(this);
        }
    }

    public static abstract class OperateLogQueryPageDTOBuilder<C extends OperateLogQueryPageDTO, B extends OperateLogQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String method;
        private String lkName;
        private String lkAction;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public B method(String method) {
            this.method = method;
            return self();
        }

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        public B lkAction(String lkAction) {
            this.lkAction = lkAction;
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

