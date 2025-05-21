
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class UserQueryDTO
         extends BaseDTO
         {
    
    @ApiModelProperty("账户")
     private String name;

    
    
    public void setName(String name) {
        /* 15 */
        this.name = name;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserQueryDTO;
    }



    /* 16 */
    protected UserQueryDTO(UserQueryDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
    }

    public static UserQueryDTOBuilder<?, ?> builder() {
        return new UserQueryDTOBuilderImpl();
    }

    private static final class UserQueryDTOBuilderImpl extends UserQueryDTOBuilder<UserQueryDTO, UserQueryDTOBuilderImpl> {
        private UserQueryDTOBuilderImpl() {
        }

        protected UserQueryDTOBuilderImpl self() {
            return this;
        }

        public UserQueryDTO build() {
            return new UserQueryDTO(this);
        }
    }

    public static abstract class UserQueryDTOBuilder<C extends UserQueryDTO, B extends UserQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B name(String name) {
            this.name = name;
            return self();
        }

        private String name;

        protected abstract B self();

        public abstract C build();

    }

    public UserQueryDTO(String name) {
        /* 17 */
        this.name = name;
        
    }

    
    public UserQueryDTO() {
    }

    
    
    public String getName() {
        /* 22 */
        return this.name;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/user/dto/UserQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */