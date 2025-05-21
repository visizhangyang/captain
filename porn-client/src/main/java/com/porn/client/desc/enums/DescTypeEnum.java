
package com.porn.client.desc.enums;






 public enum DescTypeEnum
         {
    /* 15 */   RECHARGE(Integer.valueOf(0), "充值"),
    
    /* 17 */   WITHDRAW(Integer.valueOf(1), "提现"),
    
    /* 19 */   TRANSFER(Integer.valueOf(2), "转账"),
    
    /* 21 */   TREASURE(Integer.valueOf(3), "余U宝"),
    
    /* 23 */   RECOMMEND(Integer.valueOf(4), "买币推荐"),
    
    /* 25 */   DATA(Integer.valueOf(5), " 数据"),
    
    /* 27 */   ACCOUNTINFO(Integer.valueOf(6), " 个人信息"),
    
    /* 29 */   INDEX(Integer.valueOf(7), " 首页"),
    
    /* 31 */   DOUBLEFOCUS(Integer.valueOf(8), "重点关注"),
    
    /* 33 */   MARKETNITICE(Integer.valueOf(9), "市场公告"),
    
    /* 35 */   LOTTERY(Integer.valueOf(10), "抽奖公告"),
    
    /* 37 */   PLAN(Integer.valueOf(11), "计划说明"),
    
    /* 39 */   SHARE(Integer.valueOf(12), "推广说明"),
    
    /* 41 */   WEBSITE(Integer.valueOf(13), "官网"),
    
    /* 43 */   CUSTOMSERVICE(Integer.valueOf(14), "客服");
       private Integer type;

    
       private String description;

       DescTypeEnum(Integer type, String description) {
        
        this.type = type;
        
        this.description = description;
        
    }
    

    
    
    public Integer getType() {
        /* 48 */
        return this.type;
        
    }

    
    public String getDescription() {
        /* 51 */
        return this.description;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/desc/enums/DescTypeEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */