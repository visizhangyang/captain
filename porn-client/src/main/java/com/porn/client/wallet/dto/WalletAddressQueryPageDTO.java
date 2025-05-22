
package com.porn.client.wallet.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BasePageDTO;


public class WalletAddressQueryPageDTO extends BasePageDTO {
    @ApiModelProperty("名称")
     private String name;
    @ApiModelProperty("名称模糊")
     private String lkName;
    @ApiModelProperty("编码")
     private String code;
    @ApiModelProperty("编码模糊")
     private String lkCode;
    
    @ApiModelProperty("地址")
     private String lkAddress;
    
    @ApiModelProperty("地址状态")
     private Integer addressStatus;
    
    @ApiModelProperty("状态")
     private Integer status;
    
    @ApiModelProperty("备注")
     private String lkRemark;

    
    /* 15 */
    public void setName(String name) {
        this.name = name;
    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLkCode(String lkCode) {
        this.lkCode = lkCode;
    }

    public void setLkAddress(String lkAddress) {
        this.lkAddress = lkAddress;
    }

    public void setAddressStatus(Integer addressStatus) {
        this.addressStatus = addressStatus;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setLkRemark(String lkRemark) {
        this.lkRemark = lkRemark;
    }


    protected boolean canEqual(Object other) {
        return other instanceof WalletAddressQueryPageDTO;
    }



    /* 16 */
    protected WalletAddressQueryPageDTO(WalletAddressQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.lkName = b.lkName;
        this.code = b.code;
        this.lkCode = b.lkCode;
        this.lkAddress = b.lkAddress;
        this.addressStatus = b.addressStatus;
        this.status = b.status;
        this.lkRemark = b.lkRemark;
    }

    public static WalletAddressQueryPageDTOBuilder<?, ?> builder() {
        return new WalletAddressQueryPageDTOBuilderImpl();
    }

    private static final class WalletAddressQueryPageDTOBuilderImpl extends WalletAddressQueryPageDTOBuilder<WalletAddressQueryPageDTO, WalletAddressQueryPageDTOBuilderImpl> {
        private WalletAddressQueryPageDTOBuilderImpl() {
        }

        protected WalletAddressQueryPageDTOBuilderImpl self() {
            return this;
        }

        public WalletAddressQueryPageDTO build() {
            return new WalletAddressQueryPageDTO(this);
        }
    }

    public static abstract class WalletAddressQueryPageDTOBuilder<C extends WalletAddressQueryPageDTO, B extends WalletAddressQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String name;
        private String lkName;
        private String code;
        private String lkCode;

        public B name(String name) {
            this.name = name;
            return self();
        }

        private String lkAddress;
        private Integer addressStatus;
        private Integer status;
        private String lkRemark;

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        public B code(String code) {
            this.code = code;
            return self();
        }

        public B lkCode(String lkCode) {
            this.lkCode = lkCode;
            return self();
        }

        public B lkAddress(String lkAddress) {
            this.lkAddress = lkAddress;
            return self();
        }

        public B addressStatus(Integer addressStatus) {
            this.addressStatus = addressStatus;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B lkRemark(String lkRemark) {
            this.lkRemark = lkRemark;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public WalletAddressQueryPageDTO(String name, String lkName, String code, String lkCode, String lkAddress, Integer addressStatus, Integer status, String lkRemark) {
        /* 17 */
        this.name = name;
        this.lkName = lkName;
        this.code = code;
        this.lkCode = lkCode;
        this.lkAddress = lkAddress;
        this.addressStatus = addressStatus;
        this.status = status;
        this.lkRemark = lkRemark;
        
    }

    
    public WalletAddressQueryPageDTO() {
    }

    
    
    public String getName() {
        /* 22 */
        return this.name;
        
    }

    
    public String getLkName() {
        /* 25 */
        return this.lkName;
        
    }

    
    public String getCode() {
        /* 28 */
        return this.code;
        
    }

    
    public String getLkCode() {
        /* 31 */
        return this.lkCode;
        
    }

    
    public String getLkAddress() {
        /* 34 */
        return this.lkAddress;
        
    }

    
    public Integer getAddressStatus() {
        /* 37 */
        return this.addressStatus;
        
    }

    
    public Integer getStatus() {
        /* 40 */
        return this.status;
        
    }

    
    public String getLkRemark() {
        /* 43 */
        return this.lkRemark;
        
    }
}


