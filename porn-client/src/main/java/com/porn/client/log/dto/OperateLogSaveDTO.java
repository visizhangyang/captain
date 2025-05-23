package com.porn.client.log.dto;

import com.porn.client.common.req.ServiceRequest;
import io.swagger.annotations.ApiModelProperty;

public class OperateLogSaveDTO extends ServiceRequest {
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("请求方法")
    private String method;

    @ApiModelProperty("请求入口")
    private String action;

    @ApiModelProperty("参数")
    private String params;

    @ApiModelProperty("起始时间")
    private Long startTime;

    @ApiModelProperty("结束时间")
    private Long endTime;

    protected OperateLogSaveDTO(OperateLogSaveDTOBuilder<?, ?> b) {
        super(b);
        this.userId = b.userId;
        this.name = b.name;
        this.method = b.method;
        this.action = b.action;
        this.params = b.params;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
    }

    public OperateLogSaveDTO(Long userId, String name, String method, String action, String params, Long startTime, Long endTime) {

        this.userId = userId;
        this.name = name;
        this.method = method;
        this.action = action;
        this.params = params;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public OperateLogSaveDTO() {
    }

    public static OperateLogSaveDTOBuilder<?, ?> builder() {
        return new OperateLogSaveDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OperateLogSaveDTO;
    }

    public Long getUserId() {

        return this.userId;

    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {

        return this.method;

    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAction() {

        return this.action;

    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getParams() {

        return this.params;

    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getStartTime() {

        return this.startTime;

    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {

        return this.endTime;

    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    private static final class OperateLogSaveDTOBuilderImpl extends OperateLogSaveDTOBuilder<OperateLogSaveDTO, OperateLogSaveDTOBuilderImpl> {
        private OperateLogSaveDTOBuilderImpl() {
        }

        protected OperateLogSaveDTOBuilderImpl self() {
            return this;
        }

        public OperateLogSaveDTO build() {
            return new OperateLogSaveDTO(this);
        }
    }

    public static abstract class OperateLogSaveDTOBuilder<C extends OperateLogSaveDTO, B extends OperateLogSaveDTOBuilder<C, B>> extends ServiceRequest.ServiceRequestBuilder<C, B> {
        private Long userId;
        private String name;
        private String method;
        private String action;
        private String params;
        private Long startTime;
        private Long endTime;

        public B userId(Long userId) {
            this.userId = userId;
            return self();
        }

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B method(String method) {
            this.method = method;
            return self();
        }

        public B action(String action) {
            this.action = action;
            return self();
        }

        public B params(String params) {
            this.params = params;
            return self();
        }

        public B startTime(Long startTime) {
            this.startTime = startTime;
            return self();
        }

        public B endTime(Long endTime) {
            this.endTime = endTime;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}


