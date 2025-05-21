
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BasePageDTO;

 public class UserQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("模糊匹配姓名")
     private String lkName;

    @ApiModelProperty("模糊匹配昵称")
     private String lkNickName;

    @ApiModelProperty("用户名称")
     private String name;

    @ApiModelProperty("状态")
     private Integer status;


    /* 15 */
    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public void setLkNickName(String lkNickName) {
        this.lkNickName = lkNickName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserQueryPageDTO;
    }



    /* 16 */
    protected UserQueryPageDTO(UserQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkName = b.lkName;
        this.lkNickName = b.lkNickName;
        this.name = b.name;
        this.status = b.status;
    }

    public static UserQueryPageDTOBuilder<?, ?> builder() {
        return new UserQueryPageDTOBuilderImpl();
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

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        private String name;
        private Integer status;

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

    public UserQueryPageDTO(String lkName, String lkNickName, String name, Integer status) {
        /* 17 */
        this.lkName = lkName;
        this.lkNickName = lkNickName;
        this.name = name;
        this.status = status;

    }


    public UserQueryPageDTO() {
    }



    public String getLkName() {
        /* 22 */
        return this.lkName;

    }


    public String getLkNickName() {
        /* 25 */
        return this.lkNickName;

    }


    public String getName() {
        /* 28 */
        return this.name;

    }


    public Integer getStatus() {
        /* 31 */
        return this.status;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/user/dto/UserQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */