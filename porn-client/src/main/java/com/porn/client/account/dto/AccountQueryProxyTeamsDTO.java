
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;






 public class AccountQueryProxyTeamsDTO
         implements Serializable
         {
    
    @ApiModelProperty("管理系统用户ID")
     private Long mngUserId;

    
    
    public void setMngUserId(Long mngUserId) {
        /* 15 */
        this.mngUserId = mngUserId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountQueryProxyTeamsDTO;
    }



    /* 16 */
    public static AccountQueryProxyTeamsDTOBuilder builder() {
        return new AccountQueryProxyTeamsDTOBuilder();
    }

    public static class AccountQueryProxyTeamsDTOBuilder {
        public AccountQueryProxyTeamsDTOBuilder mngUserId(Long mngUserId) {
            this.mngUserId = mngUserId;
            return this;
        }

        private Long mngUserId;

        public AccountQueryProxyTeamsDTO build() {
            return new AccountQueryProxyTeamsDTO(this.mngUserId);
        }

    }

    public AccountQueryProxyTeamsDTO(Long mngUserId) {
        /* 17 */
        this.mngUserId = mngUserId;
        
    }

    
    public AccountQueryProxyTeamsDTO() {
    }

    
    
    public Long getMngUserId() {
        /* 22 */
        return this.mngUserId;
        
    }
    
}


