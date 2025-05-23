package com.porn.client.account.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class AccountQueryProxyTeamsDTO
        implements Serializable {

    @ApiModelProperty("管理系统用户ID")
    private Long mngUserId;

    public AccountQueryProxyTeamsDTO(Long mngUserId) {

        this.mngUserId = mngUserId;

    }

    public AccountQueryProxyTeamsDTO() {
    }

    public static AccountQueryProxyTeamsDTOBuilder builder() {
        return new AccountQueryProxyTeamsDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountQueryProxyTeamsDTO;
    }

    public Long getMngUserId() {

        return this.mngUserId;

    }

    public void setMngUserId(Long mngUserId) {

        this.mngUserId = mngUserId;
    }

    public static class AccountQueryProxyTeamsDTOBuilder {
        private Long mngUserId;

        public AccountQueryProxyTeamsDTOBuilder mngUserId(Long mngUserId) {
            this.mngUserId = mngUserId;
            return this;
        }

        public AccountQueryProxyTeamsDTO build() {
            return new AccountQueryProxyTeamsDTO(this.mngUserId);
        }

    }

}

