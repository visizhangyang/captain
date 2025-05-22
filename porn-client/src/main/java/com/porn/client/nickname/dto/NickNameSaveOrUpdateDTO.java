
package com.porn.client.nickname.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class NickNameSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("昵称")
     private String nickName;

    @ApiModelProperty("昵称类型, NickNameTypeEnum, 0-机器人, 1-手工导入")
     private Integer nickNameType;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
     private Integer status;


    /* 15 */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setNickNameType(Integer nickNameType) {
        this.nickNameType = nickNameType;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NickNameSaveOrUpdateDTO;
    }



    /* 16 */
    protected NickNameSaveOrUpdateDTO(NickNameSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.nickName = b.nickName;
        this.nickNameType = b.nickNameType;
        this.status = b.status;
    }

    public static NickNameSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new NickNameSaveOrUpdateDTOBuilderImpl();
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

        public B nickName(String nickName) {
            this.nickName = nickName;
            return self();
        }

        private Integer nickNameType;
        private Integer status;

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

    public NickNameSaveOrUpdateDTO(String nickName, Integer nickNameType, Integer status) {
        /* 17 */
        this.nickName = nickName;
        this.nickNameType = nickNameType;
        this.status = status;

    }


    public NickNameSaveOrUpdateDTO() {
    }



    public String getNickName() {
        /* 22 */
        return this.nickName;

    }


    public Integer getNickNameType() {
        /* 25 */
        return this.nickNameType;

    }


    public Integer getStatus() {
        /* 28 */
        return this.status;

    }

}


