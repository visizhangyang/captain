
package com.porn.client.nickname.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class NickNameQueryDTO extends BaseDTO {

    @ApiModelProperty("模糊查询")
     private String lkNickName;

    @ApiModelProperty("昵称类型, NickNameTypeEnum, 0-机器人, 1-手工导入")
     private Integer nickNameType;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
     private Integer status;


    /* 15 */
    public void setLkNickName(String lkNickName) {
        this.lkNickName = lkNickName;
    }

    public void setNickNameType(Integer nickNameType) {
        this.nickNameType = nickNameType;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NickNameQueryDTO;
    }



    /* 16 */
    protected NickNameQueryDTO(NickNameQueryDTOBuilder<?, ?> b) {
        super(b);
        this.lkNickName = b.lkNickName;
        this.nickNameType = b.nickNameType;
        this.status = b.status;
    }

    public static NickNameQueryDTOBuilder<?, ?> builder() {
        return new NickNameQueryDTOBuilderImpl();
    }

    private static final class NickNameQueryDTOBuilderImpl extends NickNameQueryDTOBuilder<NickNameQueryDTO, NickNameQueryDTOBuilderImpl> {
        private NickNameQueryDTOBuilderImpl() {
        }

        protected NickNameQueryDTOBuilderImpl self() {
            return this;
        }

        public NickNameQueryDTO build() {
            return new NickNameQueryDTO(this);
        }
    }

    public static abstract class NickNameQueryDTOBuilder<C extends NickNameQueryDTO, B extends NickNameQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String lkNickName;

        public B lkNickName(String lkNickName) {
            this.lkNickName = lkNickName;
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

    public NickNameQueryDTO(String lkNickName, Integer nickNameType, Integer status) {
        /* 17 */
        this.lkNickName = lkNickName;
        this.nickNameType = nickNameType;
        this.status = status;

    }


    public NickNameQueryDTO() {
    }



    public String getLkNickName() {
        /* 22 */
        return this.lkNickName;

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


