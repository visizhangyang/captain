package com.porn.client.log.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class LoginLogQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("用户名模糊搜索")
    private String lkName;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    protected LoginLogQueryPageDTO(LoginLogQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkName = b.lkName;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
    }

    public LoginLogQueryPageDTO(String lkName, LocalDateTime startTime, LocalDateTime endTime) {

        this.lkName = lkName;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public LoginLogQueryPageDTO() {
    }

    public static LoginLogQueryPageDTOBuilder<?, ?> builder() {
        return new LoginLogQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof LoginLogQueryPageDTO;
    }

    public String getLkName() {

        return this.lkName;

    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
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

    private static final class LoginLogQueryPageDTOBuilderImpl extends LoginLogQueryPageDTOBuilder<LoginLogQueryPageDTO, LoginLogQueryPageDTOBuilderImpl> {
        private LoginLogQueryPageDTOBuilderImpl() {
        }

        protected LoginLogQueryPageDTOBuilderImpl self() {
            return this;
        }

        public LoginLogQueryPageDTO build() {
            return new LoginLogQueryPageDTO(this);
        }
    }

    public static abstract class LoginLogQueryPageDTOBuilder<C extends LoginLogQueryPageDTO, B extends LoginLogQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String lkName;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public B lkName(String lkName) {
            this.lkName = lkName;
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

