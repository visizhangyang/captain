
package com.porn.client.nickname.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BasePageDTO;





 public class NickNameQueryPageDTO extends BasePageDTO {

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
        return other instanceof NickNameQueryPageDTO;
    }



    /* 16 */
    protected NickNameQueryPageDTO(NickNameQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkNickName = b.lkNickName;
        this.nickNameType = b.nickNameType;
        this.status = b.status;
    }

    public static NickNameQueryPageDTOBuilder<?, ?> builder() {
        return new NickNameQueryPageDTOBuilderImpl();
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

    public NickNameQueryPageDTO() {
    }

    public NickNameQueryPageDTO(String lkNickName, Integer nickNameType, Integer status) {
        /* 18 */
        this.lkNickName = lkNickName;
        this.nickNameType = nickNameType;
        this.status = status;

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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/nickname/dto/NickNameQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */