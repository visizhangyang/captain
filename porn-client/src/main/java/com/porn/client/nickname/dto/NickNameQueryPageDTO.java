package com.porn.client.nickname.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

public class NickNameQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("模糊查询")
    private String lkNickName;

    @ApiModelProperty("昵称类型, NickNameTypeEnum, 0-机器人, 1-手工导入")
    private Integer nickNameType;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer status;

    protected NickNameQueryPageDTO(NickNameQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkNickName = b.lkNickName;
        this.nickNameType = b.nickNameType;
        this.status = b.status;
    }

    public NickNameQueryPageDTO() {
    }

    public NickNameQueryPageDTO(String lkNickName, Integer nickNameType, Integer status) {

        this.lkNickName = lkNickName;
        this.nickNameType = nickNameType;
        this.status = status;

    }

    public static NickNameQueryPageDTOBuilder<?, ?> builder() {
        return new NickNameQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NickNameQueryPageDTO;
    }

    public String getLkNickName() {

        return this.lkNickName;

    }

    public void setLkNickName(String lkNickName) {
        this.lkNickName = lkNickName;
    }

    public Integer getNickNameType() {

        return this.nickNameType;

    }

    public void setNickNameType(Integer nickNameType) {
        this.nickNameType = nickNameType;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private static final class NickNameQueryPageDTOBuilderImpl extends NickNameQueryPageDTOBuilder<NickNameQueryPageDTO, NickNameQueryPageDTOBuilderImpl> {
        private NickNameQueryPageDTOBuilderImpl() {
        }

        protected NickNameQueryPageDTOBuilderImpl self() {
            return this;
        }

        public NickNameQueryPageDTO build() {
            return new NickNameQueryPageDTO(this);
        }
    }

    public static abstract class NickNameQueryPageDTOBuilder<C extends NickNameQueryPageDTO, B extends NickNameQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String lkNickName;
        private Integer nickNameType;
        private Integer status;

        public B lkNickName(String lkNickName) {
            this.lkNickName = lkNickName;
            return self();
        }

        public B nickNameType(Integer nickNameType) {
            this.nickNameType = nickNameType;
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

