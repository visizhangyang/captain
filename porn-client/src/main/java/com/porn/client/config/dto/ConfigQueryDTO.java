
package com.porn.client.config.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

import java.util.List;

 public class ConfigQueryDTO extends BaseDTO {

    @ApiModelProperty("配置编码")
     private String configCode;

    @ApiModelProperty("配置编码列表")
     private List<String> configCodeList;

    @ApiModelProperty("配置组")
     private String configGroup;

    @ApiModelProperty("配置组列表")
     private List<String> configGroupList;

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("状态")
     private Integer status;


    /* 16 */
    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public void setConfigCodeList(List<String> configCodeList) {
        this.configCodeList = configCodeList;
    }

    public void setConfigGroup(String configGroup) {
        this.configGroup = configGroup;
    }

    public void setConfigGroupList(List<String> configGroupList) {
        this.configGroupList = configGroupList;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ConfigQueryDTO;
    }



    /* 17 */
    protected ConfigQueryDTO(ConfigQueryDTOBuilder<?, ?> b) {
        super(b);
        this.configCode = b.configCode;
        this.configCodeList = b.configCodeList;
        this.configGroup = b.configGroup;
        this.configGroupList = b.configGroupList;
        this.accountId = b.accountId;
        this.status = b.status;
    }

    public static ConfigQueryDTOBuilder<?, ?> builder() {
        return new ConfigQueryDTOBuilderImpl();
    }

    private static final class ConfigQueryDTOBuilderImpl extends ConfigQueryDTOBuilder<ConfigQueryDTO, ConfigQueryDTOBuilderImpl> {
        private ConfigQueryDTOBuilderImpl() {
        }

        protected ConfigQueryDTOBuilderImpl self() {
            return this;
        }

        public ConfigQueryDTO build() {
            return new ConfigQueryDTO(this);
        }
    }

    public static abstract class ConfigQueryDTOBuilder<C extends ConfigQueryDTO, B extends ConfigQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String configCode;
        private List<String> configCodeList;
        private String configGroup;

        public B configCode(String configCode) {
            this.configCode = configCode;
            return self();
        }

        private List<String> configGroupList;
        private Long accountId;
        private Integer status;

        public B configCodeList(List<String> configCodeList) {
            this.configCodeList = configCodeList;
            return self();
        }

        public B configGroup(String configGroup) {
            this.configGroup = configGroup;
            return self();
        }

        public B configGroupList(List<String> configGroupList) {
            this.configGroupList = configGroupList;
            return self();
        }

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public ConfigQueryDTO(String configCode, List<String> configCodeList, String configGroup, List<String> configGroupList, Long accountId, Integer status) {
        /* 18 */
        this.configCode = configCode;
        this.configCodeList = configCodeList;
        this.configGroup = configGroup;
        this.configGroupList = configGroupList;
        this.accountId = accountId;
        this.status = status;

    }


    public ConfigQueryDTO() {
    }



    public String getConfigCode() {
        /* 23 */
        return this.configCode;

    }


    public List<String> getConfigCodeList() {
        /* 26 */
        return this.configCodeList;

    }


    public String getConfigGroup() {
        /* 29 */
        return this.configGroup;

    }


    public List<String> getConfigGroupList() {
        /* 32 */
        return this.configGroupList;

    }


    public Long getAccountId() {
        /* 35 */
        return this.accountId;

    }


    public Integer getStatus() {
        /* 38 */
        return this.status;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/config/dto/ConfigQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */