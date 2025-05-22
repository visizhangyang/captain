
package com.porn.client.common.enums;






 public enum LangTypeEnum
         {
    /* 15 */   ZH(Integer.valueOf(0), "中文"),

    /* 17 */   EN(Integer.valueOf(1), "英语");
       private Integer type;


       private String description;

       LangTypeEnum(Integer type, String description) {

        this.type = type;

        this.description = description;

    }




    public Integer getType() {
        /* 22 */
        return this.type;

    }


    public String getDescription() {
        /* 25 */
        return this.description;

    }








    public static LangTypeEnum queryByTag(String tag) {
        /* 34 */
        if (tag == null) {
            /* 35 */
            return null;

        }
        /* 37 */
        for (LangTypeEnum langTypeEnum : values()) {
            /* 38 */
            if (langTypeEnum.name().toLowerCase().equals(tag.toLowerCase())) {
                /* 39 */
                return langTypeEnum;

            }

        }
        /* 42 */
        return null;

    }








    public static LangTypeEnum queryByType(Integer type) {
        /* 51 */
        if (type == null) {
            /* 52 */
            return null;

        }
        /* 54 */
        for (LangTypeEnum langTypeEnum : values()) {
            /* 55 */
            if (langTypeEnum.type.equals(type)) {
                /* 56 */
                return langTypeEnum;

            }

        }
        /* 59 */
        return null;

    }

}


