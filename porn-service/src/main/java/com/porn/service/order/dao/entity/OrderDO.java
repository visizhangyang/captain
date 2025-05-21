
package com.porn.service.order.dao.entity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@TableName("porn_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class OrderDO extends BaseDO {
    @TableField("order_no")
     private String orderNo;
    @TableField("account_id")
     private Long accountId;
    
    @TableField("account_name")
     private String accountName;
    
    @TableField("merchant_id")
     private Long merchantId;
    
    @TableField("merchant_name")
     private String merchantName;
    
    @TableField("order_amount")
     private BigDecimal orderAmount;
    
    @TableField("order_rate")
     private BigDecimal orderRate;

    @TableField("free_amount")
    private BigDecimal freeAmount;
    @TableField("order_status")
    private Integer orderStatus;
    @TableField("play_status")
    private Integer playStatus;
    @TableField("order_type")
    private Integer orderType;
    @TableField("remark")
    private String remark;
    @TableField("address")
    private String address;
    @TableField("wallet_code")
    private String walletCode;
    @TableField("wallet_name")
    private String walletName;

}
