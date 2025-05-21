
package com.porn.service.dingdingmsg.dto;



import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;




 public class ProxyMsgDTO
         implements Serializable
         {

    @ApiModelProperty("工作的账户ID")
     private Long accountId;

    @ApiModelProperty("推送的消息")
     private String msg;



    public void setAccountId(Long accountId) {
        /* 15 */
        this.accountId = accountId;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    /* 16 */
    public static ProxyMsgDTOBuilder builder() {
        return new ProxyMsgDTOBuilder();
    }

    public static class ProxyMsgDTOBuilder {
        private Long accountId;

        public ProxyMsgDTOBuilder accountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        private String msg;

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

    public ProxyMsgDTO() {
    }

    public ProxyMsgDTO(Long accountId, String msg) {
        /* 18 */
        this.accountId = accountId;
        this.msg = msg;

    }



    public Long getAccountId() {
        /* 22 */
        return this.accountId;

    }


    public String getMsg() {
        /* 25 */
        return this.msg;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/dingdingmsg/dto/ProxyMsgDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */