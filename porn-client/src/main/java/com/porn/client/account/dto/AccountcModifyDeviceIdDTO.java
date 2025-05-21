
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class AccountcModifyDeviceIdDTO
         extends BaseDTO
         {

    @ApiModelProperty("设备id")
     private String deviceId;



    public void setDeviceId(String deviceId) {
        /* 15 */
        this.deviceId = deviceId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountcModifyDeviceIdDTO;
    }



    /* 16 */
    protected AccountcModifyDeviceIdDTO(AccountcModifyDeviceIdDTOBuilder<?, ?> b) {
        super(b);
        this.deviceId = b.deviceId;
    }

    public static AccountcModifyDeviceIdDTOBuilder<?, ?> builder() {
        return new AccountcModifyDeviceIdDTOBuilderImpl();
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
        public B deviceId(String deviceId) {
            this.deviceId = deviceId;
            return self();
        }

        private String deviceId;

        protected abstract B self();

        public abstract C build();


    }

    public AccountcModifyDeviceIdDTO() {
    }

    public AccountcModifyDeviceIdDTO(String deviceId) {
        /* 18 */
        this.deviceId = deviceId;

    }



    public String getDeviceId() {
        /* 22 */
        return this.deviceId;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountcModifyDeviceIdDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */