package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class AccountExtConfigDTO
        implements Serializable {

    @ApiModelProperty("扩展类型, AccountExtTypeEnum")
    private List<Integer> extTypeList;

    @ApiModelProperty("扩展key列表")
    private List<String> extKeyList;

    public AccountExtConfigDTO(List<Integer> extTypeList, List<String> extKeyList) {

        this.extTypeList = extTypeList;
        this.extKeyList = extKeyList;

    }

    public AccountExtConfigDTO() {
    }

    public static AccountExtConfigDTOBuilder builder() {
        return new AccountExtConfigDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountExtConfigDTO;
    }

    public List<Integer> getExtTypeList() {

        return this.extTypeList;

    }

    public void setExtTypeList(List<Integer> extTypeList) {

        this.extTypeList = extTypeList;
    }

    public List<String> getExtKeyList() {

        return this.extKeyList;

    }

    public void setExtKeyList(List<String> extKeyList) {
        this.extKeyList = extKeyList;
    }

    public static class AccountExtConfigDTOBuilder {
        private List<Integer> extTypeList;
        private List<String> extKeyList;

        public AccountExtConfigDTOBuilder extTypeList(List<Integer> extTypeList) {
            this.extTypeList = extTypeList;
            return this;
        }

        public AccountExtConfigDTOBuilder extKeyList(List<String> extKeyList) {
            this.extKeyList = extKeyList;
            return this;
        }

        public AccountExtConfigDTO build() {
            return new AccountExtConfigDTO(this.extTypeList, this.extKeyList);
        }

    }

}

