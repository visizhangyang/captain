
package com.porn.client.config.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

public class ConfigSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("配置编码")
     private String configCode;
    @ApiModelProperty("配置组")
     private String configGroup;

    @ApiModelProperty("配置值")
     private String configValue;

    @ApiModelProperty("配置描述")
     private String configDesc;

    @ApiModelProperty("是否启用, EnableStatusEnum")
     private Integer status;

    @ApiModelProperty("排序值")
     private Integer sortNo;

    @ApiModelProperty("账户ID")
     private Long accountId;


    /* 15 */
    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public void setConfigGroup(String configGroup) {
        this.configGroup = configGroup;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ConfigSaveOrUpdateDTO;
    }



    /* 16 */
    protected ConfigSaveOrUpdateDTO(ConfigSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.configCode = b.configCode;
        this.configGroup = b.configGroup;
        this.configValue = b.configValue;
        this.configDesc = b.configDesc;
        this.status = b.status;
        this.sortNo = b.sortNo;
        this.accountId = b.accountId;
    }

    public static ConfigSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new ConfigSaveOrUpdateDTOBuilderImpl();
    }

    private static final class ConfigSaveOrUpdateDTOBuilderImpl extends ConfigSaveOrUpdateDTOBuilder<ConfigSaveOrUpdateDTO, ConfigSaveOrUpdateDTOBuilderImpl> {
        private ConfigSaveOrUpdateDTOBuilderImpl() {
        }

        protected ConfigSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public ConfigSaveOrUpdateDTO build() {
            return new ConfigSaveOrUpdateDTO(this);
        }
    }

    public static abstract class ConfigSaveOrUpdateDTOBuilder<C extends ConfigSaveOrUpdateDTO, B extends ConfigSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String configCode;
        private String configGroup;
        private String configValue;

        public B configCode(String configCode) {
            this.configCode = configCode;
            return self();
        }

        private String configDesc;
        private Integer status;
        private Integer sortNo;
        private Long accountId;

        public B configGroup(String configGroup) {
            this.configGroup = configGroup;
            return self();
        }

        public B configValue(String configValue) {
            this.configValue = configValue;
            return self();
        }

        public B configDesc(String configDesc) {
            this.configDesc = configDesc;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B sortNo(Integer sortNo) {
            this.sortNo = sortNo;
            return self();
        }

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public ConfigSaveOrUpdateDTO(String configCode, String configGroup, String configValue, String configDesc, Integer status, Integer sortNo, Long accountId) {
        /* 17 */
        this.configCode = configCode;
        this.configGroup = configGroup;
        this.configValue = configValue;
        this.configDesc = configDesc;
        this.status = status;
        this.sortNo = sortNo;
        this.accountId = accountId;

    }


    public ConfigSaveOrUpdateDTO() {
    }



    public String getConfigCode() {
        /* 22 */
        return this.configCode;

    }


    public String getConfigGroup() {
        /* 25 */
        return this.configGroup;

    }


    public String getConfigValue() {
        /* 28 */
        return this.configValue;

    }


    public String getConfigDesc() {
        /* 31 */
        return this.configDesc;

    }


    public Integer getStatus() {
        /* 34 */
        return this.status;

    }


    public Integer getSortNo() {
        /* 37 */
        return this.sortNo;

    }


    public Long getAccountId() {
        /* 40 */
        return this.accountId;

    }
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/config/dto/ConfigSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */