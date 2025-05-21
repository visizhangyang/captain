
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BasePageDTO;





 public class UserOnlineQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("模糊匹配姓名")
     private String lkName;

    @ApiModelProperty("模糊匹配昵称")
     private String lkNickName;

    @ApiModelProperty("用户名称")
     private String name;


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


    protected boolean canEqual(Object other) {
        return other instanceof UserOnlineQueryPageDTO;
    }



    /* 16 */
    protected UserOnlineQueryPageDTO(UserOnlineQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkName = b.lkName;
        this.lkNickName = b.lkNickName;
        this.name = b.name;
    }

    public static UserOnlineQueryPageDTOBuilder<?, ?> builder() {
        return new UserOnlineQueryPageDTOBuilderImpl();
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

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        private String lkNickName;
        private String name;

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

    public UserOnlineQueryPageDTO(String lkName, String lkNickName, String name) {
        /* 17 */
        this.lkName = lkName;
        this.lkNickName = lkNickName;
        this.name = name;

    }


    public UserOnlineQueryPageDTO() {
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

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/user/dto/UserOnlineQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */