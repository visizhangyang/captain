package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

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

    protected UserSaveOrUpdateDTO(UserSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.nickName = b.nickName;
        this.sign = b.sign;
        this.avatar = b.avatar;
        this.status = b.status;
    }

    public UserSaveOrUpdateDTO(String name, String nickName, String sign, String avatar, Integer status) {

        this.name = name;
        this.nickName = nickName;
        this.sign = sign;
        this.avatar = avatar;
        this.status = status;

    }

    public UserSaveOrUpdateDTO() {
    }

    public static UserSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new UserSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserSaveOrUpdateDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {

        return this.nickName;

    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSign() {

        return this.sign;

    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAvatar() {

        return this.avatar;

    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
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
        private String sign;
        private String avatar;
        private Integer status;

        public B name(String name) {
            this.name = name;
            return self();
        }

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

}

