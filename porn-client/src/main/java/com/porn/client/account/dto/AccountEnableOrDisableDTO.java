
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class AccountEnableOrDisableDTO extends BaseDTO {

    @ApiModelProperty("id列表")
     private List<Long> idList;

    @ApiModelProperty("0-状态, 1-搬砖, 2-提现, 3-转账, 4-搬砖自动审核, 5-重点关注, 6-自动搬砖, 7-上传, 8-计划, 9-抽奖")
     private Integer type;

    @ApiModelProperty("状态, 1-启用, 0-禁用")
     private Integer status;


    /* 17 */
    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountEnableOrDisableDTO;
    }



    /* 18 */
    protected AccountEnableOrDisableDTO(AccountEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
        this.type = b.type;
        this.status = b.status;
    }

    public static AccountEnableOrDisableDTOBuilder<?, ?> builder() {
        return new AccountEnableOrDisableDTOBuilderImpl();
    }

    private static final class AccountEnableOrDisableDTOBuilderImpl extends AccountEnableOrDisableDTOBuilder<AccountEnableOrDisableDTO, AccountEnableOrDisableDTOBuilderImpl> {
        private AccountEnableOrDisableDTOBuilderImpl() {
        }

        protected AccountEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public AccountEnableOrDisableDTO build() {
            return new AccountEnableOrDisableDTO(this);
        }
    }

    public static abstract class AccountEnableOrDisableDTOBuilder<C extends AccountEnableOrDisableDTO, B extends AccountEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> idList;

        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        private Integer type;
        private Integer status;

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public AccountEnableOrDisableDTO(List<Long> idList, Integer type, Integer status) {
        /* 19 */
        this.idList = idList;
        this.type = type;
        this.status = status;

    }


    public AccountEnableOrDisableDTO() {
    }



    public List<Long> getIdList() {
        /* 24 */
        return this.idList;

    }


    public Integer getType() {
        /* 27 */
        return this.type;

    }


    public Integer getStatus() {
        /* 30 */
        return this.status;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountEnableOrDisableDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */