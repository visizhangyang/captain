
package com.porn.client.log.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BasePageDTO;


import java.time.LocalDateTime;







 public class LoginLogQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("用户名模糊搜索")
     private String lkName;

    @ApiModelProperty("开始时间")
     private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
     private LocalDateTime endTime;


    /* 17 */
    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    protected boolean canEqual(Object other) {
        return other instanceof LoginLogQueryPageDTO;
    }



    /* 18 */
    protected LoginLogQueryPageDTO(LoginLogQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkName = b.lkName;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
    }

    public static LoginLogQueryPageDTOBuilder<?, ?> builder() {
        return new LoginLogQueryPageDTOBuilderImpl();
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

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        private LocalDateTime startTime;
        private LocalDateTime endTime;

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

    public LoginLogQueryPageDTO(String lkName, LocalDateTime startTime, LocalDateTime endTime) {
        /* 19 */
        this.lkName = lkName;
        this.startTime = startTime;
        this.endTime = endTime;

    }


    public LoginLogQueryPageDTO() {
    }



    public String getLkName() {
        /* 24 */
        return this.lkName;

    }


    public LocalDateTime getStartTime() {
        /* 27 */
        return this.startTime;

    }


    public LocalDateTime getEndTime() {
        /* 30 */
        return this.endTime;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/log/dto/LoginLogQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */