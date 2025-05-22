
package com.porn.client.config.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class ConfigDeleteDTO
         extends BaseDTO {
    
    @ApiModelProperty("账户ID")
     private Long accountId;

    
    
    public void setAccountId(Long accountId) {
        /* 14 */
        this.accountId = accountId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ConfigDeleteDTO;
    }



    /* 15 */
    protected ConfigDeleteDTO(ConfigDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
    }

    public static ConfigDeleteDTOBuilder<?, ?> builder() {
        return new ConfigDeleteDTOBuilderImpl();
    }

    private static final class ConfigDeleteDTOBuilderImpl extends ConfigDeleteDTOBuilder<ConfigDeleteDTO, ConfigDeleteDTOBuilderImpl> {
        private ConfigDeleteDTOBuilderImpl() {
        }

        protected ConfigDeleteDTOBuilderImpl self() {
            return this;
        }

        public ConfigDeleteDTO build() {
            return new ConfigDeleteDTO(this);
        }
    }

    public static abstract class ConfigDeleteDTOBuilder<C extends ConfigDeleteDTO, B extends ConfigDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private Long accountId;

        protected abstract B self();

        public abstract C build();

        
    }

    
    public ConfigDeleteDTO() {
    }

    
    
    public Long getAccountId() {
        /* 20 */
        return this.accountId;
        
    }
    
}


