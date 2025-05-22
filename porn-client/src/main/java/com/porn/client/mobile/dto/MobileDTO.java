
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;




 public class MobileDTO implements Serializable {

    @ApiModelProperty("类型, EncryptTypeEnum")
     private Integer type;

    @ApiModelProperty("请求方法体")
     private String body;

    @ApiModelProperty("扩展数据")
     private MobileExtDTO mobileExtDTO;



    public void setType(Integer type) {
        /* 15 */
        this.type = type;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setMobileExtDTO(MobileExtDTO mobileExtDTO) {
        this.mobileExtDTO = mobileExtDTO;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MobileDTO;
    }



    /* 16 */
    public static MobileDTOBuilder builder() {
        return new MobileDTOBuilder();
    }

    public static class MobileDTOBuilder {
        private Integer type;

        public MobileDTOBuilder type(Integer type) {
            this.type = type;
            return this;
        }

        private String body;
        private MobileExtDTO mobileExtDTO;

        public MobileDTOBuilder body(String body) {
            this.body = body;
            return this;
        }

        public MobileDTOBuilder mobileExtDTO(MobileExtDTO mobileExtDTO) {
            this.mobileExtDTO = mobileExtDTO;
            return this;
        }

        public MobileDTO build() {
            return new MobileDTO(this.type, this.body, this.mobileExtDTO);
        }

    }

    public MobileDTO(Integer type, String body, MobileExtDTO mobileExtDTO) {
        /* 17 */
        this.type = type;
        this.body = body;
        this.mobileExtDTO = mobileExtDTO;

    }


    public MobileDTO() {
    }



    public Integer getType() {
        /* 22 */
        return this.type;

    }


    public String getBody() {
        /* 25 */
        return this.body;

    }


    public MobileExtDTO getMobileExtDTO() {
        /* 28 */
        return this.mobileExtDTO;

    }

}


