package com.porn.client.user.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

public class UserOnlineQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("模糊匹配姓名")
    private String lkName;

    @ApiModelProperty("模糊匹配昵称")
    private String lkNickName;

    @ApiModelProperty("用户名称")
    private String name;

    protected UserOnlineQueryPageDTO(UserOnlineQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkName = b.lkName;
        this.lkNickName = b.lkNickName;
        this.name = b.name;
    }

    public UserOnlineQueryPageDTO(String lkName, String lkNickName, String name) {

        this.lkName = lkName;
        this.lkNickName = lkNickName;
        this.name = name;

    }

    public UserOnlineQueryPageDTO() {
    }

    public static UserOnlineQueryPageDTOBuilder<?, ?> builder() {
        return new UserOnlineQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserOnlineQueryPageDTO;
    }

    public String getLkName() {

        return this.lkName;

    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public String getLkNickName() {

        return this.lkNickName;

    }

    public void setLkNickName(String lkNickName) {
        this.lkNickName = lkNickName;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    private static final class UserOnlineQueryPageDTOBuilderImpl extends UserOnlineQueryPageDTOBuilder<UserOnlineQueryPageDTO, UserOnlineQueryPageDTOBuilderImpl> {
        private UserOnlineQueryPageDTOBuilderImpl() {
        }

        protected UserOnlineQueryPageDTOBuilderImpl self() {
            return this;
        }

        public UserOnlineQueryPageDTO build() {
            return new UserOnlineQueryPageDTO(this);
        }
    }

    public static abstract class UserOnlineQueryPageDTOBuilder<C extends UserOnlineQueryPageDTO, B extends UserOnlineQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String lkName;
        private String lkNickName;
        private String name;

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        public B lkNickName(String lkNickName) {
            this.lkNickName = lkNickName;
            return self();
        }

        public B name(String name) {
            this.name = name;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

