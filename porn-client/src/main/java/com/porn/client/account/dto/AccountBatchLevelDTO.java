
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class AccountBatchLevelDTO extends BaseDTO {

    @ApiModelProperty("账户ID列表")
     private List<Long> idList;

    @ApiModelProperty("账户级别")
     private Integer accountLevel;



    public void setIdList(List<Long> idList) {
        /* 16 */
        this.idList = idList;
    }

    public void setAccountLevel(Integer accountLevel) {
        this.accountLevel = accountLevel;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountBatchLevelDTO;
    }



    /* 17 */
    protected AccountBatchLevelDTO(AccountBatchLevelDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
        this.accountLevel = b.accountLevel;
    }

    public static AccountBatchLevelDTOBuilder<?, ?> builder() {
        return new AccountBatchLevelDTOBuilderImpl();
    }

    private static final class AccountBatchLevelDTOBuilderImpl extends AccountBatchLevelDTOBuilder<AccountBatchLevelDTO, AccountBatchLevelDTOBuilderImpl> {
        private AccountBatchLevelDTOBuilderImpl() {
        }

        protected AccountBatchLevelDTOBuilderImpl self() {
            return this;
        }

        public AccountBatchLevelDTO build() {
            return new AccountBatchLevelDTO(this);
        }
    }

    public static abstract class AccountBatchLevelDTOBuilder<C extends AccountBatchLevelDTO, B extends AccountBatchLevelDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> idList;

        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        private Integer accountLevel;

        public B accountLevel(Integer accountLevel) {
            this.accountLevel = accountLevel;
            return self();
        }

        protected abstract B self();

        public abstract C build();


    }

    public AccountBatchLevelDTO() {
    }

    public AccountBatchLevelDTO(List<Long> idList, Integer accountLevel) {
        /* 19 */
        this.idList = idList;
        this.accountLevel = accountLevel;

    }



    public List<Long> getIdList() {
        /* 23 */
        return this.idList;

    }


    public Integer getAccountLevel() {
        /* 26 */
        return this.accountLevel;

    }

}


