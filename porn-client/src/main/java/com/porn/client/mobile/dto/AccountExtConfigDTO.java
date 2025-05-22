
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;
import java.util.List;





 public class AccountExtConfigDTO
         implements Serializable
         {
    
    @ApiModelProperty("扩展类型, AccountExtTypeEnum")
     private List<Integer> extTypeList;
    
    @ApiModelProperty("扩展key列表")
     private List<String> extKeyList;

    
    
    public void setExtTypeList(List<Integer> extTypeList) {
        /* 16 */
        this.extTypeList = extTypeList;
    }

    public void setExtKeyList(List<String> extKeyList) {
        this.extKeyList = extKeyList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountExtConfigDTO;
    }



    /* 17 */
    public static AccountExtConfigDTOBuilder builder() {
        return new AccountExtConfigDTOBuilder();
    }

    public static class AccountExtConfigDTOBuilder {
        private List<Integer> extTypeList;

        public AccountExtConfigDTOBuilder extTypeList(List<Integer> extTypeList) {
            this.extTypeList = extTypeList;
            return this;
        }

        private List<String> extKeyList;

        public AccountExtConfigDTOBuilder extKeyList(List<String> extKeyList) {
            this.extKeyList = extKeyList;
            return this;
        }

        public AccountExtConfigDTO build() {
            return new AccountExtConfigDTO(this.extTypeList, this.extKeyList);
        }

    }

    public AccountExtConfigDTO(List<Integer> extTypeList, List<String> extKeyList) {
        /* 18 */
        this.extTypeList = extTypeList;
        this.extKeyList = extKeyList;
        
    }

    
    public AccountExtConfigDTO() {
    }

    
    
    public List<Integer> getExtTypeList() {
        /* 23 */
        return this.extTypeList;
        
    }

    
    public List<String> getExtKeyList() {
        /* 26 */
        return this.extKeyList;
        
    }
    
}


