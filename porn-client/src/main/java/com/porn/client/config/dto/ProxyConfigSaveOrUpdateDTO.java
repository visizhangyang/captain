package com.porn.client.config.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class ProxyConfigSaveOrUpdateDTO extends BaseDTO {
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

    protected ProxyConfigSaveOrUpdateDTO(ProxyConfigSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.configCode = b.configCode;
        this.configGroup = b.configGroup;
        this.configValue = b.configValue;
        this.configDesc = b.configDesc;
        this.status = b.status;
        this.sortNo = b.sortNo;
        this.accountId = b.accountId;
    }

    public ProxyConfigSaveOrUpdateDTO(String configCode, String configGroup, String configValue, String configDesc, Integer status, Integer sortNo, Long accountId) {

        this.configCode = configCode;
        this.configGroup = configGroup;
        this.configValue = configValue;
        this.configDesc = configDesc;
        this.status = status;
        this.sortNo = sortNo;
        this.accountId = accountId;

    }

    public ProxyConfigSaveOrUpdateDTO() {
    }

    public static ProxyConfigSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new ProxyConfigSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProxyConfigSaveOrUpdateDTO;
    }

    public String getConfigCode() {

        return this.configCode;

    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public String getConfigGroup() {

        return this.configGroup;

    }

    public void setConfigGroup(String configGroup) {
        this.configGroup = configGroup;
    }

    public String getConfigValue() {

        return this.configValue;

    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigDesc() {

        return this.configDesc;

    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortNo() {

        return this.sortNo;

    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    private static final class ProxyConfigSaveOrUpdateDTOBuilderImpl extends ProxyConfigSaveOrUpdateDTOBuilder<ProxyConfigSaveOrUpdateDTO, ProxyConfigSaveOrUpdateDTOBuilderImpl> {
        private ProxyConfigSaveOrUpdateDTOBuilderImpl() {
        }

        protected ProxyConfigSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public ProxyConfigSaveOrUpdateDTO build() {
            return new ProxyConfigSaveOrUpdateDTO(this);
        }
    }

    public static abstract class ProxyConfigSaveOrUpdateDTOBuilder<C extends ProxyConfigSaveOrUpdateDTO, B extends ProxyConfigSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String configCode;
        private String configGroup;
        private String configValue;
        private String configDesc;
        private Integer status;
        private Integer sortNo;
        private Long accountId;

        public B configCode(String configCode) {
            this.configCode = configCode;
            return self();
        }

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
}

