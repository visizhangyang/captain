
package com.porn.client.merchant.enums;






 public enum MemberLevelEnum
         {
    /* 15 */   BRONZE(Integer.valueOf(0), "青铜VIP"),

    /* 17 */   SILVER(Integer.valueOf(1), "白银VIP"),

    /* 19 */   GOLD(Integer.valueOf(2), "黄金VIP"),

    /* 21 */   DIAMOND(Integer.valueOf(3), "钻石VIP");
       private Integer level;


       private String description;

       MemberLevelEnum(Integer level, String description) {

        this.level = level;

        this.description = description;

    }




    public Integer getLevel() {
        /* 26 */
        return this.level;

    }


    public String getDescription() {
        /* 29 */
        return this.description;

    }







    public static String queryByLevel(Integer level) {
        /* 37 */
        for (MemberLevelEnum memberLevelEnum : values()) {
            /* 38 */
            if (memberLevelEnum.getLevel().equals(level)) {
                /* 39 */
                return memberLevelEnum.getDescription();

            }

        }
        /* 42 */
        return null;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/merchant/enums/MemberLevelEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */