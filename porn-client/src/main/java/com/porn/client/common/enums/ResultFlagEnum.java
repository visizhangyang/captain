
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


