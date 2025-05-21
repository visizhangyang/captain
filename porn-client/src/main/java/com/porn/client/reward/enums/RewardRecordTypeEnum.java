
package com.porn.client.reward.enums;






 public enum RewardRecordTypeEnum
         {
    /* 15 */   ADD(Integer.valueOf(1), "增加数量"),

    /* 17 */   SUB(Integer.valueOf(-1), "减少数量");
       private Integer type;


       private String description;

       RewardRecordTypeEnum(Integer type, String description) {

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

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/enums/RewardRecordTypeEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */