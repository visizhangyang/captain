package com.porn.client.user.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

public class UserQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("模糊匹配姓名")
    private String lkName;

    @ApiModelProperty("模糊匹配昵称")
    private String lkNickName;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("状态")
    private Integer status;

    protected UserQueryPageDTO(UserQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkName = b.lkName;
        this.lkNickName = b.lkNickName;
        this.name = b.name;
        this.status = b.status;
    }

    public UserQueryPageDTO(String lkName, String lkNickName, String name, Integer status) {

        this.lkName = lkName;
        this.lkNickName = lkNickName;
        this.name = name;
        this.status = status;

    }

    public UserQueryPageDTO() {
    }

    public static UserQueryPageDTOBuilder<?, ?> builder() {
        return new UserQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserQueryPageDTO;
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

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private static final class UserQueryPageDTOBuilderImpl extends UserQueryPageDTOBuilder<UserQueryPageDTO, UserQueryPageDTOBuilderImpl> {
        private UserQueryPageDTOBuilderImpl() {
        }

        protected UserQueryPageDTOBuilderImpl self() {
            return this;
        }

        public UserQueryPageDTO build() {
            return new UserQueryPageDTO(this);
        }
    }

    public static abstract class UserQueryPageDTOBuilder<C extends UserQueryPageDTO, B extends UserQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String lkName;
        private String lkNickName;
        private String name;
        private Integer status;

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

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

