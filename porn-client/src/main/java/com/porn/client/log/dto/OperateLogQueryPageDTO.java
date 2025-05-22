
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


    /* 16 */
    public void setMethod(String method) {
        this.method = method;
    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public void setLkAction(String lkAction) {
        this.lkAction = lkAction;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    protected boolean canEqual(Object other) {
        return other instanceof OperateLogQueryPageDTO;
    }



    /* 17 */
    protected OperateLogQueryPageDTO(OperateLogQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.method = b.method;
        this.lkName = b.lkName;
        this.lkAction = b.lkAction;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
    }

    public static OperateLogQueryPageDTOBuilder<?, ?> builder() {
        return new OperateLogQueryPageDTOBuilderImpl();
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

        public B method(String method) {
            this.method = method;
            return self();
        }

        private String lkAction;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

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

    public OperateLogQueryPageDTO(String method, String lkName, String lkAction, LocalDateTime startTime, LocalDateTime endTime) {
        /* 18 */
        this.method = method;
        this.lkName = lkName;
        this.lkAction = lkAction;
        this.startTime = startTime;
        this.endTime = endTime;

    }


    public OperateLogQueryPageDTO() {
    }



    public String getMethod() {
        /* 23 */
        return this.method;

    }


    public String getLkName() {
        /* 26 */
        return this.lkName;

    }


    public String getLkAction() {
        /* 29 */
        return this.lkAction;

    }


    public LocalDateTime getStartTime() {
        /* 32 */
        return this.startTime;

    }


    public LocalDateTime getEndTime() {
        /* 35 */
        return this.endTime;

    }

}


