
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class AccountRobotCreateDTO
         extends BaseDTO
         {

    @ApiModelProperty("创建次数")
     private Integer createCount;



    public void setCreateCount(Integer createCount) {
        /* 15 */
        this.createCount = createCount;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountRobotCreateDTO;
    }



    /* 16 */
    protected AccountRobotCreateDTO(AccountRobotCreateDTOBuilder<?, ?> b) {
        super(b);
        this.createCount = b.createCount;
    }

    public static AccountRobotCreateDTOBuilder<?, ?> builder() {
        return new AccountRobotCreateDTOBuilderImpl();
    }

    private static final class AccountRobotCreateDTOBuilderImpl extends AccountRobotCreateDTOBuilder<AccountRobotCreateDTO, AccountRobotCreateDTOBuilderImpl> {
        private AccountRobotCreateDTOBuilderImpl() {
        }

        protected AccountRobotCreateDTOBuilderImpl self() {
            return this;
        }

        public AccountRobotCreateDTO build() {
            return new AccountRobotCreateDTO(this);
        }
    }

    public static abstract class AccountRobotCreateDTOBuilder<C extends AccountRobotCreateDTO, B extends AccountRobotCreateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B createCount(Integer createCount) {
            this.createCount = createCount;
            return self();
        }

        private Integer createCount;

        protected abstract B self();

        public abstract C build();

    }

    public AccountRobotCreateDTO(Integer createCount) {
        /* 17 */
        this.createCount = createCount;

    }


    public AccountRobotCreateDTO() {
    }



    public Integer getCreateCount() {
        /* 22 */
        return this.createCount;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountRobotCreateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */