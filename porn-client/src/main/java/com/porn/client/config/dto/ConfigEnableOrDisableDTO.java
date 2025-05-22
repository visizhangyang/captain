
package com.porn.client.config.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class ConfigEnableOrDisableDTO
         extends BaseDTO
         {
    
    @ApiModelProperty("状态, 1-启用, 0-禁用")
     private Integer status;

    
    
    public void setStatus(Integer status) {
        /* 15 */
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ConfigEnableOrDisableDTO;
    }



    /* 16 */
    protected ConfigEnableOrDisableDTO(ConfigEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.status = b.status;
    }

    public static ConfigEnableOrDisableDTOBuilder<?, ?> builder() {
        return new ConfigEnableOrDisableDTOBuilderImpl();
    }

    private static final class ConfigEnableOrDisableDTOBuilderImpl extends ConfigEnableOrDisableDTOBuilder<ConfigEnableOrDisableDTO, ConfigEnableOrDisableDTOBuilderImpl> {
        private ConfigEnableOrDisableDTOBuilderImpl() {
        }

        protected ConfigEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public ConfigEnableOrDisableDTO build() {
            return new ConfigEnableOrDisableDTO(this);
        }
    }

    public static abstract class ConfigEnableOrDisableDTOBuilder<C extends ConfigEnableOrDisableDTO, B extends ConfigEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B status(Integer status) {
            this.status = status;
            return self();
        }

        private Integer status;

        protected abstract B self();

        public abstract C build();

    }

    public ConfigEnableOrDisableDTO(Integer status) {
        /* 17 */
        this.status = status;
        
    }

    
    public ConfigEnableOrDisableDTO() {
    }

    
    
    public Integer getStatus() {
        /* 22 */
        return this.status;
        
    }
    
}


