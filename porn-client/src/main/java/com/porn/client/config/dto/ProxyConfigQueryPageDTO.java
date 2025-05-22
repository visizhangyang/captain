
package com.porn.client.config.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BasePageDTO;

import java.util.List;

public class ProxyConfigQueryPageDTO extends BasePageDTO {
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

    @ApiModelProperty("账户ID列表")
     private List<Long> accountIdList;


    /* 17 */
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

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ProxyConfigQueryPageDTO;
    }



    /* 18 */
    protected ProxyConfigQueryPageDTO(ProxyConfigQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.configCode = b.configCode;
        this.configGroup = b.configGroup;
        this.configValue = b.configValue;
        this.configDesc = b.configDesc;
        this.status = b.status;
        this.sortNo = b.sortNo;
        this.accountId = b.accountId;
        this.accountIdList = b.accountIdList;
    }

    public static ProxyConfigQueryPageDTOBuilder<?, ?> builder() {
        return new ProxyConfigQueryPageDTOBuilderImpl();
    }

    private static final class ProxyConfigQueryPageDTOBuilderImpl extends ProxyConfigQueryPageDTOBuilder<ProxyConfigQueryPageDTO, ProxyConfigQueryPageDTOBuilderImpl> {
        private ProxyConfigQueryPageDTOBuilderImpl() {
        }

        protected ProxyConfigQueryPageDTOBuilderImpl self() {
            return this;
        }

        public ProxyConfigQueryPageDTO build() {
            return new ProxyConfigQueryPageDTO(this);
        }
    }

    public static abstract class ProxyConfigQueryPageDTOBuilder<C extends ProxyConfigQueryPageDTO, B extends ProxyConfigQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String configCode;
        private String configGroup;
        private String configValue;
        private String configDesc;

        public B configCode(String configCode) {
            this.configCode = configCode;
            return self();
        }

        private Integer status;
        private Integer sortNo;
        private Long accountId;
        private List<Long> accountIdList;

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

    public ProxyConfigQueryPageDTO() {
    }

    public ProxyConfigQueryPageDTO(String configCode, String configGroup, String configValue, String configDesc, Integer status, Integer sortNo, Long accountId, List<Long> accountIdList) {
        /* 20 */
        this.configCode = configCode;
        this.configGroup = configGroup;
        this.configValue = configValue;
        this.configDesc = configDesc;
        this.status = status;
        this.sortNo = sortNo;
        this.accountId = accountId;
        this.accountIdList = accountIdList;

    }



    public String getConfigCode() {
        /* 24 */
        return this.configCode;

    }


    public String getConfigGroup() {
        /* 27 */
        return this.configGroup;

    }


    public String getConfigValue() {
        /* 30 */
        return this.configValue;

    }


    public String getConfigDesc() {
        /* 33 */
        return this.configDesc;

    }


    public Integer getStatus() {
        /* 36 */
        return this.status;

    }


    public Integer getSortNo() {
        /* 39 */
        return this.sortNo;

    }


    public Long getAccountId() {
        /* 42 */
        return this.accountId;

    }


    public List<Long> getAccountIdList() {
        /* 45 */
        return this.accountIdList;

    }
}


