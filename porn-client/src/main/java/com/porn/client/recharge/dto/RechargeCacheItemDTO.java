
package com.porn.client.recharge.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;





 public class RechargeCacheItemDTO
         implements Serializable
         {

    @ApiModelProperty(value = "编码", hidden = true)
     private String code;

    @ApiModelProperty("表达式")
     private String expr;



    public void setCode(String code) {
        /* 16 */
        this.code = code;
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RechargeCacheItemDTO;
    }



    /* 17 */
    public static RechargeCacheItemDTOBuilder builder() {
        return new RechargeCacheItemDTOBuilder();
    }

    public static class RechargeCacheItemDTOBuilder {
        private String code;

        public RechargeCacheItemDTOBuilder code(String code) {
            this.code = code;
            return this;
        }

        private String expr;

        public RechargeCacheItemDTOBuilder expr(String expr) {
            this.expr = expr;
            return this;
        }

        public RechargeCacheItemDTO build() {
            return new RechargeCacheItemDTO(this.code, this.expr);
        }


    }

    public RechargeCacheItemDTO() {
    }

    public RechargeCacheItemDTO(String code, String expr) {
        /* 19 */
        this.code = code;
        this.expr = expr;

    }



    public String getCode() {
        /* 23 */
        return this.code;

    }


    public String getExpr() {
        /* 26 */
        return this.expr;

    }

}


