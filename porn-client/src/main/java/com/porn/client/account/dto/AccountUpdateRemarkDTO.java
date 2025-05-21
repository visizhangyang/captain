
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class AccountUpdateRemarkDTO
         extends BaseDTO
         {

    @ApiModelProperty("备注")
     private String remark;



    public void setRemark(String remark) {
        /* 17 */
        this.remark = remark;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountUpdateRemarkDTO;
    }



    /* 18 */
    protected AccountUpdateRemarkDTO(AccountUpdateRemarkDTOBuilder<?, ?> b) {
        super(b);
        this.remark = b.remark;
    }

    public static AccountUpdateRemarkDTOBuilder<?, ?> builder() {
        return new AccountUpdateRemarkDTOBuilderImpl();
    }

    private static final class AccountUpdateRemarkDTOBuilderImpl extends AccountUpdateRemarkDTOBuilder<AccountUpdateRemarkDTO, AccountUpdateRemarkDTOBuilderImpl> {
        private AccountUpdateRemarkDTOBuilderImpl() {
        }

        protected AccountUpdateRemarkDTOBuilderImpl self() {
            return this;
        }

        public AccountUpdateRemarkDTO build() {
            return new AccountUpdateRemarkDTO(this);
        }
    }

    public static abstract class AccountUpdateRemarkDTOBuilder<C extends AccountUpdateRemarkDTO, B extends AccountUpdateRemarkDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B remark(String remark) {
            this.remark = remark;
            return self();
        }

        private String remark;

        protected abstract B self();

        public abstract C build();

    }

    public AccountUpdateRemarkDTO(String remark) {
        /* 19 */
        this.remark = remark;

    }


    public AccountUpdateRemarkDTO() {
    }



    public String getRemark() {
        /* 24 */
        return this.remark;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountUpdateRemarkDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */