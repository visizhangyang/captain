package com.porn.client.wallet.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class WalletAddressSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("编码")
    private String code;
    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("锁定时间")
    private LocalDateTime lockTime;

    @ApiModelProperty("清除锁定时间")
    private boolean clearLockTime;

    @ApiModelProperty("地址状态")
    private Integer addressStatus;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("IP")
    private String remoteIP;

    protected WalletAddressSaveOrUpdateDTO(WalletAddressSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.code = b.code;
        this.address = b.address;
        this.lockTime = b.lockTime;
        this.clearLockTime = b.clearLockTime;
        this.addressStatus = b.addressStatus;
        this.status = b.status;
        this.password = b.password;
        this.remoteIP = b.remoteIP;
    }

    public WalletAddressSaveOrUpdateDTO(String code, String address, LocalDateTime lockTime, boolean clearLockTime, Integer addressStatus, Integer status, String password, String remoteIP) {

        this.code = code;
        this.address = address;
        this.lockTime = lockTime;
        this.clearLockTime = clearLockTime;
        this.addressStatus = addressStatus;
        this.status = status;
        this.password = password;
        this.remoteIP = remoteIP;

    }

    public WalletAddressSaveOrUpdateDTO() {
    }

    public static WalletAddressSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new WalletAddressSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WalletAddressSaveOrUpdateDTO;
    }

    public String getCode() {

        return this.code;

    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {

        return this.address;

    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getLockTime() {

        return this.lockTime;

    }

    public void setLockTime(LocalDateTime lockTime) {
        this.lockTime = lockTime;
    }

    public boolean isClearLockTime() {

        return this.clearLockTime;

    }

    public void setClearLockTime(boolean clearLockTime) {
        this.clearLockTime = clearLockTime;
    }

    public Integer getAddressStatus() {

        return this.addressStatus;

    }

    public void setAddressStatus(Integer addressStatus) {
        this.addressStatus = addressStatus;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPassword() {

        return this.password;

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemoteIP() {

        return this.remoteIP;

    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    private static final class WalletAddressSaveOrUpdateDTOBuilderImpl extends WalletAddressSaveOrUpdateDTOBuilder<WalletAddressSaveOrUpdateDTO, WalletAddressSaveOrUpdateDTOBuilderImpl> {
        private WalletAddressSaveOrUpdateDTOBuilderImpl() {
        }

        protected WalletAddressSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public WalletAddressSaveOrUpdateDTO build() {
            return new WalletAddressSaveOrUpdateDTO(this);
        }
    }

    public static abstract class WalletAddressSaveOrUpdateDTOBuilder<C extends WalletAddressSaveOrUpdateDTO, B extends WalletAddressSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String code;
        private String address;
        private LocalDateTime lockTime;
        private boolean clearLockTime;
        private Integer addressStatus;
        private Integer status;
        private String password;
        private String remoteIP;

        public B code(String code) {
            this.code = code;
            return self();
        }

        public B address(String address) {
            this.address = address;
            return self();
        }

        public B lockTime(LocalDateTime lockTime) {
            this.lockTime = lockTime;
            return self();
        }

        public B clearLockTime(boolean clearLockTime) {
            this.clearLockTime = clearLockTime;
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

        public B password(String password) {
            this.password = password;
            return self();
        }

        public B remoteIP(String remoteIP) {
            this.remoteIP = remoteIP;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

