package com.porn.service.dingdingmsg.dto;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ProxyMsgDTO
        implements Serializable {

    @ApiModelProperty("工作的账户ID")
    private Long accountId;

    @ApiModelProperty("推送的消息")
    private String msg;


    public ProxyMsgDTO() {
    }

    public ProxyMsgDTO(Long accountId, String msg) {

        this.accountId = accountId;
        this.msg = msg;

    }

    public static ProxyMsgDTOBuilder builder() {
        return new ProxyMsgDTOBuilder();
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ProxyMsgDTO)) return false;
        ProxyMsgDTO other = (ProxyMsgDTO) o;
        if (!other.canEqual(this)) return false;
        Object this$accountId = getAccountId(), other$accountId = other.getAccountId();
        if ((this$accountId == null) ? (other$accountId != null) : !this$accountId.equals(other$accountId))
            return false;
        Object this$msg = getMsg(), other$msg = other.getMsg();
        return !((this$msg == null) ? (other$msg != null) : !this$msg.equals(other$msg));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProxyMsgDTO;
    }

    public String toString() {
        return "ProxyMsgDTO(accountId=" + getAccountId() + ", msg=" + getMsg() + ")";
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {

        this.accountId = accountId;
    }

    public String getMsg() {

        return this.msg;

    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ProxyMsgDTOBuilder {
        private Long accountId;
        private String msg;

        public ProxyMsgDTOBuilder accountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        public ProxyMsgDTOBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ProxyMsgDTO build() {
            return new ProxyMsgDTO(this.accountId, this.msg);
        }

        public String toString() {
            return "ProxyMsgDTO.ProxyMsgDTOBuilder(accountId=" + this.accountId + ", msg=" + this.msg + ")";
        }

    }

}

