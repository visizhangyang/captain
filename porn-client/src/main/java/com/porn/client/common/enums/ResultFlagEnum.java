
package com.porn.client.common.enums;


 public enum ResultFlagEnum {
    /* 10 */   CODE_SUCCESS(Integer.valueOf(200), "操作成功"),
    /* 11 */   CODE_FAIL(Integer.valueOf(400), "操作失败"),

    /* 13 */   CODE_AUTHFAIL(Integer.valueOf(500), "鉴权失败");
       private Integer flag;


       private String description;

       ResultFlagEnum(Integer flag, String description) {

        this.flag = flag;

        this.description = description;

    }




    public Integer getFlag() {
        /* 18 */
        return this.flag;

    }


    public String getDescription() {
        /* 21 */
        return this.description;

    }







    public static ResultFlagEnum findByFlag(Integer flag) {
        /* 29 */
        for (ResultFlagEnum delFlagEnum : values()) {
            /* 30 */
            if (delFlagEnum.getFlag().equals(flag)) {
                /* 31 */
                return delFlagEnum;

            }

        }
        /* 34 */
        return null;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/common/enums/ResultFlagEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */