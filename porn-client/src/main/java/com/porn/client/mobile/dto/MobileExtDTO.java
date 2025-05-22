
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;




 public class MobileExtDTO
         implements Serializable
         {

    @ApiModelProperty("登录IP")
     private String remoteIP;

    @ApiModelProperty("用户请求头")
     private String userAgent;



    public void setRemoteIP(String remoteIP) {
        /* 15 */
        this.remoteIP = remoteIP;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MobileExtDTO;
    }



    /* 16 */
    public static MobileExtDTOBuilder builder() {
        return new MobileExtDTOBuilder();
    }

    public static class MobileExtDTOBuilder {
        private String remoteIP;

        public MobileExtDTOBuilder remoteIP(String remoteIP) {
            this.remoteIP = remoteIP;
            return this;
        }

        private String userAgent;

        public MobileExtDTOBuilder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public MobileExtDTO build() {
            return new MobileExtDTO(this.remoteIP, this.userAgent);
        }

    }

    public MobileExtDTO(String remoteIP, String userAgent) {
        /* 17 */
        this.remoteIP = remoteIP;
        this.userAgent = userAgent;

    }


    public MobileExtDTO() {
    }



    public String getRemoteIP() {
        /* 22 */
        return this.remoteIP;

    }


    public String getUserAgent() {
        /* 25 */
        return this.userAgent;

    }

}


