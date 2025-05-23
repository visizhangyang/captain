package com.porn.client.config.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

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

    protected ConfigQueryDTO(ConfigQueryDTOBuilder<?, ?> b) {
        super(b);
        this.configCode = b.configCode;
        this.configCodeList = b.configCodeList;
        this.configGroup = b.configGroup;
        this.configGroupList = b.configGroupList;
        this.accountId = b.accountId;
        this.status = b.status;
    }

    public ConfigQueryDTO(String configCode, List<String> configCodeList, String configGroup, List<String> configGroupList, Long accountId, Integer status) {

        this.configCode = configCode;
        this.configCodeList = configCodeList;
        this.configGroup = configGroup;
        this.configGroupList = configGroupList;
        this.accountId = accountId;
        this.status = status;

    }

    public ConfigQueryDTO() {
    }

    public static ConfigQueryDTOBuilder<?, ?> builder() {
        return new ConfigQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ConfigQueryDTO;
    }

    public String getConfigCode() {

        return this.configCode;

    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public List<String> getConfigCodeList() {

        return this.configCodeList;

    }

    public void setConfigCodeList(List<String> configCodeList) {
        this.configCodeList = configCodeList;
    }

    public String getConfigGroup() {

        return this.configGroup;

    }

    public void setConfigGroup(String configGroup) {
        this.configGroup = configGroup;
    }

    public List<String> getConfigGroupList() {

        return this.configGroupList;

    }

    public void setConfigGroupList(List<String> configGroupList) {
        this.configGroupList = configGroupList;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
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
        private List<String> configGroupList;
        private Long accountId;
        private Integer status;

        public B configCode(String configCode) {
            this.configCode = configCode;
            return self();
        }

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

}

