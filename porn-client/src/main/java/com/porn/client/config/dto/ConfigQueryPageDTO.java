
package com.porn.client.config.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BasePageDTO;


import java.util.List;

public class ConfigQueryPageDTO extends BasePageDTO {
    @ApiModelProperty("配置编码")
    private String configCode;

    @ApiModelProperty("配置编码(模糊)")
    private String lkConfigCode;

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

    @ApiModelProperty("账户ID列表")
    private List<Long> accountIdList;

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public void setLkConfigCode(String lkConfigCode) {
        this.lkConfigCode = lkConfigCode;
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

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ConfigQueryPageDTO;
    }



    /* 17 */
    protected ConfigQueryPageDTO(ConfigQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.configCode = b.configCode;
        this.lkConfigCode = b.lkConfigCode;
        this.configGroup = b.configGroup;
        this.configValue = b.configValue;
        this.configDesc = b.configDesc;
        this.status = b.status;
        this.sortNo = b.sortNo;
        this.accountId = b.accountId;
        this.accountIdList = b.accountIdList;
    }

    public static ConfigQueryPageDTOBuilder<?, ?> builder() {
        return new ConfigQueryPageDTOBuilderImpl();
    }

    private static final class ConfigQueryPageDTOBuilderImpl extends ConfigQueryPageDTOBuilder<ConfigQueryPageDTO, ConfigQueryPageDTOBuilderImpl> {
        private ConfigQueryPageDTOBuilderImpl() {
        }

        protected ConfigQueryPageDTOBuilderImpl self() {
            return this;
        }

        public ConfigQueryPageDTO build() {
            return new ConfigQueryPageDTO(this);
        }
    }

    public static abstract class ConfigQueryPageDTOBuilder<C extends ConfigQueryPageDTO, B extends ConfigQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String configCode;
        private String lkConfigCode;
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
        private List<Long> accountIdList;

        public B lkConfigCode(String lkConfigCode) {
            this.lkConfigCode = lkConfigCode;
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

        public B accountIdList(List<Long> accountIdList) {
            this.accountIdList = accountIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();


    }

    public ConfigQueryPageDTO() {
    }

    public ConfigQueryPageDTO(String configCode, String lkConfigCode, String configGroup, String configValue, String configDesc, Integer status, Integer sortNo, Long accountId, List<Long> accountIdList) {
        /* 19 */
        this.configCode = configCode;
        this.lkConfigCode = lkConfigCode;
        this.configGroup = configGroup;
        this.configValue = configValue;
        this.configDesc = configDesc;
        this.status = status;
        this.sortNo = sortNo;
        this.accountId = accountId;
        this.accountIdList = accountIdList;

    }



    public String getConfigCode() {
        /* 23 */
        return this.configCode;

    }


    public String getLkConfigCode() {
        /* 26 */
        return this.lkConfigCode;

    }


    public String getConfigGroup() {
        /* 29 */
        return this.configGroup;

    }


    public String getConfigValue() {
        /* 32 */
        return this.configValue;

    }


    public String getConfigDesc() {
        /* 35 */
        return this.configDesc;

    }


    public Integer getStatus() {
        /* 38 */
        return this.status;

    }


    public Integer getSortNo() {
        /* 41 */
        return this.sortNo;

    }


    public Long getAccountId() {
        /* 44 */
        return this.accountId;

    }


    public List<Long> getAccountIdList() {
        /* 47 */
        return this.accountIdList;

    }
}


