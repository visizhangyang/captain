
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class AccountDeleteDTO
         extends BaseDTO
         {

    @ApiModelProperty("id列表")
     private List<Long> idList;



    public void setIdList(List<Long> idList) {
        /* 16 */
        this.idList = idList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountDeleteDTO;
    }



    /* 17 */
    protected AccountDeleteDTO(AccountDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
    }

    public static AccountDeleteDTOBuilder<?, ?> builder() {
        return new AccountDeleteDTOBuilderImpl();
    }

    private static final class AccountDeleteDTOBuilderImpl extends AccountDeleteDTOBuilder<AccountDeleteDTO, AccountDeleteDTOBuilderImpl> {
        private AccountDeleteDTOBuilderImpl() {
        }

        protected AccountDeleteDTOBuilderImpl self() {
            return this;
        }

        public AccountDeleteDTO build() {
            return new AccountDeleteDTO(this);
        }
    }

    public static abstract class AccountDeleteDTOBuilder<C extends AccountDeleteDTO, B extends AccountDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        private List<Long> idList;

        protected abstract B self();

        public abstract C build();


    }

    public AccountDeleteDTO() {
    }

    public AccountDeleteDTO(List<Long> idList) {
        /* 19 */
        this.idList = idList;

    }



    public List<Long> getIdList() {
        /* 23 */
        return this.idList;

    }

}


