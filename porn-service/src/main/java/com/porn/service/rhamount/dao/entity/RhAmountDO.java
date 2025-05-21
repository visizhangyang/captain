
package com.porn.service.rhamount.dao.entity;
import java.time.LocalDateTime;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;







@TableName("porn_rhamount")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class RhAmountDO
         extends BaseDO {

    @TableField("_amount")
     private BigDecimal amount;

    @TableField("sort_no")
     private Integer sortNo;



}

