
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class AccountChangeParentDTO
         extends BaseDTO
         {

    @ApiModelProperty("父节点ID")
     private Long parentId;



    public void setParentId(Long parentId) {
        /* 15 */
        this.parentId = parentId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountChangeParentDTO;
    }



    /* 16 */
    protected AccountChangeParentDTO(AccountChangeParentDTOBuilder<?, ?> b) {
        super(b);
        this.parentId = b.parentId;
    }

    public static AccountChangeParentDTOBuilder<?, ?> builder() {
        return new AccountChangeParentDTOBuilderImpl();
    }

    private static final class AccountChangeParentDTOBuilderImpl extends AccountChangeParentDTOBuilder<AccountChangeParentDTO, AccountChangeParentDTOBuilderImpl> {
        private AccountChangeParentDTOBuilderImpl() {
        }

        protected AccountChangeParentDTOBuilderImpl self() {
            return this;
        }

        public AccountChangeParentDTO build() {
            return new AccountChangeParentDTO(this);
        }
    }

    public static abstract class AccountChangeParentDTOBuilder<C extends AccountChangeParentDTO, B extends AccountChangeParentDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B parentId(Long parentId) {
            this.parentId = parentId;
            return self();
        }

        private Long parentId;

        protected abstract B self();

        public abstract C build();

    }

    public AccountChangeParentDTO(Long parentId) {
        /* 17 */
        this.parentId = parentId;

    }


    public AccountChangeParentDTO() {
    }



    public Long getParentId() {
        /* 22 */
        return this.parentId;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountChangeParentDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */