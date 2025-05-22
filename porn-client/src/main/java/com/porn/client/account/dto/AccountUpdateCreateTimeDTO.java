
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.time.LocalDateTime;







 public class AccountUpdateCreateTimeDTO
         extends BaseDTO
         {

    @ApiModelProperty("更新时间")
     private LocalDateTime createTime;



    public void setCreateTime(LocalDateTime createTime) {
        /* 16 */
        this.createTime = createTime;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountUpdateCreateTimeDTO;
    }



    /* 17 */
    protected AccountUpdateCreateTimeDTO(AccountUpdateCreateTimeDTOBuilder<?, ?> b) {
        super(b);
        this.createTime = b.createTime;
    }

    public static AccountUpdateCreateTimeDTOBuilder<?, ?> builder() {
        return new AccountUpdateCreateTimeDTOBuilderImpl();
    }

    private static final class AccountUpdateCreateTimeDTOBuilderImpl extends AccountUpdateCreateTimeDTOBuilder<AccountUpdateCreateTimeDTO, AccountUpdateCreateTimeDTOBuilderImpl> {
        private AccountUpdateCreateTimeDTOBuilderImpl() {
        }

        protected AccountUpdateCreateTimeDTOBuilderImpl self() {
            return this;
        }

        public AccountUpdateCreateTimeDTO build() {
            return new AccountUpdateCreateTimeDTO(this);
        }
    }

    public static abstract class AccountUpdateCreateTimeDTOBuilder<C extends AccountUpdateCreateTimeDTO, B extends AccountUpdateCreateTimeDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return self();
        }

        private LocalDateTime createTime;

        protected abstract B self();

        public abstract C build();

    }

    public AccountUpdateCreateTimeDTO(LocalDateTime createTime) {
        /* 18 */
        this.createTime = createTime;

    }


    public AccountUpdateCreateTimeDTO() {
    }



    public LocalDateTime getCreateTime() {
        /* 23 */
        return this.createTime;

    }

}


