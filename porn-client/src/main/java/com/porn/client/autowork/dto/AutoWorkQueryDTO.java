
package com.porn.client.autowork.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class AutoWorkQueryDTO
         extends BaseDTO
         {

    @ApiModelProperty("账户ID")
     private Long accountId;



    public void setAccountId(Long accountId) {
        /* 15 */
        this.accountId = accountId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AutoWorkQueryDTO;
    }



    /* 16 */
    protected AutoWorkQueryDTO(AutoWorkQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
    }

    public static AutoWorkQueryDTOBuilder<?, ?> builder() {
        return new AutoWorkQueryDTOBuilderImpl();
    }

    private static final class AutoWorkQueryDTOBuilderImpl extends AutoWorkQueryDTOBuilder<AutoWorkQueryDTO, AutoWorkQueryDTOBuilderImpl> {
        private AutoWorkQueryDTOBuilderImpl() {
        }

        protected AutoWorkQueryDTOBuilderImpl self() {
            return this;
        }

        public AutoWorkQueryDTO build() {
            return new AutoWorkQueryDTO(this);
        }
    }

    public static abstract class AutoWorkQueryDTOBuilder<C extends AutoWorkQueryDTO, B extends AutoWorkQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private Long accountId;

        protected abstract B self();

        public abstract C build();

    }

    public AutoWorkQueryDTO(Long accountId) {
        /* 17 */
        this.accountId = accountId;

    }


    public AutoWorkQueryDTO() {
    }



    public Long getAccountId() {
        /* 22 */
        return this.accountId;

    }

}


