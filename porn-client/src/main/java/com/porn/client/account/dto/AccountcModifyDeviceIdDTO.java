package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountcModifyDeviceIdDTO
        extends BaseDTO {

    @ApiModelProperty("设备id")
    private String deviceId;

    protected AccountcModifyDeviceIdDTO(AccountcModifyDeviceIdDTOBuilder<?, ?> b) {
        super(b);
        this.deviceId = b.deviceId;
    }

    public AccountcModifyDeviceIdDTO() {
    }

    public AccountcModifyDeviceIdDTO(String deviceId) {

        this.deviceId = deviceId;

    }

    public static AccountcModifyDeviceIdDTOBuilder<?, ?> builder() {
        return new AccountcModifyDeviceIdDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountcModifyDeviceIdDTO;
    }

    public String getDeviceId() {

        return this.deviceId;

    }

    public void setDeviceId(String deviceId) {

        this.deviceId = deviceId;
    }

    private static final class AccountcModifyDeviceIdDTOBuilderImpl extends AccountcModifyDeviceIdDTOBuilder<AccountcModifyDeviceIdDTO, AccountcModifyDeviceIdDTOBuilderImpl> {
        private AccountcModifyDeviceIdDTOBuilderImpl() {
        }

        protected AccountcModifyDeviceIdDTOBuilderImpl self() {
            return this;
        }

        public AccountcModifyDeviceIdDTO build() {
            return new AccountcModifyDeviceIdDTO(this);
        }
    }

    public static abstract class AccountcModifyDeviceIdDTOBuilder<C extends AccountcModifyDeviceIdDTO, B extends AccountcModifyDeviceIdDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String deviceId;

        public B deviceId(String deviceId) {
            this.deviceId = deviceId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

