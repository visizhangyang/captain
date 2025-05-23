package com.porn.client.recharge.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class RechargeCacheItemDTO
        implements Serializable {

    @ApiModelProperty(value = "编码", hidden = true)
    private String code;

    @ApiModelProperty("表达式")
    private String expr;

    public RechargeCacheItemDTO() {
    }

    public RechargeCacheItemDTO(String code, String expr) {

        this.code = code;
        this.expr = expr;

    }

    public static RechargeCacheItemDTOBuilder builder() {
        return new RechargeCacheItemDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RechargeCacheItemDTO;
    }

    public String getCode() {

        return this.code;

    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getExpr() {

        return this.expr;

    }

    public void setExpr(String expr) {
        this.expr = expr;
    }

    public static class RechargeCacheItemDTOBuilder {
        private String code;
        private String expr;

        public RechargeCacheItemDTOBuilder code(String code) {
            this.code = code;
            return this;
        }

        public RechargeCacheItemDTOBuilder expr(String expr) {
            this.expr = expr;
            return this;
        }

        public RechargeCacheItemDTO build() {
            return new RechargeCacheItemDTO(this.code, this.expr);
        }

    }

}

