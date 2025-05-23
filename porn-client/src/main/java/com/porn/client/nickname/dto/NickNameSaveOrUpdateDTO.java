package com.porn.client.nickname.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class NickNameSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("昵称类型, NickNameTypeEnum, 0-机器人, 1-手工导入")
    private Integer nickNameType;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer status;

    protected NickNameSaveOrUpdateDTO(NickNameSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.nickName = b.nickName;
        this.nickNameType = b.nickNameType;
        this.status = b.status;
    }

    public NickNameSaveOrUpdateDTO(String nickName, Integer nickNameType, Integer status) {

        this.nickName = nickName;
        this.nickNameType = nickNameType;
        this.status = status;

    }

    public NickNameSaveOrUpdateDTO() {
    }

    public static NickNameSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new NickNameSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NickNameSaveOrUpdateDTO;
    }

    public String getNickName() {

        return this.nickName;

    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    private static final class NickNameSaveOrUpdateDTOBuilderImpl extends NickNameSaveOrUpdateDTOBuilder<NickNameSaveOrUpdateDTO, NickNameSaveOrUpdateDTOBuilderImpl> {
        private NickNameSaveOrUpdateDTOBuilderImpl() {
        }

        protected NickNameSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public NickNameSaveOrUpdateDTO build() {
            return new NickNameSaveOrUpdateDTO(this);
        }
    }

    public static abstract class NickNameSaveOrUpdateDTOBuilder<C extends NickNameSaveOrUpdateDTO, B extends NickNameSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String nickName;
        private Integer nickNameType;
        private Integer status;

        public B nickName(String nickName) {
            this.nickName = nickName;
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

