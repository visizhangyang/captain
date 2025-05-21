
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class AccountChangeNameDTO
         extends BaseDTO
         {
    
    @ApiModelProperty("修改后的账号")
     private String name;

    
    
    public void setName(String name) {
        /* 15 */
        this.name = name;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountChangeNameDTO;
    }



    /* 16 */
    protected AccountChangeNameDTO(AccountChangeNameDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
    }

    public static AccountChangeNameDTOBuilder<?, ?> builder() {
        return new AccountChangeNameDTOBuilderImpl();
    }

    private static final class AccountChangeNameDTOBuilderImpl extends AccountChangeNameDTOBuilder<AccountChangeNameDTO, AccountChangeNameDTOBuilderImpl> {
        private AccountChangeNameDTOBuilderImpl() {
        }

        protected AccountChangeNameDTOBuilderImpl self() {
            return this;
        }

        public AccountChangeNameDTO build() {
            return new AccountChangeNameDTO(this);
        }
    }

    public static abstract class AccountChangeNameDTOBuilder<C extends AccountChangeNameDTO, B extends AccountChangeNameDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B name(String name) {
            this.name = name;
            return self();
        }

        private String name;

        protected abstract B self();

        public abstract C build();

    }

    public AccountChangeNameDTO(String name) {
        /* 17 */
        this.name = name;
        
    }

    
    public AccountChangeNameDTO() {
    }

    
    
    public String getName() {
        /* 22 */
        return this.name;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountChangeNameDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */