
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

 public class UserSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("用户名")
     private String name;

    @ApiModelProperty("昵称")
     private String nickName;

    @ApiModelProperty("个性签名")
     private String sign;

    @ApiModelProperty("头像")
     private String avatar;

    @ApiModelProperty("状态")
     private Integer status;


    /* 15 */
    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserSaveOrUpdateDTO;
    }



    /* 16 */
    protected UserSaveOrUpdateDTO(UserSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.nickName = b.nickName;
        this.sign = b.sign;
        this.avatar = b.avatar;
        this.status = b.status;
    }

    public static UserSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new UserSaveOrUpdateDTOBuilderImpl();
    }

    private static final class UserSaveOrUpdateDTOBuilderImpl extends UserSaveOrUpdateDTOBuilder<UserSaveOrUpdateDTO, UserSaveOrUpdateDTOBuilderImpl> {
        private UserSaveOrUpdateDTOBuilderImpl() {
        }

        protected UserSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public UserSaveOrUpdateDTO build() {
            return new UserSaveOrUpdateDTO(this);
        }
    }

    public static abstract class UserSaveOrUpdateDTOBuilder<C extends UserSaveOrUpdateDTO, B extends UserSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;
        private String nickName;

        public B name(String name) {
            this.name = name;
            return self();
        }

        private String sign;
        private String avatar;
        private Integer status;

        public B nickName(String nickName) {
            this.nickName = nickName;
            return self();
        }

        public B sign(String sign) {
            this.sign = sign;
            return self();
        }

        public B avatar(String avatar) {
            this.avatar = avatar;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public UserSaveOrUpdateDTO(String name, String nickName, String sign, String avatar, Integer status) {
        /* 17 */
        this.name = name;
        this.nickName = nickName;
        this.sign = sign;
        this.avatar = avatar;
        this.status = status;

    }


    public UserSaveOrUpdateDTO() {
    }



    public String getName() {
        /* 22 */
        return this.name;

    }


    public String getNickName() {
        /* 25 */
        return this.nickName;

    }


    public String getSign() {
        /* 28 */
        return this.sign;

    }


    public String getAvatar() {
        /* 31 */
        return this.avatar;

    }


    public Integer getStatus() {
        /* 34 */
        return this.status;

    }

}


